package model;

import customExceptions.*;

public class Course {
	private double maxGrade;
	private double minGrade;
	private int currentWeek;
	private int totalGradesAmount;
	private int maxQuota;

	private Student[] studentsEnrolled;

	public Course(int tga, double mx, double mn, int mq) {
		currentWeek = 1;
		maxGrade = mx;
		minGrade = mn;
		totalGradesAmount = tga;
		maxQuota = mq;
		studentsEnrolled = new Student[mq];
	}

	public void enroll(String id) throws QuotaEnrollExceedException, WeekExceededEnrollException, AlreadyEnrolledException {
		if (currentWeek > 2) {
			throw new WeekExceededEnrollException(currentWeek);
		}
		if (searchStudent(id) != -1) {
			throw new AlreadyEnrolledException(id);
		}
		int posNewStudent = searchFirstAvailable();
		if (posNewStudent == -1) {
			throw new QuotaEnrollExceedException(maxQuota);
		} else {
			studentsEnrolled[posNewStudent] = new Student(id, totalGradesAmount);
		}
	}

	public void cancelEnrollment(String id) throws CancelationNotAllowedException, StudentNotEnrolledException {
		if (currentWeek > (totalGradesAmount / 2)) {
			throw new CancelationNotAllowedException(currentWeek, totalGradesAmount);
		}
		int posStudent = searchStudent(id);
		if (posStudent == -1) {
			throw new StudentNotEnrolledException(id);
		}
		studentsEnrolled[posStudent] = null;
	}

	public void setStudentGrade(String id, int gradeNumber, double grade)
			throws ArrayIndexOutOfBoundsException, OutOfRangeGradeException {
		if (grade < minGrade || grade > maxGrade) {
			throw new OutOfRangeGradeException(grade, maxGrade, minGrade);
		}
		int posStudent = searchStudent(id);
		studentsEnrolled[posStudent].setGrade(gradeNumber, grade);
	}

	public void advanceWeek() {
		currentWeek++;
	}

	private int searchFirstAvailable() {
		for (int i = 0; i < studentsEnrolled.length; i++) {
			if (studentsEnrolled[i] == null) return i;
		}
		return -1;
	}

	private int searchStudent(String id) {
		for (int i = 0; i < studentsEnrolled.length; i++) {
			if (studentsEnrolled[i] != null && studentsEnrolled[i].getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	public String showEnrolledStudents() {
		StringBuilder msg = new StringBuilder();
		for (Student s : studentsEnrolled) {
			if (s != null) msg.append(s.getId()).append("\n");
		}
		return msg.toString();
	}

	public String showStudentGrades(String id) {
		int pos = searchStudent(id);
		return pos != -1 ? studentsEnrolled[pos].showGrades() : "Error";
	}
}
	
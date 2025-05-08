package model;

public class Student {
	private String id;
	private double[] grades;

	public Student(String identifier, int totalGrades) {
		id = identifier;
		grades = new double[totalGrades];
	}

	public void setGrade(int gradeNumber, double grade) throws ArrayIndexOutOfBoundsException {
		grades[gradeNumber - 1] = grade;
	}

	public double getGrade(int gradeNumber) throws ArrayIndexOutOfBoundsException {
		return grades[gradeNumber - 1];
	}

	public String getId() {
		return id;
	}

	public String showGrades() {
		StringBuilder msg = new StringBuilder("[");
		for (int i = 0; i < grades.length; i++) {
			msg.append(" ").append(grades[i]);
			if (i < grades.length - 1) msg.append(" |");
			else msg.append(" ]");
		}
		return msg.toString();
	}
}

package ui;

import customExceptions.AlreadyEnrolledException;
import customExceptions.CancelationNotAllowedException;
import customExceptions.OutOfRangeGradeException;
import customExceptions.QuotaEnrollExceedException;
import customExceptions.StudentNotEnrolledException;
import customExceptions.WeekExceededEnrollException;
import java.util.Scanner;
import model.Course;

public class Main {

	private Course myCourse;
	private Scanner reader;

	public static void main(String[] args) {
		Main exe = new Main();
		exe.menu();
	}

	public Main() {
		reader = new Scanner(System.in);
		init();
	}
	//AAA

	public void init() {
		System.out.println("Welcome to course management");

		System.out.print("Please enter the total grades amount along the semester: ");
		int totalGradesAmount = Integer.parseInt(reader.nextLine());

		System.out.print("Please enter the course enrollment capacity: ");
		int quota = Integer.parseInt(reader.nextLine());

		System.out.print("Please enter the minimum grade: ");
		double min = Double.parseDouble(reader.nextLine());

		System.out.print("Please enter the maximum grade: ");
		double max = Double.parseDouble(reader.nextLine());

		myCourse = new Course(totalGradesAmount, max, min, quota);
	}

	public void menu() {
		int option;
		do {
			System.out.println("Menu");
			System.out.println("1. Enroll a student");
			System.out.println("2. Unenroll a student");
			System.out.println("3. Set grade to a student");
			System.out.println("4. Advance a week");
			System.out.println("5. Exit the program");
			System.out.print("Please choose an option: ");

			option = Integer.parseInt(reader.nextLine());

			String id;
			switch (option) {
				case 1:
					System.out.print("Please enter the new student id: ");
					id = reader.nextLine();
					try {
						myCourse.enroll(id);
						System.out.println("Student has been enrolled");
					} catch (QuotaEnrollExceedException | WeekExceededEnrollException | AlreadyEnrolledException e) {
						System.out.println("It was not possible to enroll the student with id " + id);
						System.out.println(e.getMessage());
					}
					break;

				case 2:
					System.out.println("These are the current students enrolled:");
					System.out.println(myCourse.showEnrolledStudents());

					System.out.println("Please enter the student id to unenroll: ");
					id = reader.nextLine();

					try {
						myCourse.cancelEnrollment(id);
						System.out.println("Student has been unenrolled");
					} catch (CancelationNotAllowedException | StudentNotEnrolledException e) {
						System.out.println("Could not unenroll the student with id " + id);
						System.out.println(e.getMessage());
					}
					break;

				case 3:
					System.out.println("These are the current students enrolled:");
					System.out.println(myCourse.showEnrolledStudents());
					System.out.print("Please enter the student id to grade: ");
					id = reader.nextLine();
					System.out.println(myCourse.showStudentGrades(id));

					System.out.print("Please enter the student grade: ");
					double g = Double.parseDouble(reader.nextLine());

					System.out.print("Please enter the grade number in the semester: ");
					int gradeNumber = Integer.parseInt(reader.nextLine());

					try {
						myCourse.setStudentGrade(id, gradeNumber, g);
						System.out.println("Student has been graded");
					} catch (OutOfRangeGradeException | ArrayIndexOutOfBoundsException e) {
						System.out.println("Failed to set grade: " + e.getMessage());
					}
					break;

				case 4:
					myCourse.advanceWeek();
					System.out.println("The current week has advanced");
					break;

				case 5:
					System.out.println("Thank you. Bye!");
					break;
			}
		} while (option != 5);

		reader.close();
	}
}

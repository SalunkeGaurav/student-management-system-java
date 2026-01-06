package app;

import model.Student;
import service.StudentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService ss = new StudentService();
        boolean running = true;

        while (running) {
            System.out.println("----------------------------");
            System.out.println(" STUDENT MANAGEMENT SYSTEM  ");
            System.out.println("----------------------------");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Update student");
            System.out.println("5. Delete student");
            System.out.println("6. Exit");
            System.out.println("----------------------------");

            int choice;
            try {
                System.out.print("Select an option: ");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Student Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Student Course: ");
                        String course = sc.nextLine();

                        Student student = new Student(id, name, age, course);
                        boolean addStatus = ss.addStudent(student);
                        if (addStatus == false) {
                            System.out.println("Student Already Exists!");
                        } else {
                            System.out.println("Student Added Successfully.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter correct data.");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    if (ss.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : ss.viewAllStudents()) {
                            System.out.println(s);
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Student ID to Search: ");
                        int searchId = sc.nextInt();
                        sc.nextLine();
                        Student searchStudent = ss.searchStudentById(searchId);
                        if (searchStudent == null) {
                            System.out.println("Student not found.");
                        } else {
                            System.out.println(searchStudent);
                            System.out.println("--------------------");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Enter a number.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Student ID to Update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        Student existingStudent = ss.searchStudentById(updateId);
                        if (existingStudent == null) {
                            System.out.println("Student Not Found.");
                            break;
                        }
                        System.out.print("Enter Updated Name: ");
                        String updatedName = sc.nextLine();
                        System.out.print("Enter Updated Age: ");
                        int updatedAge = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Updated Course: ");
                        String updatedCourse = sc.nextLine();

                        boolean updateStatus = ss.updateStudent(updateId, updatedName, updatedAge, updatedCourse);
                        if (updateStatus) {
                            System.out.println("Student Updated Successfully.");
                        } else {
                            System.out.println("Update Failed!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter correct data.");
                        sc.nextLine();
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter Student ID to Delete: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();
                        Student studentToDelete = ss.searchStudentById(deleteId);
                        if (studentToDelete == null) {
                            System.out.println("Student Not Found.");
                        } else {
                            boolean deleteStatus = ss.deleteStudent(deleteId);
                            if (deleteStatus) {
                                System.out.println("Student Deleted Successfully.");
                            } else {
                                System.out.println("Delete Failed!");
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Enter a number.");
                        sc.nextLine();
                    }
                    break;

                case 6:
                    System.out.println("Exiting Program.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Choose 1-6.");
                    break;
            }
        }

        sc.close();
    }
}

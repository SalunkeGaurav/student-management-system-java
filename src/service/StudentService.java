package service;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final ArrayList<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                return false;
            }
       }

        students.add(student);
        return true;


    }

    public Student searchStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }

        }
        return null;

    }

    public boolean updateStudent(int id, String name, int age, String course) {
        Student student = searchStudentById(id);
        if (student == null) {
            return false;
        }
        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        return true;
    }

    public boolean deleteStudent(int id){
        Student student = searchStudentById(id);
        if (student == null){
            return false;
        }
         students.remove(student);
        return true;
    }

    public List<Student> viewAllStudents() {
        return students;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }





}

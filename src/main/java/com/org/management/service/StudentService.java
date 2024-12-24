package com.org.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.management.entity.Student;
import com.org.management.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer studentId, Student studentDetails) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setStudentName(studentDetails.getStudentName());
            existingStudent.setStudentAge(studentDetails.getStudentAge());
            existingStudent.setCourse(studentDetails.getCourse());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public boolean deleteStudent(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }
}

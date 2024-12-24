package com.org.management.entity;

import lombok.Data;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Data // Lombok will generate getters, setters, toString, equals, hashCode
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId; // Student ID

    @Column(name = "student_name")
    private String studentName; // Student Name

    @Column(name = "student_age")
    private Integer studentAge; // Student Salary

    @ManyToMany
    @JoinTable(name = "Student_Course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> course; // Reference to the course entity (foreign key)

    // Lombok will generate the necessary getters and setters for all fields
}

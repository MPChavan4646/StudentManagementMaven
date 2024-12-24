package com.org.management.entity;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Data // Lombok will generate getters, setters, toString, equals, hashCode
@Table(name = "COURSE")
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToMany(mappedBy = "course")
    @JsonIgnore
    private List<Student> students;

}
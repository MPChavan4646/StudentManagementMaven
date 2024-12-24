package com.org.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.management.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Custom queries can be defined here if needed
}
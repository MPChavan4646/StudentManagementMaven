package com.org.management.controller;

import com.org.management.entity.Course;
import com.org.management.exceptions.*;
import com.org.management.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        logger.info("Request to fetch all courses");
        return courseService.getAllCourses();
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        logger.info("Request to fetch course with ID: {}", id);
        try {
            Optional<Course> course = courseService.getCourseById(id);
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            logger.error("Course not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        logger.info("Request to create a course with name: {}", course.getCourseName());
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (BadException ex) {
            logger.error("Bad request: {}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        logger.info("Request to update course with ID: {}", id);
        try {
            Course updatedCourse = courseService.updateCourse(id, courseDetails);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            logger.error("Course not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        logger.info("Request to delete course with ID: {}", id);
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            logger.error("Course not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

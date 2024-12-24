package com.org.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.management.entity.Course;
import com.org.management.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Integer courseId) {
        return courseRepository.findById(courseId);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer courseId, Course courseDetails) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            Course existingCourse = course.get();
            existingCourse.setCourseName(courseDetails.getCourseName());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public boolean deleteCourse(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            courseRepository.delete(course.get());
            return true;
        }
        return false;
    }
}

package com.shei.cms.controller;

import com.shei.cms.entity.Course;
import com.shei.cms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public void findCourseById(@PathVariable int id) {
        courseRepository.findById(id);
    }
}
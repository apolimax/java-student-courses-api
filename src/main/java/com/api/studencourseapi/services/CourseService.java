package com.api.studencourseapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studencourseapi.entities.Course;
import com.api.studencourseapi.repositories.CourseRepository;
import com.api.studencourseapi.services.exceptions.ObjectNotFoundException;

@Service
public class CourseService {
    @Autowired
    CourseRepository repository;

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Long id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Course not found!"));
    }

    public Course create(Course course) {
        return repository.save(course);
    }

    public Course update(Long id, Course course) throws ObjectNotFoundException {
        Course savedCourse = findById(id);
        updateCourse(savedCourse, course);
        return repository.save(savedCourse);
    }

    public void delete(Long id) throws ObjectNotFoundException {
        findById(id);
        repository.deleteById(id);
    }

    private void updateCourse(Course savedCourse, Course course) {
        savedCourse.setName(course.getName());
    }

}

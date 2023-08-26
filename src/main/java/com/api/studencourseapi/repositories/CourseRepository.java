package com.api.studencourseapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.studencourseapi.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}

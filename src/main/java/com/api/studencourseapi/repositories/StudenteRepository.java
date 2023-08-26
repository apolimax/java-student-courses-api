package com.api.studencourseapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.studencourseapi.entities.Student;

public interface StudenteRepository extends JpaRepository<Student, Long> {

}

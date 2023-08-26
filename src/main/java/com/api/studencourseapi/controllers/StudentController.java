package com.api.studencourseapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.studencourseapi.entities.Student;
import com.api.studencourseapi.services.StudentService;
import com.api.studencourseapi.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "students")
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = service.findAll();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        try {
            Student student = service.findById(id);
            return ResponseEntity.ok().body(student);
        } catch (ObjectNotFoundException e) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found
            // with the provided id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        var savedStudent = service.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        try {
            Student updatedStudent = service.update(id, student);
            return ResponseEntity.ok().body(updatedStudent);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with the provided id");
        }
    }
}

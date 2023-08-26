package com.api.studencourseapi.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.studencourseapi.entities.Student;
import com.api.studencourseapi.services.StudentService;

@SpringBootTest
public class StudentControllerTest {
    @InjectMocks
    StudentController controller;

    @Mock
    StudentService service;

    Student student;

    private static final Long ID = 1L;
    private static final String NAME = "João";
    private static final Integer AGE = 25;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(ID, NAME, AGE);
    }

    @Test
    public void Should_ReturnAllStudents_When_FindAll() {
        Mockito.when(service.findAll()).thenReturn(List.of(student));

        ResponseEntity<List<Student>> response = controller.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Student.class, response.getBody().get(0).getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(ID, response.getBody().get(0).getId());
        Assertions.assertEquals(NAME, response.getBody().get(0).getName());
        Assertions.assertEquals(AGE, response.getBody().get(0).getAge());
    }

    @Test
    public void Should_ReturnStudent_When_FindById() {
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(student);

        ResponseEntity<Student> response = controller.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Student.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(AGE, response.getBody().getAge());
    }

    @Test
    public void Should_CreateStudent_When_Create() {
        Mockito.when(service.create(Mockito.any())).thenReturn(student);

        ResponseEntity<Student> response = controller.create(student);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void Should_UpdateStudent_When_Update() {
        Mockito.when(service.update(Mockito.anyLong(), Mockito.any())).thenReturn(student);

        ResponseEntity<Student> response = controller.update(ID, student);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(AGE, response.getBody().getAge());
    }

    @Test
    public void Should_DeleteStudent_When_Delete() {
        Mockito.doNothing().when(service).delete(Mockito.anyLong());

        controller.delete(ID);

        Mockito.verify(service).delete(ID);
    }

}

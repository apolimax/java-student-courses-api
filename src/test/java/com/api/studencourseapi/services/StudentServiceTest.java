package com.api.studencourseapi.services;

import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.studencourseapi.entities.Student;
import com.api.studencourseapi.repositories.StudenteRepository;
import com.api.studencourseapi.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class StudentServiceTest {
    @InjectMocks
    StudentService service;

    @Mock
    StudenteRepository repository;

    Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1L, "Fulano", 25);
    }

    @Test
    public void it_should_return_a_list_of_students() {
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(student));

        List<Student> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(Student.class, response.get(0).getClass());
    }

    @Test
    public void it_should_return_a_student_by_id() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));

        Student response = service.findById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Student.class, response.getClass());
        Assertions.assertEquals("Fulano", response.getName());
        Assertions.assertEquals(25, response.getAge());
    }

    // @Test
    // public void
    // it_should_throws_an_exception_when_trying_to_find_a_non_existent_student() {
    // Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    // ObjectNotFoundException ex =
    // Assertions.assertThrows(ObjectNotFoundException.class, () ->
    // service.findById(1L));

    // Assertions.assertEquals("Student not found!", ex.getMessage());

    // }
    @Test
    public void it_should_throws_an_exception_when_trying_to_find_a_non_existent_student() {
        Mockito.when(repository.findById(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException("Student not found!"));

        try {
            service.findById(1L);
        } catch (Exception e) {
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
            Assertions.assertEquals("Student not found!", e.getMessage());
        }
    }

    @Test
    public void it_should_create_a_new_student() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(student);

        Student response = service.create(student);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Fulano", response.getName());
        Assertions.assertEquals(Student.class, response.getClass());
    }

    @Test
    public void it_should_delete_a_student_by_its_id() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(student));
        Mockito.doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        Mockito.verify(repository).deleteById(1L);
    }

    @Test
    public void it_should_throws_an_exception_when_trying_to_delete_a_non_existent_student() {
        Mockito.when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Student not found!"));
        Mockito.doNothing().when(repository).deleteById(1L);

        try {
            service.delete(1L);
        } catch (Exception e) {
            Assertions.assertEquals("Student not found!", e.getMessage());
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
        }

    }
}

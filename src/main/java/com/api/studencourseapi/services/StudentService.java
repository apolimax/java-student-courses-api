package com.api.studencourseapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studencourseapi.entities.Student;
import com.api.studencourseapi.repositories.StudenteRepository;
import com.api.studencourseapi.services.exceptions.ObjectNotFoundException;

@Service
public class StudentService {
    @Autowired
    StudenteRepository repository;

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Student not found!"));
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student student) throws ObjectNotFoundException {
        Student savedStudent = findById(id);
        updateStudant(savedStudent, student);

        return repository.save(savedStudent);
    }

    public void delete(Long id) throws ObjectNotFoundException {
        findById(id);
        repository.deleteById(id);
    }

    private void updateStudant(Student savedStudent, Student student) {
        savedStudent.setName(student.getName());
        savedStudent.setAge(student.getAge());
    }

}

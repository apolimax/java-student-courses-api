package com.api.studencourseapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.studencourseapi.entities.Course;
import com.api.studencourseapi.entities.Student;
import com.api.studencourseapi.repositories.CourseRepository;
import com.api.studencourseapi.repositories.StudenteRepository;

@Configuration
@Profile("test")
public class ConfigTest implements CommandLineRunner {
    @Autowired
    StudenteRepository studenteRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        Student s1 = new Student(null, "João", 25);
        Student s2 = new Student(null, "José", 27);
        Student s3 = new Student(null, "Jonas", 21);

        Course c1 = new Course(null, "Português");
        Course c2 = new Course(null, "Matemática");

        courseRepository.saveAll(Arrays.asList(c1, c2));

        s1.getEnrolledCourses().add(c1);
        s2.getEnrolledCourses().add(c1);
        s2.getEnrolledCourses().add(c2);

        studenteRepository.saveAll(Arrays.asList(s1, s2, s3));
    }

}

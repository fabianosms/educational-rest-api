package com.machado.fabiano.forum.repository;

import com.machado.fabiano.forum.model.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void shouldFindACourseWhenSearchingByItsName() {

        String courseName = "HTML 5";

        Course course = courseRepository.findByName(courseName);
        Assertions.assertNotNull(course);
        Assertions.assertEquals(courseName, course.getName());
    }

    @Test
    public void shouldNotFindACourseWhichNameIsNotRegistered() {

        String courseName = "JPA";
        Course course = courseRepository.findByName(courseName);
        Assertions.assertNull(course);
    }
}
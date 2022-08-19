package com.machado.fabiano.forum.controller.form;

import com.machado.fabiano.forum.model.Course;
import com.machado.fabiano.forum.model.Topic;
import com.machado.fabiano.forum.repository.CourseRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class TopicForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String title;
    @NotNull @NotEmpty
    private String message;
    @NotNull @NotEmpty
    private String courseName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Topic convert(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(courseName);
        return new Topic(title, message, course);
    }
}

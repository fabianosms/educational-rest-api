package com.machado.fabiano.forum.controller.form;

import com.machado.fabiano.forum.model.Topic;
import com.machado.fabiano.forum.repository.TopicRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicUpdateForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String title;

    @NotNull @NotEmpty
    private String message;

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

    public Topic update(Long id, TopicRepository topicRepository) {

        Topic topic = topicRepository.getReferenceById(id);
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }
}
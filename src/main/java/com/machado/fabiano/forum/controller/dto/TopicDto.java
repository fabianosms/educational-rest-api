package com.machado.fabiano.forum.controller.dto;

import com.machado.fabiano.forum.model.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
    }

    public static Page<TopicDto> converter(Page<Topic> topicos) {
        return topicos.map(TopicDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

//    public static List<TopicDto> converter(List<Topic> topicos) {
//        // precisamos da lista de dtos, inicialmente vazia
//        List<TopicDto> dtos = new ArrayList<>();
//
//        // percorremos todos os topicos
//        for(Topic topico : topicos) {
//            // para cada Topic, precisamos criar um dto e passar o proprio topico para o construtor
//            TopicDto dto = new TopicDto(topico);
//
//            // adicionamos o dto na lista de dtos
//            dtos.add(dto);
//        }
//
//        // retornamos a lista de dtos, que foi preenchida no loop for anterior
//        return dtos;
//    }

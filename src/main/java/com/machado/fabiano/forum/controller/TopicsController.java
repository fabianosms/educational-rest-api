package com.machado.fabiano.forum.controller;

import com.machado.fabiano.forum.controller.dto.DetailedTopicDto;
import com.machado.fabiano.forum.controller.dto.TopicDto;
import com.machado.fabiano.forum.controller.form.TopicUpdateForm;
import com.machado.fabiano.forum.controller.form.TopicForm;
import com.machado.fabiano.forum.model.Topic;
import com.machado.fabiano.forum.repository.CourseRepository;
import com.machado.fabiano.forum.repository.TopicRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "topicList")
    public Page<TopicDto> listTopics(@RequestParam(required = false) String courseName,
                                     @PageableDefault(sort = "title", direction = Sort.Direction.DESC,
                                         page = 0, size = 10) Pageable paging) {   

        Page<Topic> topics;

        if (courseName == null) {
            topics = topicRepository.findAll(paging);
        } else {
            topics = topicRepository.findByCourseName(courseName, paging);
        }

        return TopicDto.converter(topics);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topic topic = form.convert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @GetMapping("/{id}")
    public DetailedTopicDto searchSingleTopic(@PathVariable Long id) {

        Topic topic = topicRepository.getReferenceById(id);
        return new DetailedTopicDto(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {

        Topic topic = form.update(id, topicRepository);

        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key") //Swagger
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {

        topicRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
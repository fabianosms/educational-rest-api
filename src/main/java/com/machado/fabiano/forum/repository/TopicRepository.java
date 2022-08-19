package com.machado.fabiano.forum.repository;

import com.machado.fabiano.forum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourseName(String courseName, Pageable paging);

    @Query("SELECT t FROM Topic t WHERE t.course.name = :courseName")
    List<Topic> findTopicByCourseName(@Param("courseName") String courseName);

}

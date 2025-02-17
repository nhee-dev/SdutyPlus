package com.d205.sdutyplus.domain.task.repository;

import com.d205.sdutyplus.domain.task.entity.Task;
import com.d205.sdutyplus.domain.task.repository.querydsl.TaskRepositoryQuerydsl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryQuerydsl {
    List<Task> findAllByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}

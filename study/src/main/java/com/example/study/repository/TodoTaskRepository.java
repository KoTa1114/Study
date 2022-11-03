package com.example.study.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.study.entity.TodoTask;

public interface TodoTaskRepository extends CrudRepository<TodoTask, Integer> {
	@Query("SELECT todo_id FROM todo_task ORDER BY RANDOM() limit 1")
	Integer getRandomId();
}
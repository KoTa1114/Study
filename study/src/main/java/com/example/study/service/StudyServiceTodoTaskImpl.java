package com.example.study.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.entity.TodoTask;
import com.example.study.repository.TodoTaskRepository;

@Service
@Transactional
public class StudyServiceTodoTaskImpl implements StudyServiceTodoTask {
	@Autowired
	TodoTaskRepository repository;
	@Override
	public Iterable<TodoTask> selectAllTodoTask() {
		return repository.findAll();
	}
	@Override
	public Optional<TodoTask> selectOneTodoTaskById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public Optional<TodoTask> selectOneRandomTodoTask() {
		Integer randId = repository.getRandomId();
		if(randId == null) {
			return Optional.empty();
		} else {
			return repository.findById(randId);
		}
	}
	@Override
	public void insertTodoTask(TodoTask todoTask) {
		repository.save(todoTask);
	}
	@Override
	public void updateTodoTask(TodoTask todoTask) {
		repository.save(todoTask);
	}
	@Override
	public void deleteTodoTaskById(Integer id) {
		repository.deleteById(id);
	}
}

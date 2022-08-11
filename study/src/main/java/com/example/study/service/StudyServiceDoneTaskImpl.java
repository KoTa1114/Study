package com.example.study.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.entity.DoneTask;
import com.example.study.repository.DoneTaskRepository;

@Service
@Transactional
public class StudyServiceDoneTaskImpl implements StudyServiceDoneTask {
	@Autowired
	DoneTaskRepository repository;
	@Override
	public Iterable<DoneTask> selectAllDoneTask() {
		return repository.findAll();
	}
	@Override
	public Optional<DoneTask> selectOneDoneTaskById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public void updateDoneTask(DoneTask doneTask) {
		repository.save(doneTask);
	}
	@Override
	public void deleteDoneTaskById(Integer id) {
		repository.deleteById(id);
	}
}

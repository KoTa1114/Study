package com.example.study.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.study.entity.DoneTask;

public interface DoneTaskRepository extends CrudRepository<DoneTask, Integer> {

}
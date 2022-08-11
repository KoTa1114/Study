package com.example.study.service;

import java.util.Optional;

import com.example.study.entity.TodoTask;


public interface StudyServiceTodoTask {
	//TodoTaskを全件取得する
	Iterable<TodoTask> selectAllTodoTask();
	//TodoTaskをidをキーにして1件取得する
	Optional<TodoTask> selectOneTodoTaskById(Integer id);
	//TodoTaskをランダムで1件取得する
	Optional<TodoTask> selectOneRandomTodoTask();
	//TodoTaskを登録する
	void insertTodoTask(TodoTask todoTask);
	//TodoTaskを更新する
	void updateTodoTask(TodoTask todoTask);
	//TodoTaskを削除する
	void deleteTodoTaskById(Integer id);
}

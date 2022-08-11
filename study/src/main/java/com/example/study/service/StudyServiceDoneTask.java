package com.example.study.service;

import java.util.Optional;

import com.example.study.entity.DoneTask;

public interface StudyServiceDoneTask {
	//DoneTaskを全件取得する
	Iterable<DoneTask> selectAllDoneTask();
	//DoneTaskをidをキーにして1件取得する
	Optional<DoneTask> selectOneDoneTaskById(Integer id);
	//DoneTaskを更新する
	void updateDoneTask(DoneTask doneTask);
	//DoneTaskを削除する
	void deleteDoneTaskById(Integer id);
}
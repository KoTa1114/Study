package com.example.study.service;

import java.util.Optional;
import com.example.study.entity.Memo;

public interface StudyServiceMemo {
	//Memoを全件取得する
	Iterable<Memo> selectAllMemo();
	Optional<Memo> selectOneMemoById(Integer id);
	//Memoを登録する
	void insertMemo(Memo memo);
	void updateMemo(Memo memo);
	void deleteMemoById(Integer id);
}
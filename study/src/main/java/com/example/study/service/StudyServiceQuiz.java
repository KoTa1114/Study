package com.example.study.service;

import java.util.Optional;
import com.example.study.entity.Quiz;

public interface StudyServiceQuiz {
	//Quizを全件取得する
	Iterable<Quiz> selectAllQuiz();
	//Quizをidをキーにして1件取得する
	Optional<Quiz> selectOneQuizById(Integer id);
	//Quizをランダムで1件取得する
	Optional<Quiz> selectOneRandomQuiz();
	//Quizを登録する
	void insertQuiz(Quiz quiz);
	//Quizを更新する
	void updateQuiz(Quiz quiz);
	//Quizを削除する
	void deleteQuizById(Integer id);
	//Quizの正解/不正解を判定する
	Boolean checkQuiz(Integer id, Boolean myAnswer);
}
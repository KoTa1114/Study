package com.example.study.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.study.entity.Quiz;
import com.example.study.repository.QuizRepository;

@Service
@Transactional
public class StudyServiceQuizImpl implements StudyServiceQuiz {
	@Autowired
	QuizRepository repository;
	@Override
	public Iterable<Quiz> selectAllQuiz() {
		return repository.findAll();
	}
	@Override
	public Optional<Quiz> selectOneQuizById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public Optional<Quiz> selectOneRandomQuiz() {
		Integer randId = repository.getRandomId();
		if(randId == null) {
			return Optional.empty();
		} else {
			return repository.findById(randId);
		}
	}
	@Override
	public void insertQuiz(Quiz quiz) {
		repository.save(quiz);
	}
	@Override
	public void updateQuiz(Quiz quiz) {
		repository.save(quiz);
	}
	@Override
	public void deleteQuizById(Integer id) {
		repository.deleteById(id);
	}
}
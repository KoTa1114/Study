package com.example.study.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.study.entity.Memo;
import com.example.study.repository.MemoRepository;

@Service
@Transactional
public class StudyServiceMemoImpl implements StudyServiceMemo {
	@Autowired
	MemoRepository repository;
	@Override
	public Iterable<Memo> selectAllMemo() {
		return repository.findAll();
	}
	@Override
	public Optional<Memo> selectOneMemoById(Integer id) {
		return repository.findById(id);
	}
	@Override
	public void insertMemo(Memo memo) {
		repository.save(memo);
	}
	@Override
	public void updateMemo(Memo memo) {
		repository.save(memo);
	}
	@Override
	public void deleteMemoById(Integer id) {
		repository.deleteById(id);
	}
}
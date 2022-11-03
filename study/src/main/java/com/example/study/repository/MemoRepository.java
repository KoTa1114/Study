package com.example.study.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.study.entity.Memo;

public interface MemoRepository extends CrudRepository<Memo, Integer> {
	@Query("SELECT memo_id FROM memo ORDER BY RANDOM() limit 1")
}
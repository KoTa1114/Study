package com.example.study.entity;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//quizテーブル用 Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	@Id
	private Integer quiz_id;
	private String quiz_question;
	private Boolean quiz_answer;
}
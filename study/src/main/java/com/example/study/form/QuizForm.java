package com.example.study.form;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {
	private Integer quiz_id;
	@NotBlank
	private String quiz_question;
	@NotBlank
	private String quiz_answer;
}
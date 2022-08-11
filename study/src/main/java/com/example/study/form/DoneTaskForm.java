package com.example.study.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoneTaskForm {
	private Integer done_id;
	@NotBlank
	private String done_title;
	@NotBlank
	private String done_content;
	@Positive
	@NotNull
	private Integer done_time;
}
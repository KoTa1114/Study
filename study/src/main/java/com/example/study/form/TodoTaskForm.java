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
public class TodoTaskForm {
	private Integer todo_id;
	@NotBlank
	private String todo_title;
	@NotBlank
	private String todo_content;
	@Positive
	@NotNull
	private Integer todo_time;
	@NotBlank
	private String todo_limit;
	private Boolean newTodoTask;
	@NotNull
	@Positive
	private Integer todo_priority;
}
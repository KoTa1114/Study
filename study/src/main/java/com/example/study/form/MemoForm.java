package com.example.study.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoForm {
	private Integer memo_id;
	@NotBlank
	private String memo_title;
	@NotBlank
	private String memo_content;
	private Boolean newMemoForm;
}
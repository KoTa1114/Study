package com.example.study.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxTimeForm {
	@NotNull
	@Min(value=1)
	@Max(value=1440)
	private Integer max_time;
}
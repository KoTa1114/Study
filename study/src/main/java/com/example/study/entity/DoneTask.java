package com.example.study.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoneTask {
	@Id
	private Integer done_id; //識別ID
	private String done_title; //タスクのタイトル
	private String done_content; //タスクの中身
	private Integer done_time; //タスクにかかるはずだった予想時間
}

package com.example.study.entity;
//todo_taskテーブル用エンティティ
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoTask {
	@Id
	private Integer todo_id; //識別ID
	private String todo_title; //タスクのタイトル
	private String todo_content; //タスクの中身
	private Integer todo_time; //タスクにかかる予想時間
	private String todo_limit; //タスクの期限
	private Integer todo_priority; //タスクの優先順位 低い1   100高い
}

package com.example.study.entity;
//memoテーブル用エンティティ
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memo {
	@Id
	private Integer memo_id; //識別ID
	private String memo_title; //メモのタイトル
	private String memo_content; //メモの内容
}
# 学習補助アプリ
## 機能一覧
Todo  
新規タスクの登録、登録済みタスクの閲覧、編集、削除、終了済みタスクへの移動  
登録済みのTodoタスクから入力された時間内で優先度の和が最大になるようにTodoタスクを選出(動的計画法を利用)   
Done  
終了済みタスクの登録、登録済み終了済みタスクの閲覧、編集、削除  
Memo  
新規メモの登録、登録済みメモの閲覧、編集、削除  
Quiz  
新規クイズの登録、登録済みクイズの閲覧、編集、削除  
登録済みクイズからランダムで一つのクイズを選出し挑戦できる  

## 目的
競技プログラミングや英語学習、資格取得に取り組む中でそれを補助するツールが欲しくなったため  
実際の開発を通してWebアプリケーション開発について学習するため  

## 構成
メインはTodo,Done,Memo,Quizの4画面  
Todo  
<img width="1680" alt="Todo" src="https://user-images.githubusercontent.com/108277601/200160805-97d08995-c646-4fe0-80e7-cef556618a22.png">  
入力フォームでは入力が適切であるか確認する  
<img width="1680" alt="Todo:Validation" src="https://user-images.githubusercontent.com/108277601/200160863-0211d082-baf1-46b0-b9ae-fe5d21f06c42.png">  
タスク自動選出機能を使用した結果  
<img width="1680" alt="Todoタスク選出" src="https://user-images.githubusercontent.com/108277601/200160867-83160b0b-3545-46d4-9ba7-fa4659d61057.png">  
Done  
<img width="1680" alt="Done" src="https://user-images.githubusercontent.com/108277601/200160826-b0c71638-0f7f-4583-9ae2-a0bf46632da2.png">
Memo  
<img width="1680" alt="Memo" src="https://user-images.githubusercontent.com/108277601/200160832-8119508e-5b2a-4834-ae74-432c23ab69f6.png">
Quiz  
<img width="1680" alt="Quiz" src="https://user-images.githubusercontent.com/108277601/200160837-6c7b4a50-7a1a-4730-be2a-d5b7fc1aa561.png">  
クイズに挑戦するを押した結果  
<img width="1680" alt="Quiz:play" src="https://user-images.githubusercontent.com/108277601/200160838-1a0380a2-1a8a-4cb2-9a26-0104bb770351.png">  
クイズの正解/不正解を表示  
<img width="1680" alt="Quiz:result" src="https://user-images.githubusercontent.com/108277601/200160842-e436c86b-47cd-45a8-b97a-5e8a49bbe7bc.png">

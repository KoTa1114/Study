package com.example.study.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.study.entity.TodoTask;
import com.example.study.entity.DoneTask;
import com.example.study.entity.Memo;
import com.example.study.entity.Quiz;
import com.example.study.service.StudyServiceTodoTask;
import com.example.study.service.StudyServiceDoneTask;
import com.example.study.service.StudyServiceMemo;
import com.example.study.service.StudyServiceQuiz;
import com.example.study.form.TodoTaskForm;
import com.example.study.form.DoneTaskForm;
import com.example.study.form.MemoForm;
import com.example.study.form.QuizForm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



@Controller
@RequestMapping("/study")
public class StudyController {
	@Autowired
	StudyServiceTodoTask service;
	@Autowired
	StudyServiceDoneTask serviceDoneTask;
	@Autowired
	StudyServiceMemo serviceMemo;
	@Autowired
	StudyServiceQuiz serviceQuiz;
	@ModelAttribute
	public TodoTaskForm setUpForm() {
		TodoTaskForm form = new TodoTaskForm();
		return form;
	}
	@ModelAttribute
	public DoneTaskForm setUpDoneTaskForm() {
		DoneTaskForm doneTaskForm = new DoneTaskForm();
		return doneTaskForm;
	}
	@ModelAttribute
	public MemoForm setUpMemoForm() {
		MemoForm memoForm = new MemoForm();
		return memoForm;
	}
	@ModelAttribute
	public QuizForm setUpQuizForm() {
		QuizForm quizForm = new QuizForm();
		return quizForm;
	}
	@GetMapping
	public String showList(TodoTaskForm todoTaskForm, Model model) {
		todoTaskForm.setNewTodoTask(true);
		Iterable<TodoTask> list = service.selectAllTodoTask();
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "crud";
	}
	@GetMapping("/done")
	public String showDoneList(Model model) {
		Iterable<DoneTask> doneList = serviceDoneTask.selectAllDoneTask();
		model.addAttribute("doneList", doneList);
		model.addAttribute("title", "登録用フォーム");
		return "/done";
	}
	@GetMapping("/memo")
	public String showMemoList(Model model) {
		Iterable<Memo> memoList = serviceMemo.selectAllMemo();
		model.addAttribute("memoList",memoList);
		model.addAttribute("title","登録用フォーム");
		return "/memo";
	}
	@GetMapping("/quiz")
	public String showQuizList(Model model) {
		Iterable<Quiz> quizList = serviceQuiz.selectAllQuiz();
		model.addAttribute("quizList",quizList);
		model.addAttribute("title","登録用フォーム");
		return "/quiz";
	}
	@PostMapping("/insert")
	public String insert(@Validated TodoTaskForm todoTaskForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		//FormからEntityへの詰め替え
		TodoTask todoTask = makeTodoTask(todoTaskForm);
		//入力チェック
		if(!bindingResult.hasErrors()) {
			service.insertTodoTask(todoTask);
			redirectAttributes.addFlashAttribute("complete","登録が完了しました");
			return "redirect:/study";
		} else {
			//エラーがある場合は、一覧表示処理を呼ぶ
			return showList(todoTaskForm, model);
		}
	}
	@GetMapping("/{todo_id}")
	public String showUpdate(TodoTaskForm todoTaskForm, @PathVariable Integer todo_id, Model model) {
		//TodoTaskを取得(Optionalでラップ)
		Optional<TodoTask> todoTaskOpt = service.selectOneTodoTaskById(todo_id);
		//TodoTaskFormへの詰め直し
		Optional<TodoTaskForm> todoTaskFormOpt = todoTaskOpt.map(t -> makeTodoTaskForm(t));
		//TodoTaskFormがnullでなければ中身を取り出す
		if(todoTaskFormOpt.isPresent()) {
			todoTaskForm = todoTaskFormOpt.get();
		}
		//更新用のModelを作成する
		makeUpdateModel(todoTaskForm, model);
		return "crud";
	}
	@GetMapping("/done/{done_id}")
	public String showUpdateDoneTask(DoneTaskForm doneTaskForm, @PathVariable Integer done_id, Model model) {
		//TodoTaskを取得(Optionalでラップ)
		Optional<DoneTask> doneTaskOpt = serviceDoneTask.selectOneDoneTaskById(done_id);
		//TodoTaskFormへの詰め直し
		Optional<DoneTaskForm> doneTaskFormOpt = doneTaskOpt.map(t -> makeDoneTaskForm(t));
		//TodoTaskFormがnullでなければ中身を取り出す
		if(doneTaskFormOpt.isPresent()) {
			doneTaskForm = doneTaskFormOpt.get();
		}
		//更新用のModelを作成する
		makeUpdateDoneTaskModel(doneTaskForm, model);
		return "done";
	}
	@GetMapping("/memo/{memo_id}")
	public String showUpdateMemo(MemoForm memoForm, @PathVariable Integer memo_id, Model model) {
		//Memoを取得
		Optional<Memo> memoOpt = serviceMemo.selectOneMemoById(memo_id);
		//MemoFormへの詰め直し
		Optional<MemoForm> memoFormOpt = memoOpt.map(t -> makeMemoForm(t));
		//MemoFormがnullでなければ中身を取り出す
		if(memoFormOpt.isPresent()) {
			memoForm = memoFormOpt.get();
		}
		//更新用のmodelを作成する
		makeUpdateMemoModel(memoForm, model);
		return "memo";
	}
	@GetMapping("/quiz/{quiz_id}")
	public String showUpdateQuiz(QuizForm quizForm, @PathVariable Integer quiz_id, Model model) {
		//Quizを取得
		Optional<Quiz> quizOpt = serviceQuiz.selectOneQuizById(quiz_id);
		//QuizFormへの詰め直し
		Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
		//QuizFormがnullでなければ中身を取り出す
		if(quizFormOpt.isPresent()) {
			quizForm = quizFormOpt.get();
		}
		//更新用のmodelを作成する
		makeUpdateQuizModel(quizForm, model);
		return "quiz";
	}
	private void makeUpdateModel(TodoTaskForm todoTaskForm, Model model) {
		model.addAttribute("todo_id", todoTaskForm.getTodo_id());
		todoTaskForm.setNewTodoTask(false);
		model.addAttribute("todoTaskForm", todoTaskForm);
		model.addAttribute("title", "更新用フォーム");
	}
	private void makeUpdateDoneTaskModel(DoneTaskForm doneTaskForm, Model model) {
		model.addAttribute("done_id", doneTaskForm.getDone_id());
		model.addAttribute("doneTaskForm", doneTaskForm);
		model.addAttribute("title", "更新用フォーム");
	}
	private void makeUpdateMemoModel(MemoForm memoForm, Model model) {
		model.addAttribute("memo_id", memoForm.getMemo_id());
		model.addAttribute("memoForm", memoForm);
		model.addAttribute("title", "更新用フォーム");
	}
	private void makeUpdateQuizModel(QuizForm quizForm, Model model) {
		model.addAttribute("quiz_id",quizForm.getQuiz_id());
		model.addAttribute("quizForm",quizForm);
		model.addAttribute("title","更新用フォーム");
	}
	@PostMapping("/update")
	public String update(
		@Validated TodoTaskForm todoTaskForm,
		BindingResult result,
		Model model,
		RedirectAttributes redirectAttributes) {
		//TodoTaskFormからTodoTaskに詰め直す
		TodoTask todoTask = makeTodoTask(todoTaskForm);
		//入力チェック
		if(!result.hasErrors()) {
			//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ)
			service.updateTodoTask(todoTask);
			redirectAttributes.addFlashAttribute("complete","更新が完了しました");
			//更新画面を表示する
			return "redirect:/study/";
		} else {
			//更新用のModelを作成する
			makeUpdateModel(todoTaskForm, model);
			return "crud";
			}
		}
	@PostMapping("/updateDoneTask")
	public String updateDoneTask(
		@Validated DoneTaskForm doneTaskForm,
		BindingResult result,
		Model model,
		RedirectAttributes redirectAttributes) {
		//DoneTaskFormからDoneTaskに詰め直す
		DoneTask doneTask = makeDoneTask(doneTaskForm);
		//入力チェック
		if(!result.hasErrors()) {
			//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ)
			serviceDoneTask.updateDoneTask(doneTask);
			redirectAttributes.addFlashAttribute("completeDoneTask","更新が完了しました");
			//更新画面を表示する
			return "redirect:/study/done/";
		} else {
			//更新用のModelを作成する
			makeUpdateDoneTaskModel(doneTaskForm, model);
			return "done";
			}
		}
	@PostMapping("/updateMemo")
	public String updateMemo(
		@Validated MemoForm memoForm,
		BindingResult result,
		Model model,
		RedirectAttributes redirectAttributes) {
		//MemoFromからMemoに詰め直す
		Memo memo = makeMemo(memoForm);
		//入力チェック
		if(!result.hasErrors()) {
			serviceMemo.updateMemo(memo);
			redirectAttributes.addFlashAttribute("completeMemo", "更新が完了しました");
			//更新画面を表示する
			return "redirect:/study/memo/";
		} else {
			//更新用のModelを作成する
			makeUpdateMemoModel(memoForm, model);
			return "memo";
		}
	}
	@PostMapping("/updateQuiz")
	public String updateQuiz(
		@Validated QuizForm quizForm,
		BindingResult result,
		Model model,
		RedirectAttributes redirectAttributes) {
		//QuizFormからQuizに詰め直す
		Quiz quiz = makeQuiz(quizForm);
		//入力チェック
		if(!result.hasErrors()) {
			serviceQuiz.updateQuiz(quiz);
			redirectAttributes.addFlashAttribute("completeQuiz","更新が完了しました");
			//更新画面を表示する
			return "redirect:/study/quiz/";
		} else {
			//更新用のmodelを作成する
			makeUpdateQuizModel(quizForm, model);
			return "quiz";
		}
	}
	@PostMapping("/delete")
	public String delete (
			@RequestParam("todo_id") String id,
			Model model,
			RedirectAttributes redirectAttributes) {
		//タスクを1件削除してリダイレクト
		service.deleteTodoTaskById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/study";
	}
	@PostMapping("/deleteDoneTask")
	public String deleteDoneTask (
			@RequestParam("done_id") String id,
			Model model,
			RedirectAttributes redirectAttributes) {
		//タスクを1件削除してリダイレクト
		serviceDoneTask.deleteDoneTaskById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/study/done";
	}
	@PostMapping("/deleteMemo")
	public String deleteMemo (
			@RequestParam("memo_id") String id,
			Model model,
			RedirectAttributes redirectAttributes) {
		//Memoを一件削除してリダイレクト
		serviceMemo.deleteMemoById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/study/memo";
	}
	@PostMapping("/deleteQuiz")
	public String deleteQuiz(
			@RequestParam("quiz_id") String id,
			Model model,
			RedirectAttributes redirectAttributes) {
		//Quizを一件削除してリダイレクト
		serviceQuiz.deleteQuizById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete","削除が完了しました");
		return "redirect:/study/quiz";
	}
	@GetMapping("/random")
	public String showTodoTask(TodoTaskForm todoTaskForm, Model model) {
		//TodoTaskを取得
		Optional<TodoTask> todoTaskOpt = service.selectOneRandomTodoTask();
		//値が入っているか判定する
		if(todoTaskOpt.isPresent()) {
			//TodoTaskFormへの詰め直し
			Optional<TodoTaskForm> todoTaskFormOpt = todoTaskOpt.map(t -> makeTodoTaskForm(t));
			todoTaskForm = todoTaskFormOpt.get();
		} else {
			model.addAttribute("msg", "Todoタスクがありません");
			return "random";
		}
		//表示用Modelへの格納
		model.addAttribute("todoTaskForm", todoTaskForm);
		return "random";
	}
	
	@GetMapping("/doneTodoTask")
	public String doneTodoTask(@RequestParam("todo_id") String id,TodoTaskForm todoTaskForm,
		Model model, RedirectAttributes redirectAttributes) {
		//Todoタスクのうち終了したものをDoneタスクに移動する処理を追加する
		Optional<TodoTask> todoTaskOpt = service.selectOneTodoTaskById(Integer.parseInt(id));
		if(todoTaskOpt.isPresent()) {
			TodoTask todoTask = todoTaskOpt.get();
			service.deleteTodoTaskById(todoTask.getTodo_id());
			DoneTask doneTask = changeTodoTaskToDoneTask(todoTask);
			serviceDoneTask.updateDoneTask(doneTask);
			redirectAttributes.addFlashAttribute("doneChange","移動が完了しました");
		} else {
			model.addAttribute("msg", "Todoタスクがありません");
			return "redirect:/study";
		}
		return "redirect:/study";
	}
	@GetMapping("/play")
	public String showQuiz(QuizForm quizForm, Model model) {
		//Quizを取得
		Optional<Quiz> quizOpt = serviceQuiz.selectOneRandomQuiz();
		//値が入っているか判定する
		if(quizOpt.isPresent()) {
			//QuizFormへの詰め直し
			Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
			quizForm = quizFormOpt.get();
		} else {
			model.addAttribute("msg", "問題がありません");
			return "/play";
		}
		//表示用Modelへの格納
		model.addAttribute("quizForm", quizForm);
		return "/play";
	}
	@PostMapping("/check")
	public String checkQuiz(QuizForm quizForm, @RequestParam Boolean answer, Model model) {
		if(serviceQuiz.checkQuiz(quizForm.getQuiz_id(), answer)) {
			model.addAttribute("msg","正解です");
		} else {
			model.addAttribute("msg","不正解です");
		}
		return "/result";
	}
		//TodoTaskFormからTodoTaskに詰め直して戻り値として返す
		private TodoTask makeTodoTask(TodoTaskForm todoTaskForm) {
			TodoTask todoTask = new TodoTask();
			todoTask.setTodo_id(todoTaskForm.getTodo_id());
			todoTask.setTodo_title(todoTaskForm.getTodo_title());
			todoTask.setTodo_content(todoTaskForm.getTodo_content());
			todoTask.setTodo_time(todoTaskForm.getTodo_time());
			todoTask.setTodo_limit(todoTaskForm.getTodo_limit());
			todoTask.setTodo_priority(todoTaskForm.getTodo_priority());
			return todoTask;
		}
		//DoneTaskFromからDoneTaskに詰め直して戻り値として返す
		private DoneTask makeDoneTask(DoneTaskForm doneTaskForm) {
			DoneTask doneTask = new DoneTask();
			doneTask.setDone_id(doneTaskForm.getDone_id());
			doneTask.setDone_title(doneTaskForm.getDone_title());
			doneTask.setDone_content(doneTaskForm.getDone_content());
			doneTask.setDone_time(doneTaskForm.getDone_time());
			return doneTask;
		}
		//MemoFormからMemoに詰め直して戻り値として返す
		private Memo makeMemo(MemoForm memoForm) {
			Memo memo = new Memo();
			memo.setMemo_id(memoForm.getMemo_id());
			memo.setMemo_title(memoForm.getMemo_title());
			memo.setMemo_content(memoForm.getMemo_content());
			return memo;
		}
		//QuizFormからQuizに詰め直して戻り値として返す
		private Quiz makeQuiz(QuizForm quizForm) {
			Quiz quiz = new Quiz();
			quiz.setQuiz_id(quizForm.getQuiz_id());
			quiz.setQuiz_question(quizForm.getQuiz_question());
			quiz.setQuiz_answer(quizForm.getQuiz_answer());
			return quiz;
		}
		
	/** TodoTaskからTodoTaskFormに詰め直して戻り値として返す */
	private TodoTaskForm makeTodoTaskForm(TodoTask todoTask) {
		TodoTaskForm form = new TodoTaskForm();
		form.setTodo_id(todoTask.getTodo_id());
		form.setTodo_title(todoTask.getTodo_title());
		form.setTodo_content(todoTask.getTodo_content());
		form.setTodo_time(todoTask.getTodo_time());
		form.setTodo_limit(todoTask.getTodo_limit());
		form.setTodo_priority(todoTask.getTodo_priority());
		form.setNewTodoTask(false);
		return form;
	}
	//DoneTaskからDoneTaskFormに詰め直して戻り値として返す
	private DoneTaskForm makeDoneTaskForm(DoneTask doneTask) {
		DoneTaskForm form = new DoneTaskForm();
		form.setDone_id(doneTask.getDone_id());
		form.setDone_title(doneTask.getDone_title());
		form.setDone_content(doneTask.getDone_content());
		form.setDone_time(doneTask.getDone_time());
		return form;
	}
	//MemoからMemoFormに詰め直して戻り値として返す
	private MemoForm makeMemoForm(Memo memo) {
		MemoForm form = new MemoForm();
		form.setMemo_id(memo.getMemo_id());
		form.setMemo_title(memo.getMemo_title());
		form.setMemo_content(memo.getMemo_content());
		return form;
	}
	private QuizForm makeQuizForm(Quiz quiz) {
		QuizForm form = new QuizForm();
		form.setQuiz_id(quiz.getQuiz_id());
		form.setQuiz_question(quiz.getQuiz_question());
		form.setQuiz_answer(quiz.getQuiz_answer());
		return form;
	}
	//TodoTaskからDoneTaskに変更する
	private DoneTask changeTodoTaskToDoneTask(TodoTask todoTask) {
		DoneTask doneTask = new DoneTask();
		doneTask.setDone_title(todoTask.getTodo_title());
		doneTask.setDone_content(todoTask.getTodo_content());
		doneTask.setDone_time(todoTask.getTodo_time());
		return doneTask;
	}
}

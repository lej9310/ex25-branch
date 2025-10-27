package com.lej.ex25_branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lej.ex25_branch.domain.Student;
import com.lej.ex25_branch.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students/valid")
@RequiredArgsConstructor
public class StudentController_valid {

	private final StudentService studentService;

	// =========================================
	// list 화면: 전체 학생 목록 출력
	// http://localhost:8080/students/valid
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "student/list_validtest";
	}

	// =========================================
	// 등록 form 화면: 새로운 학생 정보 입력
	// http://localhost:8080/students/valid/new
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

	// 등록 form 화면 - ValidTest: 새로운 학생 정보 입력
	// http://localhost:8080/students/valid/new/valid
	@GetMapping("/new/valid")
	public String createFormValid(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

	// =========================================
	// 등록처리: 학생정보 기록 후, 저장 클릭 >> 학생정보 삽입
	// @Valid >> 검증해라
	@PostMapping
	public String create(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			// 결과에 오류가 있으면 >> 입력창으로 돌아가기
			return "student/form_validtest";
		}
		// 오류 없으면 >> 학생 정보 삽입
		studentService.createStudent(student);
		return "redirect:/students/valid";
	}

	// =========================================
	// 수정 form
	// http://localhost:8080/students/valid/14/edit
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "student/form_validtest";
	}

	// 수정 처리
	// @Valid >> 검증해라
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// 결과에 오류가 있으면 >> 입력창으로 돌아가기
			return "student/form_validtest";
		}
		// 오류 없으면 >> 학생 정보 삽입
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students/valid";
	}

	// =========================================
	// 삭제 처리
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students/valid";
	}
}

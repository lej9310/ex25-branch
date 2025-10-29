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

// build.gradle에 Jakarta Validation API 의존성 추가 >> Refresh gradle project
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students/valid")
@RequiredArgsConstructor
public class StudentController_valid {

	private final StudentService studentService;
	
	// list 조희 화면: 전체 학생 목록 조희 ======================================
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudents());

		return "student/list_validtest";
	}

	// 학생 등록 form 화면: 새로운 학생 정보를 입력 ================================
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

	// *** 학생 등록 폼 => ValidTest: 입력한 학생 정보가 양식에 맞는지 오류 검사
	@GetMapping("/new/valid")
	public String createFormValid(Model model) {
		model.addAttribute("student", new Student());
		return "student/form_validtest";
	}

	// 등록 처리: 학생정보 입력 후 저장버튼 클릭 >> 새로운 학생 정보 목록에 삽입
	@PostMapping
	public String create(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
//			결과에 에러가 있으면, 입력안하고, 입력창으로 돌아가기
			return "student/form_validtest";
		}
//		이미 학생 저장이 완료되요
		studentService.createStudent(student);
		return "redirect:/students/valid";
	}

//	http://localhost:8080/students/valid/11/edit
//	수정폼
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "student/form_validtest";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
//			결과에 에러가 있으면, 입력안하고, 입력창으로 돌아가기
			return "student/form_validtest";
		}
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students/valid";

	}

//	http://localhost:8080/students/11/delete
	// 삭제 처리
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students/valid";
	}
}

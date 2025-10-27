package com.lej.ex25_branch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lej.ex25_branch.domain.Student;
import com.lej.ex25_branch.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	// list 화면: 전체 학생 목록 ============================================
	@GetMapping
	public String list(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "student/list";
	}

	// form 화면: 신규 학생 등록 ============================================
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("student", new Student());
		return "student/form";
	}

	// 신규 학생 등록 - 저장 >> 정보 삽입(insert) ===============================
	@PostMapping
	public String create(@ModelAttribute Student student) {
		// 이미 학생 저장 완료 필요
		studentService.createStudent(student);
		return "redirect:/students";
	}

	// 수정 form ==========================================================
	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudent(id));
		return "student/form";
	}

	// 수정 처리 ===========================================================
	// http://localhost:8080/students/6/edit
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Student student) {
		student.setId(id);
		studentService.updateStudent(student);
		return "redirect:/students";
	}

	// DELETE ==========================================
	// http://localhost:8080/students/6/delete
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}

}

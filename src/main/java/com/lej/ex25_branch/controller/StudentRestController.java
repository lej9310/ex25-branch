package com.lej.ex25_branch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lej.ex25_branch.domain.Student;
import com.lej.ex25_branch.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentRestController {

	private final StudentService studentService;

	// 
	@GetMapping
	public List<Student> list() {
		return studentService.getAllStudents();
	}

	// 
	@GetMapping("/{id}")
	public ResponseEntity<Student> detail(@PathVariable Long id) {
		Student student = studentService.getStudent(id);
		if (student == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);
	}

	// 
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody Student student) {
		studentService.createStudent(student);
		return ResponseEntity.ok(student);
	}

	// 
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
		student.setId(id);
		studentService.updateStudent(student);
		return ResponseEntity.ok(student);
	}

	// 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delte(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok().build();
	}
}

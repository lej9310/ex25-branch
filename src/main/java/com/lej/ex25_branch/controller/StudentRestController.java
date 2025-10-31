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

	// 1. StudentService 클래스의 인스턴스 주입 ============
	private final StudentService studentService;

	// 2. 엔드포인트 메서드 ===============================
	
	// 2-1. GET: 학생 목록 조회 - StudentService에서 모든 학생의 리스트 가져와서 반환
	// URL: "/api/students"
	@GetMapping
	public List<Student> list() {
		return studentService.getAllStudents();
	}

	// 2-2. GET: 특정 ID를 가진 학생의 상세 정보 조회
	// URL: "/api/students/{id}"
	@GetMapping("/{id}")
	public ResponseEntity<Student> detail(@PathVariable Long id) {
		Student student = studentService.getStudent(id);
		//  요청한 학생 정보가 없을 경우 404 Not Found 응답 반환
		if (student == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student);
	}

	// 2-3. POST: 학생 추가: 요청 본문에 포함된 학생 데이터를 서비스에 전달하여 새 학생을 생성하고, 생성한 학생 정보를 반환
	// URL: "/api/students"
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody Student student) {
		studentService.createStudent(student);
		return ResponseEntity.ok(student);
	}

	// 2-4. PUT: 학생 정보 수정 - 특정 ID를 가진 학생의 정보를 요청 본문에 포함된 학생 객체로 업데이트
	// URL: "/api/students/{id}"
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
		// 요청 객체에 ID를 명시적으로 설정한 후 서비스에 전달
		student.setId(id);
		studentService.updateStudent(student);
		return ResponseEntity.ok(student);
	}

	// 2-5. DELETE: 학생 삭제 - 특정 ID를 가진 학생을 삭제
	// URL: "/api/students/{id}"
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delte(@PathVariable Long id) {
		studentService.deleteStudent(id);
		// 성공적으로 삭제 시, OK 응답 메시지를 반환
		return ResponseEntity.ok().build();
	}
}

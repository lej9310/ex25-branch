package com.lej.ex25_branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lej.ex25_branch.domain.Student;
import com.lej.ex25_branch.mapper.StudentMapper;

import lombok.RequiredArgsConstructor;

// @Service: 서비스 레이어 >> 비즈니스 로직을 처리 >> 학생 관련 CRUD(Create, Read, Update, Delete) 동작을 처리하는 서비스
// @RequiredArgsConstructor: Lombok @, final 키워드로 선언된 필드를 초기화하는 생성자를 자동 생성
// @Transactional(readOnly = true) >> 이 트랜잭션이 읽기 전용 (Default: false)
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
	 
	// 1. MyBatis의 studentMapper 인터페이스를 주입 =================
	private final StudentMapper studentMapper;

	// 2. 읽기 메서드 =============================================
	public List<Student> getAllStudents() {
		return studentMapper.findAll();
	}

	public Student getStudent(Long id) {
		return studentMapper.findById(id);
	}

	// 3. 쓰기 메서드(readOnly = false 메서드) >> 데이터 수정/변경 =====================
	// 새로운 학생 정보 삽입
	@Transactional
	public void createStudent(Student student) {
		studentMapper.insert(student);
	}

	// 기존 학생 정보 업데이트
	@Transactional
	public void updateStudent(Student student) {
		studentMapper.update(student);
	}

	// ID로 특정 학생 정보 삭제
	@Transactional
	public void deleteStudent(Long id) {
		studentMapper.delete(id);
	}

}

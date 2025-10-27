package com.lej.ex25_branch.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lej.ex25_branch.domain.Student;
import com.lej.ex25_branch.mapper.StudentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

	private final StudentMapper studentMapper;

	public List<Student> getAllStudents() {
		return studentMapper.findAll();

		// DB연결전에 테스트 ===========================
		// List<Student> students = new ArrayList<>();
		// students.add(new Student() {{setId(1L); setName("홍길동");}});
		// students.add(new Student() {{setId(2L); setName("이몽룡");}});
		// students.add(new Student() {{setId(3L); setName("성춘향");}});
		// return students;
	}

	public Student getStudent(Long id) {
		return studentMapper.findById(id);
	}

	// readOnly가 아닌 수정 메서드 >> 롤백 =====================
	@Transactional
	public void createStudent(Student student) {
		studentMapper.insert(student);
	}

	@Transactional
	public void updateStudent(Student student) {
		studentMapper.update(student);
	}

	@Transactional
	public void deleteStudent(Long id) {
		studentMapper.delete(id);		
	}	
	
}

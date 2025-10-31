package com.lej.ex25_branch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lej.ex25_branch.domain.Student;

// MyBatis Mapper는 반드시 인터페이스로 작성!!!
// 클래스: 단일 상속만 가능 >> 한 클래스는 다른 하나의 클래스만 상속받음
// 인터페이스: 다중 상속 가능 >> 여러 인터페이스를 동시에 구현
@Mapper
public interface StudentMapper {

	// 1. 조회
	List<Student> findAll();

	// 2. 단건 조회
	Student findById(Long id);

	// 3.등록(삽입)
	void insert(Student student);

	// 4. 수정
	void update(Student student);

	// 5. 삭제
	void delete(Long id);

}
package com.lej.ex25_branch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lej.ex25_branch.domain.Student;

@Mapper
public class StudentMapper {

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
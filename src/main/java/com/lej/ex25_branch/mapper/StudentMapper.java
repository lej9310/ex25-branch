package com.lej.ex25_branch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lej.ex25_branch.domain.Student;

@Mapper
public interface StudentMapper {

	// 1. 전체 조회
	List<Student> findAll();

	// StudentMapper.xml의 쿼리 이동 ======================================
	// 2. 등록(삽입)
	@Select("SELECT * FROM student WHERE id = #{id}")
	Student findById(Long id);

	// 3. 삽입
	@Insert("INSERT INTO student(NAME, email, age) \r\n" + "  	VALUES (#{name}, #{email}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Student student);

	// 4. 수정
	@Update("UPDATE student\r\n" + "	SET NAME = #{name}, email = #{email}, age = #{age}\r\n" + "	WHERE id = #{id}")
	void update(Student student);

	// 5. 삭제
	@Delete("DELETE FROM student WHERE id = #{id}")
	void delete(Long id);

}

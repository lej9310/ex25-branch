package com.lej.ex25_branch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lej.ex25_branch.domain.Student;

// MyBatis Mapper는 반드시 인터페이스로 작성!!!
// 클래스: 단일 상속만 가능 >> 한 클래스는 다른 하나의 클래스만 상속받음
// 인터페이스: 다중 상속 가능 >> 여러 인터페이스를 동시에 구현

@Mapper
public interface StudentMapper {

	// ===============================================
	// XML 매퍼 파일 >> StudentMapper.xml 내 해당 메서드에 매핑된 SQL 쿼리 존재

	// 1. 전체 조회
	// 별도의 SQL 쿼리 정의(예: @Select 등)
	List<Student> findAll();

	// ===============================================
	// 어노테이션 방식 >> 별도의 SQL 쿼리 정의

	// 2. 단건 조회
	@Select("SELECT * FROM student WHERE id = #{id}")
	Student findById(Long id);

	// 3.등록(삽입)
	// @Options: (useGeneratedKeys=true: 자동 생성되는 기본키(id) 반환 (예: MySQL의
	// AUTO_INCREMENT))
	// (keyProperty="id": 반환되는 기본키 값을 매핑하여 Student 객체의 id 필드에 설정)
	@Insert("INSERT INTO student(NAME, email, age) VALUES\r\n" + "		(#{name}, #{email}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(Student student);

	// 4. 수정
	@Update("UPDATE student\r\n" + "	SET NAME = #{name}, email = #{email}, age = #{age}\r\n" + "	WHERE id = #{id}")
	void update(Student student);

	// 5. 삭제
	@Delete("DELETE FROM student WHERE id = #{id}")
	void delete(Long id);

}
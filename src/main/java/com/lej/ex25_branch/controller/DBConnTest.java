package com.lej.ex25_branch.controller;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController >> 반환값을 HTTP 응답 본문에 JSON/문자열로 반환
@RestController
public class DBConnTest {

	// 1. 의존성 주입
	@Autowired
	private DataSource dataSource;

	// 2. 엔드포인트 정의
	@GetMapping("/dbconn")
	public String dbconn() {
		
		// 3. 데이터베이스 연결 및 메타데이터 확인
		// 3-1. DB 연결(Connection)을 획득
		try (Connection conn = dataSource.getConnection()) {
			// 3-2. 현재 연결된 DB의 URL(주소)을 String 형식으로 반환
			String result = conn.getMetaData().getURL();
			return "DB연결 성공: " + result;
		} catch (Exception e) {
			e.printStackTrace();
			return "DB연결 실패";
		}
	}
}

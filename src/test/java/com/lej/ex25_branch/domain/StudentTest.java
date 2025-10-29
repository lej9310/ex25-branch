package com.lej.ex25_branch.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentTest {

	private Long id;

	private String name;

	private String email;

	private Integer age;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}

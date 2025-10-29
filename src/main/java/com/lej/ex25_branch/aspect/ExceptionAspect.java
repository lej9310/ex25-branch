package com.lej.ex25_branch.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// AOP 사용 >> 공통 기능을 별도의 모듈(Aspect)로 분리하여 관리 
// 1. Aspect: 여러 지점(join point)에서 실행될 공통적인 행동을 정의 - 로깅, 권한 검증 등
// 2. Join point: 비즈니스 로직이 실행되는 특정 지점 - 메소드 호출(AOP에서 주로), 필드 접근, 예외 발생 등
// 3. Pointcut: 어떤 조인포인트에서 Aspect가 동작할지를 결정하는 표현식 - 특정 패키지/클래스/메소드의 패턴을 지정
// 4. Advice: Aspect가 정의한 특정 조인포인트에서 수행될 실제 코드
// - Before: 조인포인트 실행 전에 실행
// - After: 조인포인트 실행 후(정상/예외 여부와 상관없음)에 실행
// - After-returning: 조인포인트가 정상적으로 반환된 후에 실행
// - After-throwing: 조인포인트에서 예외가 발생한 후에 실행
// - Around: 조인포인트 실행 전후 모두 제어 가능, 조인포인트 실행을 대리(proxy)

@Slf4j
@Aspect
@Component
public class ExceptionAspect {
	@AfterThrowing(pointcut = "execution(* com.lej.ex25_branch.service.*.*(..))", throwing = "excep")
	public void logError(JoinPoint jp, Exception excep) {
		log.info("##### 메서드중 오류\n 예외발생위치: {}, \n오류상황: {} #####end#####", jp.getSignature(), excep.getMessage());
	}
}

package com.shinhan.sbproject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// Advice : 공통 관심 사항 코드
@Slf4j
@Component
@Aspect // pointcut + advice(보조업무)
public class LoggingAdvice{
	
	// @Pointcut("execution(* selectAll(..))") // 모든 selectAll 함수
	@Pointcut("within(com.shinhan.sbproject.controllerfinal.WebBoardController)")
	public void targetMethod() {
		// logic은 없지만 함수 형태만 가능, 내용 안적어야 함
	}

	@Around("targetMethod()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {	// 함수 이름 달라져도 됨
		// 주 관심사 가기 전
		log.info("[메서드 호출 전 : LoggingAdvice] ");
		log.info(joinPoint.getSignature().getName() + "메서드 호출 전");
		
		// 주 관심사 가기, 주 관심사의 return을 object로 받음
		Object object = joinPoint.proceed();
		log.info("[LoggingAdvice] 주 관심사 return 내용 : " + object);
		
		// 주 관심사 다녀온 후
		log.info("[메서드 호출 후 : LoggingAdvice] ");
		log.info(joinPoint.getSignature().getName() + "메서드 호출 후");
		
		return object;
	}
	
	@Before("targetMethod()")
	public void before(JoinPoint joinpoint) {
		String method_name = joinpoint.getSignature().getName();
		
		log.info("Before 함수명 : " + method_name);
	}
	
	@AfterReturning("targetMethod()")
	public void afterReturn(JoinPoint joinpoint) {
		String method_name = joinpoint.getSignature().getName();
		
		log.info("AfterReturning 함수명 : " + method_name);
	}

}

package com.shinhan.sbproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class StopWatchAdvice{

	// @Pointcut("execution(* selectAll(..))")
	@Pointcut("within(com.shinhan.sbproject.controllerfinal.WebBoardController)")
	public void targetMethod() {}
	
	@Around("targetMethod()") // 주 업무 전 후로 수행되는 보조 업
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// 주 관심사 가기 전
		log.info("[메서드 호출 전 : StopWatchAdvice] ");
		log.info(joinPoint.getSignature().getName() + "메서드 호출 전");
		
		StopWatch watch = new StopWatch("수행시간");
		watch.start();
		// 주 관심사 가기, 주 관심사의 return을 object로 받음
		Object object = joinPoint.proceed();
		log.info("[StopWatchAdvice] 주 관심사 return 내용 : " + object);
		
		// 주 관심사 다녀온 후
		watch.stop();
		log.info("[메서드 호출 후 : StopWatchAdvice] ");
		log.info(joinPoint.getSignature().getName() + "메서드 호출 후");
		log.info("수행하는데 걸린 시간 : " + watch.getTotalTimeMillis());
		
		return object;
	}

}

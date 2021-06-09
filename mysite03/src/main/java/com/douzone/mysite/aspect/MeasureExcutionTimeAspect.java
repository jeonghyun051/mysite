package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExcutionTimeAspect {
	
	// repository 밑에 있는 모든 클래스의 모든 매서드에 이 어드바이스를 적용한다.
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		// 어떤 클래스가 실행됐는지 확인
		String className = pjp.getTarget().getClass().getName();
		
		// 어떤 매서드가 실행됐는지 확인
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time][" + taskName + "]" + totalTime + "millis");
		
		return result;
	}
}
package poc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectSample {
//	static final String POINTCUT_EXPRESSION = "execution(* hello())";
	static final String POINTCUT_EXPRESSION = "execution(* poc.basic.*.hello())";

	@Before(POINTCUT_EXPRESSION)
	public void before() {
		System.out.println("before !!");
	}

	@After(POINTCUT_EXPRESSION)
	public void after() {
		System.out.println("after !!");
	}

	@AfterReturning(value = POINTCUT_EXPRESSION, returning = "s")
	public void afterRetuning(String s) {
		System.out.println("after returning !!");
	}

	@Around(POINTCUT_EXPRESSION)
	public String around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("around before !!");
		String s = (String) pjp.proceed();
		System.out.println("around after !!");
		return s;
	}

	@AfterThrowing(value = POINTCUT_EXPRESSION, throwing = "ex")
	public void afterThrowing(Throwable ex) {
		System.out.println("after throwing !!");
	}
}
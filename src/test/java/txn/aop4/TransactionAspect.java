package txn.aop4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope("prototype")
@Scope("request")
@Aspect
@Component
public class TransactionAspect {
	private Logger L = LoggerFactory.getLogger(TransactionAspect.class);
//	static final String POINTCUT_EXPRESSION = "within(*..*ServiceImpl) && execution(* *(**))";
//	static final String POINTCUT_EXPRESSION = "@within(org.springframework.stereotype.Service)";
	static final String POINTCUT_EXPRESSION = "within(*..*Controller) && execution(* *(**))";

	private int calls = 0;

	public TransactionAspect() {
		L.info("★★★にゅー");
	}

	@Before(POINTCUT_EXPRESSION)
	public void before(JoinPoint jp) throws Throwable {
		L.info("■before! {}", ++calls);
	}

	@AfterReturning(value = POINTCUT_EXPRESSION, returning = "o")
	public void afterRetuning(JoinPoint jp, Object o) {
		// 正常終了時のみ呼び出される
		L.info(jp.getTarget().getClass().getName());
		L.info("■after! {}", --calls);
	}

	@AfterThrowing(value = POINTCUT_EXPRESSION, throwing = "ex")
	public void afterThrowing(Throwable ex) {
		// サブクラスが投げられても呼び出される
		L.info("■after throwing !!{} {}", ex.getClass(), --calls);
	}
}
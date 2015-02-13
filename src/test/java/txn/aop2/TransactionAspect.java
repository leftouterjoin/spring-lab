package txn.aop2;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TransactionAspect {
	private Logger L = LoggerFactory.getLogger(BarServiceTest1.class);
//	static final String POINTCUT_EXPRESSION = "within(*..*ServiceImpl) && execution(* *(**))";
	static final String POINTCUT_EXPRESSION = "@within(org.springframework.stereotype.Service)";

	@AfterReturning(value = POINTCUT_EXPRESSION, returning = "o")
	public void afterRetuning(Object o) {
		// 正常終了時のみ読み出される
		L.info("■after!");
	}

	@AfterThrowing(value = POINTCUT_EXPRESSION, throwing = "ex")
	public void afterThrowing(Throwable ex) {
		// サブクラスが投げられても呼び出される
		L.info("■after throwing !!{}", ex.getClass());
	}
}
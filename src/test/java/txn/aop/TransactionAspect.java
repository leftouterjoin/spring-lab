package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
	private Logger L = LoggerFactory.getLogger(FooServiceTest1.class);
	static final String POINTCUT_EXPRESSION = "within(*..*ServiceImpl) && (execution(* insert(**)) || execution(* update(**)) || execution(* select(**)))";

	@Autowired
	TransactionHandler th;

	@Around(POINTCUT_EXPRESSION)
	public Object around(final ProceedingJoinPoint pjp) throws Throwable {
		// @TransactionalしてPlatformTransactionManagerに制御を移譲したい場合はこっちが良い。
		// 独自に何かやる場合は@AfterReturning、@AfterThrowingで良い
		L.info("■{}", pjp.getSignature());
		return th.invoke(new TransactionHandler.Func() {
			@Override
			public Object invoke() throws Throwable {
				return pjp.proceed();
			}
		});
	}

	@After(POINTCUT_EXPRESSION)
	public void after() {
		// 例外が投げられても必ず呼び出される
		L.info("■after!");
	}

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

	@AfterThrowing(value = POINTCUT_EXPRESSION, throwing = "ex")
	public void afterThrowing(IllegalAccessException ex) {
		// 該当例外時のみ呼び出される
		L.info("■after throwing !!{}", ex.getClass());
	}

	@AfterThrowing(value = POINTCUT_EXPRESSION, throwing = "ex")
	public void afterThrowing(JdbcSQLException ex) {
		// 該当例外時のみ呼び出される
		L.info("■after throwing !!{}", ex.getClass());
	}
}
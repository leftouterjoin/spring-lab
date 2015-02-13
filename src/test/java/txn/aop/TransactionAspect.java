package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {
	private Logger L = LoggerFactory
			.getLogger(FooServiceTest1.class);
//	static final String POINTCUT_EXPRESSION = "execution(* FooService.insert(**))";
//	static final String POINTCUT_EXPRESSION = "within(txn.aop.*) && execution(* insert(**))";
//	static final String POINTCUT_EXPRESSION = "bean(*Service*)";
	static final String POINTCUT_EXPRESSION = "within(*..*ServiceImpl) && (execution(* insert(**)) || execution(* update(**)) || execution(* delete(**)))";

	@Around(POINTCUT_EXPRESSION)
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		L.info("■{}", pjp.getSignature());
//		Object o = pjp.proceed();
//		return o;
		return transactionalOperation.doOperation(pjp);
	}

	@Autowired
	TransactionalOperation transactionalOperation;

//	@Transactional
//	private Object transactionalOperation(ProceedingJoinPoint pjp) throws Throwable {
//		return pjp.proceed();
//	}
}
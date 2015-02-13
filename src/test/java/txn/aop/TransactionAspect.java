package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {
	private Logger L = LoggerFactory
			.getLogger(FooServiceTest1.class);
	static final String POINTCUT_EXPRESSION = "within(*..*ServiceImpl) && (execution(* insert(**)) || execution(* update(**)) || execution(* delete(**)))";

	@Around(POINTCUT_EXPRESSION)
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		L.info("■{}", pjp.getSignature());
		return transactionalOperation.doOperation(pjp);
	}

	@Autowired
	TransactionHandler transactionalOperation;
}
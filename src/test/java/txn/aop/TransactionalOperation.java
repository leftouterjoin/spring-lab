package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TransactionalOperation {
	Object doOperation(ProceedingJoinPoint pjp) throws Throwable;
}

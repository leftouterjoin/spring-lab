package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TransactionHandler {
	Object doOperation(ProceedingJoinPoint pjp) throws Throwable;
}

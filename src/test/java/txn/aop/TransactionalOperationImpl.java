package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalOperationImpl implements TransactionalOperation {

	@Transactional(rollbackFor = JdbcSQLException.class)//★
	@Override
	public Object doOperation(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}

}

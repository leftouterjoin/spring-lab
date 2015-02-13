package txn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionHandlerImpl implements TransactionHandler, AutoCloseable {
	private Logger L = LoggerFactory
			.getLogger(FooServiceTest1.class);

	@Transactional(rollbackFor = JdbcSQLException.class)//★
	@Override
	public Object doOperation(ProceedingJoinPoint pjp) throws Throwable {
		L.info("■{}", pjp.getSignature());
		return pjp.proceed();
	}

	@Override
	public void close() throws Exception {
		L.info("■closing");
	}
}

package txn.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class OakTransactionManager implements PlatformTransactionManager {
	private Logger L = LoggerFactory.getLogger(FooServiceTest1.class);

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition)
			throws TransactionException {
		return null;
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
		L.info(status.toString());
	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		L.info(status.toString());
	}

}

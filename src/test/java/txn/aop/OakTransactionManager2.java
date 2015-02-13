package txn.aop;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class OakTransactionManager2 extends DataSourceTransactionManager {
	private Logger L = LoggerFactory.getLogger(FooServiceTest1.class);

	public OakTransactionManager2(DataSource ds) {
		super(ds);
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);
	}
}

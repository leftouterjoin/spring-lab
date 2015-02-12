package txn.basic;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.jdbc.JdbcSQLException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Component
@Configuration
@ComponentScan
@EnableTransactionManagement
@Transactional(rollbackFor = JdbcSQLException.class)
public class H2TxnClassScopeTest {
	private Logger L = LoggerFactory.getLogger(H2TxnClassScopeTest.class);

	@Test
	public void testTxnCommit() throws Throwable {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				H2TxnClassScopeTest.class);
		H2TxnClassScopeTest me = context.getBean(H2TxnClassScopeTest.class);
		me.doInsert(new String[] { "create table t1(c1 int unique, c2 int)",
				"insert into t1 values(1, 10)", "insert into t1 values(2, 20)" });
		assertNotEquals(me.doSelect("select * from t1"), 0);
	}

	@Test
	public void testTxnRollback() throws Throwable {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				H2TxnClassScopeTest.class);
		H2TxnClassScopeTest me = context.getBean(H2TxnClassScopeTest.class);
		try {
			me.doInsert(new String[] {
					"create table t2(c1 int unique, c2 int)",
					"insert into t2 values(1, 10)",
					"insert into t2 values(1, 10)" });
		} catch (JdbcSQLException e) {
			// expected.
			L.info(e.getMessage());
		}
		assertEquals(me.doSelect("select * from t2"), 0);
	}

	public void doInsert(String[] sqls) throws Throwable {
		Connection c = dataSource.getConnection();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		for (String sql : sqls)
			s.executeUpdate(sql);
	}

	public int doSelect(String sql) throws Throwable {
		Connection c = dataSource.getConnection();
		System.out.println(c);
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql);
		int i = 0;
		while (rs.next()) {
			i++;
			L.info(String.format("★%d", rs.getInt(1)));
		}
		return i;
	}

	@Bean
	protected PlatformTransactionManager createTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Autowired
	DataSource dataSource;

	@Bean
	protected DataSource createTransactionAwareDataSourceProxy() {
		return new TransactionAwareDataSourceProxy(
				new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
						.setName("Nuts").build());
	}
}

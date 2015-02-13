package txn.aop;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.h2.jdbc.JdbcSQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FooServiceTest1.AppConfig.class })
public class FooServiceTest1 {
	private static Logger L = LoggerFactory
			.getLogger(FooServiceTest1.class);
	@BeforeClass
	public static void setup() {
		L.info("■hajime");
	}

	@Before
	public void setup2() {
		L.info("■hajime");
	}

	@After
	public void teardown() {
		L.info("■owari");
	}

	@AfterClass
	public static void teardown2() {
		L.info("■owari");
	}

	@Test
	public void testTxnCommit() throws Throwable {
		service.insert(new String[] {
				"create table t1(c1 int unique, c2 int)",
				"insert into t1 values(1, 10)", "insert into t1 values(2, 20)" });
		assertNotEquals(service.select("select * from t1"), 0);
	}

	@Test
	public void testTxnRollback() throws Throwable {
		try {
			service.insert(new String[] {
					"create table t2(c1 int unique, c2 int)",
					"insert into t2 values(1, 10)",
					"insert into t2 values(1, 10)" });
		} catch (JdbcSQLException e) {
			// expected.
			L.info(e.getMessage());
		}
		assertEquals(service.select("select * from t2"), 0);
	}

	@Autowired
	FooService service;

	@Component
	@Configuration
	@ComponentScan({ "txn.aop" })
	@EnableAspectJAutoProxy
	@EnableTransactionManagement
	public static class AppConfig {
		@Bean
		TransactionHandler createTransactionalOperation() {
			return new TransactionHandlerImpl();
		}

		@Bean
		FooService createHogeServiceClassScope() {
			return new FooServiceImpl();
		}

		@Bean
		PlatformTransactionManager createTransactionManager(DataSource ds) {
			return new DataSourceTransactionManager(ds);
		}

		@Bean
		DataSource createTransactionAwareDataSourceProxy() {
			return new TransactionAwareDataSourceProxy(
					new EmbeddedDatabaseBuilder()
							.setType(EmbeddedDatabaseType.H2).setName("Nuts")
							.build());
		}
	}
}

package txn.aop3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { A_ServiceTest1.AppConfig.class })
public class A_ServiceTest1 {
	private static Logger L = LoggerFactory.getLogger(A_ServiceTest1.class);

	@Test
	public void testTxnCommit() throws Throwable {
		service.serv1(false);
	}

	@Test
	public void testTxnRollback() throws Throwable {
		try {
			service.serv1(false);
		} catch (Throwable e) {
			// expected.
			L.info(e.getMessage());
		}
	}

	@Autowired
	A_Service service;

	@Component
	@Configuration
	@ComponentScan({ "txn.aop3" })
	@EnableAspectJAutoProxy
	public static class AppConfig {
	}
}

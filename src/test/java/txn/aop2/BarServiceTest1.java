package txn.aop2;

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
@ContextConfiguration(classes = { BarServiceTest1.AppConfig.class })
public class BarServiceTest1 {
	private static Logger L = LoggerFactory.getLogger(BarServiceTest1.class);

	@Test
	public void testTxnCommit() throws Throwable {
		service.foo(false);
		service.bar(false);
	}

	@Test
	public void testTxnRollback() throws Throwable {
		try {
			service.foo(false);
			service.bar(true);
		} catch (Throwable e) {
			// expected.
			L.info(e.getMessage());
		}
	}

	@Autowired
	BarService service;

	@Component
	@Configuration
	@ComponentScan({ "txn.aop2" })
	@EnableAspectJAutoProxy
	public static class AppConfig {
	}
}

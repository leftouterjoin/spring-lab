package txn.aop4;

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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
//@ContextConfiguration(classes = { D_ControllerTest1.AppConfig.class })
public class D_ControllerTest1 {
	private static Logger L = LoggerFactory.getLogger(D_ControllerTest1.class);

	@Test
	public void testTxnCommit() throws Throwable {
		controller.serv1(false);
	}

	@Test
	public void testTxnRollback() throws Throwable {
		try {
			controller.serv1(false);
		} catch (Throwable e) {
			// expected.
			L.info(e.getMessage());
		}
	}

	@Autowired
	D_Controller controller;

	@Component
	@Configuration
//	@ComponentScan({ "txn.aop4" })
	@ComponentScan
	@EnableAspectJAutoProxy
	@EnableWebMvc
	public static class AppConfig {
		public D_Controller createD_Controller() {
			return new D_Controller();
		}

		public D_Service createD_Service() {
			return new D_ServiceImpl();
		}
	}
}

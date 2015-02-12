package poc.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan
public class HelloTest {
	private Logger L = LoggerFactory.getLogger(HelloTest.class);

	@Autowired
	private Hello hello;

	@Test
	public void test1() throws Throwable {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				HelloTest.class);
		context.getBean(HelloTest.class).run();
	}

	private void run() {
		L.info("★{}", hello.hello());
	}
}

package poc.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan({ "poc.basic", "poc.aop" })
@EnableAspectJAutoProxy
public class AopHelloTest {
	private Logger L = LoggerFactory.getLogger(AopHelloTest.class);

	@Autowired
	private Hello hello;

	@Test
	public void test1() throws Throwable {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				AopHelloTest.class);
		context.getBean(AopHelloTest.class).run();
	}

	private void run() {
		L.info("★{}", hello.hello());
	}
}

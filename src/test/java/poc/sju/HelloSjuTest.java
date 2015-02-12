package poc.sju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poc.basic.Hello;
import poc.basic.HelloImpl;
import poc.basic.Person;
import poc.basic.PersonImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HelloSjuTest.AppConfig1.class })
public class HelloSjuTest {
	private Logger L = LoggerFactory.getLogger(HelloSjuTest.class);

	@Autowired
	private Hello hello;

	@Test
	public void test1() throws Throwable {
		run();
	}

	private void run() {
		L.info("★{}", hello.hello());
	}

	/*
	 * 自動instantiate
	 */
	@Configuration
	@ComponentScan("poc.basic")
	public static class AppConfig1 {
	}

	/*
	 * 手動instantiate
	 */
	@Configuration
	public static class AppConfig2 {
		@Bean
		public Hello a() {
			return new HelloImpl();
		}

		@Bean
		public Person b() {
			return new PersonImpl();
		}
	}

	/*
	 * 自動instantiate + AOP
	 */
	@Component
	@Configuration
	@ComponentScan({ "poc.basic", "poc.aop" })
	@EnableAspectJAutoProxy
	public static class AppConfig3 {
	}
}

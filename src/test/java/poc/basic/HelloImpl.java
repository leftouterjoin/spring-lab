package poc.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements Hello {

	@Autowired
	private Person person;

	@Override
	public String hello() {
		return "Hello " + person.getName();
	}

}
package poc.basic;

import org.springframework.stereotype.Component;

@Component
public class PersonImpl implements Person {

	@Override
	public String getName() {
		return "hoge";
	}

}
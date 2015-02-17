package txn.aop3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HogeSessionImple implements HogeSession {
	private Logger L = LoggerFactory.getLogger(HogeSessionImple.class);

	public int serciceCalls = 0;

	@Override
	public void save() {
		L.info("■");
	}

	@Override
	public void bar() {
		L.info("■");
	}
}

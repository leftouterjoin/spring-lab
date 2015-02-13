package txn.aop2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {
	private Logger L = LoggerFactory.getLogger(BarServiceImpl.class);

	@Override
	public void foo(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
		if (isFail) throw new RuntimeException("えらー");
	}

	@Override
	public void bar(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
		if (isFail) throw new RuntimeException("えらー");
	}
}

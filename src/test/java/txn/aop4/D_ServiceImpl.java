package txn.aop4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class D_ServiceImpl implements D_Service {
	private Logger L = LoggerFactory.getLogger(D_ServiceImpl.class);

	@Override
	public void serv1(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
		if (isFail) throw new RuntimeException("えらー");
	}
}

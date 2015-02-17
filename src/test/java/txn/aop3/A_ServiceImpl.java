package txn.aop3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A_ServiceImpl implements A_Service {
	private Logger L = LoggerFactory.getLogger(A_ServiceImpl.class);

//	@Autowired
//	HogeSession hogeSession;

	@Override
	public void serv1(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
//		hogeSession.bar();
		if (isFail) throw new RuntimeException("えらー");
	}
}

package txn.aop3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class C_ServiceImpl implements C_Service {
	private Logger L = LoggerFactory.getLogger(C_ServiceImpl.class);

//	@Qualifier("txn.aop3.C_ServiceImpl.NoTxnA_ServiceImpl")
	@Autowired
	A_Service service;

	@Override
	public void serv1(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
		service.serv1(isFail);
	}

//	@Component("txn.aop3.C_ServiceImpl.NoTxnA_ServiceImpl")
//	public static class NoTxnA_ServiceImpl extends A_ServiceImpl {}
}

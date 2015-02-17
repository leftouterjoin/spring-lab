package txn.aop4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoge")
public class D_Controller {
	private static Logger L = LoggerFactory.getLogger(D_Controller.class);

    @Autowired
    private D_Service service;

    public void serv1(boolean isFail) throws Throwable {
		L.info("isFail = {}", isFail);
		service.serv1(isFail);
	}
}

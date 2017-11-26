package miw.tc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PinCodeTC {
	private static final Logger logger = LoggerFactory.getLogger(PinCodeTC.class);

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private int waitHostSec = 5;
	private int waitPreTCSec = 1;

	private WSClient generatePinCodeWs;
	private WSClient queryPinCodeWs;
	private WSClient requestInfoWs;
	private WSClient cancelPinCodeWs;

	public int run_test(int num) {
		return jdbcTemplate.update("call miw_test_package.run_test(?) ", num);
	}
	public void delay(int sec) throws InterruptedException {
		logger.warn("Sleep " + sec + " s.");
		Thread.sleep(sec * 1000);
	}

	public PinCodeTC(DataSource dataSource) { // To have dependencies injected at construction time
		logger.warn("datasource= " + dataSource);
		generatePinCodeWs = new WSClient("http://localhost:8080/PinCodeGw/api/PortOut", "misc/PinCode/PortOutServiceServiceSoapBinding/generatePinCode");
		queryPinCodeWs = new WSClient("http://localhost:8080/PinCodeGw/api/PortOut", "misc/PinCode/PortOutServiceServiceSoapBinding/queryPinCode");
		requestInfoWs = new WSClient("http://localhost:8080/PinCodeGw/api/PortOut", "misc/PinCode/PortOutServiceServiceSoapBinding/requestInfo");
		cancelPinCodeWs = new WSClient("http://localhost:8080/PinCodeGw/api/PortOut", "misc/PinCode/PortOutServiceServiceSoapBinding/cancelPinCode");
	}

	public void run() throws Exception {
		logger.warn("End");
	}
	public void om() throws Exception {
		logger.warn("1.x om");
		generatePinCodeWs.send("1.x om.xml");
	}
	public void individual() throws Exception {
		logger.warn("individual");
		generatePinCodeWs.send("individual.xml");
	}
	public void individualSms() throws Exception {
		logger.warn("individualSms");
		generatePinCodeWs.send("individualSms.xml");
	}
	public void organization() throws Exception {
		logger.warn("organization"); // alot!
		generatePinCodeWs.send("organization.xml");
	}
	public void organizationEmail() throws Exception {
		logger.warn("organizationEmail");
		generatePinCodeWs.send("organizationEmail.xml");
	}
	public void organizationNoEmail() throws Exception {
		logger.warn("organizationNoEmail");
		generatePinCodeWs.send("organizationNoEmail.xml");
	}

	// query
	public void CopyofRequest1() throws Exception {
		logger.warn("Copy of Request 1.xml");
		queryPinCodeWs.send("Copy of Request 1.xml");
	}
	public void Request1() throws Exception {
		logger.warn("Request 1");
		queryPinCodeWs.send("Request 1.xml");
	}

	// requestInfo
	public void requestInfo() throws Exception {
		logger.warn("requestInfo");
		requestInfoWs.send("Request 1.xml");
	}

	// cancelPinCode
	public void individualEmail() throws Exception {
		logger.warn("individualEmail");
		cancelPinCodeWs.send("individualEmail.xml");
	}

	public void c_individualSms() throws Exception {
		logger.warn("individualSms");
		cancelPinCodeWs.send("individualSms.xml");
	}

	public void c_organizationEmail() throws Exception {
		logger.warn("organizationEmail");
		cancelPinCodeWs.send("organizationEmail.xml");
	}

}

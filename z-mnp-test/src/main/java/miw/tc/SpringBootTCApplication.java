package miw.tc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringBootTCApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SpringBootTCApplication.class);
	WSClient clhWs;
	WSClient intClhWs;
	@Autowired
	DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootTCApplication.class, args);
	}
	public int run_test(int num) {
		return jdbcTemplate.update("call miw_test_package.run_test(?) ", num);
	}
	public void delay(int sec) throws InterruptedException {
		// int sec = 1;
		logger.warn("Sleep " + sec + " s.");
		Thread.sleep(sec * 1000);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.warn("DATASOURCE = " + dataSource);
		clhWs = new WSClient("http://localhost:8080/ClhWs/services/NPCWebService");
		intClhWs = new WSClient("http://localhost:8080/IntClhWs/services/NPCWebService");
		run();
		logger.warn("Done");
	}

	public void run() throws Exception {
		// tc1();// OM Port Req (External)
		// tc2();// OM Port Req (Internal)
		// tc3();// OM Port Req (External Online)
		// tc4(); // OM Port Req (Internal Online)

		// tc5(); // Port Req Ack (1002)with Success (EXT)
		// tc6(); // Port Req Ack (1002)with Fail (EXT)
		// tc7(); // Port Req Ack (1002)with Success (INT)
		// tc8(); // Port Req Ack (1002)with Fail (INT)

		// tc9(); // Port Req (1003) (EXT)
		// tc10(); // Port Req (1003) (INT)
		// tc11(); // Port Response (1004)with success (EXT)
		// tc12(); // Port Response (1004)with Failed (EXT)
		// tc13(); // Port Response (1004)with success (INT)
		// tc14(); // Port Response (1004)with Failed (INT)
		// tc15(); // Port Response (1005)with success (EXT)
		// tc16(); // Port Response (1005)with success (INT)

		// tc17(); // Port Notification (1006) with success (EXT)
		// tc18(); //Port Notification (1006) with failed (EXT)
//		 tc19(); //Port Notification (1006) with success (INT)
//		 tc20(); //Port Notification (1006) with failed (INT)
		 tc21(); //Port Notification (1007) with success (EXT)
		// tc22(); //Port Notification (1007) with failed (EXT)
		// tc23(); //Port Notification (1007) with success (INT)
		// tc24(); //Port Notification (1007) with failed (INT)
		// tc25(); //Port Deactivate (1008) with success (EXT)
		// tc26(); //Port Deactivate (1008) with failed (EXT)
		// tc27(); //Port Deactivate (1008) with success (INT)
		// tc28(); //Port Deactivate (1008) with failed (INT)
		// tc29(); //Port Deactivate (1009) with success (EXT)
		// tc30(); //Port Deactivate (1009) with Failed (EXT)
		// tc31(); //Port Deactivate (1009) with success (INT)
		// tc32(); //Port Deactivate (1009) with Failed (INT)

	}

	void tc1() {
		logger.warn("OM Port Req (External)");
		run_test(1);
	}
	void tc2() {
		logger.warn("OM Port Req (Internal)");
		run_test(2);
	}
	void tc3() {
		logger.warn("OM Port Req (External Online)");
		run_test(3);
	}
	void tc4() {
		logger.warn("OM Port Req (Internal Online)");
		run_test(4);
	}

	void tc5() throws Exception {
		logger.warn("Port Req Ack (1002)with Success (EXT)");
		tc1();
		delay(1);
		clhWs.send("MIW_OM_1002.xml");
	}
	void tc6() throws Exception {
		logger.warn("Port Req Ack (1002)with Fail (EXT)");
		tc1();
		delay(1);
		clhWs.send("MIW_OM_1002_FAIL.xml");
	}
	void tc7() throws Exception {
		logger.warn("Port Req Ack (1002)with Success (INT)");
		tc2();
		delay(1);
		intClhWs.send("MIW_OM_1002_INT.xml");
	}
	void tc8() throws Exception {
		logger.warn("Port Req Ack (1002)with Fail (INT)");
		tc2();
		delay(1);
		intClhWs.send("MIW_OM_1002_INT_FAIL.xml");
	}

	void tc9() throws Exception {
		logger.warn("Port Req (1003) (EXT)");
		run_test(9);
		delay(1);
		clhWs.send("MIW_OM_1003.xml");
	}
	void tc10() throws Exception {
		logger.warn("Port Req (1003) (INT)");
		run_test(10);
		delay(1);
		intClhWs.send("MIW_OM_1003_INT.xml");
	}
	void tc11() throws Exception {
		logger.warn("Port Response (1004)with success (EXT)");
		tc9();
		delay(5); // wait host process
		run_test(11);
	}
	void tc12() throws Exception {
		logger.warn("Port Response (1004)with Failed (EXT)");
		tc9();
		delay(5); // wait host process
		run_test(12);
	}
	void tc13() throws Exception {
		logger.warn("Port Response (1004)with success (INT)");
		tc10();
		delay(5); // wait host process
		run_test(13);
	}
	void tc14() throws Exception {
		logger.warn("Port Response (1004)with Failed (INT)");
		tc10();
		delay(5); // wait host process
		run_test(14);
	}
	void tc15() throws Exception {
		logger.warn("Port Response (1005)with success (EXT)");
		tc9();
		delay(5); // wait host process
		run_test(15);
	}
	void tc16() throws Exception {
		logger.warn("Port Response (1005)with success (INT)");
		tc10();
		delay(5); // wait host process
		run_test(16);
	}
	void tc17() throws Exception {
		logger.warn("Port Notification (1006) with success (EXT)");
		tc5();
		delay(1);
		clhWs.send("MIW_OM_1006.xml");
	}
	void tc18() throws Exception {
		logger.warn("Port Notification (1006) with failed (EXT)");
		tc5();
		delay(1);
		clhWs.send("MIW_OM_1006_REJ.xml");
	}
	void tc19() throws Exception {
		logger.warn("Port Notification (1006) with success (INT)");
		tc7();
		delay(1);
		intClhWs.send("MIW_OM_1006_INT.xml");
	}
	void tc20() throws Exception {
		logger.warn("Port Notification (1006) with failed (INT)");
		tc7();
		delay(1);
		intClhWs.send("MIW_OM_1006_INT_REJ.xml");
	}
	void tc21() throws Exception {
		logger.warn("Port Notification (1007) with success (EXT)");
		tc11();
		delay(1);
		clhWs.send("MIW_OM_1007.xml");
	}

}
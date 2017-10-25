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
		logger.warn("SimpleThreadScope");
	}

	public void run() throws Exception {
		// tc1("OM Port Req (External)");
		// tc2("OM Port Req (Internal)");
		// tc3("OM Port Req (External Online)");
		// tc4("OM Port Req (Internal Online)");
		// tc5("Port Req Ack (1002)with Success (EXT)");
		// tc6("Port Req Ack (1002)with Fail (EXT)");
		// tc7("Port Req Ack (1002)with Success (INT)");
		// tc8("Port Req Ack (1002)with Fail (INT)");
		// tc9("Port Req (1003) (EXT)");
		// tc10("Port Req (1003) (INT)");
		// tc11("Port Response (1004)with success (EXT)");
		// tc12("Port Response (1004)with Failed (EXT)");
		// tc13("Port Response (1004)with success (INT)");
		// tc14("Port Response (1004)with Failed (INT)");
		// tc15("Port Response (1005)with success (EXT)");
		// tc16("Port Response (1005)with success (INT)");
		// tc17("Port Notification (1006) with success (EXT)");
		// tc18("Port Notification (1006) with failed (EXT)");
		// tc19("Port Notification (1006) with success (INT)");
		// tc20("Port Notification (1006) with failed (INT)");
		// tc21("Port Notification (1007) with success (EXT)");
		// tc22("Port Notification (1007) with failed (EXT)"); // FIXME: cant see reject msg
		// tc23("Port Notification (1007) with success (INT)"); // FIXME: fail
		// tc24("Port Notification (1007) with failed (INT)"); // FIXME: skip
		tc25("Port Deactivate (1008) with success (EXT)");// FIXME:  No msg 1008
		// tc26("Port Deactivate (1008) with failed (EXT)");// // FIXME: runtest() passive
		// tc27("Port Deactivate (1008) with success (INT)"); // FIXME: No msg 1008
		// tc28("Port Deactivate (1008) with failed (INT)");// FIXME: fail
		// tc29("Port Deactivate (1009) with success (EXT)"); //FIXME: verify input + Data +เรียนทีมงาน MNP
		// tc30("Port Deactivate (1009) with Failed (EXT)"); //FIXME: verify input + Data +soap file
		// tc31("Port Deactivate (1009) with success (INT)"); //FIXME: verify input + Data +soap file
		// tc32("Port Deactivate (1009) with Failed (INT)"); //FIXME: verify input + Data +soap file

	}

	void tc1(String msg) {
		logger.warn(msg);
		run_test(1);
	}
	void tc2(String msg) {
		logger.warn(msg);
		run_test(2);
	}
	void tc3(String msg) {
		logger.warn(msg);
		run_test(3);
	}
	void tc4(String msg) {
		logger.warn(msg);
		run_test(4);
	}

	void tc5(String msg) throws Exception {
		logger.warn(msg);
		tc1("Pre...");
		delay(1);
		clhWs.send("MIW_OM_1002.xml");
	}
	void tc6(String msg) throws Exception {
		logger.warn(msg);
		tc1("Pre...");
		delay(1);
		clhWs.send("MIW_OM_1002_FAIL.xml");
	}
	void tc7(String msg) throws Exception {
		logger.warn(msg);
		tc2("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1002_INT.xml");
	}
	void tc8(String msg) throws Exception {
		logger.warn(msg);
		tc2("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1002_INT_FAIL.xml");
	}

	void tc9(String msg) throws Exception {
		logger.warn(msg);
		run_test(9);
		delay(1);
		clhWs.send("MIW_OM_1003.xml");
	}
	void tc10(String msg) throws Exception {
		logger.warn(msg);
		run_test(10);
		delay(1);
		intClhWs.send("MIW_OM_1003_INT.xml");
	}
	void tc11(String msg) throws Exception {
		logger.warn(msg);
		tc9("Pre...");
		delay(5); // wait host process
		run_test(11);
	}
	void tc12(String msg) throws Exception {
		logger.warn(msg);
		tc9("Pre...");
		delay(5); // wait host process
		run_test(12);
	}
	void tc13(String msg) throws Exception {
		logger.warn(msg);
		tc10("Pre...");
		delay(5); // wait host process
		run_test(13);
	}
	void tc14(String msg) throws Exception {
		logger.warn(msg);
		tc10("Pre...");
		delay(5); // wait host process
		run_test(14);
	}
	void tc15(String msg) throws Exception {
		logger.warn(msg);
		tc9("Pre...");
		delay(5); // wait host process
		run_test(15);
	}
	void tc16(String msg) throws Exception {
		logger.warn(msg);
		tc10("Pre...");
		delay(5); // wait host process
		run_test(16);
	}
	void tc17(String msg) throws Exception {
		logger.warn(msg);
		tc5("Pre...");
		delay(5);
		clhWs.send("MIW_OM_1006.xml");
	}
	void tc18(String msg) throws Exception {
		logger.warn(msg);
		tc5("Pre...");
		delay(5);
		clhWs.send("MIW_OM_1006_REJ.xml");
	}
	void tc19(String msg) throws Exception {
		logger.warn(msg);
		tc7("Pre...");
		delay(5);
		intClhWs.send("MIW_OM_1006_INT.xml");
	}
	void tc20(String msg) throws Exception {
		logger.warn(msg);
		tc7("Pre...");
		delay(5);
		intClhWs.send("MIW_OM_1006_INT_REJ.xml");
	}
	void tc21(String msg) throws Exception {
		logger.warn(msg);
		tc11("Pre...");
		delay(1);  // ?
		clhWs.send("MIW_OM_1007.xml");
	}
	void tc22(String msg) throws Exception {
		logger.warn(msg);
		tc11("Pre...");
		delay(1);
		clhWs.send("MIW_OM_1007_REJ.xml");
	}
	void tc23(String msg) throws Exception {
		logger.warn(msg);
		tc13("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1007_INT.xml");
	}
	void tc24(String msg) throws Exception {
		logger.warn(msg);
		tc13("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1007_INT_REJ.xml");
	}
	void tc25(String msg) throws Exception {
		logger.warn(msg);
		tc21("Pre...");
		delay(1);
		run_test(17);
	}
	void tc26(String msg) throws Exception {
		logger.warn(msg);
		tc21("Pre...");
		delay(1);
		run_test(18);
	}
	void tc27(String msg) throws Exception {
		logger.warn(msg);
		tc23("Pre...");
		delay(1);
		run_test(19);
	}
	void tc28(String msg) throws Exception {
		logger.warn(msg);
		tc23("Pre...");
		delay(1);
		run_test(20);
	}
	void tc29(String msg) throws Exception {
		logger.warn(msg);
		tc21("Pre...");
		delay(1);
		clhWs.send("MIW_OM_1009.xml");
	}
	void tc30(String msg) throws Exception {
		logger.warn(msg);
		tc21("Pre...");
		delay(1);
		clhWs.send("MIW_OM_1009_REJ.xml");
	}
	void tc31(String msg) throws Exception {
		logger.warn(msg);
		tc23("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1009_INT.xml");
	}
	void tc32(String msg) throws Exception {
		logger.warn(msg);
		tc23("Pre...");
		delay(1);
		intClhWs.send("MIW_OM_1009_INT_REJ.xml");
	}

}
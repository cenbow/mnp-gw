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
public class GwTC {
	private static final Logger logger = LoggerFactory.getLogger(GwTC.class);

	@Autowired
	DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private int waitHostSec = 5;
	private int waitPreTCSec = 1;

	WSClient clhWs;
	WSClient clhMvnoWs;
	WSClient intClhWs;
	WSClient mvnoWs;

	public int run_test(int num) {
		return jdbcTemplate.update("call miw_test_package.run_test(?) ", num);
	}
	public void delay(int sec) throws InterruptedException {
		logger.warn("Sleep " + sec + " s.");
		Thread.sleep(sec * 1000);
	}

	public GwTC(DataSource dataSource) { // To have dependencies injected at construction time
		logger.warn("datasource= " + dataSource);
		clhWs = new WSClient("http://localhost:8080/ClhWs/services/NPCWebService", "misc/ClhWsNPCWebServiceDr/NPCWebServiceSoap12Binding/processNPCMsg");
		intClhWs = new WSClient("http://localhost:8080/IntClhWs/services/NPCWebService", "misc/ClhWsNPCWebServiceDr/NPCWebServiceSoap12Binding/processNPCMsg");
		clhMvnoWs = new WSClient("http://localhost:8080/MvnoWs/services/NPCWebService", "misc/ClhWsNPCWebService_External/NPCWebServiceSoap12Binding/processNPCMsg");
		mvnoWs = new WSClient("http://localhost:8080/MvnoWs/services/NPCWebService", "misc/MvnoWsNPCWebService/NPCWebServiceSoap12Binding/processNPCMsg");
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
		// tc18(); // Port Notification (1006) with failed (EXT)
		// tc19(); // Port Notification (1006) with success (INT) // XXX:: now orderId not found
		// tc20(); // Port Notification (1006) with failed (INT)
		// tc21(); // Port Notification (1007) with success (EXT)
		// tc22(); // Port Notification (1007) with failed (EXT) // ok normally no msg info
		// tc23(); // Port Notification (1007) with success (INT)
		// tc24(); // Port Notification (1007) with failed (INT)
		// tc25(); // Port Deactivate (1008) with success (EXT)// XXX:: Ignore trigger error
		// tc26(); // Port Deactivate (1008) with failed (EXT)// XXX:: no msg to merge is OK?
		// tc27(); // Port Deactivate (1008) with success (INT) // XXX:: (No msg to merge run_test(19) push ผิด)
		// tc28(); // Port Deactivate (1008) with failed (INT)// XXX:: Ignore trigger error
		// tc29(); // Port Deactivate (1009) with success (EXT) // XXX:: Ignore trigger error
		// tc30(); // Port Deactivate (1009) with Failed (EXT)
		// tc31(); // Port Deactivate (1009) with success (INT)// XXX:: Ignore trigger error
		// tc32(); // Port Deactivate (1009) with Failed (INT) // XXX:: No soapMsg defined yet
		// tc33(); //Port BroadCast EXT(1010)
		// tc34(); //Port Notification Exception (1011)
		// tc35(); //Port Notification Exception (1012)
		// tc36(); //Port Notification Exception (1011) Internal
		// tc37(); //Port Notification Exception (1012) Internal

		logger.warn("End");
	}
	public void tc1() throws Exception {
		logger.warn("OM Port Req (External)");
		run_test(1);
	}
	public void tc2() throws Exception {
		logger.warn("OM Port Req (Internal)");
		run_test(2);
	}
	public void tc3() throws Exception {
		logger.warn("OM Port Req (External Online)");
		run_test(3);
	}
	public void tc4() throws Exception {
		logger.warn("OM Port Req (Internal Online)");
		run_test(4);
	}

	public void tc5() throws Exception {
		logger.warn("Port Req Ack (1002)with Success (EXT)");
		tc1();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1002.xml");
	}
	public void tc6() throws Exception {
		logger.warn("Port Req Ack (1002)with Fail (EXT)");
		tc1();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1002_FAIL.xml");
	}
	public void tc7() throws Exception {
		logger.warn("Port Req Ack (1002)with Success (INT)");
		tc2();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1002_INT.xml");
	}
	public void tc8() throws Exception {
		logger.warn("Port Req Ack (1002)with Fail (INT)");
		tc2();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1002_INT_FAIL.xml");
	}

	public void tc9() throws Exception {
		logger.warn("Port Req (1003) (EXT)");
		run_test(9);
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1003.xml");
	}
	public void tc10() throws Exception {
		logger.warn("Port Req (1003) (INT)");
		run_test(10);
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1003_INT.xml");
	}
	public void tc11() throws Exception {
		logger.warn("Port Response (1004)with success (EXT)");
		tc9();
		delay(waitHostSec); // wait host process
		run_test(11);
	}
	public void tc12() throws Exception {
		logger.warn("Port Response (1004)with Failed (EXT)");
		tc9();
		delay(waitHostSec); // wait host process
		run_test(12);
	}
	public void tc13() throws Exception {
		logger.warn("Port Response (1004)with success (INT)");
		tc10();
		delay(waitHostSec); // wait host process
		run_test(13);
	}
	public void tc14() throws Exception {
		logger.warn("Port Response (1004)with Failed (INT)");
		tc10();
		delay(waitHostSec); // wait host process
		run_test(14);
	}
	public void tc15() throws Exception {
		logger.warn("Port Response (1005)with success (EXT)");
		tc9();
		delay(waitHostSec); // wait host process
		run_test(15);
	}
	public void tc16() throws Exception {
		logger.warn("Port Response (1005)with success (INT)");
		tc10();
		delay(waitHostSec); // wait host process
		run_test(16);
	}
	public void tc17() throws Exception {
		logger.warn("Port Notification (1006) with success (EXT)");
		tc5();
		delay(waitHostSec);
		clhWs.send("MIW_OM_1006.xml");
	}
	public void tc18() throws Exception {
		logger.warn("Port Notification (1006) with failed (EXT)");
		tc5();
		delay(waitHostSec);
		clhWs.send("MIW_OM_1006_REJ.xml");
	}
	public void tc19() throws Exception {
		logger.warn("Port Notification (1006) with success (INT)");
		tc7();
		delay(waitHostSec);
		intClhWs.send("MIW_OM_1006_INT.xml");
	}
	public void tc20() throws Exception {
		logger.warn("Port Notification (1006) with failed (INT)");
		tc7();
		delay(waitHostSec);
		intClhWs.send("MIW_OM_1006_INT_REJ.xml");
	}
	public void tc21() throws Exception {
		logger.warn("Port Notification (1007) with success (EXT)");
		tc11();
		delay(waitHostSec); // ?
		clhWs.send("MIW_OM_1007.xml");
	}
	public void tc22() throws Exception {
		logger.warn("Port Notification (1007) with failed (EXT)");
		tc11();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1007_REJ.xml");
	}
	public void tc23() throws Exception {
		logger.warn("Port Notification (1007) with success (INT)");
		tc13();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1007_INT.xml");
	}
	public void tc24() throws Exception {
		logger.warn("Port Notification (1007) with failed (INT)");
		tc13();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1007_INT_REJ.xml");
	}
	public void tc25() throws Exception {
		logger.warn("Port Deactivate (1008) with success (EXT)");
		tc21();
		delay(waitPreTCSec);
		run_test(17);
	}
	public void tc26() throws Exception {
		logger.warn("Port Deactivate (1008) with failed (EXT)");
		tc21();
		delay(waitPreTCSec);
		run_test(18);
	}
	public void tc27() throws Exception {
		logger.warn("Port Deactivate (1008) with success (INT)");
		tc23();
		delay(waitPreTCSec);
		run_test(19);
	}
	public void tc28() throws Exception {
		logger.warn("Port Deactivate (1008) with failed (INT)");
		tc23();
		delay(waitPreTCSec);
		run_test(20);
	}
	public void tc29() throws Exception {
		logger.warn("Port Deactivate (1009) with success (EXT)");
		tc17();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1009.xml");
	}
	public void tc30() throws Exception {
		logger.warn("Port Deactivate (1009) with Failed (EXT)");
		tc18();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1009_REJ.xml");
	}
	public void tc31() throws Exception {
		logger.warn("Port Deactivate (1009) with success (INT)");
		tc19();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1009_INT.xml");
	}
	public void tc32() throws Exception {
		logger.warn("Port Deactivate (1009) with Failed (INT)");
		tc20();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1009_INT_REJ.xml");
	}

	public void tc33() throws Exception {
		logger.warn("Port BroadCast EXT(1010)");
		clhWs.send("MIW_OM_1010.xml");
	}
	public void tc34() throws Exception {
		logger.warn("Port Notification Exception (1011)");
		tc17();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1011.xml");
	}
	public void tc35() throws Exception {
		logger.warn("Port Notification Exception (1012)");
		tc21();
		delay(waitPreTCSec);
		clhWs.send("MIW_OM_1012.xml");
	}
	public void tc36() throws Exception {
		logger.warn("");
		tc19();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1011_INT.xml");
	}
	public void tc37() throws Exception {
		logger.warn("");
		tc19();
		delay(waitPreTCSec);
		intClhWs.send("MIW_OM_1012_INT.xml");
	}
	public void reloadExternal() throws Exception {
		logger.warn("Reload Mvno External)");
		clhMvnoWs.send("rmv001 - 1001.xml");
		delay(waitHostSec);
		run_test(21);
	}

	public void activateCatOmPortSync4001() throws Exception {
		logger.warn("activateCatOmPortSync: 4001)");
		run_test(2000);
	}

	public void activateMvnoPortSync4001() throws Exception {
		logger.warn("activateMvnoPortSync: 4001)");
		mvnoWs.send("MIW-rmv-4001.xml");
	}

	public void portSync4002() throws Exception {
		logger.warn("portSync4002)");
		clhWs.send("MIW_4002.xml");
	}

	public void portRev2002() throws Exception {
		logger.warn("portRev2002)");
		run_test(3000);
	}
	public void portRevDonor2001() throws Exception {
		logger.warn("portRevDonor2001)");
		run_test(3001);
	}


}

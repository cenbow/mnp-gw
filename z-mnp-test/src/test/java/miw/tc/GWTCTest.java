package miw.tc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import miw.junit.MiwTestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MiwTestApplication.class)
public class GWTCTest {
	@Autowired
	GwTC t;

	@Test
	public void tc1() throws Exception { // OM Port Req (External)
		t.tc1();
	}
	@Test
	public void tc2() throws Exception { // OM Port Req (Internal)
		t.tc2();
	}
	@Test
	public void tc3() throws Exception { // OM Port Req (External Online)
		t.tc3();
	}
	@Test
	public void tc4() throws Exception { // OM Port Req (Internal Online)
		t.tc4();
	}
	@Test
	public void tc5() throws Exception { // Port Req Ack (1002)with Success (EXT)
		t.tc5();
	}
	@Test
	public void tc6() throws Exception { // Port Req Ack (1002)with Fail (EXT)
		t.tc6();
	}
	@Test
	public void tc7() throws Exception { // Port Req Ack (1002)with Success (INT)
		t.tc7();
	}
	@Test
	public void tc8() throws Exception { // Port Req Ack (1002)with Fail (INT)
		t.tc8();
	}
	@Test
	public void tc9() throws Exception { // Port Req (1003) (EXT)
		t.tc9();
	}
	@Test
	public void tc10() throws Exception { // Port Req (1003) (INT)
		t.tc10();
	}
	@Test
	public void tc11() throws Exception { // Port Response (1004)with success (EXT)
		t.tc11();
	}
	@Test
	public void tc12() throws Exception { // Port Response (1004)with Failed (EXT)
		t.tc12();
	}
	@Test
	public void tc13() throws Exception { // Port Response (1004)with success (INT)
		t.tc13();
	}
	@Test
	public void tc14() throws Exception { // Port Response (1004)with Failed (INT)
		t.tc14();
	}
	@Test
	public void tc15() throws Exception { // Port Response (1005)with success (EXT)
		t.tc15();
	}
	@Test
	public void tc16() throws Exception { // Port Response (1005)with success (INT)
		t.tc16();
	}
	@Test
	public void tc17() throws Exception { // Port Notification (1006) with success (EXT)
		t.tc17();
	}
	@Test
	public void tc18() throws Exception { // Port Notification (1006) with failed (EXT)
		t.tc18();
	}
	@Test
	public void tc19() throws Exception { // Port Notification (1006) with success (INT)
		t.tc19();
	}
	@Test
	public void tc20() throws Exception { // Port Notification (1006) with failed (INT)
		t.tc20();
	}
	@Test
	public void tc21() throws Exception { // Port Notification (1007) with success (EXT)
		t.tc21();
	}
	@Test
	public void tc22() throws Exception { // Port Notification (1007) with failed (EXT) // ok normally no msg info
		t.tc22();
	}
	@Test
	public void tc23() throws Exception { // Port Notification (1007) with success (INT)
		t.tc23();
	}
	@Test
	public void tc24() throws Exception { // Port Notification (1007) with failed (INT)
		t.tc24();
	}
	@Test
	public void tc25() throws Exception { // Port Deactivate (1008) with success (EXT)
		t.tc25();
	}
	@Test
	public void tc26() throws Exception { // Port Deactivate (1008) with failed (EXT)
		t.tc26();
	}
	@Test
	public void tc27() throws Exception { // Port Deactivate (1008) with success (INT)
		t.tc27();
	}
	@Test
	public void tc28() throws Exception { // Port Deactivate (1008) with failed (INT)
		t.tc28();
	}
	@Test
	public void tc29() throws Exception { // Port Deactivate (1009) with success (EXT)
		t.tc29();
	}
	@Test
	public void tc30() throws Exception { // Port Deactivate (1009) with Failed (EXT)
		t.tc30();
	}
	@Test
	public void tc31() throws Exception { // Port Deactivate (1009) with success (INT)
		t.tc31();
	}
	@Test
	public void tc32() throws Exception { // Port Deactivate (1009) with Failed (INT)
		t.tc32();
	}
	@Test
	public void tc33() throws Exception { // Port BroadCast EXT(1010)
		t.tc33();
	}
	@Test
	public void tc34() throws Exception { // Port Notification Exception (1011)
		t.tc34();
	}
	@Test
	public void tc35() throws Exception { // Port Notification Exception (1012)
		t.tc35();
	}
	@Test
	public void tc36() throws Exception { // Port Notification Exception (1011) Internal
		t.tc36();
	}
	@Test
	public void tc37() throws Exception { // Port Notification Exception (1012) Internal
		t.tc37();
	}
	@Test
	public void testReloadExternal() throws Exception {
		t.reloadExternal();
	}
	@Test
	public void testActivateOMPortSync4001() throws Exception {
		t.activateOMPortSync4001();
	}

}

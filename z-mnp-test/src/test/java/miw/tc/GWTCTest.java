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
	GwTC tc;

	@Test
	public void tc1() throws Exception { // OM Port Req (External)
		tc.tc1();
	}
	@Test
	public void tc2() throws Exception { // OM Port Req (Internal)
		tc.tc2();
	}
	@Test
	public void tc3() throws Exception { // OM Port Req (External Online)
		tc.tc3();
	}
	@Test
	public void tc4() throws Exception { // OM Port Req (Internal Online)
		tc.tc4();
	}
	@Test
	public void tc5() throws Exception { // Port Req Ack (1002)with Success (EXT)
		tc.tc5();
	}
	@Test
	public void tc6() throws Exception { // Port Req Ack (1002)with Fail (EXT)
		tc.tc6();
	}
	@Test
	public void tc7() throws Exception { // Port Req Ack (1002)with Success (INT)
		tc.tc7();
	}
	@Test
	public void tc8() throws Exception { // Port Req Ack (1002)with Fail (INT)
		tc.tc8();
	}
	@Test
	public void tc9() throws Exception { // Port Req (1003) (EXT)
		tc.tc9();
	}
	@Test
	public void tc10() throws Exception { // Port Req (1003) (INT)
		tc.tc10();
	}
	@Test
	public void tc11() throws Exception { // Port Response (1004)with success (EXT)
		tc.tc11();
	}
	@Test
	public void tc12() throws Exception { // Port Response (1004)with Failed (EXT)
		tc.tc12();
	}
	@Test
	public void tc13() throws Exception { // Port Response (1004)with success (INT)
		tc.tc13();
	}
	@Test
	public void tc14() throws Exception { // Port Response (1004)with Failed (INT)
		tc.tc14();
	}
	@Test
	public void tc15() throws Exception { // Port Response (1005)with success (EXT)
		tc.tc15();
	}
	@Test
	public void tc16() throws Exception { // Port Response (1005)with success (INT)
		tc.tc16();
	}
	@Test
	public void tc17() throws Exception { // Port Notification (1006) with success (EXT)
		tc.tc17();
	}
	@Test
	public void tc18() throws Exception { // Port Notification (1006) with failed (EXT)
		tc.tc18();
	}
	@Test
	public void tc19() throws Exception { // Port Notification (1006) with success (INT)
		tc.tc19();
	}
	@Test
	public void tc20() throws Exception { // Port Notification (1006) with failed (INT)
		tc.tc20();
	}
	@Test
	public void tc21() throws Exception { // Port Notification (1007) with success (EXT)
		tc.tc21();
	}
	@Test
	public void tc22() throws Exception { // Port Notification (1007) with failed (EXT) // ok normally no msg info
		tc.tc22();
	}
	@Test
	public void tc23() throws Exception { // Port Notification (1007) with success (INT)
		tc.tc23();
	}
	@Test
	public void tc24() throws Exception { // Port Notification (1007) with failed (INT)
		tc.tc24();
	}
	@Test
	public void tc25() throws Exception { // Port Deactivate (1008) with success (EXT)
		tc.tc25();
	}
	@Test
	public void tc26() throws Exception { // Port Deactivate (1008) with failed (EXT)
		tc.tc26();
	}
	@Test
	public void tc27() throws Exception { // Port Deactivate (1008) with success (INT)
		tc.tc27();
	}
	@Test
	public void tc28() throws Exception { // Port Deactivate (1008) with failed (INT)
		tc.tc28();
	}
	@Test
	public void tc29() throws Exception { // Port Deactivate (1009) with success (EXT)
		tc.tc29();
	}
	@Test
	public void tc30() throws Exception { // Port Deactivate (1009) with Failed (EXT)
		tc.tc30();
	}
	@Test
	public void tc31() throws Exception { // Port Deactivate (1009) with success (INT)
		tc.tc31();
	}
	@Test
	public void tc32() throws Exception { // Port Deactivate (1009) with Failed (INT)
		tc.tc32();
	}
	@Test
	public void tc33() throws Exception { // Port BroadCast EXT(1010)
		tc.tc33();
	}
	@Test
	public void tc34() throws Exception { // Port Notification Exception (1011)
		tc.tc34();
	}
	@Test
	public void tc35() throws Exception { // Port Notification Exception (1012)
		tc.tc35();
	}
	@Test
	public void tc36() throws Exception { // Port Notification Exception (1011) Internal
		tc.tc36();
	}
	@Test
	public void tc37() throws Exception { // Port Notification Exception (1012) Internal
		tc.tc37();
	}

}

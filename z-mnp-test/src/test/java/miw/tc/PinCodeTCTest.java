package miw.tc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import miw.junit.MiwTestApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MiwTestApplication.class)
public class PinCodeTCTest {
	@Autowired
	PinCodeTC t;

	@Test
	public  void testOm() throws Exception {
	t.om();
	}

	@Test
	public  void testCopyofRequest1() throws Exception {
	t.CopyofRequest1();
	}

	@Test
	public  void testRequest1() throws Exception {
	t.Request1();
	}

	@Test
	public  void testRequestInfo() throws Exception {
	t.requestInfo();
	}

	@Test
	public  void testOrganizationNoEmail() throws Exception {
	t.organizationNoEmail();
	}

	@Test
	public  void testIndividualEmail() throws Exception {
	t.individualEmail();
	}

	@Test
	public  void testC_individualSms() throws Exception {
	t.c_individualSms();
	}

	@Test
	public  void testC_organizationEmail() throws Exception {
	t.c_organizationEmail();
	}

	@Test
	public  void testSmsRequest1() throws Exception {
	t.SmsRequest1();
	}

	@Test
	public  void testUssdRequest1() throws Exception {
	t.UssdRequest1();
	}

	@Test
	public  void testSmsCancel1() throws Exception {
	t.SmsCancel1();
	}

	@Test
	public  void testUssdCancel() throws Exception {
	t.UssdCancel();
	}

	@Test
	public  void testRequest3_1() throws Exception {
	t.request3_1();
	}

	@Test
	public  void testSit20161125_1_2cancel() throws Exception {
	t.sit20161125_1_2cancel();
	}

	@Test
	public  void testSit20161125_1_3cancel() throws Exception {
	t.sit20161125_1_3cancel();
	}

	@Test
	public  void testSit20161125_1_4_1cancel() throws Exception {
	t.sit20161125_1_4_1cancel();
	}

	@Test
	public  void testSit20161125_1_4_2cancel() throws Exception {
	t.sit20161125_1_4_2cancel();
	}

	@Test
	public  void testSit20161125_1_5cancel() throws Exception {
	t.sit20161125_1_5cancel();
	}

	@Test
	public  void testUssdReqInfoRequest_1() throws Exception {
	t.UssdReqInfoRequest_1();
	}

	@Test
	public  void testSmsReqInfoRequest_1() throws Exception {
	t.SmsReqInfoRequest_1();
	}






}

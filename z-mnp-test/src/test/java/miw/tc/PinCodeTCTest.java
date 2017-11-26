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






}

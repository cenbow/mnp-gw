package miw.tc;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GWExtTCTest {
	@Autowired
	private GwExtTC tc;

	@Test
	public void testTc1() {
		tc.tc1("ss");
	}

}

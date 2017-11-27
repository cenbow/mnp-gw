package miw.test.aq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"hello"})
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class TestAQApplication  implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(TestAQApplication.class);
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestAQApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		logger.warn("Test AQ");
	}


}
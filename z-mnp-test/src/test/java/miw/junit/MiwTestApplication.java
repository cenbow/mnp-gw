package miw.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("miw.tc")
@SpringBootApplication
public class MiwTestApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(MiwTestApplication.class);
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MiwTestApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("JUnit ENV");
	}

}
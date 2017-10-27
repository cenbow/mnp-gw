package miw.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import miw.tc.GwExtTC;

@SpringBootApplication
@ComponentScan("miw.tc")
public class GWExtTCApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(GWExtTCApplication.class);
	@Autowired
	private GwExtTC tc;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GWExtTCApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Main Application");
		tc.run();
	}

}
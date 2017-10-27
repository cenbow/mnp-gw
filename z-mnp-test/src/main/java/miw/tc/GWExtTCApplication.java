package miw.tc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GWExtTCApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(GWExtTCApplication.class);
	@Autowired
	private GwExtTC tc;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GWExtTCApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {		
		tc.run();
	}

}
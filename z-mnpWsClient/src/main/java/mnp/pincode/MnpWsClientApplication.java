package mnp.pincode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mnp.pincode.wsclient.PortoutClient;

@SpringBootApplication
public class MnpWsClientApplication implements CommandLineRunner{
	@Autowired
	private PortoutClient portoutClient;

	public static void main(String[] args) {
		SpringApplication.run(MnpWsClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start");
		portoutClient.generatePinCode();
		System.out.println("END");
	}

}

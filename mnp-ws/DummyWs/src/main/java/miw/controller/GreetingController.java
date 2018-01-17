package miw.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@RequestMapping(value = "/C1rtGw/api/subscribers/{msisdn}/info/summary", produces = "application/json;charset=UTF-8")
	public String greeting(@PathVariable("msisdn") int msisdn) throws IOException {
		String fStr = GreetingController.class.getResource("cr1.json").getFile();
		String jsonStr = FileUtils.readFileToString(new File(fStr));

		return jsonStr;
	}
}
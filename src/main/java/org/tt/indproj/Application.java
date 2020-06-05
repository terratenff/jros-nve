package org.tt.indproj;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("Application has finished its initializations.");
    }

	@Override
	public void run(String... args) {
		if (args.length == 0) {
			logger.info("No args provided. Default configurations are used.");
		} else {
			logger.info("Interpreting provided args...");
			ParamReader.configureApplication(args);
		}
	}
}

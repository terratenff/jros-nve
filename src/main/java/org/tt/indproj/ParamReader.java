package org.tt.indproj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.database_operators.DBBroker;

public class ParamReader {
	
	private static Logger logger = LoggerFactory.getLogger(ParamReader.class);
	
	public static void configureApplication(String... params) {
		// TODO
		DBBroker.setDatabase(false, false);
	}
	
	private static void logParamOptions() {
		logger.info("Available parameters:");
		logger.info("TODO");
	}
}

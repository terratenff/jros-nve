package org.tt.indproj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.database_operators.DBBroker;


public class ParamReader {
	
	private static enum Arg {
		HELP, VIEW, CLEAR, RESET, SQL
	}
	
	private static enum Table {
		NONE, ALL, PEOPLE, STORIES, RATINGS
	}
	
	private static Logger logger = LoggerFactory.getLogger(ParamReader.class);
	
	public static void configureApplication(String... args) {
		for (String arg : args) {
			logger.info(arg);
			String[] components = arg.split("-");
			String params = components.length > 1 ? components[1] : "";
			String argUpper = components[0].toUpperCase();
			try {
				Arg argType = Arg.valueOf(argUpper);
				selectAction(argType, params);
			} catch (IllegalArgumentException e) {
				logger.warn("Argument " + arg + " is not supported. It has been skipped.");
			} catch (NullPointerException e) {
				logger.warn("Null arguments are not supported.");
			}
		}
		DBBroker.setDatabase(false, false);
	}
	
	private static void selectAction(Arg arg, String params) {
		switch(arg) {
		case HELP:
			help(params);
			break;
		case VIEW:
			view(params);
			break;
		case CLEAR:
			clear(params);
			break;
		case RESET:
			reset();
			break;
		case SQL:
			sql(params);
		default:
			break;
		}
	}
	
	private static Table getTable(String var) {
		Table table;
		
		if (var.isEmpty()) {
			table = Table.ALL;
		} else {
			try {
				table = Table.valueOf(var.toUpperCase());
			} catch (IllegalArgumentException e) {
				logger.warn("Argument parameter '"
						+ var
						+ "' does not define an existing database table.");
				table = Table.NONE;
			}
		}
		return table;
	}
	
	private static void help(String range) {
		boolean all = range.isEmpty();
		// TODO
		
		if (range.isEmpty()) logger.info("Supported arguments:");
		logger.info("'help' - Lists supported arguments.");
		logger.info("'help-<arg>' - Provides detailed information about specified args.");
		logger.info("'view' - Lists contents of the database.");
		logger.info("'view-<table>' - Lists contents of specified database table.");
		logger.info("'clear' - Deletes all data from the database.");
		logger.info("'clear-<table>' - Deletes all data from specified database table.");
		logger.info("'reset' - Deletes the database, along with its data, "
				+ "and creates it again from scratch. This is useful if the structure "
				+ "of the database changes.");
		logger.info("'sql-<query>' - Performs specified SQL query before booting up.");
	}
	
	private static void view(String tables) {
		logger.info("function view(table/s) called.");
		String[] tableCollection = tables.split(" ");
		for (String tableString : tableCollection) {
			Table table = getTable(tableString);
			if (table == Table.NONE) continue; 
		}
		// TODO
	}
	
	private static void clear(String tables) {
		logger.info("function clear(table/s) called.");
		String[] tableCollection = tables.split(" ");
		for (String tableString : tableCollection) {
			Table table = getTable(tableString);
			if (table == Table.NONE) continue; 
		}
		// TODO
	}
	
	private static void reset() {
		logger.info("function reset() called.");
		// TODO
	}
	
	private static void sql(String sql) {
		logger.info("function sql(sql) called");
		// TODO
	}
}

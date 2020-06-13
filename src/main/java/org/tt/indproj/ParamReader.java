package org.tt.indproj;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tt.indproj.database_operators.DBBroker;

/**
 * Collection of functions that parse command line arguments during
 * the start of the application.
 * @author terratenff
 *
 */
public class ParamReader {
	
	/**
	 * Enums for the application's command line arguments.
	 * @author terratenff
	 *
	 */
	private static enum Arg {
		HELP, VIEW, CLEAR, RESET, SQL
	}
	
	/**
	 * Enums that express the database tables that the application uses.
	 * @author terratenff
	 *
	 */
	private static enum Table {
		NONE, ALL, PEOPLE, STORIES, RATINGS;
		
		/**
		 * Getter for the table enums that are actually used by the
		 * application database.
		 * @return Array of enums used by the database.
		 */
		public static Table[] getEffectiveTables() {
			Table[] tables = {Table.PEOPLE, Table.STORIES, Table.RATINGS};
			return tables;
		}
	}
	
	/**
	 * This logger could be used to write down events relating to the use
	 * of command line arguments, examples being error reporting and viewing
	 * database contents.
	 */
	private static Logger logger = LoggerFactory.getLogger(ParamReader.class);
	
	/**
	 * Entrty function for application configuration. Command line arguments
	 * are taken and processed for application configuration purposes.
	 * @param args Command line arguments. They must follow the following format:<br>
	 * 'main_arg1-param1 param2 param3' 'main_arg2-param1 param2 param3' etc.
	 */
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
		DBBroker.setDatabase(false, false, true);
	}
	
	/**
	 * Main argument function selector.
	 * @param arg Subject command line argument.
	 * @param params Parameters of subject command line argument.
	 */
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
	
	/**
	 * Getter for a database table enum.
	 * @param var Target database table.
	 * @return Specified database table. If function parameter
	 * is an empty string, "ALL" tables are selected. If it is
	 * invalid, table "NONE" is selected instead.
	 */
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
	
	/**
	 * Getter for a collection of database table enums.
	 * @param varCollection String of database table names (should be separated by whitespace).
	 * @return Array of table enums found on provided string.
	 */
	private static Table[] getTables(String varCollection) {
		Table[] tableSet;
		if (getTable(varCollection) == Table.ALL) {
			return Table.getEffectiveTables();
		}
		
		List<Table> tableList = new ArrayList<Table>();
		String[] tableCollection = varCollection.split(" ");
		
		for (String tableString : tableCollection) {
			Table table = getTable(tableString);
			
			if (table == Table.NONE) continue;
			else if (table == Table.ALL) {
				return Table.getEffectiveTables();
			} else if (!tableList.contains(table)) {
				tableList.add(table);
			}
		}
		
		tableSet = tableList.toArray(new Table[0]);
		return tableSet;
	}
	
	/**
	 * Main function for the main argument "help".
	 * @param range Specifies the range of how much help is provided.
	 */
	private static void help(String range) {
		boolean all = range.isEmpty();
		boolean help = all || range.contains("help");
		boolean view = all || range.contains("view");
		boolean clear = all || range.contains("clear");
		boolean reset = all || range.contains("reset");
		boolean sql = all || range.contains("sql");
		
		if (!all && !view && !clear && !reset && !sql) {
			logger.warn("Arguments in '" + range + "' are not supported.");
			return;
		}
		
		if (all) {
			logger.info("Supported arguments:");
		}
		if (help) {
			logger.info("'help' - Lists supported arguments.");
			logger.info("'help-<arg1> <arg2> <arg3>' - Provides detailed information about specified args.");
			logger.info("Available arguments: help, view, clear, reset, sql.");
		}
		if (view) {
			logger.info("'view' - Lists contents of the database.");
			logger.info("'view-<table>' - Lists contents of specified database table.");
			logger.info("'view-<table> <param>' - Lists contents of specified database table, "
					+ "while also taking a parameter into account.");
			logger.info("Available parameters:");
			logger.info("skiplargeinstances - Any result that has a length greater than 255 is skipped.");
		}
		if (clear) {
			logger.info("'clear' - Deletes all data from the database.");
			logger.info("'clear-<table>' - Deletes all data from specified database table.");
		}
		if (reset) {
			logger.info("'reset' - Deletes the database, along with its data, "
					+ "and creates it again from scratch. This is useful if the structure "
					+ "of the database changes.");
		}
		if (sql) {
			logger.info("'sql-<query>' - Performs specified SQL query before booting up.");
			logger.info("'sql-<query> <param>' - Performs specified SQL query before booting up, "
					+ "while also taking a parameter into account.");
			logger.info("Available parameters:");
			logger.info("skiplargeinstances - Any result that has a length greater than 255 is skipped.");
		}
	}
	
	/**
	 * Main function for the main argument "view".
	 * @param tables Collection of database tables that are to be
	 * viewed.
	 */
	private static void view(String tables) {
		logger.info("function view(table/s) called.");
		Table[] tableSet = getTables(tables);
		boolean skipLargeInstances = false;
		
		if (tables.toLowerCase().contains("skiplargeinstances")) {
			skipLargeInstances = true;
		}
		
		for (Table table : tableSet) {
			String tableStr = table.toString().toLowerCase();
			DBBroker.viewTable(tableStr, skipLargeInstances);
		}
	}
	
	/**
	 * Main function for the main argument "clear".
	 * @param tables Collection of database tables that are to be
	 * cleared of data.
	 */
	private static void clear(String tables) {
		logger.info("function clear(table/s) called.");
		Table[] tableSet = getTables(tables);
		
		for (Table table : tableSet) {
			String tableStr = table.toString().toLowerCase();
			DBBroker.clearTable(tableStr);
		}
	}
	
	/**
	 * Main function for the main argument "reset".
	 */
	private static void reset() {
		logger.info("function reset() called.");
		DBBroker.setDatabase(true, false, false);
	}
	
	/**
	 * Main function for the main argument "sql".
	 * @param sql SQL query that is to be executed.
	 */
	private static void sql(String sql) {
		logger.info("function sql(sql) called");
		String sqlFinal;
		boolean skipLargeInstances = false;
		
		if (sql.toLowerCase().contains("skiplargeinstances")) {
			skipLargeInstances = true;
			sqlFinal = sql.replaceAll("skiplargeinstances", "");
		} else {
			sqlFinal = sql;
		}
		
		DBBroker.executeSql(sqlFinal, skipLargeInstances);
	}
}

package org.ipsedixit.javaexitgracefullyexample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Entrypoint {
	private static final Logger LOGGER = LogManager.getLogger(Entrypoint.class);

	// a class that extends thread that is to be called when program is exiting
	static class ExitGracefully extends Thread {

		public void run() {
			LOGGER.info("Inside exit gracefully.");
		}
	}

	public static void main(String[] argv) {
		try {
			// Register ExitGracefully as shutdown hook
			Runtime.getRuntime().addShutdownHook(new ExitGracefully());
			LOGGER.info("Program is starting and exit after 20 seconds or when SIGINT is received...");
			Thread.sleep(20000);
			LOGGER.info("Printed");
			System.exit(0);
			// This steps are not executed because it is called a System.exit
			LOGGER.info("Not printed");
		} catch (Exception exc) {
			// In case an error occurred
			LOGGER.error("An error occurred", exc);
		} finally {
			// This steps are not executed because it is called a System.exit
			LOGGER.info("Not executed");
		}
	}
}

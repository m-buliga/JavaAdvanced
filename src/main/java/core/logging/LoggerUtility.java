package core.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
    // define a logger instance
    public static Logger logger = LogManager.getLogger();

    // define a method to start a test
    public static void startTestCase(String testName) {
        logger.info("****** " + testName + " execution started ******");
    }

    // define a method to stop a test
    public static void finishTestCase(String testName) {
        logger.info("****** " + testName + " execution finished ******");
    }

    // info method
    public static void infoTest(String message) {
        logger.info(message);
    }

    // error method
    public static void errorTest(String message) {
        logger.error(message);
    }
}

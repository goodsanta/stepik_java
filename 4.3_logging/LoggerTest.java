package org.stepic.java.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class LoggerTest {
    private static void configureLogging() {
        Logger LOGGER_A = Logger.getLogger("org.stepic.java.logging.ClassA");
        Logger LOGGER_B = Logger.getLogger("org.stepic.java.logging.ClassB");
        Logger LOGGER_C = Logger.getLogger("org.stepic.java");

        ConsoleHandler consoleHandler = new ConsoleHandler();
        XMLFormatter xmlFormatter = new XMLFormatter();

        //set logging level
        LOGGER_A.setLevel(Level.ALL);
        LOGGER_B.setLevel(Level.WARNING);
        LOGGER_C.setLevel(Level.ALL);

        //limit messages sent to Handler
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(xmlFormatter);

        LOGGER_C.addHandler(consoleHandler);
        LOGGER_C.setUseParentHandlers(false);
    }

    public static void main(String[] args) {

    }
}

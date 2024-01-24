package com.appbroda.loggerlibrary;

import android.util.Log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
public class MyLogger {
    public static final Logger logger = LogManager.getLogger(MyLogger.class);
    public MyLogger() {
        Log.i("MyLogger", "constructor Logger - Library");
//        logger.info("Logger constructor");
    }
    public void logMessage(String message) {
        BasicConfigurator.configure();
        logger.info(message);
        Log.i("LC log", "This is the LogCat Log (:");
    }
}

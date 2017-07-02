package com.corti;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerUtils {
  public Logger getLogger(String _loggerName, String _outputFile) {
    Logger logger = null;
    try {
      logger = Logger.getLogger(_loggerName);

      // The args to handler are, filename, size limit of file, number or files
      // to create, append flag
      Handler handler = new FileHandler(_outputFile, 100000, 5, true);
      Formatter formatter = new SingleLineFormatter();

      // If wanted to see a log warning can do
      // LogRecord record = new LogRecord(Level.WARNING,"This is a test warning
      // message");
      // logger.log(record);

      handler.setFormatter(formatter);
      logger.addHandler(handler);

      // Caller can now do logger.info("string") logger.warning... (see doc)
    } catch (Exception e) {
      e.printStackTrace();
    }
    return logger;
  }

}

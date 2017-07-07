package com.corti.javalogger;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerUtils {
  private Logger getLoggerHelper(String _loggerName, String _outputFile, Class formatterClass) {
    Logger logger = null;
    try {
      logger = Logger.getLogger(_loggerName);

      // The args to handler are, filename, size limit of file, number or files
      // to create, append flag
      Handler handler = new FileHandler(_outputFile, 100000, 5, true);      
      Formatter formatter = (Formatter)formatterClass.newInstance();     
      
      // If wanted to see a log warning can do
      // LogRecord record = new LogRecord(Level.WARNING,"This is a test warning
      // message");
      // logger.log(record);
      // or you can call logger.warning("..."), the warning (and other methods) return a LogRecord
      
      handler.setFormatter(formatter);  // Passed in
      logger.addHandler(handler);

      // Caller can now do logger.info("string") logger.warning... (see doc)
    } catch (Exception e) {
      e.printStackTrace();
    }
    return logger;
  }

  // This will create a logger with the SingleLineFormatter (the default)
  public Logger getLogger(String _loggerName, String _outputFile) {
    return getLoggerHelper(_loggerName, _outputFile, SingleLineFormatter.class);
  }
  
  // This one will create a logger that uses a CSV formatter (fields delimitted).
  public Logger getLogger(String _loggerName, String _outputFile, boolean _csvLogFormatter) {
    return getLoggerHelper(_loggerName, _outputFile, CSVLineFormatter.class);
  }
  
}

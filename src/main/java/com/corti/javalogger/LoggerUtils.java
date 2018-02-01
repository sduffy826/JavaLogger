package com.corti.javalogger;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
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

      System.out.println("logging.properties:" + getClass().getClassLoader().getResource("logging.properties"));
      
      
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
  
  // Change the log level for the arg passed in
  public synchronized void setLogLevel(Logger _logger, Level _newLevel) {
    // Set level
    _logger.setLevel(_newLevel);
    
    // Check if there's a console handler already defined if so we'll set it's level, we don't bother
    // setting other handlers here (they have ALL setting, but if find down the road that this changes
    // just set level here)
    boolean didSetConsole = false;
    Handler[] handlers = _logger.getHandlers();
    for (Handler handle: handlers) {
      if ( handle.getClass().getSimpleName().equalsIgnoreCase("ConsoleHandler") ) {
        handle.setLevel(_newLevel);
        didSetConsole = true;        
      }
    }
    
    // If we didn't set the console handler above then define it here
    if (!didSetConsole) {
      // Override the console handler
      Handler sysout = new ConsoleHandler();
      sysout.setLevel(_newLevel);
      _logger.addHandler(sysout);
    }
  }    
}

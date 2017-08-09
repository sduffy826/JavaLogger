import java.util.logging.ConsoleHandler;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.corti.javalogger.LoggerUtils;

public class TestIt {

  // Typically you'll use static objects for logging
  public static final LoggerUtils logUtils = new LoggerUtils();
  public static final java.util.logging.Logger log = logUtils.getLogger("MyTestLogger", "TestLogger.log");
  
  // Mainline
  public static void main(String[] args) {   
    
    // Write log message (an info one)
    MyLogger.log.info("This is the first one");
    
    // If want to see the handlers defined
    Handler[] handlers = MyLogger.log.getHandlers();
    for (Handler handle: handlers) {
      System.out.println("handler getName: " + handle.getClass().getName() + " level: " + handle.getLevel().getName());
    }    

    // Show another way to instantiate where we don't keep ref to LoggerUtils obj
    // this is using same logger name and output file.
    // Notes: if you don't use same logger name but uses same output file then
    //          the each logger will output to different name; the second will
    //          have a name like foofy.bar.n.1 where the log1 will be writing
    //          to foofy.bar.n
    //        If you use the same logger name but different output file then it's 
    //          like the logger has two output filenames; from that point forward
    //          messages are written to both files.  This is what I do below (the
    //          logname is the same one as in MyLogger class)
    Logger log2 = (new LoggerUtils()).getLogger("MyTestLogger", "AnotherLoggerFile.log");
    log2.info("This is from second logger object");
    
    MyLogger.log.info("First logger object");

    // Show logging using record
    try {
      LogRecord record = new LogRecord(Level.SEVERE,"This is a severe LogRecord");
      log2.log(record);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // Use different log levels
    MyLogger.log.severe("This is a severe message");
    MyLogger.log.fine("This is a fine message before level changed");  // won't show
  
    // Change level, this will also set level of console handler (it defaults to info)
    MyLogger.logUtils.setLogLevel(MyLogger.log,  Level.FINE);
    
    // Just to show some info on the handlers
    handlers = MyLogger.log.getHandlers();
    System.out.println("Number of handlers: " + handlers.length);
    for (Handler handle: handlers) {
      System.out.println("handler getName: " + handle.getClass().getName() + " level: " + handle.getLevel().getName());
      System.out.println("        getSimpleName: " + handle.getClass().getSimpleName()); 
      System.out.println("        getCanonicalName: " + handle.getClass().getCanonicalName());
      System.out.println("        getTypeName: " + handle.getClass().getTypeName());      
    }   
    
    // You should see this once the level has been reset
    MyLogger.log.fine("This is fine after setting console handler");   
  }
}

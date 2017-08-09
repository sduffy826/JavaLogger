import com.corti.javalogger.LoggerUtils;

public class MyLogger {  
  // Typically you'll use static objects for logging
  public static final LoggerUtils logUtils = new LoggerUtils();
  public static final java.util.logging.Logger log = logUtils.getLogger("MyTestLogger", "TestLogger.log");
 
  protected MyLogger() { }    
}

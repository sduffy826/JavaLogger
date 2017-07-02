import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.corti.javalogger.LoggerUtils;

public class TestIt {

  /** Little stub to show how to user logger; in this we have two objects but they
   * are both writing to the same log file (and it works :))
   * @param args
   */
  public static void main(String[] args) {
    // get Objects
    LoggerUtils aLog = new LoggerUtils();
    Logger log1 = aLog.getLogger("MyTestLogger", "foofy.bar");
    
    // Write log message (an info one)
    log1.info("This is the first one");

    // Show another way to instantiate where we don't keep ref to LoggerUtils obj
    // this is using same logger name and output file.
    // Notes: if you don't use same logger name but uses same output file then
    //          the each logger will output to different name; the second will
    //          have a name like foofy.bar.n.1 where the log1 will be writing
    //          to foofy.bar.n
    //        If you use the same logger name but different output file then it's 
    //          like the logger has two output filenames; from that point forward
    //          messages are written to both files.
    Logger log2 = (new LoggerUtils()).getLogger("MyTestLogger", "foofy1.bar");
    log2.info("This is from second object");
    
    log1.info("First object");

    try {
      LogRecord record = new LogRecord(Level.SEVERE,
          "This is a severe message");
      log2.log(record);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

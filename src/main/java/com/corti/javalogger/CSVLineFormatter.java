package com.corti.javalogger;

import java.text.*;
import java.util.*;
import java.util.logging.*;

public class CSVLineFormatter extends java.util.logging.Formatter {
  private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
  private Date date = new Date();

  // Return formatted version of log record in csv format
  public String format(LogRecord record) {
    date.setTime(System.currentTimeMillis());
    return "\"" + dateformat.format(date) + "\"," + 
           "\"" + record.getLevel() + "\"," + 
           record.getMessage() + "\r\n";                                            
  }
}

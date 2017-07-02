package com.corti.javalogger;

import java.text.*;
import java.util.*;
import java.util.logging.*;
public class SingleLineFormatter extends java.util.logging.Formatter
{
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private Date date = new Date();
    public String format(LogRecord record)
    {
        date.setTime(System.currentTimeMillis());
        return "[" + dateformat.format(date) + "] " +
          record.getLevel() + " : " +
          record.getMessage() + "\r\n";
    }
}


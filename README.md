JavaLogger
----------
This project has some utility methods to make it easier to use the java logger.  The main
thing you're probably interested in is:
```
  LoggerUtils.getLogger(loggerName, loggerFileName, &lt;booleanForCSVFormatter&gt;) 
  setLogLevel(Logger, LogLevel)
```

fyi: Think of the 'loggerName' as a singleton; you can have multiple loggerFileName's 
for the same 'loggerName', doing this will cause messages to be written to
multiple output files.  This is useful when you want to output log data in 
multiple formats (i.e. 'regular text' and formatted for 'csv').

Easiest way to see usage is to look at the 'TestIt.java' code; it shows:
- Multiple ways to get a logger
- Writing log messages, as String or using LogRecord
- Showing handlers
- Setting log level
- Getting a log file that's in 'csv' format (to multiple output files) 
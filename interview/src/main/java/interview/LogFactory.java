package interview;

import org.apache.commons.logging.impl.SimpleLog;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

public class LogFactory {
	
	private static Map<String,SimpleLog> loggers = new HashMap<String,SimpleLog>();
	private static final int defaultLogLevel = SimpleLog.LOG_LEVEL_INFO;
	
	public enum LogLevel {
		DEBUG(SimpleLog.LOG_LEVEL_DEBUG),
		INFO(SimpleLog.LOG_LEVEL_INFO),
		TRACE(SimpleLog.LOG_LEVEL_TRACE),
		WARN(SimpleLog.LOG_LEVEL_WARN),
		ERROR(SimpleLog.LOG_LEVEL_ERROR),
		FATAL(SimpleLog.LOG_LEVEL_FATAL);
		
		private final int value;
		LogLevel(int value) {
			this.value = value;
		}
		
		public static final EnumSet<LogLevel> ValidLevel = EnumSet.allOf(LogLevel.class);
		
		public static boolean isValid(int value) {
 			return ValidLevel.contains(value);
 		}
	}
	
	private LogFactory() {}
	
	public static SimpleLog getLogger(String loggerName) {
		SimpleLog log = loggers.get(loggerName);
		if (log == null) {
			log = new SimpleLog(loggerName);
	        log.setLevel(defaultLogLevel);  
			loggers.put(loggerName, log);
		}				
        return log;
	}
	
	public static SimpleLog getLogger(String loggerName, LogLevel loggerLevel) {
		SimpleLog log = getLogger(loggerName);
		log = setLogLevel(loggerName,loggerLevel);
        return log;
	}
	
	public static SimpleLog setLogLevel(String loggerName, LogLevel loggerLevel) {
		SimpleLog log = getLogger(loggerName);
		if (log == null) 
			return null;
		log.setLevel(loggerLevel.value);
		return log;
	}

}

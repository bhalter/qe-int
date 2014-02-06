package interview;

import org.apache.commons.logging.impl.SimpleLog;

import interview.LogFactory;
import interview.WebAccess;
import interview.Processor;

/**
 * @author crhoffman
 *
 */
public class Interview {

	public static SimpleLog log;
	private static final String loggerName = "Interview";
	
	
	public static void main(String[] args) {
        log = LogFactory.getLogger(loggerName, LogFactory.LogLevel.WARN);       
        log.debug("main test");
                        
        WebAccess webAccess = null;
        try {
        	// CHROME works as well, but the driver seems much slower.
            webAccess = new WebAccess(WebAccess.WebBrowser.FIREFOX,log);            
        	
            Processor processor = new Processor(webAccess,log);
            processor.run();
        	        	
        }
        catch (InterruptedException e) {
        	log.error("exception: " + e.getMessage());
        }
        catch (Exception e) {
        	log.error("exception: " + e.getMessage());
        }
        finally {
        	if (webAccess != null) {
        		webAccess.releaseDriver();
        	}
        }
        log.debug("main test - exit");
	}

}

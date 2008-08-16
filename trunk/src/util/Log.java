package util;

import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

public class Log {
	public static Logger debugLogger = Logger.getLogger( "vidis.debug" );
	{
		 debugLogger.addAppender( new WriterAppender() );
	}
	public static void debug( String msg ){
		debugLogger.debug( msg );
	}
}

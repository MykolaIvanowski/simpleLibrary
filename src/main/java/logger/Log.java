package logger;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger LOGGER = Logger.getLogger(Log.class);

    public static void infoMessage(String info){

        LOGGER.info(info);
    }
}

package com.instpro;
// importing loggers package
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * welcome to institution pro
 *
 */
public class App 
{
     private static final Logger logger =
            LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        logger.info("Application started");
        System.out.println( "welcome to institution pro\r\n" );

        try {
            int result = 10 / 2;
            logger.debug("Calculation result: {}", result);
            }
        catch (Exception e) {
             logger.error("Error occurred", e);
            }

        logger.info("Application finished");

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Lazy logging class
 * Even if classes should have their own Logger instance, it may be easier for flyweights or so
 * to use this wrapper class.
 * Note that each class can change the Level so it can't be controlled whether a message is printed
 * out or not
 * @author uli
 */
public class Logging
{
    private static Logger logger = Logger.getLogger(Logging.class);

    /**
     * Logs a message with level Level.TRACE if TRACE logging
     * is enabled
     * @param msg The message to log
     */
    public static void logTrace(String msg)
    {
        if (logger.isTraceEnabled())
        {
            logger.log(Level.TRACE, msg);
        }
    }

    /**
     * Sets the logger level to a specific value
     * @param level The level to set the logger level to
     */
    public static void setLevel(Level level)
    {
        logger.setLevel(level);
    }
    
    /**
     * Logs a message with level Level.DEBUG if DEBUG logging is enabled
     * @param msg The message to log
     */
    public static void logDebug(String msg)
    {
        if (logger.isDebugEnabled())
        {
            logger.log(Level.DEBUG, msg);
        }
    }

    /**
     * Logs a message with level Level.INFO if INFO logging is enabled
     * @param msg The message to log
     */
    public static void logInfo(String msg)
    {
        if (logger.isInfoEnabled())
        {
            logger.log(Level.WARN, msg);
        }
    }

}

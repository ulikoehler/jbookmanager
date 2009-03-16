/**
 * Represents one book
 * (a number of identical books is represented by one instance of this class)
 */
package jbookmanager.model;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author uli
 */
public class Book implements Serializable
{
    private String isbn;
    private String title;
    private String comment;
    //Optional info
    private double price;
    private int count;
    private static Logger logger = Logger.getLogger(Book.class);

    /**
     * @return the isbn
     */
    public String getIsbn()
    {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn)
    {
        /**
         * Log the change
         */
//        if(logger.isInfoEnabled())
//        {
//            logger.info("Changed ISBN of book '" + title + "' from '" + this.isbn + "' to '" + isbn + "'");
//        }
        this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        /**
         * Log the change
         */
//        if(logger.isInfoEnabled())
//        {
//            logger.info("Changed book title from '" + this.title + "' to '" + title + "'");
//        }
        this.title = title;
    }

    /**
     * @return the comment
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment)
    {
        /**
         * Log the change
         */
//        if(logger.isInfoEnabled())
//        {
//            logger.info("Changed comment of book '" + title + "' from '" + this.comment + "' to '" + comment + "'");
//        }
        this.comment = comment;
    }

    /**
     * @return the count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count)
    {
        /**
         * Log the change
         */
//        if(logger.isInfoEnabled())
//        {
//            logger.info("Changed count of book '" + title + "' from '" + this.count + "' to '" + count + "'");
//        }
        this.count = count;
    }

    /**
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price)
    {
        /**
         * Log the change
         */
        if(logger.isInfoEnabled())
        {
            logger.info("Changed price of book '" + title + "' from '" + this.price + "' to '" + price + "'");
        }
        this.price = price;
    }
}

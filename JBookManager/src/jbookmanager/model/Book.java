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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + (this.isbn != null ? this.isbn.hashCode() : 0);
        hash = 71 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 71 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 71 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Book other = (Book) obj;
        if ((this.isbn == null) ? (other.isbn != null) : !this.isbn.equals(other.isbn))
        {
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title))
        {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment))
        {
            return false;
        }
        if (this.price != other.price)
        {
            return false;
        }
        if (this.count != other.count)
        {
            return false;
        }
        return true;
    }

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
        if (logger.isInfoEnabled())
        {
            logger.info("Changed price of book '" + title + "' from '" + this.price + "' to '" + price + "'");
        }
        this.price = price;
    }
}

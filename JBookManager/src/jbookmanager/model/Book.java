/**
 * Represents one book
 * (a number of identical books is represented by one instance of this class)
 */
package jbookmanager.model;

import java.io.Serializable;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

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

    public Book()
    {
        
    }

    public Book(String isbn, String title, String comment, double price, int count)
    {
        this.isbn = isbn;
        this.title = title;
        this.comment = comment;
        this.price = price;
        this.count = count;
    }

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
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): Argument is null");
            }
            return false;
        }
        if (getClass() != obj.getClass())
        {
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): Class types do not match");
            }
            return false;
        }
        final Book other = (Book) obj;
        if ((this.isbn == null) ? (other.isbn != null) : !this.isbn.equals(other.isbn))
        {
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): ISBNs do not match");
            }
            return false;
        }
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title))
        {
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): ISBNs do not match");
            }
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment))
        {
            if(logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): Comments do not match");
            }
            return false;
        }
        if (this.price != other.price)
        {
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): Prices do not match");
            }
            return false;
        }
        if (this.count != other.count)
        {
            if (logger.isTraceEnabled())
            {
                logger.log(Level.TRACE, "equals(): Counts do not match");
            }
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
        this.price = price;
    }
}

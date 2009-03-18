package jbookmanager.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This is named AtomicOrder because it's the order in particular
 * and not dividable
 */
public class AtomicOrder
{
    private String bookISBN;
    private int count;
    private static Logger logger = Logger.getLogger(AtomicOrder.class);

    public AtomicOrder()
    {
        
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
        final AtomicOrder other = (AtomicOrder) obj;
        if ((this.bookISBN == null) ? (other.bookISBN != null) : !this.bookISBN.equals(other.bookISBN))
        {
            if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): Book ISBN does not match");
                    }
            return false;
        }
        if (this.count != other.count)
        {
            if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): Book count does not match");
                    }
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 41 * hash + (this.bookISBN != null ? this.bookISBN.hashCode() : 0);
        hash = 41 * hash + this.count;
        return hash;
    }

    

    public AtomicOrder(String isbn, int count)
    {
        this.bookISBN = isbn;
        this.count = count;
    }

    /**
     * @return the bookTitle
     */
    public String getBookTitle()
    {
        return LibraryManager.library.getBookByISBN(getBookISBN()).getTitle();
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setBookTitle(String bookTitle)
    {
        this.setBookISBN(LibraryManager.library.getBookByTitle(bookTitle).getIsbn());
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
        this.count = count;
    }

    /**
     * @return the bookISBN
     */
    public String getBookISBN()
    {
        return bookISBN;
    }

    /**
     * @param bookISBN the bookISBN to set
     */
    public void setBookISBN(String bookISBN)
    {
        this.bookISBN = bookISBN;
    }
}

package jbookmanager.model;

import jbookmanager.model.LibraryManager;

/**
 * This is named AtomicOrder because it's the order in particular
 * and not dividable
 */
public class AtomicOrder
{
    private String bookISBN;
    private int count;

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.bookISBN != null ? this.bookISBN.hashCode() : 0);
        hash = 97 * hash + this.count;
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
        final AtomicOrder other = (AtomicOrder) obj;
        if ((this.bookISBN == null) ? (other.bookISBN != null) : !this.bookISBN.equals(other.bookISBN))
        {
            return false;
        }
        if (this.count != other.count)
        {
            return false;
        }
        return true;
    }



    public AtomicOrder()
    {
        
    }

    public AtomicOrder(String bookTitle, int count)
    {
        this.bookISBN = LibraryManager.library.getBookByTitle(bookTitle).getIsbn();
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

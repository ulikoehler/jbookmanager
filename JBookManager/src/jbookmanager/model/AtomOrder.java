package jbookmanager.model;

/**
 * This is named AtomOrder because it's the order in particular
 * and not dividable
 */
public class AtomOrder
{
    private String bookTitle;
    private int count;

    public AtomOrder()
    {
        
    }

    public AtomOrder(String bookTitle, int count)
    {
        this.bookTitle = bookTitle;
        this.count = count;
    }

    /**
     * @return the bookTitle
     */
    public String getBookTitle()
    {
        return bookTitle;
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setBookTitle(String bookTitle)
    {
        this.bookTitle = bookTitle;
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
}

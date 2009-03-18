/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 *
 * @author uli
 */
public class Library implements Serializable
{

    private Vector<Book> books;
    private OrderManager orderManager = new OrderManager();
    private static Logger logger = Logger.getLogger(Library.class);

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + (this.books != null ? this.books.hashCode() : 0);
        hash = 41 * hash + (this.orderManager != null ? this.orderManager.hashCode() : 0);
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
        final Library other = (Library) obj;
        //Compare the book array
        if (this.books != other.books && (this.books == null || !this.books.equals(other.books)))
        {
            for (Book book : books)
            {
                boolean state = false; //True if two books matched
                for (Book otherBook : other.books)
                {
                    if (book.equals(otherBook))
                    {
                        state = true;break;
                    }
                }
                //If state == true, continue
                if (!state)
                {
                    if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): Book with ISBN " + book.getIsbn() +
                                " doesn't match any book in the other Library instance.");
                    }
                    return false;
                }
            }
        }
        if (this.orderManager != other.orderManager &&
                (this.orderManager == null || !this.orderManager.equals(other.orderManager)))
        {
            return false;
        }
        return true;
    }

    public Library()
    {
        books = new Vector<Book>();
    }

    public void addBook(Book b)
    {
        getBooks().add(b);
    }

    public int getBookCount()
    {
        return getBooks().size();
    }

    public Book getBookAt(int index)
    {
        return getBooks().elementAt(index);
    }

    public Book getBookByISBN(String isbn)
    {
        for (Book b : getBooks())
        {
            if (b.getIsbn().equals(isbn))
            {
                return b;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title)
    {
        for (Book b : getBooks())
        {
            if (b.getTitle().equals(title))
            {
                return b;
            }
        }
        return null;
    }

    public void deleteBook(int index)
    {
        getBooks().remove(index);
    }

    /**
     * A wrapper function that covers all book and all columns within a 5-column array
     * @return An array representation of the library
     */
    public Object[][] getDataVector()
    {
        Object[][] ret = new Object[getBookCount()][5];
        for (int i = 0; i < getBookCount(); i++)
        {
            Book b = getBooks().elementAt(i);
            ret[i][0] = b.getIsbn();
            ret[i][1] = b.getTitle();
            ret[i][2] = LibraryManager.currencyFormat.format(b.getPrice());
            ret[i][3] = b.getCount();
            ret[i][4] = b.getComment();
        }
        return ret;
    }

    public List<String> getTitles()
    {
        List<String> titles = new LinkedList<String>();
        for (Book b : books)
        {
            titles.add(b.getTitle());
        }
        return titles;
    }

    /**
     * @return the books
     */
    public Vector<Book> getBooks()
    {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Vector<Book> books)
    {
        this.setBooks(books);
    }

    /**
     * @return the orders
     */
    public OrderManager getOrderManager()
    {
        return orderManager;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrderManager(OrderManager orders)
    {
        this.orderManager = orders;
    }
}

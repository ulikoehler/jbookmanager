/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author uli
 */
public class Library implements Serializable {
    private String name;
    private Vector<Book> books;

    public Library()
    {
        books = new Vector<Book>();
    }

    public void addBook(Book b)
    {
        books.add(b);
    }

    public int getBookCount()
    {
        return books.size();
    }

    public Book getBookAt(int index)
    {
        return books.elementAt(index);
    }

    public Book getBookByISBN(String isbn)
    {
        for(Book b : books)
        {
            if(b.getIsbn().equals(isbn))
            {
                return b;
            }
        }
        return null;
    }

    public Book getBookByTitle(String title)
    {
        for(Book b : books)
        {
            if(b.getTitle().equals(title))
            {
                return b;
            }
        }
        return null;
    }

    public Object[][] getData()
    {
        Object[][] ret = new Object[getBookCount()][5];
        for(int i = 0; i < getBookCount(); i++)
        {
            Book b = books.elementAt(i);
            ret[i][0] = b.getIsbn();
            ret[i][1] = b.getTitle();
            ret[i][2] = b.getPrice();
            ret[i][3] = b.getCount();
            ret[i][4] = b.getComment();
        }
        return ret;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
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
        this.books = books;
    }
}

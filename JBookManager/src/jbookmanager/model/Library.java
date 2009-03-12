/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.Serializable;
import java.util.Vector;
import javax.swing.DefaultListModel;

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

    public String[] getTitleList()
    {
        String[] ret = new String[books.size()];
        for(int i = 0; i < books.size(); i++)
        {
            Book b = books.elementAt(i);
            ret[i] = b.getTitle();
        }
        return ret;
    }

    public void updateTitleListModel(DefaultListModel model)
    {
        model.removeAllElements();
        model.copyInto(getTitleList());
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Logger.getLogger(JBookManagerConfiguration.class.getName()).log(Level.SEVERE, "Added book", "Added book");
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

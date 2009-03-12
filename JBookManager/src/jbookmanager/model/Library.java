/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author uli
 */
public class Library {
    private String name;
    private List<Book> books;

    public Library()
    {
        books = new LinkedList<Book>();
    }

    public void addBook(Book b)
    {
        books.add(b);
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

    public Book getBookByTitle(String isbn)
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
    public List<Book> getBooks()
    {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}

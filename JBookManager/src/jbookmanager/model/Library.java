/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.util.List;

/**
 *
 * @author uli
 */
public class Library {
    private String name;
    private List<Book> books;

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

/**
 * Represents one book
 * (a number of identical books is represented by one instance of this class)
 */
package jbookmanager.model;

import java.util.List;

/**
 *
 * @author uli
 */
public class Book
{
    public String name;
    public String isbn;
    public String title;
    public List<String> authors;
    public String comment;
    public int pages;
    public int count;
}

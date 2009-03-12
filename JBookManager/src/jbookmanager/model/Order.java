/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.model;

import java.io.Serializable;
import java.util.Vector;
import jbookmanager.controller.LibraryManager;

/**
 *
 * @author uli
 */
public class Order implements Serializable
{

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
     * This is named AtomOrder because it's the order in particular
     * and not dividable
     */
    class AtomOrder
    {
        public String bookTitle;
        public int count;
        public AtomOrder(String bookTitle, int count)
        {
            this.bookTitle = bookTitle;
            this.count = count;
        }
    }

    public Order()
    {
        data = new Vector<AtomOrder>();
    }

    public void addOrder(String bookTitle, int count)
    {
        data.add(new AtomOrder(bookTitle, count));
    }

    public void deleteOrder(int index)
    {
        data.remove(index);
    }

    public double getPriceSum()
    {
        double sum = 0.0;
        for(AtomOrder a : data)
        {
            sum += a.count * LibraryManager.library.getBookByTitle(a.bookTitle).getPrice();
        }
        return sum;
    }

    public Object[][] getData()
    {
        Object[][] ret = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++)
        {
            AtomOrder a = data.elementAt(i);
            ret[i][0] = a.bookTitle;
            ret[i][1] = a.count;
        }
        return ret;
    }

    private String name;
    private Vector<AtomOrder> data;
}

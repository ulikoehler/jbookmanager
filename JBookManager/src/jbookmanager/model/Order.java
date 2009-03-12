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

    public void setOrderCount(int index, int count)
    {
        data.elementAt(index).setCount(count);
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the data
     */
    public Vector<AtomOrder> getData()
    {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Vector<AtomOrder> data)
    {
        this.data = data;
    }

    public Order()
    {
        data = new Vector<AtomOrder>();
    }

    public void addOrder(String bookTitle, int count)
    {
        getData().add(new AtomOrder(bookTitle, count));
    }

    public void deleteOrder(int index)
    {
        getData().remove(index);
    }

    public double getPriceSum()
    {
        double sum = 0.0;
        for(AtomOrder a : getData())
        {
            sum += a.getCount() * LibraryManager.library.getBookByTitle(a.getBookTitle()).getPrice();
        }
        return sum;
    }

    public Object[][] getDataVector()
    {
        Object[][] ret = new Object[getData().size()][2];
        for (int i = 0; i < getData().size(); i++)
        {
            AtomOrder a = getData().elementAt(i);
            ret[i][0] = a.getBookTitle();
            ret[i][1] = a.getCount();
        }
        return ret;
    }

    private String name;
    private Vector<AtomOrder> data;
}

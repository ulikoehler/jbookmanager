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
        public Book b;
        public int count;
        public AtomOrder(Book b, int count)
        {
            this.b = b;
            this.count = count;
        }
    }

    public Order(String name)
    {
        this.name = name;
        data = new Vector<AtomOrder>();
    }

    public void addOrder(Book b, int count)
    {
        data.add(new AtomOrder(b, count));
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
            sum += a.count * a.b.getPrice();
        }
        return sum;
    }

    public Object[][] getData()
    {
        Object[][] ret = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++)
        {
            AtomOrder a = data.elementAt(i);
            ret[i][0] = a.b.getTitle();
            ret[i][1] = a.count;
        }
        return ret;
    }

    private String name;
    private Vector<AtomOrder> data;
}

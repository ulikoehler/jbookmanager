/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.Serializable;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 *
 * @author uli
 */
public class Order implements Serializable
{

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 13 * hash + (this.data != null ? this.data.hashCode() : 0);
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
        final Order other = (Order) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): Names do not match");
                    }
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data)))
        {
            for (AtomicOrder atomicOrder : data)
            {
                boolean state = false; //True if two books matched
                for (AtomicOrder otherAtomicOrder : other.data)
                {
                    if (atomicOrder.equals(otherAtomicOrder))
                    {
                        state = true;
                        break;
                    }
                }
                //If state == true, continue
                if (!state)
                {
                    if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): The atomic order referencing " + atomicOrder.getBookISBN() +
                                " doesn't match any atomic order in the other Order instance.");
                    }
                    return false;
                }
            }
        }
        return true;
    }

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
    public Vector<AtomicOrder> getData()
    {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Vector<AtomicOrder> data)
    {
        this.data = data;
    }

    public Order()
    {
        data = new Vector<AtomicOrder>();
    }

    public Order(String name)
    {
        data = new Vector<AtomicOrder>();
        this.name = name;
    }

    public void addAtomicOrder(String isbn, int count)
    {
        data.add(new AtomicOrder(isbn, count));
    }

    public void deleteOrder(int index)
    {
        getData().remove(index);
    }

    public double getPriceSum()
    {
        double sum = 0.0;
        for (AtomicOrder a : getData())
        {
            sum += a.getCount() * LibraryManager.library.getBookByTitle(a.getBookTitle()).getPrice();
        }
        return sum;
    }

    /**
     * Generates a 2-dimensional array of the data hold by this object
     * @return The data array
     */
    public Object[][] getDataVector()
    {
        Object[][] ret = new Object[getData().size()][3];
        for (int i = 0; i < getData().size(); i++)
        {
            AtomicOrder a = getData().elementAt(i);
            ret[i][0] = a.getBookTitle();
            ret[i][1] = a.getCount();
            ret[i][2] = LibraryManager.currencyFormat.format(a.getCount() * LibraryManager.library.getBookByTitle(a.
                    getBookTitle()).getPrice());
        }
        return ret;
    }
    private String name;
    private Vector<AtomicOrder> data;
    private static Logger logger = Logger.getLogger(Order.class);
}

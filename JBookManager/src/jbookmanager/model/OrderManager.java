/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.model;

import java.io.Serializable;
import jbookmanager.model.*;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 *
 * @author uli
 */
public class OrderManager implements Serializable
{
    private Vector<Order> orders;
    private Logger logger = Logger.getLogger(OrderManager.class);


    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + (this.orders != null ? this.orders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final OrderManager other = (OrderManager) obj;
        if (this.orders != other.orders && (this.orders == null || !this.orders.equals(other.orders)))
        {
            for (Order order : orders)
            {
                boolean state = false; //True if two books matched
                for (Order otherOrder : other.orders)
                {
                    if (order.equals(otherOrder))
                    {
                        state = true;break;
                    }
                }
                //If state == true, continue
                if (!state)
                {
                    if (logger.isTraceEnabled())
                    {
                        logger.log(Level.TRACE, "equals(): The order named " + order.getName() +
                                " doesn't match any order in the other Library instance.");
                    }
                    return false;
                }
            }
        }
        return true;
    }



    public OrderManager()
    {
        orders = new Vector<Order>();
    }

    public void addOrder(Order order)
    {
        getOrders().add(order);
    }

    public void removeOrder(int index)
    {
        getOrders().remove(index);
    }

    public Order getOrderAt(int index)
    {
        return getOrders().elementAt(index);
    }

    public void setOrderAt(Order order, int index)
    {
        getOrders().set(index, order);
    }

    public Object[][] getDataVector()
    {
        Object[][] ret = new Object[getOrders().size()][2];
        for (int i = 0; i < getOrders().size(); i++)
        {
            Order o = getOrders().elementAt(i);
            ret[i][0] = o.getName();
            ret[i][1] = LibraryManager.currencyFormat.format(o.getPriceSum());
        }
        return ret;
    }

    /**
     * @return the orders
     */
    public Vector<Order> getOrders()
    {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Vector<Order> orders)
    {
        this.orders = orders;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.controller;

import java.io.Serializable;
import jbookmanager.model.*;
import java.util.Vector;

/**
 *
 * @author uli
 */
public class OrderManager implements Serializable
{
    public Vector<Order> orders;

    public OrderManager()
    {
        orders = new Vector<Order>();
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public void removeOrder(int index)
    {
        orders.remove(index);
    }

    public Order getOrderAt(int index)
    {
        return orders.elementAt(index);
    }

    public void setOrderAt(Order order, int index)
    {
        orders.set(index, order);
    }

    public Object[][] getOrders()
    {
        Object[][] ret = new Object[orders.size()][2];
        for (int i = 0; i < orders.size(); i++)
        {
            Order o = orders.elementAt(i);
            ret[i][0] = o.getName();
            ret[i][1] = o.getPriceSum();
        }
        return ret;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.controller;

/**
 *
 * @author uli
 */

import java.util.List;
import javax.swing.table.AbstractTableModel;
import jbookmanager.model.Library;

public class LibraryTableModel extends AbstractTableModel
{
    private Library library;
    private static final String[] columnNames = {"ISBN","Title","Authors","Price","Count","Comment"};
    public LibraryTableModel(Library library)
    {
        this.library = library;
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0: return String.class; //ISBN
            case 1: return String.class; //Title
            case 2: return String.class; //Authors //TODO Add own panel class for this
            case 3: return double.class; //Price
            case 4: return int.class; //Count
            case 5: return String.class; //Comment
            default: throw new IllegalArgumentException("Illegal column index: " + columnIndex);
        }
    }

    /**
     * Generates a semicolon-separated list of the list elements
     * @param data the list to be converted
     * @return a semicolon-separated list of the list elements
     */
    private String listOverview(List<String> data)
    {
        String ret = "";
        for(String s : data)
        {
            ret += s + ";";
        }
        return ret;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        switch(column)
        {
            case 0: //ISBN
            {
                return library.getBookAt(row).getIsbn();
            }
            case 1: //Title
            {
                return library.getBookAt(row).getTitle();
            }
            case 2: //Authors
            {
                return listOverview(library.getBookAt(row).getAuthors());
            }
            case 3: //Price
            {
                return library.getBookAt(row).getPrice();
            }
            case 4: //Count
            {
                return library.getBookAt(row).getCount();
            }
            case 5: //Comment
            {
                return library.getBookAt(row).getComment();
            }
            default: return null;
        }
        //return null;
    }

    

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return library.getBooks().size();
    }
}

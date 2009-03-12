/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbookmanager.controller;

/**
 *
 * @author uli
 */
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import jbookmanager.model.Library;
import jbookmanager.view.JBookManagerFrame;

public class LibraryTableModel extends DefaultTableModel
{

    private Library library;
    private static final String[] columnNames =
    {
        "ISBN", "Title", "Price", "Count", "Comment"
    };

    public LibraryTableModel()
    {
        addListener();
    }

    public static String[] getColumnNames()
    {
        return columnNames;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    private void addListener()
    {
        //Add the editing listener
        addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e)
            {
                int row = e.getFirstRow();
                int column = e.getColumn();
                switch (column)
                {
                    case 0: //ISBN
                    {
                        library.getBookAt(row).setIsbn((String) getValueAt(row, column));
                        break;
                    }
                    case 1: //Title
                    {
                        library.getBookAt(row).setTitle((String) getValueAt(row, column));
                        break;
                    }
                    case 2: //Price
                    {
                        library.getBookAt(row).setPrice((Double) getValueAt(row, column));
                        break;
                    }
                    case 3: //Count
                    {
                        library.getBookAt(row).setCount((Integer) getValueAt(row, column));
                        break;
                    }
                    case 4: //Comment
                    {
                        library.getBookAt(row).setComment((String) getValueAt(row, column));
                        break;
                    }
                }
            }
        });
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return String.class; //ISBN
            case 1:
                return String.class; //Title
            case 2:
                return double.class; //Price
            case 3:
                return int.class; //Count
            case 4:
                return String.class; //Comment
            default:
                throw new IllegalArgumentException("Illegal column index: " + columnIndex);
        }
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        switch (column)
        {
            case 0: //ISBN
            {
                return JBookManagerFrame.library.getBookAt(row).getIsbn();
            }
            case 1: //Title
            {
                return JBookManagerFrame.library.getBookAt(row).getTitle();
            }
            case 2: //Price
            {
                return JBookManagerFrame.library.getBookAt(row).getPrice();
            }
            case 3: //Count
            {
                return JBookManagerFrame.library.getBookAt(row).getCount();
            }
            case 4: //Comment
            {
                return JBookManagerFrame.library.getBookAt(row).getComment();
            }
            default:
                throw new IllegalArgumentException("Illegal column index: " + column);
        }
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        if (JBookManagerFrame.library == null)
        {
            return 0;
        }
        return JBookManagerFrame.library.getBookCount();
    }
}

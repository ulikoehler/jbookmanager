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
import java.util.Vector;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import jbookmanager.model.Library;

public class LibraryTableModel extends AbstractTableModel
{

    private Library library;
    private static final String[] columnNames =
    {
        "ISBN", "Title", "Authors", "Price", "Count", "Comment"
    };

    public LibraryTableModel(Library library)
    {
        this.library = library;

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
                        library.getBookAt(row).setIsbn((String)getValueAt(row, column));
                        break;
                    }
                    case 1: //Title
                    {
                        library.getBookAt(row).setTitle((String)getValueAt(row, column));
                        break;
                    }
                    case 2: //Authors
                    {
                        library.getBookAt(row).setAuthors(LibraryManager.assembleList((String)getValueAt(row, column)));
                        break;
                    }
                    case 3: //Price
                    {
                        library.getBookAt(row).setPrice((Double)getValueAt(row, column));
                        break;
                    }
                    case 4: //Count
                    {
                        library.getBookAt(row).setCount((Integer)getValueAt(row, column));
                        break;
                    }
                    case 5: //Comment
                    {
                        library.getBookAt(row).setComment((String)getValueAt(row, column));
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
                return String.class; //Authors //TODO Add own panel class for this
            case 3:
                return double.class; //Price
            case 4:
                return int.class; //Count
            case 5:
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
                return library.getBookAt(row).getIsbn();
            }
            case 1: //Title
            {
                return library.getBookAt(row).getTitle();
            }
            case 2: //Authors
            {
                return LibraryManager.listOverview(library.getBookAt(row).getAuthors());
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
            default:
                return null;
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
        if (library == null)
        {
            return 0;
        }
        return library.getBooks().size();
    }
}

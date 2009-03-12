/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BookViewTable.java
 *
 * Created on 27.02.2009, 19:39:53
 */
package jbookmanager.view;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jbookmanager.model.Library;

/**
 *
 * @author uli
 */
public class BookViewTable extends JTable implements Serializable
{

    /** Creates new form BeanForm */
    public BookViewTable()
    {
        initComponents();
        setLibrary(new Library()); //Initialize with an empty library
    }

    public void setLibrary(Library library)
    {
        DefaultTableModel model = new DefaultTableModel(library.getData(), columnNames);
        setModel(model);
        //Active the row sorter (also filterer)
        sorter = new TableRowSorter<DefaultTableModel>(model);
        setRowSorter(sorter);
        //Add a table listener
        getModel().addTableModelListener(new TableModelListener()
        {

            public void tableChanged(TableModelEvent e)
            {
                int row = e.getFirstRow();
                int column = e.getColumn();
                switch (column)
                {
                    case 0: //ISBN
                    {
                        JBookManagerFrame.library.getBookAt(row).setIsbn((String) getValueAt(row,
                                                                                             column));
                        break;
                    }
                    case 1: //Title
                    {
                        JBookManagerFrame.library.getBookAt(row).setTitle(
                                (String) getValueAt(row, column));
                        break;
                    }
                    /**
                     * If the number entered by the user is not a valid double,
                     * a input dialog is displayed to enter a valid value.
                     * If this value is invalid (as double) too,
                     * the value is set to the previous one and
                     * an information message is displayed.
                     */
                    case 2: //Price
                    {
                        double lastVal = JBookManagerFrame.library.getBookAt(row).getPrice();
                        try
                        {
                            String value = (String) getValueAt(row, column);
                            value = value.replace(',', '.'); //Delocalize the input
                            double doubleVal = new Double(value);
                            JBookManagerFrame.library.getBookAt(row).setPrice(doubleVal);
                        }
                        catch (NumberFormatException ex)
                        {
                            String value = JOptionPane.showInputDialog(null,
                                                                       i18n.getString(
                                    "This value must be a floating-point integer. New value:"),
                                                                       JBookManagerFrame.library.getBookAt(row).
                                    getPrice());
                            //Delocalize the input
                            value = value.replace(',', '.');
                            try
                            {
                                double doubleValue = new Double(value);
                                /**
                                 * If the value entered is not valid this line is not executed
                                 * because the previous line throws a NumberFormatException
                                 */
                                JBookManagerFrame.library.getBookAt(row).setPrice(doubleValue);
                            }
                            catch (NumberFormatException ex2)
                            {
                                JOptionPane.showMessageDialog(null,
                                                              i18n.getString(
                                        "Invalid floating-point integer. Setting price to previous value"),
                                                              i18n.getString("Invalid value"),
                                                              JOptionPane.INFORMATION_MESSAGE);
                                JBookManagerFrame.library.getBookAt(row).setPrice(lastVal);
                                /**
                                 * At this state the non-double (bad) value is in the table
                                 * and the good value is in the library, so just update the table data
                                 */
                                updateData(JBookManagerFrame.library);
                            }
                        }
                        break;
                    }
                    /**
                     * See price column (above) for explanation
                     */
                    case 3: //Count
                    {
                        int lastVal = JBookManagerFrame.library.getBookAt(row).getCount();
                        try
                        {
                            //Delocalize the input
                            String value = (String) getValueAt(row, column);
                            value = value.replace(',', '.'); //Delocalize the input
                            int intVal = new Integer(value);
                            JBookManagerFrame.library.getBookAt(row).setCount(intVal);
                        }
                        catch (NumberFormatException ex)
                        {
                            String value =
                                    JOptionPane.showInputDialog(null,
                                                                i18n.getString(
                                    "This value must be an Integer. New value:"),
                                                                JBookManagerFrame.library.getBookAt(row).getCount());
                            //Delocalize the input
                            value = value.replace(',', '.');
                            try
                            {
                                int intVal = new Integer(value);
                                /**
                                 * If the value entered is not valid this line is not executed
                                 * because the previous line throws a NumberFormatException
                                 */
                                JBookManagerFrame.library.getBookAt(row).setCount(intVal);
                            }
                            catch (NumberFormatException ex2)
                            {
                                JOptionPane.showMessageDialog(null,
                                                              i18n.getString(
                                        "Invalid Integer. Setting count to previous value."),
                                                              i18n.getString("Invalid value"),
                                                              JOptionPane.INFORMATION_MESSAGE);
                                JBookManagerFrame.library.getBookAt(row).setCount(lastVal);
                                /**
                                 * At this state the non-integer (bad) value is in the table
                                 * and the good value is in the library, so just update the table data
                                 */
                                updateData(JBookManagerFrame.library);
                            }
                        }
                        break;
                    }
                    case 4: //Comment
                    {
                        JBookManagerFrame.library.getBookAt(row).setComment(
                                (String) getValueAt(row, column));
                        break;
                    }
                }
            }
        });
    }

    public void containsFilter(String substr, int... column)
    {
        sorter.setRowFilter(RowFilter.regexFilter(".*" + substr + ".*", column));
    }

    public void isFilter(String substr, int... column)
    {
        sorter.setRowFilter(RowFilter.regexFilter(substr));
    }

    public void greaterFilter(String substr, int... column)
    {
        //TODO implement in main frame
        sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER,
                                                   new Double(substr), column));
    }

    public void lessFilter(String substr, int... column)
    {
        //TODO implement in main frame
        sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, new Double(
                substr), column));
    }

    public void regexFilter(String regex, int... column)
    {
        sorter.setRowFilter(RowFilter.regexFilter(regex, column));
    }

    public void deleteFilter()
    {
        sorter.setRowFilter(null);
    }

    /**
     * Updates the table content
     * @param library
     */
    public void updateData(Library library)
    {
        ((DefaultTableModel) getModel()).setDataVector(library.getData(), columnNames);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    private TableRowSorter<DefaultTableModel> sorter;
    private static final String[] columnNames =
    {
        "ISBN", "Title", "Price", "Count", "Comment"
    };
    private ResourceBundle i18n = ResourceBundle.getBundle("jbookmanager/view/Bundle");
    //Column specifiers
    public static final int ISBN_COLUMN = 0;
    public static final int TITLE_COLUMN = 1;
    public static final int PRICE_COLUMN = 2;
    public static final int COUNT_COLUMN = 3;
    public static final int COMMENT_COLUMN = 4;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

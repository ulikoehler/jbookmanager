/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbookmanager.controller;

import javax.swing.table.DefaultTableModel;

/**
 * Simple class extension for DefaultTableModel:
 * Disables editing of all cells
 * @author uli
 */
public class StaticTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }


}

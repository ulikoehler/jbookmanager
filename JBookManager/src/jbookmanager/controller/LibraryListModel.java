package jbookmanager.controller;


import javax.swing.DefaultListModel;
import jbookmanager.view.JBookManagerFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uli
 */
public class LibraryListModel extends DefaultListModel
{
    @Override
    public Object getElementAt(int index)
    {
        return JBookManagerFrame.library.getBookAt(index).getTitle();
    }



    @Override
    public int getSize()
    {
        return JBookManagerFrame.library.getBookCount();
    }
}

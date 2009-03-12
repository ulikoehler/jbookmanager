/*
 * NumberSpinner.java
 * A spinner with easier number access
 * Created on 28.02.2009, 19:22:18
 */
package jbookmanager.view;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author uli
 */
public class NumberSpinner extends JSpinner
{

    /** Creates new form BeanForm */
    public NumberSpinner()
    {
        initComponents();
    }

    public int getIntValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().intValue();
    }

    public long getLongValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().longValue();
    }

    public float getFloatValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().floatValue();
    }

    public double getDoubleValue()
    {
        return ((SpinnerNumberModel) getModel()).getNumber().doubleValue();
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
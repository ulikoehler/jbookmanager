/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListEditDialog.java
 *
 * Created on 27.02.2009, 15:10:34
 */

package jbookmanager.view;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 *
 * @author uli
 */
public class ListEditDialog extends javax.swing.JDialog {

    /** Creates new form ListEditDialog
     * @param parent
     * @param modal
     */
    public ListEditDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setData(List<String> data)
    {
        for(String s : data)
        {
            //Ensure the newline character at the end
            if(!s.endsWith("\n"))
            {
                s += "\n";
            }
            textArea.append(s);
        }
    }

    /**
     * @return a vector containing each line of the text as an element
     */
    public Vector<String> getData()
    {
        Vector<String> ret = new Vector<String>();
        String data = textArea.getText();
        String[] lines = data.split("\n");
        for(String s : lines)
        {
            ret.add(s);
        }
        return ret;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        okButton.setText( i18n.getString("ListEditDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText( i18n.getString("ListEditDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListEditDialog dialog = new ListEditDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private ResourceBundle i18n = ResourceBundle.getBundle("jbookmanager/view/Bundle");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

}

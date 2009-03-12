/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewBookDialog.java
 *
 * Created on 26.02.2009, 20:11:39
 */
package jbookmanager.view;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import jbookmanager.model.Book;

/**
 *
 * @author uli
 */
public class NewBookDialog extends javax.swing.JDialog
{

    /**
     * Creates new form NewBookDialog
     * @param parent
     * @param modal
     */
    public NewBookDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        isbnLabel = new javax.swing.JLabel();
        isbnFormattedField = new javax.swing.JFormattedTextField();
        titleLabel = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        authorsLabel = new javax.swing.JLabel();
        commentLabel = new javax.swing.JLabel();
        commentField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        listEditComboBox1 = new jbookmanager.view.ListEditComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle( i18n.getString("NewBookDialog.title")); // NOI18N

        isbnLabel.setText( i18n.getString("NewBookDialog.isbnLabel.text")); // NOI18N

        try {
            isbnFormattedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        titleLabel.setText( i18n.getString("NewBookDialog.titleLabel.text")); // NOI18N

        titleField.setText( i18n.getString("NewBookDialog.titleField.text")); // NOI18N

        authorsLabel.setText( i18n.getString("NewBookDialog.authorsLabel.text")); // NOI18N

        commentLabel.setText( i18n.getString("NewBookDialog.commentLabel.text")); // NOI18N

        okButton.setText( i18n.getString("NewBookDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(isbnLabel))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isbnFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titleField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(authorsLabel)
                            .addComponent(commentLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listEditComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(commentField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isbnLabel)
                    .addComponent(isbnFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorsLabel)
                    .addComponent(listEditComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commentLabel)
                    .addComponent(commentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        Book b = new Book();
        //ISBN
        b.setIsbn(isbnFormattedField.getText());
        //Title
        String title = titleField.getText();
        if (title.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "The title mustn't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        b.setTitle(title);
        //Authors
        List<String> authors = new LinkedList<String>();
        b.setAuthors(authors);
        //Comment
        b.setComment(commentField.getText());

    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                NewBookDialog dialog = new NewBookDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    private ResourceBundle i18n = ResourceBundle.getBundle("jbookmanager/view/Bundle");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorsLabel;
    private javax.swing.JTextField commentField;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JFormattedTextField isbnFormattedField;
    private javax.swing.JLabel isbnLabel;
    private jbookmanager.view.ListEditComboBox listEditComboBox1;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField titleField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}

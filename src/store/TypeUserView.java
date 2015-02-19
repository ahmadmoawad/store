/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author null
 */
public class TypeUserView extends BaseView implements IAction {

    private TypeUserTableModel model;
    private JTable table;
    private UserModel user;

    /**
     * Creates new form TypeUserView
     *
     * @param user
     * @throws java.lang.Exception
     */
    public TypeUserView(UserModel user) throws Exception {
        super(user);
        initComponents();
        updateInterfaceSelf();
        this.user = user;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Permission");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TypeUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TypeUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TypeUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TypeUserView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TypeUserView(null).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(TypeUserView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void jButtonDeleteAction(ActionEvent e) {

        int[] rows = table.getSelectedRows();

        for (int i = 0; i < rows.length; i++) {
            System.out.println(rows[i]);
            TypeUserModel typeuser = model.getRow(rows[i]);
            TypeUserController.setUpdateUser(typeuser, Operation.DELETE);
        }
        updateInterfaceSelf();

    }

    @Override
    public void jButtonUpdateAction(ActionEvent e) {
        try {
            TypeUserUpdate typeUserUpdate = new TypeUserUpdate(this, rootPaneCheckingEnabled, user);
            int selectedRow = table.getSelectedRow();

            TypeUserModel typeUser = model.getRow(selectedRow);

            typeUserUpdate.setTypeUserModel(typeUser);
            typeUserUpdate.setVisible(true);

            if (typeUserUpdate.isClosed()) {

                TypeUserModel typeUserModel = typeUserUpdate.getTypeUserModel();
                TypeUserController.setUpdateUser(typeUserModel, Operation.UPDATE);
                updateInterfaceSelf();

            }
        } catch (Exception ex) {
            Logger.getLogger(TypeUserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void jButtonInsertAction(ActionEvent e) {
        try {
            TypeUserNew typeUserNewDialog = new TypeUserNew(this, rootPaneCheckingEnabled, user);
            typeUserNewDialog.setVisible(true);

            if (typeUserNewDialog.isClosed()) {
                TypeUserModel typeUser = typeUserNewDialog.getTypeUser();
                TypeUserController.setUpdateUser(typeUser, Operation.INSERT);
                updateInterfaceSelf();

            }
        } catch (Exception ex) {
            Logger.getLogger(TypeUserView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateInterfaceSelf() {
        table = getTable();
        refershTable(TypeUserController.getTypes());

    }

    private void refershTable(ArrayList<TypeUserModel> users) {

        model = new TypeUserTableModel(users);
        table.setModel(model);
        if (table.isEditing()) {

            table.revalidate();
        }
    }

    @Override
    public void aInsertText() {
        aChangedText();
    }

    @Override
    public void aRemovedText() {
        aChangedText();
    }

    @Override
    public void aChangedText() {

        JTextField textSearch = getTextSearch();
        ArrayList<TypeUserModel> typeBYName = TypeUserController.getTypeByLike(textSearch.getText());
        refershTable(typeBYName);

    }

    /**
     *
     * @return
     */
    @Override
    public File aExport() {
        File file = super.aExport();
        if (file != null) {

            model.exportAsPDF(file.getPath());
        }
        return null;
    }

}

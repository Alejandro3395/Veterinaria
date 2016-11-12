/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class ClientRegisterView extends javax.swing.JFrame {

    /**
     * Creates new form CustomerRegisterView
     */
    public ClientRegisterView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_register = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        lbl_tittle = new javax.swing.JLabel();
        lbl_clientName = new javax.swing.JLabel();
        lbl_clientPhone = new javax.swing.JLabel();
        lbl_clientAddress = new javax.swing.JLabel();
        lbl_clientPhoneLada = new javax.swing.JLabel();
        field_clientName = new javax.swing.JTextField();
        field_clientPhoneLada = new javax.swing.JTextField();
        lbl_clientPhoneNumber = new javax.swing.JLabel();
        field_clientPhoneNumber = new javax.swing.JTextField();
        lbl_clientAddressStreet = new javax.swing.JLabel();
        field_clientAddressStreet = new javax.swing.JTextField();
        lbl_clientAddressColony = new javax.swing.JLabel();
        field_clientAddressColony = new javax.swing.JTextField();
        lbl_clientAddressPostalCode = new javax.swing.JLabel();
        field_clientAddressPostalCode = new javax.swing.JTextField();
        lbl_clientAddressCrossing = new javax.swing.JLabel();
        field_clientAddressCrossing = new javax.swing.JTextField();
        lbl_clientEmail = new javax.swing.JLabel();
        field_clientEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_register.setText("Registrar");

        btn_cancel.setText("Cancelar");

        lbl_tittle.setText("Registro de Clientes");

        lbl_clientName.setText("Nombre: ");

        lbl_clientPhone.setText("Telefono");

        lbl_clientAddress.setText("Direccion");

        lbl_clientPhoneLada.setText("Lada:");

        lbl_clientPhoneNumber.setText("Digitos:");

        lbl_clientAddressStreet.setText("Calle:");

        lbl_clientAddressColony.setText("Colonia:");

        lbl_clientAddressPostalCode.setText("Numero Postal:");

        lbl_clientAddressCrossing.setText("Cruzamientos:");

        lbl_clientEmail.setText("Correo Electronico:");

        field_clientEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_clientEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btn_cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_register)
                .addGap(81, 81, 81))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_tittle)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_clientName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(field_clientName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_clientPhone)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_clientAddressStreet)
                                    .addComponent(lbl_clientAddress))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_clientAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_clientAddressColony)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_clientAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_clientAddressPostalCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_clientAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_clientAddressCrossing)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_clientAddressCrossing, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lbl_clientPhoneLada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_clientPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(lbl_clientPhoneNumber)
                        .addGap(18, 18, 18)
                        .addComponent(field_clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_clientEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_clientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbl_tittle)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_clientName)
                    .addComponent(field_clientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(lbl_clientPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_clientPhoneLada)
                    .addComponent(field_clientPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_clientPhoneNumber)
                    .addComponent(field_clientPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lbl_clientAddress)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_clientAddressStreet)
                            .addComponent(field_clientAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_clientAddressColony)
                            .addComponent(field_clientAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_clientAddressPostalCode)
                            .addComponent(field_clientAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_clientAddressCrossing)
                            .addComponent(field_clientAddressCrossing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(lbl_clientEmail))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(field_clientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register)
                    .addComponent(btn_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_clientEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_clientEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_clientEmailActionPerformed

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
            java.util.logging.Logger.getLogger(ClientRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientRegisterView().setVisible(true);
            }
        });
    }

    public JButton getBtn_cancel() {
        return btn_cancel;
    }

    public void setBtn_cancel(JButton btn_cancel) {
        this.btn_cancel = btn_cancel;
    }

    public JButton getBtn_register() {
        return btn_register;
    }

    public void setBtn_register(JButton btn_register) {
        this.btn_register = btn_register;
    }

    public JTextField getField_clientAddressColony() {
        return field_clientAddressColony;
    }

    public void setField_clientAddressColony(JTextField field_clientAddressColony) {
        this.field_clientAddressColony = field_clientAddressColony;
    }

    public JTextField getField_clientAddressCrossing() {
        return field_clientAddressCrossing;
    }

    public void setField_clientAddressCrossing(JTextField field_clientAddressCrossing) {
        this.field_clientAddressCrossing = field_clientAddressCrossing;
    }

    public JTextField getField_clientAddressPostalCode() {
        return field_clientAddressPostalCode;
    }

    public void setField_clientAddressPostalCode(JTextField field_clientAddressPostalCode) {
        this.field_clientAddressPostalCode = field_clientAddressPostalCode;
    }

    public JTextField getField_clientAddressStreet() {
        return field_clientAddressStreet;
    }

    public void setField_clientAddressStreet(JTextField field_clientAddressStreet) {
        this.field_clientAddressStreet = field_clientAddressStreet;
    }

    public JTextField getField_clientEmail() {
        return field_clientEmail;
    }

    public void setField_clientEmail(JTextField field_clientEmail) {
        this.field_clientEmail = field_clientEmail;
    }

    public JTextField getField_clientName() {
        return field_clientName;
    }

    public void setField_clientName(JTextField field_clientName) {
        this.field_clientName = field_clientName;
    }

    public JTextField getField_clientPhoneLada() {
        return field_clientPhoneLada;
    }

    public void setField_clientPhoneLada(JTextField field_clientPhoneLada) {
        this.field_clientPhoneLada = field_clientPhoneLada;
    }

    public JTextField getField_clientPhoneNumber() {
        return field_clientPhoneNumber;
    }

    public void setField_clientPhoneNumber(JTextField field_clientPhoneNumber) {
        this.field_clientPhoneNumber = field_clientPhoneNumber;
    }

    public JLabel getLbl_clientAddress() {
        return lbl_clientAddress;
    }

    public void setLbl_clientAddress(JLabel lbl_clientAddress) {
        this.lbl_clientAddress = lbl_clientAddress;
    }

    public JLabel getLbl_clientAddressColony() {
        return lbl_clientAddressColony;
    }

    public void setLbl_clientAddressColony(JLabel lbl_clientAddressColony) {
        this.lbl_clientAddressColony = lbl_clientAddressColony;
    }

    public JLabel getLbl_clientAddressCrossing() {
        return lbl_clientAddressCrossing;
    }

    public void setLbl_clientAddressCrossing(JLabel lbl_clientAddressCrossing) {
        this.lbl_clientAddressCrossing = lbl_clientAddressCrossing;
    }

    public JLabel getLbl_clientAddressPostalCode() {
        return lbl_clientAddressPostalCode;
    }

    public void setLbl_clientAddressPostalCode(JLabel lbl_clientAddressPostalCode) {
        this.lbl_clientAddressPostalCode = lbl_clientAddressPostalCode;
    }

    public JLabel getLbl_clientAddressStreet() {
        return lbl_clientAddressStreet;
    }

    public void setLbl_clientAddressStreet(JLabel lbl_clientAddressStreet) {
        this.lbl_clientAddressStreet = lbl_clientAddressStreet;
    }

    public JLabel getLbl_clientEmail() {
        return lbl_clientEmail;
    }

    public void setLbl_clientEmail(JLabel lbl_clientEmail) {
        this.lbl_clientEmail = lbl_clientEmail;
    }

    public JLabel getLbl_clientName() {
        return lbl_clientName;
    }

    public void setLbl_clientName(JLabel lbl_clientName) {
        this.lbl_clientName = lbl_clientName;
    }

    public JLabel getLbl_clientPhone() {
        return lbl_clientPhone;
    }

    public void setLbl_clientPhone(JLabel lbl_clientPhone) {
        this.lbl_clientPhone = lbl_clientPhone;
    }

    public JLabel getLbl_clientPhoneLada() {
        return lbl_clientPhoneLada;
    }

    public void setLbl_clientPhoneLada(JLabel lbl_clientPhoneLada) {
        this.lbl_clientPhoneLada = lbl_clientPhoneLada;
    }

    public JLabel getLbl_clientPhoneNumber() {
        return lbl_clientPhoneNumber;
    }

    public void setLbl_clientPhoneNumber(JLabel lbl_clientPhoneNumber) {
        this.lbl_clientPhoneNumber = lbl_clientPhoneNumber;
    }

    public JLabel getLbl_tittle() {
        return lbl_tittle;
    }

    public void setLbl_tittle(JLabel lbl_tittle) {
        this.lbl_tittle = lbl_tittle;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_register;
    private javax.swing.JTextField field_clientAddressColony;
    private javax.swing.JTextField field_clientAddressCrossing;
    private javax.swing.JTextField field_clientAddressPostalCode;
    private javax.swing.JTextField field_clientAddressStreet;
    private javax.swing.JTextField field_clientEmail;
    private javax.swing.JTextField field_clientName;
    private javax.swing.JTextField field_clientPhoneLada;
    private javax.swing.JTextField field_clientPhoneNumber;
    private javax.swing.JLabel lbl_clientAddress;
    private javax.swing.JLabel lbl_clientAddressColony;
    private javax.swing.JLabel lbl_clientAddressCrossing;
    private javax.swing.JLabel lbl_clientAddressPostalCode;
    private javax.swing.JLabel lbl_clientAddressStreet;
    private javax.swing.JLabel lbl_clientEmail;
    private javax.swing.JLabel lbl_clientName;
    private javax.swing.JLabel lbl_clientPhone;
    private javax.swing.JLabel lbl_clientPhoneLada;
    private javax.swing.JLabel lbl_clientPhoneNumber;
    private javax.swing.JLabel lbl_tittle;
    // End of variables declaration//GEN-END:variables
}

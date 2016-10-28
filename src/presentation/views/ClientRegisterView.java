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
        lbl_customerName = new javax.swing.JLabel();
        lbl_customerPhone = new javax.swing.JLabel();
        lbl_customerAddress = new javax.swing.JLabel();
        lbl_customerPhoneLada = new javax.swing.JLabel();
        field_customerName = new javax.swing.JTextField();
        field_customerPhoneLada = new javax.swing.JTextField();
        lbl_customerPhoneNumber = new javax.swing.JLabel();
        field_customerPhoneNumber = new javax.swing.JTextField();
        lbl_customerAddressStreet = new javax.swing.JLabel();
        field_customerAddressStreet = new javax.swing.JTextField();
        lbl_customerAddressColony = new javax.swing.JLabel();
        field_customerAddressColony = new javax.swing.JTextField();
        lbl_customerAddressPostalCode = new javax.swing.JLabel();
        field_customerAddressPostalCode = new javax.swing.JTextField();
        lbl_customerAddressCrossing = new javax.swing.JLabel();
        field_customerAddressCrossing = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        field_customerEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_register.setText("Registrar");

        btn_cancel.setText("Cancelar");

        lbl_tittle.setText("Registro de Clientes");

        lbl_customerName.setText("Nombre: ");

        lbl_customerPhone.setText("Telefono");

        lbl_customerAddress.setText("Direccion");

        lbl_customerPhoneLada.setText("Lada:");

        lbl_customerPhoneNumber.setText("Digitos:");

        lbl_customerAddressStreet.setText("Calle:");

        lbl_customerAddressColony.setText("Colonia:");

        lbl_customerAddressPostalCode.setText("Numero Postal:");

        lbl_customerAddressCrossing.setText("Cruzamientos:");

        jLabel1.setText("Correo Electronico:");

        field_customerEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_customerEmailActionPerformed(evt);
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
                                .addComponent(lbl_customerName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(field_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_customerPhone)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_customerAddressStreet)
                                    .addComponent(lbl_customerAddress))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_customerAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_customerAddressColony)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_customerAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_customerAddressPostalCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_customerAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_customerAddressCrossing)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_customerAddressCrossing, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lbl_customerPhoneLada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_customerPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(lbl_customerPhoneNumber)
                        .addGap(18, 18, 18)
                        .addComponent(field_customerPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(field_customerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbl_tittle)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_customerName)
                    .addComponent(field_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(lbl_customerPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_customerPhoneLada)
                    .addComponent(field_customerPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_customerPhoneNumber)
                    .addComponent(field_customerPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lbl_customerAddress)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_customerAddressStreet)
                            .addComponent(field_customerAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_customerAddressColony)
                            .addComponent(field_customerAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_customerAddressPostalCode)
                            .addComponent(field_customerAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_customerAddressCrossing)
                            .addComponent(field_customerAddressCrossing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(field_customerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register)
                    .addComponent(btn_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_customerEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_customerEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_customerEmailActionPerformed

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

    public JTextField getField_customerEmail() {
        return field_customerEmail;
    }

    public void setField_customerEmail(JTextField field_customerEmail) {
        this.field_customerEmail = field_customerEmail;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public void setBtn_register(JButton btn_register) {
        this.btn_register = btn_register;
    }

    public JTextField getField_customerAddressColony() {
        return field_customerAddressColony;
    }

    public void setField_customerAddressColony(JTextField field_customerAddressColony) {
        this.field_customerAddressColony = field_customerAddressColony;
    }

    public JTextField getField_customerAddressCrossing() {
        return field_customerAddressCrossing;
    }

    public void setField_customerAddressCrossing(JTextField field_customerAddressCrossing) {
        this.field_customerAddressCrossing = field_customerAddressCrossing;
    }

    public JTextField getField_customerAddressPostalCode() {
        return field_customerAddressPostalCode;
    }

    public void setField_customerAddressPostalCode(JTextField field_customerAddressPostalCode) {
        this.field_customerAddressPostalCode = field_customerAddressPostalCode;
    }

    public JTextField getField_customerAddressStreet() {
        return field_customerAddressStreet;
    }

    public void setField_customerAddressStreet(JTextField field_customerAddressStreet) {
        this.field_customerAddressStreet = field_customerAddressStreet;
    }

    public JTextField getField_customerName() {
        return field_customerName;
    }

    public void setField_customerName(JTextField field_customerName) {
        this.field_customerName = field_customerName;
    }

    public JTextField getField_customerPhoneLada() {
        return field_customerPhoneLada;
    }

    public void setField_customerPhoneLada(JTextField field_customerPhoneLada) {
        this.field_customerPhoneLada = field_customerPhoneLada;
    }

    public JTextField getField_customerPhoneNumber() {
        return field_customerPhoneNumber;
    }

    public void setField_customerPhoneNumber(JTextField field_customerPhoneNumber) {
        this.field_customerPhoneNumber = field_customerPhoneNumber;
    }

    public JLabel getLbl_customerAddress() {
        return lbl_customerAddress;
    }

    public void setLbl_customerAddress(JLabel lbl_customerAddress) {
        this.lbl_customerAddress = lbl_customerAddress;
    }

    public JLabel getLbl_customerAddressColony() {
        return lbl_customerAddressColony;
    }

    public void setLbl_customerAddressColony(JLabel lbl_customerAddressColony) {
        this.lbl_customerAddressColony = lbl_customerAddressColony;
    }

    public JLabel getLbl_customerAddressCrossing() {
        return lbl_customerAddressCrossing;
    }

    public void setLbl_customerAddressCrossing(JLabel lbl_customerAddressCrossing) {
        this.lbl_customerAddressCrossing = lbl_customerAddressCrossing;
    }

    public JLabel getLbl_customerAddressPostalCode() {
        return lbl_customerAddressPostalCode;
    }

    public void setLbl_customerAddressPostalCode(JLabel lbl_customerAddressPostalCode) {
        this.lbl_customerAddressPostalCode = lbl_customerAddressPostalCode;
    }

    public JLabel getLbl_customerAddressStreet() {
        return lbl_customerAddressStreet;
    }

    public void setLbl_customerAddressStreet(JLabel lbl_customerAddressStreet) {
        this.lbl_customerAddressStreet = lbl_customerAddressStreet;
    }

    public JLabel getLbl_customerName() {
        return lbl_customerName;
    }

    public void setLbl_customerName(JLabel lbl_customerName) {
        this.lbl_customerName = lbl_customerName;
    }

    public JLabel getLbl_customerPhone() {
        return lbl_customerPhone;
    }

    public void setLbl_customerPhone(JLabel lbl_customerPhone) {
        this.lbl_customerPhone = lbl_customerPhone;
    }

    public JLabel getLbl_customerPhoneLada() {
        return lbl_customerPhoneLada;
    }

    public void setLbl_customerPhoneLada(JLabel lbl_customerPhoneLada) {
        this.lbl_customerPhoneLada = lbl_customerPhoneLada;
    }

    public JLabel getLbl_customerPhoneNumber() {
        return lbl_customerPhoneNumber;
    }

    public void setLbl_customerPhoneNumber(JLabel lbl_customerPhoneNumber) {
        this.lbl_customerPhoneNumber = lbl_customerPhoneNumber;
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
    private javax.swing.JTextField field_customerAddressColony;
    private javax.swing.JTextField field_customerAddressCrossing;
    private javax.swing.JTextField field_customerAddressPostalCode;
    private javax.swing.JTextField field_customerAddressStreet;
    private javax.swing.JTextField field_customerEmail;
    private javax.swing.JTextField field_customerName;
    private javax.swing.JTextField field_customerPhoneLada;
    private javax.swing.JTextField field_customerPhoneNumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_customerAddress;
    private javax.swing.JLabel lbl_customerAddressColony;
    private javax.swing.JLabel lbl_customerAddressCrossing;
    private javax.swing.JLabel lbl_customerAddressPostalCode;
    private javax.swing.JLabel lbl_customerAddressStreet;
    private javax.swing.JLabel lbl_customerName;
    private javax.swing.JLabel lbl_customerPhone;
    private javax.swing.JLabel lbl_customerPhoneLada;
    private javax.swing.JLabel lbl_customerPhoneNumber;
    private javax.swing.JLabel lbl_tittle;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class DoctorRegisterView extends javax.swing.JFrame {

    /**
     * Creates new form DoctorRegisterView
     */
    public DoctorRegisterView() {
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

        lbl_tittle = new javax.swing.JLabel();
        btn_register = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        field_doctorAddressPostalCode = new javax.swing.JTextField();
        field_doctorAddressCross = new javax.swing.JTextField();
        lbl_doctorName = new javax.swing.JLabel();
        lbl_doctorPhone = new javax.swing.JLabel();
        lbl_doctorAddress = new javax.swing.JLabel();
        field_doctorName = new javax.swing.JTextField();
        lbl_personalData = new javax.swing.JLabel();
        lbl_doctorPhoneLada = new javax.swing.JLabel();
        field_doctorPhoneLada = new javax.swing.JTextField();
        lbl_doctorPhoneNumber = new javax.swing.JLabel();
        field_doctorPhoneNumber = new javax.swing.JTextField();
        lbl_doctorAddressStreet = new javax.swing.JLabel();
        lbl_doctorAddressColony = new javax.swing.JLabel();
        lbl_doctorAddressPostalCode = new javax.swing.JLabel();
        lbl_doctorAddressCross = new javax.swing.JLabel();
        field_doctorAddressStreet = new javax.swing.JTextField();
        field_doctorAddressColony = new javax.swing.JTextField();
        lbl_doctorEmail = new javax.swing.JLabel();
        field_doctorEmail = new javax.swing.JTextField();
        lbl_doctorRFC = new javax.swing.JLabel();
        lbl_doctorProfessionalCode = new javax.swing.JLabel();
        field_doctorRFC = new javax.swing.JTextField();
        field_doctorProfessionalCode = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_userData = new javax.swing.JLabel();
        lbl_doctorUserName = new javax.swing.JLabel();
        lbl_doctorUserPassword = new javax.swing.JLabel();
        field_doctorUserName = new javax.swing.JTextField();
        field_doctorUserPassword = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_tittle.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        lbl_tittle.setText("Registro de Doctor");

        btn_register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Check.png"))); // NOI18N

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Cancel.png"))); // NOI18N

        field_doctorAddressPostalCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_doctorAddressPostalCodeActionPerformed(evt);
            }
        });

        lbl_doctorName.setText("Nombre:");

        lbl_doctorPhone.setText("Telefono");

        lbl_doctorAddress.setText("Direccion");

        field_doctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_doctorNameActionPerformed(evt);
            }
        });

        lbl_personalData.setText("Datos personales");

        lbl_doctorPhoneLada.setText("Clave lada:");

        field_doctorPhoneLada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_doctorPhoneLadaActionPerformed(evt);
            }
        });

        lbl_doctorPhoneNumber.setText("Digitos:");

        lbl_doctorAddressStreet.setText("calle:");

        lbl_doctorAddressColony.setText("colonia:");

        lbl_doctorAddressPostalCode.setText("numeroPostal:");

        lbl_doctorAddressCross.setText("cruzamientos:");

        lbl_doctorEmail.setText("Correo Electronico:");

        lbl_doctorRFC.setText("RFC:");

        lbl_doctorProfessionalCode.setText("Cedula Profesional:");

        lbl_userData.setText("Datos de usuario");

        lbl_doctorUserName.setText("Nombre de usuario:");

        lbl_doctorUserPassword.setText("Contraseña:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/doctor 3.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_doctorAddress)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_doctorRFC)
                                            .addComponent(lbl_doctorProfessionalCode))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(field_doctorRFC)
                                            .addComponent(field_doctorProfessionalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_doctorAddressStreet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(field_doctorAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(lbl_doctorAddressColony)
                                        .addGap(18, 18, 18)
                                        .addComponent(field_doctorAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_doctorUserName)
                                            .addComponent(lbl_doctorUserPassword))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(field_doctorUserPassword)
                                            .addComponent(field_doctorUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_doctorAddressPostalCode)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(field_doctorAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(lbl_doctorAddressCross)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(field_doctorAddressCross, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(178, 178, 178)
                                        .addComponent(lbl_userData))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addComponent(btn_cancel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_register))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(lbl_doctorEmail)
                                            .addGap(18, 18, 18)
                                            .addComponent(field_doctorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbl_tittle))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_doctorPhoneLada)
                                        .addGap(49, 49, 49)
                                        .addComponent(field_doctorPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_doctorPhoneNumber)
                                        .addGap(18, 18, 18)
                                        .addComponent(field_doctorPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_doctorName)
                                        .addGap(54, 54, 54)
                                        .addComponent(field_doctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(lbl_personalData)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(lbl_doctorPhone)
                    .addContainerGap(592, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_tittle)
                        .addGap(17, 17, 17)
                        .addComponent(lbl_personalData)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_doctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_doctorName))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorPhoneLada)
                            .addComponent(field_doctorPhoneLada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_doctorPhoneNumber)
                            .addComponent(field_doctorPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(lbl_doctorAddress)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorAddressStreet)
                            .addComponent(field_doctorAddressStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_doctorAddressColony)
                            .addComponent(field_doctorAddressColony, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorRFC)
                            .addComponent(field_doctorRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorProfessionalCode)
                            .addComponent(field_doctorProfessionalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_userData)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorUserName)
                            .addComponent(field_doctorUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_doctorUserPassword)
                            .addComponent(field_doctorUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_doctorEmail)
                                    .addComponent(field_doctorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_doctorAddressPostalCode)
                                .addComponent(field_doctorAddressPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_doctorAddressCross)
                                .addComponent(field_doctorAddressCross, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_register)
                    .addComponent(btn_cancel))
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(lbl_doctorPhone)
                    .addContainerGap(608, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_doctorAddressPostalCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_doctorAddressPostalCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_doctorAddressPostalCodeActionPerformed

    private void field_doctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_doctorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_doctorNameActionPerformed

    private void field_doctorPhoneLadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_doctorPhoneLadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_doctorPhoneLadaActionPerformed

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
            java.util.logging.Logger.getLogger(DoctorRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorRegisterView().setVisible(true);
            }
        });
    }

    public JTextField getField_doctorAddressColony() {
        return field_doctorAddressColony;
    }

    public void setField_doctorAddressColony(JTextField field_doctorAddressColony) {
        this.field_doctorAddressColony = field_doctorAddressColony;
    }

    public JTextField getField_doctorAddressCross() {
        return field_doctorAddressCross;
    }

    public void setField_doctorAddressCross(JTextField field_doctorAddressCross) {
        this.field_doctorAddressCross = field_doctorAddressCross;
    }

    public JTextField getField_doctorAddressPostalCode() {
        return field_doctorAddressPostalCode;
    }

    public void setField_doctorAddressPostalCode(JTextField field_doctorAddressPostalCode) {
        this.field_doctorAddressPostalCode = field_doctorAddressPostalCode;
    }

    public JTextField getField_doctorAddressStreet() {
        return field_doctorAddressStreet;
    }

    public void setField_doctorAddressStreet(JTextField field_doctorAddressStreet) {
        this.field_doctorAddressStreet = field_doctorAddressStreet;
    }

    public JTextField getField_doctorEmail() {
        return field_doctorEmail;
    }

    public void setField_doctorEmail(JTextField field_doctorEmail) {
        this.field_doctorEmail = field_doctorEmail;
    }

    public JTextField getField_doctorUserPassword() {
        return field_doctorUserPassword;
    }

    public void setField_doctorUserPassword(JTextField field_doctorPassword) {
        this.field_doctorUserPassword = field_doctorPassword;
    }

    public JTextField getField_doctorPhoneLada() {
        return field_doctorPhoneLada;
    }

    public void setField_doctorPhoneLada(JTextField field_doctorPhoneLada) {
        this.field_doctorPhoneLada = field_doctorPhoneLada;
    }

    public JTextField getField_doctorPhoneNumber() {
        return field_doctorPhoneNumber;
    }

    public void setField_doctorPhoneNumber(JTextField field_doctorPhoneNumber) {
        this.field_doctorPhoneNumber = field_doctorPhoneNumber;
    }

    public JTextField getField_doctorProfessionalCode() {
        return field_doctorProfessionalCode;
    }

    public void setField_doctorProfessionalCode(JTextField field_doctorProfessionalCode) {
        this.field_doctorProfessionalCode = field_doctorProfessionalCode;
    }

    public JTextField getField_doctorRFC() {
        return field_doctorRFC;
    }

    public void setField_doctorRFC(JTextField field_doctorRFC) {
        this.field_doctorRFC = field_doctorRFC;
    }

    public JTextField getField_doctorUserName() {
        return field_doctorUserName;
    }

    public void setField_doctorUserName(JTextField field_doctorUserName) {
        this.field_doctorUserName = field_doctorUserName;
    }

    public JTextField getField_doctorName() {
        return field_doctorName;
    }

    public void setField_doctorName(JTextField field_employeeName) {
        this.field_doctorName = field_employeeName;
    }



    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JLabel getLbl_doctorAddress() {
        return lbl_doctorAddress;
    }

    public void setLbl_doctorAddress(JLabel lbl_doctorAddress) {
        this.lbl_doctorAddress = lbl_doctorAddress;
    }

    public JLabel getLbl_doctorAddressColony() {
        return lbl_doctorAddressColony;
    }

    public void setLbl_doctorAddressColony(JLabel lbl_doctorAddressColony) {
        this.lbl_doctorAddressColony = lbl_doctorAddressColony;
    }

    public JLabel getLbl_doctorAddressCross() {
        return lbl_doctorAddressCross;
    }

    public void setLbl_doctorAddressCross(JLabel lbl_doctorAddressCross) {
        this.lbl_doctorAddressCross = lbl_doctorAddressCross;
    }

    public JLabel getLbl_doctorAddressPostalCode() {
        return lbl_doctorAddressPostalCode;
    }

    public void setLbl_doctorAddressPostalCode(JLabel lbl_doctorAddressPostalCode) {
        this.lbl_doctorAddressPostalCode = lbl_doctorAddressPostalCode;
    }

    public JLabel getLbl_doctorAddressStreet() {
        return lbl_doctorAddressStreet;
    }

    public void setLbl_doctorAddressStreet(JLabel lbl_doctorAddressStreet) {
        this.lbl_doctorAddressStreet = lbl_doctorAddressStreet;
    }

    public JLabel getLbl_doctorEmail() {
        return lbl_doctorEmail;
    }

    public void setLbl_doctorEmail(JLabel lbl_doctorEmail) {
        this.lbl_doctorEmail = lbl_doctorEmail;
    }

    public JLabel getLbl_doctorName() {
        return lbl_doctorName;
    }

    public void setLbl_doctorName(JLabel lbl_doctorName) {
        this.lbl_doctorName = lbl_doctorName;
    }


    public JLabel getLbl_doctorPhone() {
        return lbl_doctorPhone;
    }

    public void setLbl_doctorPhone(JLabel lbl_doctorPhone) {
        this.lbl_doctorPhone = lbl_doctorPhone;
    }

    public JLabel getLbl_doctorPhoneLada() {
        return lbl_doctorPhoneLada;
    }

    public void setLbl_doctorPhoneLada(JLabel lbl_doctorPhoneLada) {
        this.lbl_doctorPhoneLada = lbl_doctorPhoneLada;
    }

    public JLabel getLbl_doctorPhoneNumber() {
        return lbl_doctorPhoneNumber;
    }

    public void setLbl_doctorPhoneNumber(JLabel lbl_doctorPhoneNumber) {
        this.lbl_doctorPhoneNumber = lbl_doctorPhoneNumber;
    }

    public JLabel getLbl_doctorProfessionalCode() {
        return lbl_doctorProfessionalCode;
    }

    public void setLbl_doctorProfessionalCode(JLabel lbl_doctorProfessionalCode) {
        this.lbl_doctorProfessionalCode = lbl_doctorProfessionalCode;
    }

    public JLabel getLbl_doctorRFC() {
        return lbl_doctorRFC;
    }

    public void setLbl_doctorRFC(JLabel lbl_doctorRFC) {
        this.lbl_doctorRFC = lbl_doctorRFC;
    }


    public JLabel getLbl_personalData() {
        return lbl_personalData;
    }

    public void setLbl_personalData(JLabel lbl_personalData) {
        this.lbl_personalData = lbl_personalData;
    }

    public JLabel getLbl_tittle() {
        return lbl_tittle;
    }

    public void setLbl_tittle(JLabel lbl_tittle) {
        this.lbl_tittle = lbl_tittle;
    }

    public JLabel getLbl_userData() {
        return lbl_userData;
    }
  
    public void setLbl_userData(JLabel lbl_userData) {
        this.lbl_userData = lbl_userData;
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

    public JLabel getLbl_doctorUserName() {
        return lbl_doctorUserName;
    }

    public void setLbl_doctorUserName(JLabel lbl_doctorUserName) {
        this.lbl_doctorUserName = lbl_doctorUserName;
    }

    public JLabel getLbl_doctorUserPassword() {
        return lbl_doctorUserPassword;
    }

    public void setLbl_doctorUserPassword(JLabel lbl_doctorUserPassword) {
        this.lbl_doctorUserPassword = lbl_doctorUserPassword;
    }
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_register;
    private javax.swing.JTextField field_doctorAddressColony;
    private javax.swing.JTextField field_doctorAddressCross;
    private javax.swing.JTextField field_doctorAddressPostalCode;
    private javax.swing.JTextField field_doctorAddressStreet;
    private javax.swing.JTextField field_doctorEmail;
    private javax.swing.JTextField field_doctorName;
    private javax.swing.JTextField field_doctorPhoneLada;
    private javax.swing.JTextField field_doctorPhoneNumber;
    private javax.swing.JTextField field_doctorProfessionalCode;
    private javax.swing.JTextField field_doctorRFC;
    private javax.swing.JTextField field_doctorUserName;
    private javax.swing.JTextField field_doctorUserPassword;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_doctorAddress;
    private javax.swing.JLabel lbl_doctorAddressColony;
    private javax.swing.JLabel lbl_doctorAddressCross;
    private javax.swing.JLabel lbl_doctorAddressPostalCode;
    private javax.swing.JLabel lbl_doctorAddressStreet;
    private javax.swing.JLabel lbl_doctorEmail;
    private javax.swing.JLabel lbl_doctorName;
    private javax.swing.JLabel lbl_doctorPhone;
    private javax.swing.JLabel lbl_doctorPhoneLada;
    private javax.swing.JLabel lbl_doctorPhoneNumber;
    private javax.swing.JLabel lbl_doctorProfessionalCode;
    private javax.swing.JLabel lbl_doctorRFC;
    private javax.swing.JLabel lbl_doctorUserName;
    private javax.swing.JLabel lbl_doctorUserPassword;
    private javax.swing.JLabel lbl_personalData;
    private javax.swing.JLabel lbl_tittle;
    private javax.swing.JLabel lbl_userData;
    // End of variables declaration//GEN-END:variables
}

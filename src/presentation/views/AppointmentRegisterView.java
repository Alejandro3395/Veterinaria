/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.views;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author jozapata
 */
public class AppointmentRegisterView extends javax.swing.JFrame {

    /**
     * Creates new form scheduleView
     */
    public AppointmentRegisterView() {
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
        lbl_tittle = new javax.swing.JLabel();
        btn_cancel = new javax.swing.JButton();
        lbl_date = new javax.swing.JLabel();
        lbl_client = new javax.swing.JLabel();
        lbl_pet = new javax.swing.JLabel();
        combo_client = new javax.swing.JComboBox<>();
        combo_pet = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Check.png"))); // NOI18N
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        lbl_tittle.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        lbl_tittle.setText("Ingresa los datos de la cita");

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Cancel.png"))); // NOI18N

        lbl_date.setText("Fecha");

        lbl_client.setText("Cliente:");

        lbl_pet.setText("Mascota:");

        combo_pet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        combo_pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_petActionPerformed(evt);
            }
        });

        dateChooser.setDateFormatString("yyyy-MM-dd hh:ss");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/calendar.png"))); // NOI18N
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_tittle))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_client)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lbl_pet))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(combo_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_client, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_date)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btn_cancel)
                .addGap(84, 84, 84)
                .addComponent(btn_register)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_tittle)
                .addGap(33, 33, 33)
                .addComponent(lbl_client)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combo_client, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lbl_pet)
                        .addGap(27, 27, 27)
                        .addComponent(combo_pet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lbl_date)
                        .addGap(27, 27, 27)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_register)
                    .addComponent(btn_cancel))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_registerActionPerformed

    private void combo_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_petActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_petActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppointmentRegisterView().setVisible(true);
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

    public JComboBox<String> getCombo_client() {
        return combo_client;
    }

    public void setCombo_client(JComboBox<String> combo_client) {
        this.combo_client = combo_client;
    }

    public JComboBox<String> getCombo_pet() {
        return combo_pet;
    }

    public void setCombo_pet(JComboBox<String> combo_pet) {
        this.combo_pet = combo_pet;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }


    public JLabel getLbl_client() {
        return lbl_client;
    }

    public void setLbl_client(JLabel lbl_client) {
        this.lbl_client = lbl_client;
    }

    public JLabel getLbl_date() {
        return lbl_date;
    }

    public void setLbl_date(JLabel lbl_date) {
        this.lbl_date = lbl_date;
    }

    public JLabel getLbl_pet() {
        return lbl_pet;
    }

    public void setLbl_pet(JLabel lbl_pet) {
        this.lbl_pet = lbl_pet;
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
    private javax.swing.JComboBox<String> combo_client;
    private javax.swing.JComboBox<String> combo_pet;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_client;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_pet;
    private javax.swing.JLabel lbl_tittle;
    // End of variables declaration//GEN-END:variables
}

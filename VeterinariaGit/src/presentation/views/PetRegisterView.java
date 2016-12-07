/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class PetRegisterView extends javax.swing.JFrame {

    /**
     * Creates new form PetRegisterview
     */
    public PetRegisterView() {
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
        lbl_petType = new javax.swing.JLabel();
        combo_petType = new javax.swing.JComboBox<>();
        lbl_petName = new javax.swing.JLabel();
        lbl_petAge = new javax.swing.JLabel();
        spiner_petAge = new javax.swing.JSpinner();
        field_petName = new javax.swing.JTextField();
        btn_register = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_tittle.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        lbl_tittle.setText("Registro de Mascotas");

        lbl_petType.setText("Raza:");

        combo_petType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Pez" }));

        lbl_petName.setText("Nombre:");

        lbl_petAge.setText("Edad:");

        spiner_petAge.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        btn_register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Check.png"))); // NOI18N
        btn_register.setToolTipText("");

        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Cancel.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Module1/icons-large/Pet2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_petAge)
                                .addGap(27, 27, 27)
                                .addComponent(spiner_petAge, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_petType)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_petType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cancel)
                                .addGap(41, 41, 41)
                                .addComponent(btn_register)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbl_tittle))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_petName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_petName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_tittle)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_petName)
                    .addComponent(field_petName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_petAge)
                    .addComponent(spiner_petAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_petType)
                    .addComponent(combo_petType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_register))
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(PetRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PetRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PetRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PetRegisterView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PetRegisterView().setVisible(true);
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


    public JTextField getField_petName() {
        return field_petName;
    }

    public void setField_petName(JTextField field_petName) {
        this.field_petName = field_petName;
    }

    public JLabel getLbl_petAge() {
        return lbl_petAge;
    }

    public void setLbl_petAge(JLabel lbl_petAge) {
        this.lbl_petAge = lbl_petAge;
    }

    public JComboBox<String> getCombo_petType() {
        return combo_petType;
    }

    public void setCombo_petType(JComboBox<String> combo_petType) {
        this.combo_petType = combo_petType;
    }

    public JLabel getLbl_petType() {
        return lbl_petType;
    }

    public void setLbl_petType(JLabel lbl_petType) {
        this.lbl_petType = lbl_petType;
    }

    

    public JLabel getLbl_petName() {
        return lbl_petName;
    }

    public void setLbl_petName(JLabel lbl_petName) {
        this.lbl_petName = lbl_petName;
    }


    public JLabel getLbl_tittle() {
        return lbl_tittle;
    }

    public void setLbl_tittle(JLabel lbl_tittle) {
        this.lbl_tittle = lbl_tittle;
    }

    public JSpinner getSpiner_petAge() {
        return spiner_petAge;
    }

    public void setSpiner_petAge(JSpinner spiner_petAge) {
        this.spiner_petAge = spiner_petAge;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_register;
    private javax.swing.JComboBox<String> combo_petType;
    private javax.swing.JTextField field_petName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_petAge;
    private javax.swing.JLabel lbl_petName;
    private javax.swing.JLabel lbl_petType;
    private javax.swing.JLabel lbl_tittle;
    private javax.swing.JSpinner spiner_petAge;
    // End of variables declaration//GEN-END:variables
}
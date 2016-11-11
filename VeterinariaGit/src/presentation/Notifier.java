/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.swing.*;

/**
 *
 * @author Jorge
 */
public class Notifier {

    private int YES_OPTION = JOptionPane.YES_OPTION;
    private int NO_OPTION = JOptionPane.NO_OPTION;

    public Notifier() {
    }

    public void showSuccessMessage(String title, String message) {
        int PLAIN_MESSAGE = JOptionPane.PLAIN_MESSAGE;
        showMessageDialog(title, message, PLAIN_MESSAGE);
    }

    public void showFailMessage(String message) {
        String title = "Error";
        int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
        showMessageDialog(title, message, ERROR_MESSAGE);
    }

    public void showWarningMessage(String message) {
        String title = "Advertencia";
        int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
        showMessageDialog(title, message, WARNING_MESSAGE);
    }

    public int showConfirmDialog(String message) {
        int confirmDialog = JOptionPane.showConfirmDialog(null, message);
        return confirmDialog;
    }

    private void showMessageDialog(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }


    public void setNO_OPTION(int NO_OPTION) {
        this.NO_OPTION = NO_OPTION;
    }

    public void setYES_OPTION(int YES_OPTION) {
        this.YES_OPTION = YES_OPTION;
    }

    public int getNO_OPTION() {
        return NO_OPTION;
    }

    public int getYES_OPTION() {
        return YES_OPTION;
    }

}
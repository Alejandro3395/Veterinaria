/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Jorge
 */
public abstract class AbstractRegisterController  {
    protected abstract ArrayList<String> obtainData();
    public abstract void openWindow();

    protected void configureWindow(JFrame window) {
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }

    protected abstract void initializeView();

    protected abstract void setEvents();
    
    protected boolean isEmptyFields(ArrayList<String> data){
        boolean result = false;
        for(int i =0; i < data.size(); i++){
            if(data.get(i).isEmpty()){
                System.out.println("campo invalido");
                result = true;
            }
        }
        return result;
    }
}

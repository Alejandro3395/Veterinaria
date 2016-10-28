/**
* class: AbstractRegisterController (AbstractRegisterController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the controller for the view classes.
* The main idea in making the class abstract is to create a father class type 
* because all controllers require three main methods:
* 1. configure window
* 2. set events
* 3. initialize view
* 
* Also this class is diferent from the abstractViewController because
* this one is the father of the register controllers.
* 
*/

package presentation;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
    
    /**
     * This method checks if there is an empty field in the form.
     * @param data
     * @return 
     */
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
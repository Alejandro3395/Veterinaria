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
import exceptions.InvalidFieldException;

public abstract class InformationViewHelper extends ViewHelper  {
    protected abstract ArrayList<String> obtainDataFromView();
    protected abstract void clearFields();
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
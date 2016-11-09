/**
* class: PetRegisterController (PetRegisterController.java)
* @author: Diego Nicoli
* 
* date: October 27, 2016
* 
* This class represent the controller for the Pet entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/
package presentation.controllers;

import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.PetRegisterView;


public class PetRegisterController extends AbstractRegisterController {
    private PetRegisterView petRegisterView;
    
    public PetRegisterController(){
        setPetRegisterView(new PetRegisterView());
        
        initializeView();
    }

    public PetRegisterView getPetRegisterView() {
        return petRegisterView;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }
    
    
    @Override
    public void openWindow() {
        getPetRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getPetRegisterView() );
        getPetRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    /**
     * This method set the listeners into the view buttons.
     */

    @Override
    protected void setEvents() {
        getPetRegisterView().getBtn_register().addActionListener(actionEvent -> registerPet());
    }
   
    /**
     *  This method uses sends the data the view provides to the manager.
     */
   
    private void registerPet(){
         ArrayList<String> data = new ArrayList<String>(obtainData());
        
    }

     /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    
    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList <String>();//To change body of generated methods, choose Tools | Templates.
        String petOwner = getPetRegisterView().getCombo_petOwner().getSelectedItem().toString();
        data.add(petOwner);
        
        String petBreed = getPetRegisterView().getCombo_petBreed().getSelectedItem().toString();
        data.add(petBreed);
        
        
        String petName = getPetRegisterView().getField_petName().getText();
        data.add(petName);
        
        String petAge = getPetRegisterView().getSpiner_petAge().getValue().toString();
        
        
        
        return data;
    }
    
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.PetManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.AbstractViewController;
import presentation.views.PetRegisterView;

/**
 *
 * @author Jorge
 */
public class PetRegisterController extends AbstractRegisterController {
    private PetRegisterView petRegisterView;
    private PetManagerHelper petManagerHelper = null;
    
    private String owner ;
    
    public PetRegisterController(PetManagerHelper petManager){
        setPetRegisterView(new PetRegisterView());
        setPetManagerHelper( petManager  );
        
        initializeView();
    }

    public PetRegisterController( String owner ) {
        setPetRegisterView( new PetRegisterView());
        this.owner = owner;
        initializeView();
    }
    
    

    public PetManagerHelper getPetManagerHelper() {
        return petManagerHelper;
    }

    public void setPetManagerHelper(PetManagerHelper petManagerHelper) {
        this.petManagerHelper = petManagerHelper;
    }

    public PetRegisterView getPetRegisterView() {
        return petRegisterView;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }
    
    private void updateManagerViewTable(){
        getPetManagerHelper().updateTable();
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
        getPetRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        getPetRegisterView().getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        getPetRegisterView().dispose();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        
        ArrayList<String> petData = new ArrayList<String>(obtainData());
        
        boolean isValidField =!isEmptyFields(petData);
        
        if(petManagerHelper != null){
            owner = petManagerHelper.getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        }
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            PetManager petManager = PetManager.GetInstance();
            message = petManager.registerPet(petData,owner);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Pet");
                if(petManagerHelper != null){
                    updateManagerViewTable();
                }
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            }
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
        }
    }

    
     /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainData() {
        ArrayList<String> data = new ArrayList<String>();
        
        String petName = getPetRegisterView().getField_petName().getText();
        data.add(petName);
        
        String petAge = getPetRegisterView().getSpiner_petAge().getValue().toString();
        data.add(petAge);
        
        String petBreed = getPetRegisterView().getCombo_petBreed().getSelectedItem().toString();
        data.add(petBreed);
          
        return data;
    }
    
}

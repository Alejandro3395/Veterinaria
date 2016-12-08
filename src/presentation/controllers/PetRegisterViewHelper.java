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

import bussiness.PetInformationHandler;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.InformationViewHelper;
import presentation.ViewHelper;
import presentation.views.PetRegisterView;

/**
 *
 * @author Jorge
 */
public class PetRegisterViewHelper extends InformationViewHelper {
    private static PetRegisterViewHelper petRegisterViewHelper;
    private PetRegisterView petRegisterView;
    
    private String owner = null ;
    private static int registerPetMode = 0;
    private static int registerClientMode = 1;
    private int mode = 0;
    
    public PetRegisterViewHelper(){
        setPetRegisterView(new PetRegisterView());
        initializeView();
    }

    public PetRegisterViewHelper( String owner ) {
        setPetRegisterView( new PetRegisterView());
        this.owner = owner;
        initializeView();
    }
    
    public static PetRegisterViewHelper getInstance(){
        if( petRegisterViewHelper== null) {
         petRegisterViewHelper = new PetRegisterViewHelper();
        }
        return petRegisterViewHelper;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }
    
    
    @Override
    public void loadView() {
        petRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( petRegisterView );
        petRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        petRegisterView.getBtn_register().addActionListener(actionEvent -> registerPet());
        petRegisterView.getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeView();
    }
    
    private void closeView(){
        clearFields();
        petRegisterView.dispose();
        PetManagerViewHelper.getInstance().loadView();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void registerPet(){
        ArrayList<String> petData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField =!isEmptyFields(petData);
        
        String owner = PetManagerViewHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        
        String message="";
        
        if(isValidField){
            try{
               PetInformationHandler petInformationHandler = PetInformationHandler.GetInstance();
               petInformationHandler.registerPet(petData,owner); 
               getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Pet");             
               closeView();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
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
    protected ArrayList<String> obtainDataFromView() {
        ArrayList<String> data = new ArrayList<String>();
        
        String petName = petRegisterView.getField_petName().getText();
        data.add(petName);
        
        String petAge = petRegisterView.getSpiner_petAge().getValue().toString();
        data.add(petAge);
        
        String petType = petRegisterView.getCombo_petType().getSelectedItem().toString();
        data.add(petType);
          
        return data;
    }
    
    @Override
    protected void clearFields() {
        petRegisterView.getField_petName().setText("");
        
        petRegisterView.getSpiner_petAge().setValue(0);
        
        petRegisterView.getCombo_petType().setSelectedIndex(0);
    }
}
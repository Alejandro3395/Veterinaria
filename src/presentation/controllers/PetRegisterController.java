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
    private static PetRegisterController petRegisterController;
    private PetRegisterView petRegisterView;
    private PetManagerHelper petManagerHelper = null;
    
    private String owner = null ;
    private static int registerPetMode = 0;
    private static int registerClientMode = 1;
    private int mode = 0;
    
    public PetRegisterController(){
        setPetRegisterView(new PetRegisterView());
        //setPetManagerHelper( petManager  );
        
        initializeView();
    }

    public PetRegisterController( String owner ) {
        setPetRegisterView( new PetRegisterView());
        this.owner = owner;
        initializeView();
    }
    
    public static PetRegisterController getInstance(){
        if( petRegisterController== null) {
         petRegisterController = new PetRegisterController();
        }
        return petRegisterController;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
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
        PetManagerHelper.getInstance().updateTable();
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
        
        if(mode != 0){
            owner = PetManagerHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        }
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            PetManager petManager = PetManager.GetInstance();
            message = petManager.registerPet(petData,owner);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Pet");
                if(mode == registerClientMode ){
                    updateManagerViewTable();
                }
                resetFields();
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
    
    private void resetFields(){
        getPetRegisterView().getField_petName().setText("");
        
        getPetRegisterView().getSpiner_petAge().setValue(0);
        
        getPetRegisterView().getCombo_petBreed().setSelectedIndex(0);
    }
}
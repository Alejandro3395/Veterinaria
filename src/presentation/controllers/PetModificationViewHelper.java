/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Pet;
import bussiness.ClientManager;
import bussiness.PetManager;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.PetRegisterView;

/**
 *
 * @author Jorge
 */
public class PetModificationViewHelper extends DataViewHelper {
    private static PetModificationViewHelper petModificationViewHelper;
    private PetRegisterView petRegisterView;
    
    public PetModificationViewHelper(){
        setPetRegisterView( new PetRegisterView() );
        
        initializeView();
    }

    public static PetModificationViewHelper getInstance(){
        if( petModificationViewHelper== null) {
         petModificationViewHelper = new PetModificationViewHelper();
        }
        return petModificationViewHelper;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }

    @Override
    public void loadView() {
        loadPetData();
        petRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( petRegisterView );
        petRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        petRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        petRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadPetData(){
        int rowIndex = PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = PetManagerViewHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        PetManager petManager = PetManager.GetInstance();
        List<Pet> petList =  petManager.getPetList(petOwner);
        Pet pet = petList.get(rowIndex);
        
        setData(pet);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int rowIndex = PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = PetManagerViewHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
               PetManager petManager = PetManager.GetInstance();
               petManager.modifyPet(data,petOwner,rowIndex);
               getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Pet");
               updateManagerViewTable();
               closeWindow();
            }catch(InvalidFieldException exception){
                message = exception.getMessage();
                getNotifier().showWarningMessage( message );
            }
            
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        PetManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        petRegisterView.dispose();
        clearFields();
        PetManagerViewHelper.getInstance().loadView();
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
        
        String petBreed = petRegisterView.getCombo_petBreed().getSelectedItem().toString();
        data.add(petBreed);        
          
        return data;
    }
    
    private void setData(Pet pet){
        //setear datos de los campso
        
        String petName = pet.getName().toString();
        petRegisterView.getField_petName().setText(petName);
        
        int petAge = (pet.getAge());
        petRegisterView.getSpiner_petAge().setValue(petAge);
        
        String petBreed = pet.getBreed();
        
        int comboElements = petRegisterView.getCombo_petBreed().getItemCount();
        int breedIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(petBreed.equals(petRegisterView.getCombo_petBreed().getSelectedItem().toString())){
                breedIndex = i;
            }
        }
        
        petRegisterView.getCombo_petBreed().setSelectedIndex(breedIndex);        
        
    }
    
    private void resetFields(){
        loadPetData();
    }
    
    @Override
    protected void clearFields() {
        petRegisterView.getField_petName().setText("");
        
        petRegisterView.getSpiner_petAge().setValue(0);
        
        petRegisterView.getCombo_petBreed().setSelectedIndex(0);
    }
    
}
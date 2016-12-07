/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Pet;
import bussiness.ClientHandler;
import bussiness.PetHandler;
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
        int row = PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getSelectedRow();
        int id = Integer.valueOf( PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getValueAt(row, 0).toString() );
        
        String petOwner = PetManagerViewHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        PetHandler petManager = PetHandler.GetInstance();
        List<Pet> petList =  petManager.getPetList(petOwner);
        Pet pet = petList.get(id);
        
        setData(pet);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getSelectedRow();
        int id = Integer.valueOf( PetManagerViewHelper.getInstance().getPetManagerView().getTable_petTable().getValueAt(row, 0).toString() );
        
        String petOwner = PetManagerViewHelper.getInstance().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
               PetHandler petManager = PetHandler.GetInstance();
               petManager.modifyPet(data,petOwner,id);
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
        
        String petType = petRegisterView.getCombo_petType().getSelectedItem().toString();
        data.add(petType);        
          
        return data;
    }
    
    private void setData(Pet pet){
        //setear datos de los campso
        
        String petName = pet.getName().toString();
        petRegisterView.getField_petName().setText(petName);
        
        int petAge = (pet.getAge());
        petRegisterView.getSpiner_petAge().setValue(petAge);
        
        String petType = pet.getType();
        
        int comboElements = petRegisterView.getCombo_petType().getItemCount();
        int typeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(petType.equals(petRegisterView.getCombo_petType().getItemAt(i).toString())){
                typeIndex = i;
            }
        }
        
        petRegisterView.getCombo_petType().setSelectedIndex(typeIndex); 
        petRegisterView.getCombo_petType().setEnabled(false);
        
    }
    
    private void resetFields(){
        loadPetData();
    }
    
    @Override
    protected void clearFields() {
        petRegisterView.getField_petName().setText("");
        
        petRegisterView.getSpiner_petAge().setValue(0);
        
        petRegisterView.getCombo_petType().setSelectedIndex(0);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Pet;
import bussiness.ClientManager;
import bussiness.PetManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.AbstractRegisterController;
import presentation.views.PetRegisterView;

/**
 *
 * @author Jorge
 */
public class PetModificationHelper extends AbstractRegisterController {
    private PetRegisterView petRegisterView;
    private PetManagerHelper petManagerHelper;
    private String owner = null;
    
    public PetModificationHelper(PetManagerHelper petManager){
        setPetRegisterView( new PetRegisterView() );
        setPetManagerHelper( petManager);
        
        initializeView();
    }


    public PetRegisterView getPetRegisterView() {
        return petRegisterView;
    }

    public void setPetRegisterView(PetRegisterView petRegisterView) {
        this.petRegisterView = petRegisterView;
    }

    public PetManagerHelper getPetManagerHelper() {
        return petManagerHelper;
    }

    public void setPetManagerHelper(PetManagerHelper petManagerHelper) {
        this.petManagerHelper = petManagerHelper;
    }

    @Override
    public void openWindow() {
        loadPetData();
        getPetRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getPetRegisterView() );
        getPetRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getPetRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getPetRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadPetData(){
        int rowIndex = getPetManagerHelper().getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = getPetManagerHelper().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        PetManager petManager = PetManager.GetInstance();
        List<Pet> petList =  petManager.getPetList(petOwner);
        Pet pet = petList.get(rowIndex);
        
        setData(pet);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int rowIndex = getPetManagerHelper().getPetManagerView().getTable_petTable().getSelectedRow();
        String petOwner = getPetManagerHelper().getPetManagerView().getCombo_petOwner().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            PetManager petManager = PetManager.GetInstance();
            message = petManager.modifyPet(data,petOwner,rowIndex);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Pet");
                updateManagerViewTable();
                closeWindow();
            }else{
                getNotifier().showWarningMessage( message );
            } 
        }else{
            message = "Rellene todos los campos";
            getNotifier().showWarningMessage( message );
            resetFields();
        }
    }
    
    private void updateManagerViewTable(){
        getPetManagerHelper().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getPetRegisterView().dispose();
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
    
    private void setData(Pet pet){
        //setear datos de los campso
        
        String petName = pet.getName().toString();
        getPetRegisterView().getField_petName().setText(petName);
        
        int petAge = (pet.getAge());
        getPetRegisterView().getSpiner_petAge().setValue(petAge);
        
        String petBreed = pet.getBreed();
        
        int comboElements = getPetRegisterView().getCombo_petBreed().getItemCount();
        int breedIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(petBreed.equals(getPetRegisterView().getCombo_petBreed().getSelectedItem().toString())){
                breedIndex = i;
            }
        }
        
        getPetRegisterView().getCombo_petBreed().setSelectedIndex(breedIndex);        
        
    }
    
    private void resetFields(){
        loadPetData();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.MedicineManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.MedicineRegisterView;

/**
 *
 * @author Jorge
 */
public class MedicineRegisterController extends AbstractViewController {
    private MedicineRegisterView medicineRegisterView;
    
    
    public MedicineRegisterController(){
        setMedicineRegisterView(new MedicineRegisterView());
        
        initializeView();
    }

    public MedicineRegisterView getMedicineRegisterView() {
        return medicineRegisterView;
    }

    public void setMedicineRegisterView(MedicineRegisterView medicineRegisterView) {
        this.medicineRegisterView = medicineRegisterView;
    }
    
    
    
    @Override
    public void openWindow() {
        getMedicineRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getMedicineRegisterView() );
        getMedicineRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
       getMedicineRegisterView().getBtn_register().addActionListener(actionEvent -> registerMedicine());
    }
    
    private void registerMedicine(){
        
        ArrayList<String> data = new ArrayList<String>(obtainData());
        boolean isValidField = !isEmptyFields(data);
        
        System.out.println(isValidField);
        if(isValidField){
            String medicineName = data.get(0);
            int  medicineQuantity =  Integer.valueOf(data.get(1)).intValue();
            double medicineSellPrice =  Double.valueOf(data.get(2)).doubleValue();
        
            String medicineSupplier = data.get(3);
            String medicineAdministrationWay = data.get(4);
        
            //obtener la fecha
            String medicineExpirationDate = data.get(5);
        
            //obtener la dosis
            String medicineDose= data.get(6);
             boolean isValidData = isValidMedicine(
                medicineName,
                medicineQuantity,
                medicineSellPrice,
                medicineSupplier,
                medicineAdministrationWay,
                medicineExpirationDate,
                medicineDose);
             
             if(isValidData){
                 
             }
            
        }
        
    }
    
    private ArrayList<String> obtainData(){
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(getMedicineRegisterView().getField_productName().getText());
        data.add(getMedicineRegisterView().getField_productQuantity().toString());
        data.add(getMedicineRegisterView().getField_productSellPrize().toString());
        
        data.add(getMedicineRegisterView().getCombo_productSupplier().getSelectedItem().toString());
        data.add(getMedicineRegisterView().getCombo_productAdministrationWay().getSelectedItem().toString());
        
        //obtener la fecha
        String expirationDate = "";
        
        String expirationDay = getMedicineRegisterView().getSpinner_productExpirationDay().toString();
        String expirationMonth = getMedicineRegisterView().getSpinner_productExpirationMonth().toString();
        String expirationYear = getMedicineRegisterView().getSpinner_productExpirationYear().toString();
        
        expirationDate = expirationDay + " " + expirationMonth + " " + expirationYear;
        
        data.add(expirationDate);
        
        //obtener la dosis
        String dose= "";
        
        String doseQuantity = getMedicineRegisterView().getSpinner_productDoseQuantity().toString();
        String doseType = getMedicineRegisterView().getCombo_productDoseQuantityType().toString();
        String dosePeriod = getMedicineRegisterView().getSpinner_productDosePeriod().toString();
        String dosePeriodType = getMedicineRegisterView().getCombo_dosePeriodType().toString();
        
        dose = doseQuantity+ " " + doseType + " " + dosePeriod + " " + dosePeriodType;
        
        data.add(dose);
        
        return data;
    }
    
    private boolean isEmptyFields(ArrayList<String> data){
        boolean result = false;
        for(int i =0; i < data.size(); i++){
            if(data.get(i).isEmpty()){
                System.out.println("llegue");
                result = true;
            }
        }
        return result;
    }
    
    private boolean isValidMedicine(
            String name,
            int quantity,
            double sellPrice,
            String supplier,
            String administrationWay,
            String expirationDate,
            String dose
    ){
        
        boolean answer = false;
        
        MedicineManager medicineManager = MedicineManager.GetInstance();
        
        answer = medicineManager.verifyMedicine(
            name,
            quantity,
            sellPrice,
            supplier,
            administrationWay,
            expirationDate,
            dose);
        
        return answer;
    }
    
    
    
}

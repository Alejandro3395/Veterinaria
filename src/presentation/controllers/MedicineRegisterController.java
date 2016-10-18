/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import bussiness.MedicineManager;
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
        
        String medicineName = getMedicineRegisterView().getField_productName().getText();
        int  medicineQuantity =  Integer.valueOf(getMedicineRegisterView().getField_productQuantity().getText()).intValue();
        String quantity = getMedicineRegisterView().getField_productQuantity().toString();
        double medicineSellPrice =  Double.valueOf(getMedicineRegisterView().getField_productSellPrize().getText()).doubleValue();
        String price = getMedicineRegisterView().getField_productSellPrize().toString();
        
        String medicineSupplier = getMedicineRegisterView().getCombo_productSupplier().getSelectedItem().toString();
        String medicineAdministrationWay = getMedicineRegisterView().getCombo_productAdministrationWay().getSelectedItem().toString();
        
        //obtener la fecha
        String medicineExpirationDate = "";
        
        String medicineExpirationDateDay = getMedicineRegisterView().getSpinner_productExpirationDay().toString();
        String medicineExpirationDateMonth = getMedicineRegisterView().getSpinner_productExpirationMonth().toString();
        String medicineExpirationDateYear = getMedicineRegisterView().getSpinner_productExpirationYear().toString();
        
        medicineExpirationDate = medicineExpirationDateDay + medicineExpirationDateMonth + medicineExpirationDateYear;
        
        //obtener la dosis
        String medicineDose= "";
        
        String medicineDoseQuantity = getMedicineRegisterView().getSpinner_productDoseQuantity().toString();
        String medicineDoseType = getMedicineRegisterView().getCombo_productDoseQuantityType().toString();
        String medicinePeriod = getMedicineRegisterView().getSpinner_productDosePeriod().toString();
        String medicinePeriodType = getMedicineRegisterView().getCombo_dosePeriodType().toString();
        
        medicineDose = medicineDoseQuantity +" " + medicineDoseType + " " + medicinePeriod + " " + medicinePeriodType;
        
        boolean isValidField = !isEmptyFields(
                medicineName,
                quantity,
                price,
                medicineSupplier,
                medicineAdministrationWay,
                medicineExpirationDate,
                medicineDose);
        
        boolean isValidData = isValidMedicine(
                medicineName,
                medicineQuantity,
                medicineSellPrice,
                medicineSupplier,
                medicineAdministrationWay,
                medicineExpirationDate,
                medicineDose);
        
        if(isValidField){
            if(isValidData){
                
            }
        }
        
    }
    
    private boolean isEmptyFields(
            String name,
            String quantity,
            String sellPrice,
            String supplier,
            String administrationWay,
            String expirationDate,
            String dose
    ){
        return(name.isEmpty() || quantity.isEmpty() || sellPrice.isEmpty()|| 
                supplier.isEmpty() || administrationWay.isEmpty() || 
                expirationDate.isEmpty() || dose.isEmpty() );
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

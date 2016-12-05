/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineManager;
import exceptions.InvalidFieldException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.MedicineRegisterView;

/**
 *
 * @author mannu
 */
public class MedicineModificationViewHelper extends DataViewHelper {
    private static MedicineModificationViewHelper medicineModificationViewHelper;
    private MedicineRegisterView medicineRegisterView;
    private MedicineManagerViewHelper medicineManagerViewHelper;
    private String supplier = null;
    
    private MedicineModificationViewHelper(){
        setMedicineRegisterView( new MedicineRegisterView() );
        initializeView();
    }

    public static MedicineModificationViewHelper getInstance(){
        if( medicineModificationViewHelper== null) {
         medicineModificationViewHelper = new MedicineModificationViewHelper();
        }
        return medicineModificationViewHelper;
    }

    public void setMedicineRegisterView(MedicineRegisterView medicineRegisterView) {
        this.medicineRegisterView = medicineRegisterView;
    }

    public MedicineManagerViewHelper getMedicineManagerViewHelper() {
        return medicineManagerViewHelper;
    }

    public void setMedicineManagerViewHelper(MedicineManagerViewHelper medicineManagerViewHelper) {
        this.medicineManagerViewHelper = medicineManagerViewHelper;
    }

    @Override
    public void loadView() {
        loadMedicineData();
        medicineRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( medicineRegisterView );
        medicineRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        medicineRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        medicineRegisterView.getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadMedicineData(){
        int rowIndex = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineSupplier = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        MedicineManager medicineManager = MedicineManager.GetInstance();
        List<Medicine> medicineList =  medicineManager.getMedicinesBySupplierName(medicineSupplier);
        Medicine medicine = medicineList.get(rowIndex);
        
        setData(medicine);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int rowIndex = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineOwner = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
                MedicineManager medicineManager = MedicineManager.GetInstance();
                medicineManager.modifyMedicine(data,medicineOwner,rowIndex);
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Medicine");
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
        MedicineManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        medicineRegisterView.dispose();
        clearFields();
        MedicineManagerViewHelper.getInstance().loadView();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainDataFromView() {
       ArrayList<String> data = new ArrayList<String>();
        
        String medicineName = medicineRegisterView.getField_productName().getText();
        data.add(medicineName);        
        
        String medicineQuantity = medicineRegisterView.getField_productQuantity().getText();
        data.add(medicineQuantity);
        
        String medicinePrice = medicineRegisterView.getField_productSellPrize().getText();
        data.add(medicinePrice);
        
        String medicineSupplier = medicineRegisterView.getCombo_productSupplier().getSelectedItem().toString();
        data.add(medicineSupplier);
        
        String medicineAdministrationWay = medicineRegisterView.getCombo_productAdministrationWay().getSelectedItem().toString();
        data.add(medicineAdministrationWay);
        
        //obtener la fecha
        String medicineExpirationDate = "";
        
        String expirationDay = medicineRegisterView.getSpinner_productExpirationDay().getValue().toString();
        String expirationMonth = medicineRegisterView.getSpinner_productExpirationMonth().getValue().toString();
        String expirationYear = medicineRegisterView.getSpinner_productExpirationYear().getValue().toString();
        
        medicineExpirationDate = expirationDay + "-" + expirationMonth + "-" + expirationYear;
        
        data.add(medicineExpirationDate);
        
        //obtener la dosis
        String medicineDose= "";
        
        String doseQuantity = medicineRegisterView.getSpinner_productDoseQuantity().getValue().toString();
        String doseType = medicineRegisterView.getCombo_productDoseQuantityType().getSelectedItem().toString();
        String dosePeriod = medicineRegisterView.getSpinner_productDosePeriod().getValue().toString();
        String dosePeriodType = medicineRegisterView.getCombo_dosePeriodType().getSelectedItem().toString();
        
        medicineDose = doseQuantity+ " " + doseType + " cada " + dosePeriod + " " + dosePeriodType;
        
        data.add(medicineDose);
        
        return data;
    }
    
    private void setData(Medicine medicine){
        //setear datos de los campso
        
        medicineRegisterView.getField_productName().setText(medicine.getName().toString());
                
        String medicineQuantity = Integer.toString(medicine.getAmount());
        medicineRegisterView.getField_productQuantity().setText( medicineQuantity);
        
        String medicinePrice = Double.toString(medicine.getCost());
        medicineRegisterView.getField_productSellPrize().setText(medicinePrice);
        
        String medicineAdministrationWay = medicine.getAdministration().toString();
        
        int comboElements = medicineRegisterView.getCombo_productAdministrationWay().getItemCount();
        int administrationIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(medicineAdministrationWay.equals(medicineRegisterView.getCombo_productAdministrationWay().getSelectedItem().toString())){
                administrationIndex = i;
            }
        }
        
        medicineRegisterView.getCombo_productAdministrationWay().setSelectedIndex(administrationIndex);
        
        
        //obtener la fecha
        String medicineExpirationDate = medicine.getExpiration_date();
        
        String medicineExpiration[] = medicineExpirationDate.split("-");
        
        int expirationDay =  Integer.valueOf(medicineExpiration[0]);
        int expirationMonth = Integer.valueOf(medicineExpiration[1]);
        int expirationYear = Integer.valueOf( medicineExpiration[2]);
        
        
        medicineRegisterView.getSpinner_productExpirationDay().setValue(expirationDay);
        medicineRegisterView.getSpinner_productExpirationMonth().setValue(expirationMonth);
        medicineRegisterView.getSpinner_productExpirationYear().setValue(expirationYear);
        
        
        //obtener la dosis
        String medicineDose[] = medicine.getDose().split(" ");
        System.out.println("dose: "+medicineDose);
        String doseQuantity = medicineDose[0];
        String doseType = medicineDose[1];
        String dosePeriod = medicineDose[2];
        String dosePeriodType = medicineDose[3];        
        
        medicineRegisterView.getSpinner_productDoseQuantity().setValue(Integer.valueOf(doseQuantity));
        
        comboElements = medicineRegisterView.getCombo_productDoseQuantityType().getItemCount();
        int doseTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(doseType.equals(medicineRegisterView.getCombo_productDoseQuantityType().getSelectedItem().toString())){
                doseTypeIndex = i;
            }
        }
        
        medicineRegisterView.getCombo_productDoseQuantityType().setSelectedIndex(doseTypeIndex);
        
        medicineRegisterView.getSpinner_productDosePeriod().setValue(Integer.valueOf(dosePeriod));
        
        comboElements = medicineRegisterView.getCombo_dosePeriodType().getItemCount();
        int dosePeriodTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(dosePeriodType.equals(medicineRegisterView.getCombo_dosePeriodType().getSelectedItem().toString())){
                dosePeriodTypeIndex = i;
            }
        }
        medicineRegisterView.getCombo_dosePeriodType().setSelectedIndex(dosePeriodTypeIndex);
    }
    
    private void resetFields(){
        loadMedicineData();
    } 

    @Override
    protected void clearFields() {
        medicineRegisterView.getField_productName().setText("");
        medicineRegisterView.getField_productQuantity().setText( "");
        medicineRegisterView.getField_productSellPrize().setText("");
        medicineRegisterView.getCombo_productAdministrationWay().setSelectedIndex(0);
        medicineRegisterView.getSpinner_productExpirationDay().setValue(0);
        medicineRegisterView.getSpinner_productExpirationMonth().setValue(0);
        medicineRegisterView.getSpinner_productExpirationYear().setValue(0);
        medicineRegisterView.getSpinner_productDoseQuantity().setValue(0);
        medicineRegisterView.getCombo_productDoseQuantityType().setSelectedIndex(0);
        medicineRegisterView.getSpinner_productDosePeriod().setValue(0);
        medicineRegisterView.getCombo_dosePeriodType().setSelectedIndex(0);
    }
}

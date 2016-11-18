/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;
import presentation.OperationalViewHelper;
import presentation.views.MedicineRegisterView;

/**
 *
 * @author mannu
 */
public class MedicineModificationViewHelper extends OperationalViewHelper {
    private static MedicineModificationViewHelper medicineModificationViewHelper;
    private MedicineRegisterView medicineRegisterView;
    private MedicineManagerViewHelper medicineManagerViewHelper;
    private String owner = null;
    
    public MedicineModificationViewHelper(){
        setMedicineRegisterView( new MedicineRegisterView() );
        //setMedicineManagerViewHelper( medicineManager);
        
        initializeView();
    }

    public static MedicineModificationViewHelper getInstance(){
        if( medicineModificationViewHelper== null) {
         medicineModificationViewHelper = new MedicineModificationViewHelper();
        }
        return medicineModificationViewHelper;
    }

    public MedicineRegisterView getMedicineRegisterView() {
        return medicineRegisterView;
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
    public void openWindow() {
        loadMedicineData();
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
        getMedicineRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithModification());
        getMedicineRegisterView().getBtn_cancel().addActionListener(actionEvent -> cancelModification());
    }
    
    private void loadMedicineData(){
        int rowIndex = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineOwner = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        MedicineManager medicineManager = MedicineManager.GetInstance();
        List<Medicine> medicineList =  medicineManager.getMedicineList(medicineOwner);
        Medicine medicine = medicineList.get(rowIndex);
        
        setData(medicine);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainData());
        
        int rowIndex = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        String medicineOwner = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        String successStatus = "SUCCESS";
        
        if( isValidField ){
            MedicineManager medicineManager = MedicineManager.GetInstance();
            message = medicineManager.modifyMedicine(data,medicineOwner,rowIndex);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el Medicine");
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
        MedicineManagerViewHelper.getInstance().updateTable();
    }
    
    private void cancelModification(){
        closeWindow();
    }
    
    private void closeWindow(){
        getMedicineRegisterView().dispose();
    }

    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainData() {
       ArrayList<String> data = new ArrayList<String>();
        
        String medicineName = getMedicineRegisterView().getField_productName().getText();
        data.add(medicineName);        
        
        String medicineQuantity = getMedicineRegisterView().getField_productQuantity().getText();
        data.add(medicineQuantity);
        
        String medicinePrice = getMedicineRegisterView().getField_productSellPrize().getText();
        data.add(medicinePrice);
        
        String medicineSupplier = getMedicineRegisterView().getCombo_productSupplier().getSelectedItem().toString();
        data.add(medicineSupplier);
        
        String medicineAdministrationWay = getMedicineRegisterView().getCombo_productAdministrationWay().getSelectedItem().toString();
        data.add(medicineAdministrationWay);
        
        //obtener la fecha
        String medicineExpirationDate = "";
        
        String expirationDay = getMedicineRegisterView().getSpinner_productExpirationDay().getValue().toString();
        String expirationMonth = getMedicineRegisterView().getSpinner_productExpirationMonth().getValue().toString();
        String expirationYear = getMedicineRegisterView().getSpinner_productExpirationYear().getValue().toString();
        
        medicineExpirationDate = expirationDay + "-" + expirationMonth + "-" + expirationYear;
        
        data.add(medicineExpirationDate);
        
        //obtener la dosis
        String medicineDose= "";
        
        String doseQuantity = getMedicineRegisterView().getSpinner_productDoseQuantity().getValue().toString();
        String doseType = getMedicineRegisterView().getCombo_productDoseQuantityType().getSelectedItem().toString();
        String dosePeriod = getMedicineRegisterView().getSpinner_productDosePeriod().getValue().toString();
        String dosePeriodType = getMedicineRegisterView().getCombo_dosePeriodType().getSelectedItem().toString();
        
        medicineDose = doseQuantity+ " " + doseType + " cada " + dosePeriod + " " + dosePeriodType;
        
        data.add(medicineDose);
        
        return data;
    }
    
    private void setData(Medicine medicine){
        //setear datos de los campso
        
        getMedicineRegisterView().getField_productName().setText(medicine.getName().toString());
                
        String medicineQuantity = Integer.toString(medicine.getAmount());
        getMedicineRegisterView().getField_productQuantity().setText( medicineQuantity);
        
        String medicinePrice = Double.toString(medicine.getCost());
        getMedicineRegisterView().getField_productSellPrize().setText(medicinePrice);
        
        String medicineAdministrationWay = medicine.getAdministration().toString();
        
        int comboElements = getMedicineRegisterView().getCombo_productAdministrationWay().getItemCount();
        int administrationIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(medicineAdministrationWay.equals(getMedicineRegisterView().getCombo_productAdministrationWay().getSelectedItem().toString())){
                administrationIndex = i;
            }
        }
        
        getMedicineRegisterView().getCombo_productAdministrationWay().setSelectedIndex(administrationIndex);
        
        
        //obtener la fecha
        String medicineExpirationDate = medicine.getExpiration_date();
        
        String medicineExpiration[] = medicineExpirationDate.split("-");
        
        int expirationDay =  Integer.valueOf(medicineExpiration[0]);
        int expirationMonth = Integer.valueOf(medicineExpiration[1]);
        int expirationYear = Integer.valueOf( medicineExpiration[2]);
        
        
        getMedicineRegisterView().getSpinner_productExpirationDay().setValue(expirationDay);
        getMedicineRegisterView().getSpinner_productExpirationMonth().setValue(expirationMonth);
        getMedicineRegisterView().getSpinner_productExpirationYear().setValue(expirationYear);
        
        
        //obtener la dosis
        String medicineDose[] = medicine.getDose().split(" ");
        System.out.println("dose: "+medicineDose);
        String doseQuantity = medicineDose[0];
        String doseType = medicineDose[1];
        String dosePeriod = medicineDose[2];
        String dosePeriodType = medicineDose[3];        
        
        getMedicineRegisterView().getSpinner_productDoseQuantity().setValue(Integer.valueOf(doseQuantity));
        
        comboElements = getMedicineRegisterView().getCombo_productDoseQuantityType().getItemCount();
        int doseTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(doseType.equals(getMedicineRegisterView().getCombo_productDoseQuantityType().getSelectedItem().toString())){
                doseTypeIndex = i;
            }
        }
        
        getMedicineRegisterView().getCombo_productDoseQuantityType().setSelectedIndex(doseTypeIndex);
        
        getMedicineRegisterView().getSpinner_productDosePeriod().setValue(Integer.valueOf(dosePeriod));
        
        comboElements = getMedicineRegisterView().getCombo_dosePeriodType().getItemCount();
        int dosePeriodTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(dosePeriodType.equals(getMedicineRegisterView().getCombo_dosePeriodType().getSelectedItem().toString())){
                dosePeriodTypeIndex = i;
            }
        }
        getMedicineRegisterView().getCombo_dosePeriodType().setSelectedIndex(dosePeriodTypeIndex);
    }
    
    private void resetFields(){
        loadMedicineData();
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineHandler;
import exceptions.InvalidFieldException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        MedicineHandler medicineManager = MedicineHandler.GetInstance();
        List<Medicine> medicineList =  medicineManager.getMedicinesBySupplierName(medicineSupplier);
        Medicine medicine = medicineList.get(rowIndex);
        
        setData(medicine);
    }
    
    private void proceedWithModification(){
        ArrayList<String> data = new ArrayList<String>(obtainDataFromView());
        
        int row = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getSelectedRow();
        int id = Integer.valueOf( MedicineManagerViewHelper.getInstance().getMedicineManagerView().getTable_medicineTable().getValueAt(row, 0).toString() );
        
        String medicineOwner = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        boolean isValidField =!isEmptyFields(data);
        String message = "";
        
        if( isValidField ){
            try{
                MedicineHandler medicineManager = MedicineHandler.GetInstance();
                medicineManager.modifyMedicine(data,medicineOwner,id);
                getNotifier().showSuccessMessage("Modificacion exitosa", "exito al modificar el medicamento");
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
        
        
        String medicineAdministrationWay = medicineRegisterView.getCombo_productAdministrationWay().getSelectedItem().toString();
        data.add(medicineAdministrationWay);
        
        //obtener la fecha
        String medicineExpirationDate = "";
 
        String expirationDay = Integer.toString(medicineRegisterView.getDateChooser().getCalendar().get(java.util.Calendar.DATE));
        String expirationMonth = Integer.toString( (medicineRegisterView.getDateChooser().getCalendar().get(java.util.Calendar.MONTH)) + 1);
        String expirationYear = Integer.toString(medicineRegisterView.getDateChooser().getCalendar().get(java.util.Calendar.YEAR));
        
        medicineExpirationDate = expirationYear + "-" + expirationMonth + "-" +  expirationDay;
        
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
            if(medicineAdministrationWay.equals(medicineRegisterView.getCombo_productAdministrationWay().getItemAt(i).toString())){
                administrationIndex = i;
            }
        }
        
        medicineRegisterView.getCombo_productAdministrationWay().setSelectedIndex(administrationIndex);
        
        
        //obtener la fecha
        Date medicineExpirationDate = medicine.getExpiration_date();
        medicineRegisterView.getDateChooser().setDate(medicineExpirationDate);
        
        
        //obtener la dosis
        String medicineDose[] = medicine.getDose().split(" ");
        //System.out.println("dose: "+medicineDose);
        String doseQuantity = medicineDose[0];
        String doseType = medicineDose[1];
        String dosePeriod = medicineDose[3];
        String dosePeriodType = medicineDose[4];        
        
        medicineRegisterView.getSpinner_productDoseQuantity().setValue(Integer.valueOf(doseQuantity));
        
        comboElements = medicineRegisterView.getCombo_productDoseQuantityType().getItemCount();
        int doseTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(doseType.equals(medicineRegisterView.getCombo_productDoseQuantityType().getItemAt(i).toString())){
                doseTypeIndex = i;
            }
        }
        
        medicineRegisterView.getCombo_productDoseQuantityType().setSelectedIndex(doseTypeIndex);
        
        medicineRegisterView.getSpinner_productDosePeriod().setValue(Integer.valueOf(dosePeriod));
        
        comboElements = medicineRegisterView.getCombo_dosePeriodType().getItemCount();
        int dosePeriodTypeIndex = 0;
        
        for(int i =0; i < comboElements;i++ ){
            if(dosePeriodType.equals(medicineRegisterView.getCombo_dosePeriodType().getItemAt(i).toString())){
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
        medicineRegisterView.getDateChooser().setCalendar(null);
        medicineRegisterView.getSpinner_productDoseQuantity().setValue(0);
        medicineRegisterView.getCombo_productDoseQuantityType().setSelectedIndex(0);
        medicineRegisterView.getSpinner_productDosePeriod().setValue(0);
        medicineRegisterView.getCombo_dosePeriodType().setSelectedIndex(0);
    }
}

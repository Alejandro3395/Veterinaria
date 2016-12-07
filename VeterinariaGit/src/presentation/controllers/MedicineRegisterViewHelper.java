/**
* class: MedicineRegisterController (MedicineRegisterController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the controller for the medicine entitys.
* The objective of the class is to listen the events that the view
* provides and pass the data to the manager class.
* 
*/

package presentation.controllers;

import Entitys.Medicine;
import bussiness.MedicineHandler;
import exceptions.InvalidFieldException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.MedicineRegisterView;

public class MedicineRegisterViewHelper extends DataViewHelper {
    private static MedicineRegisterViewHelper medicineRegisterViewHelper;
    private MedicineRegisterView medicineRegisterView;
    
    private String supplier = null ;
    private int mode = 0;
    
    private MedicineRegisterViewHelper(){
        setMedicineRegisterView(new MedicineRegisterView());
        
        initializeView();
    }

    public MedicineRegisterViewHelper( String supplier ) {
        setMedicineRegisterView( new MedicineRegisterView());
        this.supplier = supplier;
        initializeView();
    }
    
    public static MedicineRegisterViewHelper getInstance(){
        if( medicineRegisterViewHelper== null) {
         medicineRegisterViewHelper = new MedicineRegisterViewHelper();
        }
        return medicineRegisterViewHelper;
    }

    public void setMedicineRegisterView(MedicineRegisterView medicineRegisterView) {
        this.medicineRegisterView = medicineRegisterView;
    }
    
    private void updateManagerViewTable(){
        MedicineManagerViewHelper.getInstance().updateTable();
    }
    
    @Override
    public void loadView() {
        medicineRegisterView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( medicineRegisterView );
        medicineRegisterView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        medicineRegisterView.getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        medicineRegisterView.getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        medicineRegisterView.dispose();
        clearFields();
        MedicineManagerViewHelper.getInstance().loadView();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        ArrayList<String> medicineData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField =!isEmptyFields(medicineData);
        
            supplier = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        
        
        String message="";
        
        if(isValidField){
            try{
                MedicineHandler medicineManager = MedicineHandler.GetInstance();
                medicineManager.registerMedicine(medicineData,supplier);
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Medicine");
                updateManagerViewTable();
                clearFields();
                closeWindow();
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
    protected ArrayList<String> obtainDataFromView(){
        
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
        
        String medicineExpirationDate ="";
        
        Calendar calendar = Calendar.getInstance();
        String expirationDay = Integer.toString(medicineRegisterView.getDateChooser().getCalendar().get(calendar.DATE));
        String expirationMonth = Integer.toString( (medicineRegisterView.getDateChooser().getCalendar().get(calendar.MONTH)) + 1 );
        String expirationYear = Integer.toString(medicineRegisterView.getDateChooser().getCalendar().get(calendar.YEAR));
        

        medicineExpirationDate = expirationYear  + "-" + expirationMonth + "-" +  expirationDay; 
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
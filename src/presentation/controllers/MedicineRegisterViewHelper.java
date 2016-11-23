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
import bussiness.MedicineManager;
import java.util.ArrayList;
import javax.swing.WindowConstants;
import presentation.DataViewHelper;
import presentation.views.MedicineRegisterView;

public class MedicineRegisterViewHelper extends DataViewHelper {
    private static MedicineRegisterViewHelper medicineRegisterViewHelper;
    private MedicineRegisterView medicineRegisterView;
    private MedicineManagerViewHelper medicineManagerViewHelper = null;
    
    private String owner = null ;
    private static int registerMedicineMode = 0;
    private static int registerClientMode = 1;
    private int mode = 0;
    
    public MedicineRegisterViewHelper(){
        setMedicineRegisterView(new MedicineRegisterView());
        //setMedicineManagerViewHelper( medicineManager  );
        
        initializeView();
    }

    public MedicineRegisterViewHelper( String owner ) {
        setMedicineRegisterView( new MedicineRegisterView());
        this.owner = owner;
        initializeView();
    }
    
    public static MedicineRegisterViewHelper getInstance(){
        if( medicineRegisterViewHelper== null) {
         medicineRegisterViewHelper = new MedicineRegisterViewHelper();
        }
        return medicineRegisterViewHelper;
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
    
    public MedicineManagerViewHelper getMedicineManagerViewHelper() {
        return medicineManagerViewHelper;
    }

    public void setMedicineManagerViewHelper(MedicineManagerViewHelper medicineManagerViewHelper) {
        this.medicineManagerViewHelper = medicineManagerViewHelper;
    }

    public MedicineRegisterView getMedicineRegisterView() {
        return medicineRegisterView;
    }

    public void setMedicineRegisterView(MedicineRegisterView medicineRegisterView) {
        this.medicineRegisterView = medicineRegisterView;
    }
    
    private void updateManagerViewTable(){
        MedicineManagerViewHelper.getInstance().updateTable();
    }
    
    @Override
    public void loadView() {
        getMedicineRegisterView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getMedicineRegisterView() );
        getMedicineRegisterView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getMedicineRegisterView().getBtn_register().addActionListener(actionEvent -> proceedWithRegistration());
        getMedicineRegisterView().getBtn_cancel().addActionListener(ActionEvent -> cancelRegistration());
        
    }
    
    private void cancelRegistration(){
        closeWindow();
    }
    
    private void closeWindow(){
        getMedicineRegisterView().dispose();
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void proceedWithRegistration(){
        ArrayList<String> medicineData = new ArrayList<String>(obtainDataFromView());
        
        boolean isValidField =!isEmptyFields(medicineData);
        
        if(mode != 0){
            owner = MedicineManagerViewHelper.getInstance().getMedicineManagerView().getCombo_medicineSupplier().getSelectedItem().toString();
        }
        
        String message="";
        String successStatus="SUCCESS";
        
        if(isValidField){
            MedicineManager medicineManager = MedicineManager.GetInstance();
            message = medicineManager.registerMedicine(medicineData,owner);
            if(message.equals(successStatus)){
                getNotifier().showSuccessMessage("Registro exitoso", "exito al registrar el Medicine");
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
    protected ArrayList<String> obtainDataFromView(){
        
        ArrayList<String> data = new ArrayList<String>();
        
        String medicineName = getMedicineRegisterView().getField_productName().getText();
        data.add(medicineName);        
        
        String medicineQuantity = getMedicineRegisterView().getField_productQuantity().getText();
        data.add(medicineQuantity);
        
        String medicinePrice = getMedicineRegisterView().getField_productSellPrize().getText();
        data.add(medicinePrice);
        
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
    
    
    private void resetFields(){
        getMedicineRegisterView().getField_productName().setText("");
        getMedicineRegisterView().getField_productQuantity().setText( "");
        getMedicineRegisterView().getField_productSellPrize().setText("");
        getMedicineRegisterView().getCombo_productAdministrationWay().setSelectedIndex(0);
        getMedicineRegisterView().getSpinner_productExpirationDay().setValue(0);
        getMedicineRegisterView().getSpinner_productExpirationMonth().setValue(0);
        getMedicineRegisterView().getSpinner_productExpirationYear().setValue(0);
        getMedicineRegisterView().getSpinner_productDoseQuantity().setValue(0);
        getMedicineRegisterView().getCombo_productDoseQuantityType().setSelectedIndex(0);
        getMedicineRegisterView().getSpinner_productDosePeriod().setValue(0);
        getMedicineRegisterView().getCombo_dosePeriodType().setSelectedIndex(0);
    }
}
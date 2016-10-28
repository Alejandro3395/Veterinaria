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
import presentation.AbstractRegisterController;
import presentation.views.MedicineRegisterView;

public class MedicineRegisterController extends AbstractRegisterController  {
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
    
    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
       getMedicineRegisterView().getBtn_register().addActionListener(actionEvent -> registerMedicine());
    }
    
    /**
     *  This method uses sends the data the view provides to the manager.
     */
    private void registerMedicine(){
        
        ArrayList<String> data = new ArrayList<String>(obtainData());
        boolean isValidField = !isEmptyFields(data);

        if(isValidField){
                 MedicineManager medicineManager = MedicineManager.GetInstance();
                 medicineManager.createMedicine(data);
        }
    }
    
    /**
     * This method transforms the view form into an arraylist of strings for future
     * parsing.
     * @return 
     */
    @Override
    protected ArrayList<String> obtainData(){
        
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
    
}
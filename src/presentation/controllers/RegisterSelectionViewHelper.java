/**
* class: RegisterSelectionController (RegisterSelectionController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the register selection view.
* 
* The objective of this class is let the user decide wich type of 
* entity they want to register.
* 
*/

package presentation.controllers;

import javax.swing.ButtonGroup;
import javax.swing.WindowConstants;
import presentation.ViewHelper;
import presentation.views.RegisterSelectionView;

/**
 *
 * @author Jorge
 */
public class RegisterSelectionViewHelper extends ViewHelper {
    private static RegisterSelectionViewHelper registerSelectionViewHelper = null;
    private RegisterSelectionView registerSelectionView;

    
    private RegisterSelectionViewHelper(){
        setRegisterSelectionView(new RegisterSelectionView());

        initializeView();
    }

    public static RegisterSelectionViewHelper getInstance(){
        if( registerSelectionViewHelper== null) {
         registerSelectionViewHelper = new RegisterSelectionViewHelper();
        }
        return registerSelectionViewHelper;
    }

    public RegisterSelectionView getRegisterSelectionView() {
        return registerSelectionView;
    }

    public void setRegisterSelectionView(RegisterSelectionView registerSelectionView) {
        this.registerSelectionView = registerSelectionView;
    }
    
    
    @Override
    public void loadView() {
        getRegisterSelectionView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( getRegisterSelectionView() );
        getRegisterSelectionView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getRegisterSelectionView().getBtn_accept().addActionListener(actionEvent -> openSelectedView());
    }
     
    /**
     *  This method redirects the user to the selected view.
     */
    private void openSelectedView(){
        
        int value = registerSelectionView.getRadioGroupValue();
        switch(value){
            
            case 1:
                EmployeeManagerViewHelper employeeManagerViewHelper = EmployeeManagerViewHelper.getInstance();
                employeeManagerViewHelper.loadView();
            break;
            
            case 2:
                DoctorManagerViewHelper doctorManagerViewHelper = DoctorManagerViewHelper.getInstance();
                doctorManagerViewHelper.loadView();
            break;
            
            case 3:
                SupplierManagerViewHelper supplierManagerViewHelper = SupplierManagerViewHelper.getInstance();
                supplierManagerViewHelper.loadView();
            break;
            
            case 4:
                MedicineManagerViewHelper medicineManagerViewHelper = MedicineManagerViewHelper.getInstance();
                medicineManagerViewHelper.loadView();
            break;
            
            case 5:
                ClientManagerViewHelper clientManagerViewHelper = ClientManagerViewHelper.getInstance();
                clientManagerViewHelper.loadView();
            break;
            
            case 6:
                PetManagerViewHelper petManagerViewHelper = PetManagerViewHelper.getInstance();
                petManagerViewHelper.loadView();
            break;
        }
        
    }
    
}
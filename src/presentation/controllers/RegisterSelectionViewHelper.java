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

    public void setRegisterSelectionView(RegisterSelectionView registerSelectionView) {
        this.registerSelectionView = registerSelectionView;
    }
    
    
    @Override
    public void loadView() {
        registerSelectionView.setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureView( registerSelectionView );
        registerSelectionView.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        registerSelectionView.getBtn_employee().addActionListener(actionEvent -> openEmployeeManagerView());
        registerSelectionView.getBtn_doctor().addActionListener(actionEvent -> openDoctorManagerView());
        registerSelectionView.getBtn_client().addActionListener(actionEvent -> openClientManagerView());
        registerSelectionView.getBtn_pet().addActionListener(actionEvent -> openPetManagerView());
        registerSelectionView.getBtn_supplier().addActionListener(actionEvent -> openSupplierManagerView());
        registerSelectionView.getBtn_product().addActionListener(actionEvent -> openMedicineManagerView());
        registerSelectionView.getBtn_back().addActionListener(actionEvent -> closeView());
    }
     
     private void openEmployeeManagerView(){
        registerSelectionView.dispose();
        EmployeeManagerViewHelper employeeManagerViewHelper = EmployeeManagerViewHelper.getInstance();
        employeeManagerViewHelper.loadView();
    }
    
    private void openDoctorManagerView(){
        registerSelectionView.dispose();
        DoctorManagerViewHelper doctorManagerViewHelper = DoctorManagerViewHelper.getInstance();
        doctorManagerViewHelper.loadView();
    }
    
    private void openSupplierManagerView(){
       registerSelectionView.dispose();
       SupplierManagerViewHelper supplierManagerViewHelper = SupplierManagerViewHelper.getInstance();
       supplierManagerViewHelper.loadView();
    }
    
    private void openMedicineManagerView(){
       registerSelectionView.dispose();
       MedicineManagerViewHelper medicineManagerViewHelper = MedicineManagerViewHelper.getInstance();
       medicineManagerViewHelper.loadView();
    }
    
    private void openClientManagerView(){
        registerSelectionView.dispose();
        ClientManagerViewHelper clientManagerViewHelper = ClientManagerViewHelper.getInstance();
        clientManagerViewHelper.loadView();
    }
    
    private void openPetManagerView(){
        registerSelectionView.dispose();
        PetManagerViewHelper petManagerViewHelper = PetManagerViewHelper.getInstance();
        petManagerViewHelper.loadView();
    }
    
   private void closeView(){
       registerSelectionView.dispose();
       EmployeeMainMenuViewHelper mainMenuViewHelper = EmployeeMainMenuViewHelper.getInstance();
       mainMenuViewHelper.loadView();
   }
    
}
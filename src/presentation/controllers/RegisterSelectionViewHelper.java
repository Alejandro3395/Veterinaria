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
import presentation.CommonBehaviorViewHelper;
import presentation.views.RegisterSelectionView;

/**
 *
 * @author Jorge
 */
public class RegisterSelectionViewHelper extends CommonBehaviorViewHelper {
    private static RegisterSelectionViewHelper registerSelectionViewHelper = null;
    private RegisterSelectionView registerSelectionView;
    private EmployeeManagerViewHelper employeeManagerViewHelper;
    private SupplierManagerViewHelper supplierManagerViewHelper;
    //private ProductSelectionController productRegisterController;
    private MedicineManagerViewHelper medicineManagerViewHelper;
    private ClientManagerViewHelper clientManagerViewHelper;
    private PetManagerViewHelper petManagerViewHelper;
    private DoctorManagerViewHelper doctorManagerViewHelper;
    
    public RegisterSelectionViewHelper(){
        setRegisterSelectionView(new RegisterSelectionView());
        setEmployeeManagerViewHelper(EmployeeManagerViewHelper.getInstance());
        setDoctorManagerViewHelper( DoctorManagerViewHelper.getInstance());
        setSupplierManagerViewHelper(SupplierManagerViewHelper.getInstance());
        //setProductRegisterController(ProductSelectionController.getInstance());
        setMedicineManagerViewHelper(MedicineManagerViewHelper.getInstance() );
        
        setClientManagerViewHelper(ClientManagerViewHelper.getInstance());
        setPetManagerViewHelper(PetManagerViewHelper.getInstance());
        initializeView();
    }

    public static RegisterSelectionViewHelper getInstance(){
        if( registerSelectionViewHelper== null) {
         registerSelectionViewHelper = new RegisterSelectionViewHelper();
        }
        return registerSelectionViewHelper;
    }

    public MedicineManagerViewHelper getMedicineManagerViewHelper() {
        return medicineManagerViewHelper;
    }

    public void setMedicineManagerViewHelper(MedicineManagerViewHelper medicineManagerViewHelper) {
        this.medicineManagerViewHelper = medicineManagerViewHelper;
    }
    
    
    
    public DoctorManagerViewHelper getDoctorManagerViewHelper() {
        return doctorManagerViewHelper;
    }

    public void setDoctorManagerViewHelper(DoctorManagerViewHelper doctorManagerViewHelper) {
        this.doctorManagerViewHelper = doctorManagerViewHelper;
    }

    public EmployeeManagerViewHelper getEmployeeManagerViewHelper() {
        return employeeManagerViewHelper;
    }

    public void setEmployeeManagerViewHelper(EmployeeManagerViewHelper employeeManagerViewHelper) {
        this.employeeManagerViewHelper = employeeManagerViewHelper;
    }

    public ClientManagerViewHelper getClientManagerViewHelper() {
        return clientManagerViewHelper;
    }

    public void setClientManagerViewHelper(ClientManagerViewHelper clientManagerViewHelper) {
        this.clientManagerViewHelper = clientManagerViewHelper;
    }

    public PetManagerViewHelper getPetManagerHelper() {
        return petManagerViewHelper;
    }

    public void setPetManagerViewHelper(PetManagerViewHelper petManagerViewHelper) {
        this.petManagerViewHelper = petManagerViewHelper;
    }

    public SupplierManagerViewHelper getSupplierManagerViewHelper() {
        return supplierManagerViewHelper;
    }

        public void setSupplierManagerViewHelper(SupplierManagerViewHelper supplierManagerViewHelper) {
        this.supplierManagerViewHelper = supplierManagerViewHelper;
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
        getRegisterSelectionView().getBtn_accept().addActionListener(actionEvent -> getSelectedOption());
    }
     
    /**
     *  This method redirects the user to the selected view.
     */
    private void getSelectedOption(){
        
        int value = registerSelectionView.getRadioGroupValue();
        switch(value){
            
            case 1:
                employeeManagerViewHelper.loadView();
            break;
            
            case 2:
                getDoctorManagerViewHelper().loadView();
            break;
            
            case 3:
                getSupplierManagerViewHelper().loadView();
            break;
            
            case 4:
                getMedicineManagerViewHelper().loadView();
            break;
            
            case 5:
                getClientManagerViewHelper().loadView();
            break;
            
            case 6:
                getPetManagerHelper().loadView();
            break;
        }
        
    }
    
}
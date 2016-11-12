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
import presentation.AbstractViewController;
import presentation.views.RegisterSelectionView;

/**
 *
 * @author Jorge
 */
public class RegisterSelectionController extends AbstractViewController {
    private RegisterSelectionView registerSelectionView;
    private EmployeeManagerHelper employeeManagerHelper;
    private SupplierManagerHelper supplierManagerHelper;
    private ProductSelectionController productRegisterController;
    private ClientManagerHelper clientManagerHelper;
    private PetRegisterController petRegisterController;
    private DoctorManagerHelper doctorManagerHelper;
    
    public RegisterSelectionController(){
        setRegisterSelectionView(new RegisterSelectionView());
        setEmployeeManagerHelper(new EmployeeManagerHelper());
        setDoctorManagerHelper( new DoctorManagerHelper());
        setSupplierManagerHelper(new SupplierManagerHelper());
        setProductRegisterController(new ProductSelectionController());
        setClientManagerHelper(new ClientManagerHelper());
        setPetRegisterController(new PetRegisterController());
        initializeView();
    }

    public DoctorManagerHelper getDoctorManagerHelper() {
        return doctorManagerHelper;
    }

    public void setDoctorManagerHelper(DoctorManagerHelper doctorManagerHelper) {
        this.doctorManagerHelper = doctorManagerHelper;
    }

    public EmployeeManagerHelper getEmployeeManagerHelper() {
        return employeeManagerHelper;
    }

    public void setEmployeeManagerHelper(EmployeeManagerHelper employeeManagerHelper) {
        this.employeeManagerHelper = employeeManagerHelper;
    }

    public ClientManagerHelper getClientManagerHelper() {
        return clientManagerHelper;
    }

    public void setClientManagerHelper(ClientManagerHelper clientManagerHelper) {
        this.clientManagerHelper = clientManagerHelper;
    }
    
 

    public PetRegisterController getPetRegisterController() {
        return petRegisterController;
    }

    public void setPetRegisterController(PetRegisterController petRegisterController) {
        this.petRegisterController = petRegisterController;
    }

    public SupplierManagerHelper getSupplierManagerHelper() {
        return supplierManagerHelper;
    }

    public void setSupplierManagerHelper(SupplierManagerHelper supplierManagerHelper) {
        this.supplierManagerHelper = supplierManagerHelper;
    }
 

    public ProductSelectionController getProductRegisterController() {
        return productRegisterController;
    }

    public void setProductRegisterController(ProductSelectionController productRegisterController) {
        this.productRegisterController = productRegisterController;
    }

   

    public RegisterSelectionView getRegisterSelectionView() {
        return registerSelectionView;
    }

    public void setRegisterSelectionView(RegisterSelectionView registerSelectionView) {
        this.registerSelectionView = registerSelectionView;
    }
    
    
    @Override
    public void openWindow() {
        getRegisterSelectionView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getRegisterSelectionView() );
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
                getEmployeeManagerHelper().openWindow();
            break;
            
            case 2:
                getDoctorManagerHelper().openWindow();
            break;
            
            case 3:
                getSupplierManagerHelper().openWindow();
            break;
            
            case 4:
                getProductRegisterController().openWindow();
            break;
            
            case 5:
                getClientManagerHelper().openWindow();
            break;
            
            case 6:
                getPetRegisterController().openWindow();
            break;
        }
        
    }
    
}

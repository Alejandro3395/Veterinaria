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
    private EmployeeRegisterController employeeRegisterController;
    private SupplierRegisterController supplierRegisterController;
    private ProductSelectionController productRegisterController;
    private CustomerRegisterController customerRegisterController;
    private PetRegisterController petRegisterController;
    private DoctorManagerHelper doctorCRUDController;
    
    public RegisterSelectionController(){
        setRegisterSelectionView(new RegisterSelectionView());
        setEmployeeRegisterController(new EmployeeRegisterController());
        setDoctorCRUDController( new DoctorManagerHelper());
        setSupplierRegisterController(new SupplierRegisterController());
        setProductRegisterController(new ProductSelectionController());
        setCustomerRegisterController(new CustomerRegisterController());
        setPetRegisterController(new PetRegisterController());
        initializeView();
    }

    public DoctorManagerHelper getDoctorCRUDController() {
        return doctorCRUDController;
    }

    public void setDoctorCRUDController(DoctorManagerHelper doctorCRUDController) {
        this.doctorCRUDController = doctorCRUDController;
    }
    
    

    public CustomerRegisterController getCustomerRegisterController() {
        return customerRegisterController;
    }

    public void setCustomerRegisterController(CustomerRegisterController customerRegisterController) {
        this.customerRegisterController = customerRegisterController;
    }

    public PetRegisterController getPetRegisterController() {
        return petRegisterController;
    }

    public void setPetRegisterController(PetRegisterController petRegisterController) {
        this.petRegisterController = petRegisterController;
    }
    
    public EmployeeRegisterController getEmployeeRegisterController() {
        return employeeRegisterController;
    }

    public void setEmployeeRegisterController(EmployeeRegisterController employeeRegisterController) {
        this.employeeRegisterController = employeeRegisterController;
    }



    public SupplierRegisterController getSupplierRegisterController() {
        return supplierRegisterController;
    }

    public void setSupplierRegisterController(SupplierRegisterController supplierRegisterController) {
        this.supplierRegisterController = supplierRegisterController;
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
                getEmployeeRegisterController().openWindow();
            break;
            
            case 2:
                getDoctorCRUDController().openWindow();
            break;
            
            case 3:
                getSupplierRegisterController().openWindow();
            break;
            
            case 4:
                getProductRegisterController().openWindow();
            break;
            
            case 5:
                getCustomerRegisterController().openWindow();
            break;
            
            case 6:
                getPetRegisterController().openWindow();
            break;
        }
        
    }
    
}

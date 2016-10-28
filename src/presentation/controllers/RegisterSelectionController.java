/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private DoctorRegisterController doctorRegisterController;
    private SupplierRegisterController supplierRegisterController;
    private ProductSelectionController productRegisterController;
    private ClientRegisterController customerRegisterController;
    private PetRegisterController petRegisterController;
    
    public RegisterSelectionController(){
        setRegisterSelectionView(new RegisterSelectionView());
        setEmployeeRegisterController(new EmployeeRegisterController());
        setDoctorRegisterController(new DoctorRegisterController());
        setSupplierRegisterController(new SupplierRegisterController());
        setProductRegisterController(new ProductSelectionController());
        setCustomerRegisterController(new ClientRegisterController());
        setPetRegisterController(new PetRegisterController());
        initializeView();
    }

    public ClientRegisterController getCustomerRegisterController() {
        return customerRegisterController;
    }

    public void setCustomerRegisterController(ClientRegisterController customerRegisterController) {
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

    public DoctorRegisterController getDoctorRegisterController() {
        return doctorRegisterController;
    }

    public void setDoctorRegisterController(DoctorRegisterController doctorRegisterController) {
        this.doctorRegisterController = doctorRegisterController;
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

    @Override
    protected void setEvents() {
        getRegisterSelectionView().getBtn_accept().addActionListener(actionEvent -> getValue());
    }
    
    private void getValue(){
        
        int value = registerSelectionView.getRadioGroupValue();
        switch(value){
            
            case 1:
                getEmployeeRegisterController().openWindow();
            break;
            
            case 2:
                getDoctorRegisterController().openWindow();
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

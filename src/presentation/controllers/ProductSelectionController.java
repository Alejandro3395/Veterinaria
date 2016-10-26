/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.MedicineRegisterView;
import presentation.views.ProductSelectionView;

/**
 *
 * @author Jorge
 */
public class ProductSelectionController extends AbstractViewController {
    private ProductSelectionView productSelectionView;
    private MedicineRegisterController medicineRegisterController;
    
    
    public ProductSelectionController(){
        setProductSelectionView(new ProductSelectionView());
        setMedicineRegisterController(new MedicineRegisterController());
        
        initializeView();
    }

    public ProductSelectionView getProductSelectionView() {
        return productSelectionView;
    }

    public void setProductSelectionView(ProductSelectionView productSelectionView) {
        this.productSelectionView = productSelectionView;
    }
    
    

    public MedicineRegisterController getMedicineRegisterController() {
        return medicineRegisterController;
    }

    public void setMedicineRegisterController(MedicineRegisterController medicineRegisterController) {
        this.medicineRegisterController = medicineRegisterController;
    }
    
    @Override
    public void openWindow() {
        getProductSelectionView().setVisible(true);
    }

    @Override
    protected void initializeView() {
        configureWindow( getProductSelectionView() );
        getProductSelectionView().setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getProductSelectionView().getBtn_continue().addActionListener(actionEvent -> getValue());
    }
    
    
    private void getValue(){
        int value = productSelectionView.getRadioGroupValue();
        switch(value){
            
            case 1:
                getMedicineRegisterController().openWindow();
            break;
            
            case 2:
                //getVaccineRegisterController().openWindow();
            break;
            
        }
        
    }
}

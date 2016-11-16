/**
* class: ProductSelectionController (ProductSelectionController.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the product selection view.
* 
* The objective of this class is let the user decide wich type of 
* product they want to register.
* 
*/

package presentation.controllers;

import javax.swing.WindowConstants;
import presentation.AbstractViewController;
import presentation.views.MedicineRegisterView;
import presentation.views.ProductSelectionView;

public class ProductSelectionController extends AbstractViewController {
    private static ProductSelectionController productSelectionController = null;
    private ProductSelectionView productSelectionView;
    private MedicineRegisterController medicineRegisterController;
    
    
    public ProductSelectionController(){
        setProductSelectionView(new ProductSelectionView());
        setMedicineRegisterController(new MedicineRegisterController());
        
        initializeView();
    }
    
    public static ProductSelectionController getInstance(){
        if( productSelectionController== null) {
         productSelectionController = new ProductSelectionController();
        }
        return productSelectionController;
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

    /**
     * This method set the listeners into the view buttons.
     */
    @Override
    protected void setEvents() {
        getProductSelectionView().getBtn_continue().addActionListener(actionEvent -> getValue());
    }
    
    /**
     *  This method redirects the user to the selected view.
     */
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
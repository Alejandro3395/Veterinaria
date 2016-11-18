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
import presentation.TransitionalViewHelper;
import presentation.views.MedicineRegisterView;
import presentation.views.ProductSelectionView;

public class ProductSelectionViewHelper extends TransitionalViewHelper {
    private static ProductSelectionViewHelper productSelectionViewHelper = null;
    private ProductSelectionView productSelectionView;
    private MedicineRegisterViewHelper medicineRegisterViewHelper;
    
    
    public ProductSelectionViewHelper(){
        setProductSelectionView(new ProductSelectionView());
        setMedicineRegisterViewHelper(new MedicineRegisterViewHelper());
        
        initializeView();
    }
    
    public static ProductSelectionViewHelper getInstance(){
        if( productSelectionViewHelper== null) {
         productSelectionViewHelper = new ProductSelectionViewHelper();
        }
        return productSelectionViewHelper;
    }

    public ProductSelectionView getProductSelectionView() {
        return productSelectionView;
    }

    public void setProductSelectionView(ProductSelectionView productSelectionView) {
        this.productSelectionView = productSelectionView;
    }
    
    

    public MedicineRegisterViewHelper getMedicineRegisterViewHelper() {
        return medicineRegisterViewHelper;
    }

    public void setMedicineRegisterViewHelper(MedicineRegisterViewHelper medicineRegisterViewHelper) {
        this.medicineRegisterViewHelper = medicineRegisterViewHelper;
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
                getMedicineRegisterViewHelper().openWindow();
            break;
            
            case 2:
                //getVaccineRegisterController().openWindow();
            break;
            
        }
        
    }
}
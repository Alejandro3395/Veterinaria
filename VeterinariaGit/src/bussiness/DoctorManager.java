/**
* class: DoctorManager (DoctorManager.java)
* @author: Jorge Zapata
* 
* date: October 27, 2016
* 
* This class represent the manager for the doctor entitys.
* The objective of the class is to recieve the data that the view
* collects and pass it to the entity class to insert it to the database.
* 
*/

package bussiness;

import Data.DAOs.DoctorDAO;
import Entitys.Address;
import Entitys.Doctor;
import Entitys.Phone;
import Entitys.UserDoctor;
import exceptions.InvalidFieldException;
import java.util.ArrayList;

public class DoctorManager {
    private static final DoctorManager doctorManager = new DoctorManager();
    private DoctorDAO doctorDAO;
    
    /**
     * Constants to use with the createDoctor method
     */
    private static final int nameIndex = 0;
    private static final int postalCodeIndex = 1;
    private static final int adressStreetIndex = 2;
    private static final int addressColonyIndex = 3;
    private static final int addressCrossIndex = 4;
    private static final int phoneLadaIndex = 5;
    private static final int phoneNumberIndex = 6;
    private static final int RFCIndex = 7;
    private static final int identityCardIndex = 8;
    
    /**
     * Constants to use with the createUserDoctor method
     */
    private static final int userNameIndex = 0;
    private static final int userPasswordIndex = 1;
    private static final int userEmailIndex = 2;
    
    
    
    public DoctorManager(){
        this.doctorDAO = DoctorDAO.GetInstance();
    }
    
    /**
     * This method returns an instance of the class that the other classes can
     * use.
     * @return 
     */
    public static DoctorManager GetInstance(){
        return doctorManager;
    }
    
    
    private void addDoctor(Doctor doctor) {
        doctorDAO.add(doctor);
    }
    
    private void deleteDoctor(Doctor doctor){
        doctorDAO.delete(doctor);
    }
    
    private void updateDoctor(Doctor doctor){
        doctorDAO.update(doctor);
    }
    
    public Doctor getDoctor(int id){
        return (Doctor) doctorDAO.get(id);
    }
     
    public void eliminateDoctor(int id){
       deleteDoctor((Doctor)(doctorDAO.get(id)));
    }
    
   

    /**
     * The method recieves the data array from the view and parse it 
     * so that the doctor entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     * @return data
     * @throws InvalidFieldException 
     */
    public Doctor createDoctor(ArrayList<String> data) throws InvalidFieldException {
        String doctorName = data.get(nameIndex);
        int  doctorPostalCode = Integer.valueOf(data.get(postalCodeIndex));
        String doctorAddressStreet = data.get(adressStreetIndex);
        String doctorAddressColony = data.get(addressColonyIndex);
        String doctorAddressCross = data.get(addressCrossIndex);
        String doctorPhoneLada = data.get(phoneLadaIndex);
        String doctorPhoneNumber = data.get(phoneNumberIndex);
        String doctorRFC = data.get(RFCIndex);
        String doctorIdentityCard = data.get(identityCardIndex);
        
        Doctor doctorData;
        
        Address doctorAddress = new Address(doctorPostalCode,doctorAddressStreet,doctorAddressColony,doctorAddressCross);
        Phone doctorPhone = new Phone(doctorPhoneLada,doctorPhoneNumber);
        
        doctorData = new Doctor(doctorName,doctorAddress,doctorPhone,doctorRFC,doctorIdentityCard);

        return doctorData;
    }
     /**
     * The method recieves the data array from the view and parse it 
     * so that the UserDoctor entity can understand it, finally we create a 
     * new entity, the method assumes that the data is passed in the correct order.
     * 
     * @param data
     * @return data
     * @throws InvalidFieldException 
     */
    public UserDoctor createUserDoctor(ArrayList<String> data) throws InvalidFieldException{
        
        String doctorUserName = data.get(userNameIndex);
        String doctorUserPassword = data.get(userPasswordIndex);
        String doctorUserEmail = data.get(userEmailIndex);
        
        UserDoctor userDoctor = new UserDoctor(doctorUserName, doctorUserPassword, doctorUserEmail);
        
        return userDoctor;
        
    }
    /**
     * The method recieves the data from the view and uses it to create the new 
     * entitys, an exception is thrown if there's an invalid data.
     * 
     * @param doctorData
     * @param userDoctorData 
     */
    public String registerDoctor(ArrayList<String> doctorData, ArrayList<String> userDoctorData){
        String message ="";
        try{
            Doctor doctor = new Doctor(createDoctor(doctorData));
            UserDoctor user = new UserDoctor(createUserDoctor(userDoctorData));
            setDoctorUser(doctor,user);
            message = "SUCCESS";
        }catch(InvalidFieldException exception ){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    public String modifyDoctor(ArrayList<String> newDoctorData , int id){
        String message = "";
 
        try{
            Doctor doctor =  (getDoctor(id));
            ArrayList<String> doctorData = new ArrayList<>( getDoctorData(doctor) );
            
            if(isNewData(doctorData, newDoctorData)){
                //procedemos a mover los datos de el objeto doctor contra los de newDoctorData 
                Doctor updatedDoctor = updateData(doctor,newDoctorData); //EXCEPCION
                updateDoctor(updatedDoctor);
                message = "SUCCESS";
            }else{
                System.out.println("datos sin cambio");
            }
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }
    
    private Doctor updateData(Doctor doctor, ArrayList<String> newDoctorData) throws InvalidFieldException{
        
        //obtenemos los valores del arraylist
        String newDoctorName = newDoctorData.get(nameIndex);
        int  newDoctorPostalCode = Integer.valueOf(newDoctorData.get(postalCodeIndex));
        String newDoctorAddressStreet = newDoctorData.get(adressStreetIndex);
        String newDoctorAddressColony = newDoctorData.get(addressColonyIndex);
        String newDoctorAddressCross = newDoctorData.get(addressCrossIndex);
        String newDoctorPhoneLada = newDoctorData.get(phoneLadaIndex);
        String newDoctorPhoneNumber = newDoctorData.get(phoneNumberIndex);
        String newDoctorRFC = newDoctorData.get(RFCIndex);
        String newDoctorIdentityCard = newDoctorData.get(identityCardIndex);
        
        //sustituimos valores
        doctor.setName(newDoctorName);
        
        Address newAddress = new Address(newDoctorPostalCode,newDoctorAddressStreet,newDoctorAddressColony,newDoctorAddressCross);
        doctor.setAddress(newAddress);
        
        Phone newPhone = new Phone(newDoctorPhoneLada,newDoctorPhoneNumber);
        doctor.setPhone(newPhone);
        
        doctor.setRFC(newDoctorRFC);
    
        doctor.setIdentityCard(newDoctorIdentityCard);
       
        return doctor;
    }
    
    private ArrayList<String> getDoctorData(Doctor doctor){
        
        ArrayList<String> data = new ArrayList<String>();
        
        data.add(doctor.getName().toString());
        data.add( Integer.toString( doctor.getAddress().getZipCode() ) );
        data.add(doctor.getAddress().getStreet().toString());
        data.add(doctor.getAddress().getColony().toString());
        data.add(doctor.getAddress().getCrossovers().toString());
        data.add(doctor.getPhone().getLada().toString());
        data.add(doctor.getPhone().getNumber().toString());
        data.add(doctor.getRFC().toString());
        data.add(doctor.getIdentityCard().toString());
        
        return data;
    }
    
    private boolean isNewData(ArrayList<String> doctorData, ArrayList<String> newDoctorData){
        boolean result = false;
        int diferences = 0;
        
        for(int index = 0; index < newDoctorData.size(); index++){
            
            if(!( doctorData.get(index).equals(newDoctorData.get(index) ) ) ){
                diferences++;
            }
        }
        
        if (diferences>0) { result = true;  }
        
        return result;
    }
    
    public ArrayList<Doctor> getDoctorList(){
        ArrayList<Doctor> doctorList;
        doctorList = new ArrayList<Doctor> (getDoctors());
        return doctorList; 
    }
    
    /**
     * The method recieves the doctor and the userDoctor entitys to set the user to the doctor 
     * and then insert the doctor into the DataBase.
     * @param doctor
     * @param userDoctor 
     */
    public void setDoctorUser(Doctor doctor, UserDoctor userDoctor){
        doctor.setUser(userDoctor);
        doctorManager.addDoctor(doctor);
    }
    
    public ArrayList<Doctor> getDoctors(){
        return (ArrayList<Doctor>) doctorDAO.getList();
    }
    
    public int getDoctorId(ArrayList<String> doctorData){
        int id = 0;
        
        ArrayList<Doctor> doctorList = getDoctorList();
        String doctorUserName = doctorData.get(userNameIndex);
        
        for(int i =0; i < doctorList.size(); i++){
            String userName = doctorList.get(i).getUser().getUserName().toString();
            if(doctorUserName.equals(userName)){
               id = (int) doctorList.get(i).getId();
            }
        }
        
        return id;
    }
}

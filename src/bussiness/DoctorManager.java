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

public class DoctorManager extends Recepcionist<Doctor>{
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
    
    private DoctorManager(){
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
    
    /**
     * The method recieves the doctor and the userDoctor entitys to set the user to the doctor 
     * and then insert the doctor into the DataBase.
     * @param doctor
     * @param userDoctor 
     */
    public void setDoctorUser(Doctor doctor, UserDoctor userDoctor){
        doctor.setUser(userDoctor);
        doctorManager.register(doctor);
    }
    
    public Doctor getDoctor(int id){
        return (Doctor) doctorDAO.get(id);
    }
    
    public ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> doctorList;
        doctorList = new ArrayList<Doctor> ((ArrayList<Doctor>) doctorDAO.getList());
        return doctorList; 
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
    private Doctor createDoctor(ArrayList<String> data) throws InvalidFieldException {
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
    private UserDoctor createUserDoctor(ArrayList<String> data) throws InvalidFieldException{
        
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
            Doctor updatedDoctor = createDoctor(newDoctorData);
            updatedDoctor.setId(doctor.getId());
            updatedDoctor.setUser(doctor.getUser());
            edit(updatedDoctor);
                message = "SUCCESS";
            
        }catch(InvalidFieldException exception){
            System.out.println(exception.getMessage());
            message = exception.getMessage();
        }
        return message;
    }

    @Override
    public void register(Doctor doctor) {
        doctorDAO.add(doctor);
    }

    @Override
    public void edit(Doctor doctor) {
        doctorDAO.update(doctor);
    }

    @Override
    public void remove(int id) {
        Doctor doctor =  (Doctor)(doctorDAO.get(id));
        doctorDAO.delete(doctor);
    }
}

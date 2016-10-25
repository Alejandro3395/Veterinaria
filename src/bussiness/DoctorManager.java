/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import Data.DAOs.DoctorDAO;
import Entitys.Address;
import Entitys.Doctor;
import Entitys.Phone;
import Entitys.UserDoctor;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
public class DoctorManager {
    private static final DoctorManager doctorManager = new DoctorManager();
    private DoctorDAO doctorDAO;
    
    public DoctorManager(){
        this.doctorDAO = DoctorDAO.GetInstance();
    }
    
    public static DoctorManager GetInstance(){
        return doctorManager;
    }
    
    
    private void saveDoctor(Doctor doctor) {
        doctorDAO.add(doctor);
    }
    

    
    public Doctor createDoctor(ArrayList<String> data) {
        
        //  public Doctor(String name, Address address, Phone phone, String RFC, String identityCard) {
        
        String doctorName = data.get(0);
        int  doctorPostalCode = Integer.valueOf(data.get(1));
        String doctorAddressStreet = data.get(2);
        String doctorAddressColony = data.get(3);
        String doctorAddressCross = data.get(4);
        String doctorPhoneLada = data.get(5);
        String doctorPhoneNumber = data.get(6);
        String doctorRFC = data.get(7);
        String doctorIdentityCard = data.get(8);
        
        
        
        /*
        validamos reglas de negocio de identityCard y postalcode
        
        */
        
        Address doctorAddress = new Address(doctorPostalCode,doctorAddressStreet,doctorAddressColony,doctorAddressCross);
        Phone doctorPhone = new Phone(doctorPhoneLada,doctorPhoneNumber);
        
        Doctor doctorData = new Doctor(doctorName,doctorAddress,doctorPhone,doctorRFC,doctorIdentityCard);
        
       
        
        return doctorData;
    }
    
    public UserDoctor createUserDoctor(ArrayList<String> data){
        
        String doctorUserName = data.get(0);
        String doctorUserPassword = data.get(1);
        String doctorUserEmail = data.get(2);
        
        /*
        validamos reglas del negocio
        */
        
        UserDoctor userDoctor = new UserDoctor(doctorUserName, doctorUserPassword, doctorUserEmail);
        
        return userDoctor;
        
    }
    
    public void createEntity(ArrayList<String> doctorData, ArrayList<String> userDoctorData){
        
        Doctor doctor = new Doctor(createDoctor(doctorData));
        System.out.println(doctor.getName() + " " + doctor.getIdentityCard());
        UserDoctor user = new UserDoctor(createUserDoctor(userDoctorData));
        insertDoctor(doctor,user);
        
    }
    
    public void insertDoctor(Doctor doctor, UserDoctor userDoctor){
        
        doctor.setUser(userDoctor);
        doctorManager.saveDoctor(doctor);

    }
}

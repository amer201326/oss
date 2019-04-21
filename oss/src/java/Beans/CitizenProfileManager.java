/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenProfile;
import Data.GetDB_Eman;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class CitizenProfileManager implements Serializable{
    
    CitizenProfile updateProfile;

    @ManagedProperty(value = "#{msession}")
    Session session;
    public CitizenProfileManager() {
       
        
                updateProfile = new CitizenProfile(session.citizen.getId(), FirstName, FatherName, GrandFatherName, LastName, Gender, 0, idCard, telephone, mobile, email, fax, birthday, placeOfBirth, region, quarter, street, address, citizenJob, passportNumber, passportType)
         
    }
    
     public void updateCitizen() {

        updateProfile.updateCitizenProfile();
        
    }

    
    public CitizenProfile getUpdateProfile() {
        return updateProfile;
    }

        public void setUpdateProfile(CitizenProfile updateProfile) {
        this.updateProfile = updateProfile;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
}

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
import javax.annotation.PostConstruct;
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
public class CitizenProfileManager implements Serializable {

    CitizenProfile updateProfile;

    @ManagedProperty(value = "#{msession}")
    Session session;
    int id;

    public CitizenProfileManager() {
       

            updateProfile = new CitizenProfile(session.citizen.getId(), session.citizen.getFirstName(), session.citizen.getFatherName(),
                    session.citizen.getGrandFatherName(), session.citizen.getLastName(), session.citizen.getGender(), session.citizen.getFamilyMember(),
                    session.citizen.getIdCard(), session.citizen.getTelephone(), session.citizen.getMobile(), session.citizen.getEmail(),
                    session.citizen.getFax(), session.citizen.getBirthday(), session.citizen.getPlaceOfBirth(), session.citizen.getRegion(),
                    session.citizen.getQuarter(), session.citizen.getStreet(), session.citizen.getAddress(), session.citizen.getCitizenJob(),
                    session.citizen.getPassportNumber(), session.citizen.getPassportType());

         //   updateProfile = GetDB_Eman.GetCitizenProfileById();
        
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

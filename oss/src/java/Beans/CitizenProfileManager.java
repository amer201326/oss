/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Citizen;
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

    Citizen updateProfile;

    @ManagedProperty(value = "#{msession}")
    Session session;
    int id;

    public CitizenProfileManager() {
       
        

      
        
    }
      @PostConstruct
    public void init() {
        if(session.citizen!=null){
            updateProfile = session.citizen;
            updateProfile.getAccount().setPassword("");
        }
            
    }

    public void updateCitizen() {

        updateProfile.updateCitizen();

    }

    public Citizen getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(Citizen updateProfile) {
        this.updateProfile = updateProfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}

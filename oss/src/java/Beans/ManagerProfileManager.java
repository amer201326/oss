/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Manager;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class ManagerProfileManager implements Serializable{
    
    Manager updateProfile;
    
    @ManagedProperty(value = "#{msession}")
    Session session;

    
     public ManagerProfileManager() {
       
    }
     
      @PostConstruct
    public void init() {
        if (session.manager != null) {
            updateProfile = session.manager;
            
           
        }

    }

    public Manager getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(Manager updateProfile) {
        this.updateProfile = updateProfile;
    }
    
    
    
    
    
}

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

    
    public CitizenProfileManager() {
         Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");

        updateProfile = GetDB_Eman.GetCitizenProfileById(id);
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
    
    
}

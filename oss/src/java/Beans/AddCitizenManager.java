/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.CitizenManage;
import Beans.SessionLists;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddCitizenManager implements Serializable{
    
      @ManagedProperty(value = "#{sessionLists}")
     SessionLists sessionLists;

    public SessionLists getSessionLists() {
        return sessionLists;
    }

    public void setSessionLists(SessionLists sessionLists) {
        this.sessionLists = sessionLists;
    }
     
     
     
    public void gotToEdit(){
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editCitizen.xhtml?id="+sessionLists.seletedCitizen.getId());
        } catch (IOException ex) {
            Logger.getLogger(CitizenManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

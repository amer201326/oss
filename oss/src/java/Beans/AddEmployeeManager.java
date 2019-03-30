/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.IOException;
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
public class AddEmployeeManager {
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

            FacesContext.getCurrentInstance().getExternalContext().redirect("editEmployee.xhtml?id="+sessionLists.selectedEmployee.getEmp_id());
        } catch (IOException ex) {
            Logger.getLogger(CitizenManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

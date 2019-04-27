/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Manager;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class AddAdminManager implements Serializable{
    
    Manager newManager;

    public AddAdminManager(){
    
        newManager = new Manager();
    }

    
    
    public Manager getNewManager() {
        return newManager;
    }

    public void setNewManager(Manager newManager) {
        this.newManager = newManager;
    }
    
    
    public void addManager() {
        try {
            newManager.addManagerToDB();
            
           newManager = new Manager();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("allManagers.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AddAdminManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

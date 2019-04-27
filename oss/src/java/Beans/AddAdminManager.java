/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetDB_Eman;
import Data.Manager;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class AddAdminManager implements Serializable{
    
    Manager newManager;
     List<Manager> allmanagers;
     Manager managerSelected;

    public AddAdminManager(){
    
        newManager = new Manager();
        
         allmanagers = GetDB_Eman.getAllManagers();
         managerSelected = new Manager();
    }

     public void onSelectManager(SelectEvent event){
        managerSelected  = (Manager)event.getObject();
    }
      public void onManagerEdit(RowEditEvent event) {
        ((Manager) event.getObject()).update();

    }
      
       public void onManagerCancel(RowEditEvent event) {

    }
       
        public void onSelectManagerEd(SelectEvent event){
        managerSelected  = (Manager)event.getObject();
    }

    
    public Manager getNewManager() {
        return newManager;
    }

    public void setNewManager(Manager newManager) {
        this.newManager = newManager;
    }

    public List<Manager> getAllmanagers() {
        return allmanagers;
    }

    public void setAllmanagers(List<Manager> allmanagers) {
        this.allmanagers = allmanagers;
    }

    public Manager getManagerSelected() {
        return managerSelected;
    }

    public void setManagerSelected(Manager managerSelected) {
        this.managerSelected = managerSelected;
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

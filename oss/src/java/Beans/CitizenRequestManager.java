/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenRequest;
import Data.GetDB_Eman;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class CitizenRequestManager implements Serializable {
    
    CitizenRequest newCitizen;
    List<CitizenRequest> allCitizenRequest;
    CitizenRequest citizenSelected;
     List<CitizenRequest> filterCitizen;

    public CitizenRequestManager(){
    
        newCitizen = new CitizenRequest();
        allCitizenRequest = GetDB_Eman.getallCitizenRequest();
        citizenSelected = new CitizenRequest();
    }
    
    public void addRequestCitizen(){
         System.out.println(newCitizen + "emaneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        newCitizen.addCitizenRequestToDB();
        
    }
    
    public CitizenRequestManager(CitizenRequest newCitizen) {
        this.newCitizen = newCitizen;
    }

     public void onCitizenEdit(RowEditEvent event){
        CitizenRequest c = (CitizenRequest)event.getObject();
        c.citizenRequestUpdate();
    }
    public void onCitizenEditCancel(RowEditEvent event){
        
        
    }

    public CitizenRequest getCitizenSelected() {
        return citizenSelected;
    }

    public void setCitizenSelected(CitizenRequest citizenSelected) {
        this.citizenSelected = citizenSelected;
    }
    
    public CitizenRequest getNewCitizen() {
        return newCitizen;
    }

    public void setNewCitizen(CitizenRequest newCitizen) {
        this.newCitizen = newCitizen;
    }

    public List<CitizenRequest> getAllCitizenRequest() {
        return allCitizenRequest;
    }

    public void setAllCitizenRequest(List<CitizenRequest> allCitizenRequest) {
        this.allCitizenRequest = allCitizenRequest;
    }

    public List<CitizenRequest> getFilterCitizen() {
        return filterCitizen;
    }

    public void setFilterCitizen(List<CitizenRequest> filterCitizen) {
        this.filterCitizen = filterCitizen;
    }
    
     public void gotToEdit(int id){
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editCitizenRequest.xhtml?id="+id);
        } catch (IOException ex) {
            Logger.getLogger(CitizenManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenRequest;
import Data.GetDB_Eman;
import Data.GetFromDB;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
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
public class EditCitizenRequestManager implements Serializable{

    CitizenRequest citizenEdit;
    
    public EditCitizenRequestManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");

        citizenEdit = GetDB_Eman.GetCitizenRequestById(id);
        

    }

    public void editCitizen() {

        citizenEdit.updateCitizenRequest();
        
    }
    
    public void acceptCitizenRequest(){
        citizenEdit.acceptCitizenRequestAddToDB();
    }
    
    public void rejectCitizenRequest(){
        citizenEdit.rejectCitizenRequestDeleteFromDB();
    }

    public CitizenRequest getCitizenEdit() {
        return citizenEdit;
    }

    public void setCitizenEdit(CitizenRequest citizenEdit) {
        this.citizenEdit = citizenEdit;
    }

   

    public void onCitizenEdit(RowEditEvent event) {
        CitizenRequest c = (CitizenRequest) event.getObject();
        c.citizenRequestUpdate();
    }

    public void onCitizenEditCancel(RowEditEvent event) {

    }

}

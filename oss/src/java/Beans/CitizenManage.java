/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Citizen;
import Data.CitizenAccount;
import Data.GetDB_Eman;
import Data.GetFromDB;
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
public class CitizenManage implements Serializable {

    Citizen newCitizen;
    List<Citizen> allCitizen;
    Citizen citizenSelected;
    List<Citizen> filterCitizen;

    String errorUserName;
    
    public CitizenManage() {

        newCitizen = new Citizen();
        allCitizen = GetDB_Eman.getallCitizen();
        citizenSelected = new Citizen();
        

    }

    public void addCitizen() {

        newCitizen.addCitizenToDB();
       

        //انتقال الي صفحة كل المواطنين
    }

    public List<Citizen> getAllCitizen() {
        return allCitizen;
    }

    public void setAllCitizen(List<Citizen> allCitizen) {
        this.allCitizen = allCitizen;
    }

    public Citizen getCitizenSelected() {
        return citizenSelected;
    }

    public void setCitizenSelected(Citizen citizenSelected) {
        this.citizenSelected = citizenSelected;
    }

    public Citizen getNewCitizen() {
        return newCitizen;
    }

    public void setNewCitizen(Citizen newCitizen) {
        this.newCitizen = newCitizen;
    }
    
    public void onCitizenEdit(RowEditEvent event){
        Citizen c = (Citizen)event.getObject();
        c.citizenUpdate();
    }
    public void onCitizenEditCancel(RowEditEvent event){
        
        
    }

    public List<Citizen> getFilterCitizen() {
        return filterCitizen;
    }

    public void setFilterCitizen(List<Citizen> filterCitizen) {
        this.filterCitizen = filterCitizen;
    }

    public void gotToEdit(int id){
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editCitizen.xhtml?id="+id);
        } catch (IOException ex) {
            Logger.getLogger(CitizenManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getErrorUserName() {
        return errorUserName;
    }

    public void setErrorUserName(String errorUserName) {
        this.errorUserName = errorUserName;
    }
    
    public void cheekUserName() {
       if(newCitizen.getAccount().getUserName().length() < 6){
           errorUserName = "يجب ان يكون اكبر من ٦ احرف";
       }else if(GetFromDB.cheekUserName(newCitizen.getAccount().getUserName())){
           errorUserName = "اسم المستخدم موجود بالغعل";
       }else{
           errorUserName = "";
       }
    }
    
}

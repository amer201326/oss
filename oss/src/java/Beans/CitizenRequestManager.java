/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenRequest;
import Data.GetDB_Eman;
import Data.GetFromDB;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CitizenRequestManager implements Serializable {

    CitizenRequest newCitizen;
    List<CitizenRequest> allCitizenRequest;
    List<CitizenRequest> allCitizenRejected;
    CitizenRequest citizenSelected;
    List<CitizenRequest> filterCitizen;
    String errorUserName;

    public CitizenRequestManager() {

        newCitizen = new CitizenRequest();
        allCitizenRequest = GetDB_Eman.getallCitizenRequest();
        citizenSelected = new CitizenRequest();

        allCitizenRejected = GetDB_Eman.getallCitizenRejected();

    }

    public void addRequestCitizen() throws IOException {
        System.out.println(newCitizen + "emaneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        newCitizen.addCitizenRequestToDB();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

                            FacesContext.getCurrentInstance().addMessage("sub", new FacesMessage(FacesMessage.SEVERITY_ERROR," ", "بوجد مرفقات مطلوبة لم يتم ارفاقها"));

    }

    public CitizenRequestManager(CitizenRequest newCitizen) {
        this.newCitizen = newCitizen;
    }

    public void onCitizenEdit(RowEditEvent event) {
        CitizenRequest c = (CitizenRequest) event.getObject();
        c.citizenRequestUpdate();
    }

    public void onCitizenEditCancel(RowEditEvent event) {

    }

    public List<CitizenRequest> getAllCitizenRejected() {
        return allCitizenRejected;
    }

    public void setAllCitizenRejected(List<CitizenRequest> allCitizenRejected) {
        this.allCitizenRejected = allCitizenRejected;
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

    public void gotToEdit(int id) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editCitizenRequest.xhtml?id=" + id);
        } catch (IOException ex) {
            Logger.getLogger(CitizenManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotToSee(int id) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editCitizenRejected.xhtml?id=" + id);
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
        if(newCitizen.getCit_Username().matches("[0-9]")){
        if (newCitizen.getCit_Username().length() < 6) {
            errorUserName = "يجب ان يكون اكبر من ٦ احرف";
        } else if (GetFromDB.cheekUserName(newCitizen.getAccount().getUserName())) {
            errorUserName = "اسم المستخدم موجود بالغعل";
        } else {
            errorUserName = "";
        }
    }else{
             errorUserName = "يجب ان يحتوي على احرف وارقام فقط  ";
        }
    }

}

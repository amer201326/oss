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
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class EditCitizenManage implements Serializable{

    Citizen citizenEdit;
    
     @ManagedProperty(value = "#{msession}")
    Session session;
    
    public EditCitizenManage() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");

        citizenEdit = GetDB_Eman.GetCitizenById(id);
        

    }

    public void editCitizen() {

        citizenEdit.updateCitizen();
        
    }

    public Citizen getCitizenEdit() {
        return citizenEdit;
    }

    public void setCitizenEdit(Citizen citizenEdit) {
        this.citizenEdit = citizenEdit;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    

    public void onCitizenEdit(RowEditEvent event) {
        Citizen c = (Citizen) event.getObject();
        c.citizenUpdate();
    }

    public void onCitizenEditCancel(RowEditEvent event) {

    }
    
     public String urlSideBar() {
        if (session.employee != null) {
            if (session.employee.checkTypeAdmin()) {
                System.out.println("cheackAdmin is  = " + session.employee.checkTypeAdmin());
                return "../pages/sidebar.xhtml";
            }
        }
        if (session.employee != null) {
            if (session.employee.checkTypeEMP()) {

                System.out.println("cheackemp is  = " + session.employee.checkTypeEMP());

                return "../employeePages/sidebar.xhtml";
            }
        }
        return "";
    }

}

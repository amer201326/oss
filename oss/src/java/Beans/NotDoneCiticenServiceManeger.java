/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenService;
import Data.GetFromDBaraa;
import Data.ServiceCitizen;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class NotDoneCiticenServiceManeger implements Serializable {

    @ManagedProperty(value = "#{msession}")
    Session session;

    List<ServiceCitizen> NotDoneCitizenServices;
    int idCitizen;

    @PostConstruct
    public void init() {
        if (session.citizen != null) {
            System.out.println("Cit Not Null");
            idCitizen = session.citizen.getId();
            NotDoneCitizenServices = GetFromDBaraa.notDoneCitizenServices(idCitizen);
        }
    }

    public NotDoneCiticenServiceManeger() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<ServiceCitizen> getNotDoneCitizenServices() {
        return NotDoneCitizenServices;
    }

    public void setNotDoneCitizenServices(List<ServiceCitizen> NotDoneCitizenServices) {
        this.NotDoneCitizenServices = NotDoneCitizenServices;
    }

    public int getIdCitizen() {
        return idCitizen;
    }

    public void setIdCitizen(int idCitizen) {
        this.idCitizen = idCitizen;
    }
    
    public boolean dontHaveService(){
        return NotDoneCitizenServices.isEmpty();
    }

    public void showServiceCitizen(int idServiceCitizen) {
        
        System.out.println("Bbbbbvvv");
        try {
             for (ServiceCitizen service : NotDoneCitizenServices) {
            if (service.getService_Citizen_ID() == idServiceCitizen) {
                session.serviceCitizenShow = service;
            }
        }
         
             FacesContext.getCurrentInstance().getExternalContext().redirect("ShowMyService.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(NotDoneCiticenServiceManeger.class.getName()).log(Level.SEVERE, null, ex);
        }

        
       
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenService;
import Data.GetFromDBaraa;
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
public class NotDoneCiticenServiceManeger implements Serializable{
    
    @ManagedProperty(value = "#{msession}")
    Session session;
    
    List<CitizenService> NotDoneCitizenServices;
    int idCitizen;
    
            @PostConstruct
    public void init() {
     if(session.citizen != null){
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

    public List<CitizenService> getNotDoneCitizenServices() {
        return NotDoneCitizenServices;
    }

    public void setNotDoneCitizenServices(List<CitizenService> NotDoneCitizenServices) {
        this.NotDoneCitizenServices = NotDoneCitizenServices;
    }
    public void showServiceCitizen(int idServiceCitizen) {
        
           for (CitizenService service : NotDoneCitizenServices) {
            if(service.getId() == idServiceCitizen){
                session.serviceCitizenShow = service; 
            }
           } 
    }
}

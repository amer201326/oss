/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenService;
import Data.GetFromDBaraa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class DoneCitizenServicesManeger implements Serializable {

    List<CitizenService> doneCitizenServices ;
    @ManagedProperty(value = "#{msession}")
    Session session;
    int idCitizen;

        @PostConstruct
 public void init() {
     if(session.citizen != null){
            idCitizen = session.citizen.getId();
         doneCitizenServices = GetFromDBaraa.doneCitizenServices(idCitizen);
        }
 }
    public DoneCitizenServicesManeger() {
        
       
    }

    public boolean dontHaveService(){
        return doneCitizenServices.isEmpty();
    }

    
    public List<CitizenService> getDoneCitizenServices() {
        return doneCitizenServices;
    }

    public void setDoneCitizenServices(List<CitizenService> doneCitizenServices) {
        this.doneCitizenServices = doneCitizenServices;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    public boolean nothaveSer(){
        return doneCitizenServices.isEmpty();
    }
}

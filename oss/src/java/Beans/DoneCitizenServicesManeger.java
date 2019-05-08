/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenService;
import Data.Department;
import Data.GetFromDB;
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
public class DoneCitizenServicesManeger implements Serializable {
    List<CitizenService> allDoneCitizenServices ;
    List<CitizenService> doneCitizenServices ;
    @ManagedProperty(value = "#{msession}")
    Session session;
    int idCitizen;
    int idDepartment;
    
        @PostConstruct
 public void init() {
     if(session.citizen != null){
            idCitizen = session.citizen.getId();
         allDoneCitizenServices = GetFromDBaraa.doneCitizenServices(idCitizen);
         doneCitizenServices = new ArrayList<>();
         for (CitizenService allDoneCitizenService : allDoneCitizenServices) {
             doneCitizenServices.add(allDoneCitizenService);
         }
        }
 }
    public DoneCitizenServicesManeger() {
        
       
    }
    public void filter() {
       
      doneCitizenServices.clear();
      if(idDepartment == -1){
           for (CitizenService allDoneCitizenService : allDoneCitizenServices) {
             doneCitizenServices.add(allDoneCitizenService);
         }
      }else{
        for (CitizenService doneCitizenService : allDoneCitizenServices) {
            if(doneCitizenService.getProvidServicee().getDepartment().id == idDepartment){
                doneCitizenServices.add(doneCitizenService);
            }
        }
      }
    }
     public List<Department> allDepartment() {
        return GetFromDB.getDepartments();
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

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
   public void showServiceCitizen(int idServiceCitizen) {
        
        try {
             for (ServiceCitizen service : allDoneCitizenServices) {
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

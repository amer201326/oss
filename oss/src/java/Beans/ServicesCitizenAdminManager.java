/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.Employee;
import Data.GetFromDB;
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
public class ServicesCitizenAdminManager implements Serializable {

    List<ServiceCitizen> allRequestService;
    List<ServiceCitizen> allRequestServiceView;
    List<ServiceCitizen> allRequestServiceNotView;

    ServiceCitizen serviceSelected;

    List<ServiceCitizen> filteredRequestService;
    
    @ManagedProperty(value = "#{msession}")
    Session session;

    public ServicesCitizenAdminManager() {
        serviceSelected = new ServiceCitizen();
    }

    @PostConstruct
    public void init() {
        if (session.employee != null) {
            allRequestService = new ArrayList<>();
            allRequestService = GetFromDB.getAllRequestService(session.employee);
            allRequestServiceView = new ArrayList<>();
            allRequestServiceNotView = new ArrayList<>();
            
            
                for (int i = 0; i < allRequestService.size(); i++) {
                    ServiceCitizen get = allRequestService.get(i);
                    
                    if (get.getDecisionsDepartment().getStatus().compareTo("show") == 0) {
                        //get.messages(session.employee.getEmp_id());
                        allRequestServiceView.add(get);
                    } else {
                        allRequestServiceNotView.add(get);
                    }
                }
               
        }

        serviceSelected = new ServiceCitizen();
    }

    public void showServise() {
        try {
            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("showSelectServiceCitizen.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public void showDoneServise() {
        try {

            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("showSelectServiceCitizen.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<ServiceCitizen> getAllRequestService() {
        return allRequestService;
    }

    public void setAllRequestService(List<ServiceCitizen> allRequestService) {
        this.allRequestService = allRequestService;
    }

    public List<ServiceCitizen> getAllRequestServiceView() {
        return allRequestServiceView;
    }

    public void setAllRequestServiceView(List<ServiceCitizen> allRequestServiceView) {
        this.allRequestServiceView = allRequestServiceView;
    }

    public List<ServiceCitizen> getAllRequestServiceNotView() {
        return allRequestServiceNotView;
    }

    public void setAllRequestServiceNotView(List<ServiceCitizen> allRequestServiceNotView) {
        this.allRequestServiceNotView = allRequestServiceNotView;
    }

    public ServiceCitizen getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(ServiceCitizen serviceSelected) {
        this.serviceSelected = serviceSelected;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    
    public List<ServiceCitizen> getFilteredRequestService() {
        return filteredRequestService;
    }

    public void setFilteredRequestService(List<ServiceCitizen> filteredRequestService) {
        this.filteredRequestService = filteredRequestService;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.Department;
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
    
    List<ServiceCitizen> allRequestServiceNotDone;//show
    List<ServiceCitizen> allRequestServiceDone;//done
    List<ServiceCitizen> allRequestServiceNotView;//notdone
    ServiceCitizen serviceSelected;

    List<ServiceCitizen> filteredRequestService;
    List<Department>  departments ;
     List<String> Services;
    @ManagedProperty(value = "#{msession}")
    Session session;

    public ServicesCitizenAdminManager() {
        serviceSelected = new ServiceCitizen();
    }

    @PostConstruct
    public void init() {
       
            allRequestService = new ArrayList<>();
            allRequestService = GetFromDB.getAllRequestService();
            
            allRequestServiceNotDone = new ArrayList<>();
            allRequestServiceDone = new ArrayList<>();
            allRequestServiceNotView = new ArrayList<>();
            Services = new ArrayList<>();
            
            System.out.println("size+="+allRequestService.size());
            
                for (int i = 0; i < allRequestService.size(); i++) {
                    ServiceCitizen get = allRequestService.get(i);
                    Services.add(get.service.getName());
                    if (get.getStatus().compareTo("view") == 0) {
                        //get.messages(session.employee.getEmp_id());
                        allRequestServiceNotDone.add(get);
                    } else  if (get.getStatus().compareTo("done") == 0){
                        allRequestServiceDone.add(get);
                    }else{
                        allRequestServiceNotView.add(get);
                    }
                }
        departments = new ArrayList<>();
        departments = GetFromDB.getDepartments();
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

    public List<ServiceCitizen> getAllRequestServiceNotDone() {
        return allRequestServiceNotDone;
    }

    public void setAllRequestServiceNotDone(List<ServiceCitizen> allRequestServiceNotDone) {
        this.allRequestServiceNotDone = allRequestServiceNotDone;
    }

    public List<ServiceCitizen> getAllRequestServiceDone() {
        return allRequestServiceDone;
    }

    public void setAllRequestServiceDone(List<ServiceCitizen> allRequestServiceDone) {
        this.allRequestServiceDone = allRequestServiceDone;
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<String> getServices() {
        return Services;
    }

    public void setServices(List<String> Services) {
        this.Services = Services;
    }

   

    
}

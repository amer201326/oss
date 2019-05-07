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
import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.Service;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class ServiceCitizenManager implements Serializable {

    List<ServiceCitizen> allRequestService;
    List<ServiceCitizen> allRequestServiceView;
    List<ServiceCitizen> allRequestServiceNotView;

    ServiceCitizen serviceSelected;

    @ManagedProperty(value = "#{msession}")
    Session session;

    public ServiceCitizenManager() {

        serviceSelected = new ServiceCitizen();
    }

    @PostConstruct
    public void init() {
        if (session.employee != null) {
            allRequestService = new ArrayList<>();
            allRequestService = GetFromDB.getAllRequestService(session.employee);
            allRequestServiceView = new ArrayList<>();
            allRequestServiceNotView = new ArrayList<>();
            
            if (!session.employee.checkTypeHed()) {
                for (int i = 0; i < allRequestService.size(); i++) {
                    ServiceCitizen get = allRequestService.get(i);
                    if (get.getService_Job().getStatus().compareTo("done") == 0) {
                        get.messages(session.employee.getEmp_id());
                        allRequestServiceView.add(get);
                    } else {
                        allRequestServiceNotView.add(get);
                    }
                }
            }else{
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
        }

        serviceSelected = new ServiceCitizen();
    }

    public void showServise() {
        try {
            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowService.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showHeadServise() {
        try {
            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowServiceHead.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDoneServise() {
        try {

            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowServiceDone.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showMessagesServise() {
        try {

            session.serviceCitizenShow = serviceSelected;
            FacesContext.getCurrentInstance().getExternalContext().redirect("MessagesEmployes.xhtml");

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

    private List<DecisionsDepartment> getDecisionsDepartmentsforEmployee(Employee employee) {
        List<DecisionsDepartment> dds = GetFromDB.getDecisionsDepartment(employee);

        for (DecisionsDepartment dd : dds) {
            List<DecisionSection> dses = GetFromDB.getDecisionsSection(employee, dd);
            dd.setSection(dses);
            for (DecisionSection ds : dses) {
                List<DecisionsJob> djs = GetFromDB.getDecisionsJob(employee, ds);
                ds.setJobs(djs);

            }

        }

        return dds;
    }

}

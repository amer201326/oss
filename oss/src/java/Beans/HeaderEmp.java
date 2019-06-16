/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.Employee;
import Data.GetFromDB;
import Data.Service;
import Data.ServiceCitizen;
import Data.ServiceCount;
import java.io.ByteArrayInputStream;
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
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author Amer
 */
@ManagedBean
@ViewScoped
public class HeaderEmp implements Serializable {

    Employee employee;
    List<ServiceCitizen> serviceCitizens = new ArrayList<>();
    List<ServiceCount> servicesCount;

    int numberOfNot;
    int numberOfservecNotDone;
    int numberOfservecDone;

    public HeaderEmp() {

    }

    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {
        if (session.employee != null) {
            employee = session.employee;
            updateNotification();

        }
        servicesCount = GetFromDB.getMore3ServiceRequest();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ServiceCitizen> getServiceCitizens() {
        return serviceCitizens;
    }

    public void setServiceCitizens(List<ServiceCitizen> serviceCitizens) {
        this.serviceCitizens = serviceCitizens;
    }

    public int getNumberOfNot() {
        return numberOfNot;
    }

    public void setNumberOfNot(int numberOfNot) {
        this.numberOfNot = numberOfNot;
    }

    public void updateNotification() {
        serviceCitizens.clear();
        List<ServiceCitizen> list = GetFromDB.getAllRequestService(employee);
        numberOfservecDone = 0;
        numberOfservecNotDone = 0;
        if (employee.checkTypeHed()) {
            for (ServiceCitizen list1 : list) {
                if (list1.getDecisionsDepartment() == null) {
                    System.out.println("\n\n\n null  updateNotification   getDecisionsDepartment  \n\n\n");
                }
                if (list1.getDecisionsDepartment().getRunning().compareTo("no") == 0) {
                    serviceCitizens.add(list1);
                }

            }

        } else {
            for (ServiceCitizen list1 : list) {

                if (list1.getService_Job().getStatus().compareTo("done") == 0) {
                   numberOfservecDone++;
                    
                } else {
                   numberOfservecNotDone++;
                }
                if (list1.getService_Job().getShow().compareTo("no") == 0) {
                    serviceCitizens.add(list1);
                }
            }

        }
        numberOfNot = serviceCitizens.size();

    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean showNotification() {
        if (employee.checkTypeAdmin()) {
            return false;
        }
        return true;
    }

    public void showServise() {

        System.out.println("show ssssssssssssssssssssssssssssssssss - -  -");
        try {
            if (employee.checkTypeHed()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ShowServiceHead.xhtml");

            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ShowService.xhtml");

            }

        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String urlallServices() {
        if (employee.checkTypeEMP()) {
            return "../employeePages/serviceCitizennNotDone.xhtml";
        } else if (employee.checkTypeHed()) {
            return "../employeePages/serviceCitizennNotDone.xhtml";

        }
        return "";
    }
    

    public String urlProfile() {
        if (employee.checkTypeEMP() || employee.checkTypeHed()) {
            return "../employeePages/employeeProfile.xhtml";
        }
        if (employee.checkTypeAdmin()) {
            return "../manager/managerProfile.xhtml";
        }
        return "";
    }

    public int getNumberOfservecNotDone() {
        return numberOfservecNotDone;
    }

    public void setNumberOfservecNotDone(int numberOfservecNotDone) {
        this.numberOfservecNotDone = numberOfservecNotDone;
    }

    public int getNumberOfservecDone() {
        return numberOfservecDone;
    }

    public void setNumberOfservecDone(int numberOfservecDone) {
        this.numberOfservecDone = numberOfservecDone;
    }

    public List<ServiceCount> getServicesCount() {
        return servicesCount;
    }

    public void setServicesCount(List<ServiceCount> servicesCount) {
        this.servicesCount = servicesCount;
    }

}

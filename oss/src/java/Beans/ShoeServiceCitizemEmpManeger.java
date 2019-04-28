/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Amer
 */
import DB.DB;
import Data.Employee;
import Data.GetFromDB;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
import Data.Service_Job;
import Data.ViewerAttachment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class ShoeServiceCitizemEmpManeger {

    ServiceCitizen serviseCitizen;
    Employee employee;
    
    @ManagedProperty(value = "#{msession}")
    Session session;
    
    boolean haveService = false;
    List<UploadedFile> allFileEmployee;
    @PostConstruct
    public void init() {
        serviseCitizen = session.serviceCitizenShow;
        employee =  session.employee;
        
        if (serviseCitizen != null) {
            haveService = true;
            filterFormOrNot();
        }

    }

    public void filterFormOrNot() {
        List<ServiceAttachmentName> allAtt = GetFromDB.getAttachmentByserviceCitizen(serviseCitizen.getServices_Provided_ID(), serviseCitizen.getCit_ID() , serviseCitizen.getService_Citizen_ID());
        List<ServiceAttachmentName> att = new ArrayList<ServiceAttachmentName>();
        List<ServiceAttachmentName> attform = new ArrayList<ServiceAttachmentName>();

        List<ViewerAttachment> jobViewer = GetFromDB.getJobviewerByserviceById(serviseCitizen.getServices_Provided_ID());

        for (ServiceAttachmentName serviceAttachmentName : allAtt) {
            for (ViewerAttachment viewer : jobViewer) {
                if (serviceAttachmentName.getId() == viewer.getServiceAttachmentName_ID() && 
                       employee.getDep_id()==viewer.getDep_ID()&& employee.getSec_id()==viewer.getSec_ID() &&employee.getJob_id()==viewer.getJob_ID()) {
                    
                    if ("yes".equals(serviceAttachmentName.getForm())) {
                        attform.add(serviceAttachmentName);
                    } else {
                        att.add(serviceAttachmentName);
                    }
                    
                }
            }
        }

        serviseCitizen.attachment = att;
        serviseCitizen.attwhithFile = attform;
    }

    public ServiceCitizen getServiseCitizen() {
        return serviseCitizen;
    }

    public void setServiseCitizen(ServiceCitizen serviseCitizen) {
        this.serviseCitizen = serviseCitizen;
    }

   

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isHaveService() {
        return haveService;
    }

    public void setHaveService(boolean haveService) {
        this.haveService = haveService;
    }

    public void submit() throws SQLException {
        System.out.print("cost == -"+serviseCitizen.getDecisionsJob().getCost());
        serviseCitizen.ContineuInPath(employee.getEmp_id());
        

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<UploadedFile> getAllFileEmployee() {
        return allFileEmployee;
    }

    public void setAllFileEmployee(List<UploadedFile> allFileEmployee) {
        this.allFileEmployee = allFileEmployee;
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        
        allFileEmployee.add(event.getFile());
        System.out.println("upload file");
    }

}

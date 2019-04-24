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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
public class ShoeServiceCitizemEmpManeger {

    ServiceCitizen_1 serviseCitizen;
    Employee employee;
    
    @ManagedProperty(value = "#{msession}")
    Session session;

    boolean haveService = false;

    @PostConstruct
    public void init() {
        serviseCitizen = session.serviceCitizen;
        employee =  session.employee;
        
        if (serviseCitizen != null) {
            haveService = true;
            filterFormOrNot();
        }

    }

    public void filterFormOrNot() {
        List<ServiceAttachmentName> allAtt = GetFromDB.getAttachmentByserviceCitizen(serviseCitizen.getServices_Provided_ID(), serviseCitizen.getCit_ID());
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

        serviseCitizen.att = att;
        serviseCitizen.attform = attform;
    }

    public ServiceCitizen_1 getServiseCitizen() {
        return serviseCitizen;
    }

    public void setServiseCitizen(ServiceCitizen_1 serviseCitizen) {
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
        Service_Job sj = serviseCitizen.getService_Job();
        DB db;
        try {
            db = new DB();
            if (sj.getOrder_Job() != 0) {

                serviseCitizen.getService_Job().updateDataBase();
                String q = "UPDATE service_jobs SET status = 'done' WHERE (`Dep_ID` = " + sj.getDep_ID() + ") and (`Cit_ID` = " + sj.getCit_ID() + ") and (`Sec_ID` = " + sj.getSec_ID() + ") and (`Job_ID` = " + sj.getJob_ID() + ") and (`Order_Departmant` = " + sj.getOrder_Departmant() + ") and (`Order_Section` = " + sj.getOrder_Section() + ") and (`Order_Job` = " + sj.getOrder_Job() + ") and (`Services_Provided_ID` = " + sj.getServices_Provided_ID() + ") and (`Service_Citizen_ID` = " + sj.getService_Citizen_ID() + ");";
                db.write(q);
            } else {
                String q = "UPDATE service_jobs SET status = 'done' WHERE (`Dep_ID` = " + sj.getDep_ID() + ") and (`Cit_ID` = " + sj.getCit_ID() + ") and (`Sec_ID` = " + sj.getSec_ID() + ") and (`Job_ID` = " + sj.getJob_ID() + ") and (`Order_Departmant` = " + sj.getOrder_Departmant() + ") and (`Order_Section` = " + sj.getOrder_Section() + ") and (`Order_Job` = " + sj.getOrder_Job() + ") and (`Sec_ID` = " + sj.getSec_ID() + ") and (`Order_Departmant` = " + sj.getOrder_Departmant() + ") and (`Order_Section` = " + sj.getOrder_Section() + ") and (`Services_Provided_ID` = " + sj.getServices_Provided_ID() + ") and (`Service_Citizen_ID` = " + sj.getService_Citizen_ID() + ");";
                db.write(q);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoeServiceCitizemEmpManeger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

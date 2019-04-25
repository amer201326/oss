/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class ServiceCitizen_1 implements Serializable {

    Service service;
    int Service_Citizen_ID;
    int Services_Provided_ID;
    int Cit_ID;
    String Date;
    String status;
    String note;
    Citizen citizen;
    Service_Job service_Job;

    String externalMessage;
    String internalMessage;
    
    public List<ServiceAttachmentName> att = new ArrayList<ServiceAttachmentName>();
    public List<ServiceAttachmentName> attform = new ArrayList<ServiceAttachmentName>();


    public ServiceCitizen_1() {
    }

    public ServiceCitizen_1(Service service, int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID, String Date, String status, String note, Citizen citizen, Service_Job service_Job) {
        this.service = service;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.Date = Date;
        this.status = status;

        this.note = note;
        this.citizen = citizen;
        this.service_Job = service_Job;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getService_Citizen_ID() {
        return Service_Citizen_ID;
    }

    public void setService_Citizen_ID(int Service_Citizen_ID) {
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    public int getServices_Provided_ID() {
        return Services_Provided_ID;
    }

    public void setServices_Provided_ID(int Services_Provided_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Service_Job getService_Job() {
        return service_Job;
    }

    public void setService_Job(Service_Job service_Job) {
        this.service_Job = service_Job;
    }

    public String getExternalMessage() {
        return externalMessage;
    }

    public void setExternalMessage(String externalMessage) {
        this.externalMessage = externalMessage;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }

    public List<ServiceAttachmentName> getAtt() {
        return att;
    }

    public void setAtt(List<ServiceAttachmentName> att) {
        this.att = att;
    }

    public List<ServiceAttachmentName> getAttform() {
        return attform;
    }

    public void setAttform(List<ServiceAttachmentName> attform) {
        this.attform = attform;
    }

//    public void messages(Employee e ) {
//        
//        try {
//            DB db = new DB();
//
//            String sql ="SELECT Com_ExternalMessage, Com_InternalMessage FROM decisions_job where Dep_ID= "+e.dep_id+" and Sec_ID= "+e.sec_id+" and Job_ID="+e.job_id+" and Cit_ID="+Cit_ID+" and Service_Citizen_ID="+Service_Citizen_ID +" ;";
//            System.out.println(sql);
//            ResultSet r = db.read(sql);
//            while (r.next()) {
//                externalMessage = r.getString(1);
//                internalMessage =  r.getString(2);
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(ServiceCitizen_1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

}

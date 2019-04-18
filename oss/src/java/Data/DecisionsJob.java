/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baraakali
 */
public class DecisionsJob {

    JobPath job;
    int idEmployee;
    
    String status;
    String runing;
    String cost;
    String internalMessage;
    String externalMessage;
    String date;

    int Services_Provided_ID;
    int Cit_ID;
    int Service_Citizen_ID;
    
    public DecisionsJob(JobPath job, int idEmployee, String status, String runing, String cost, String internalMessage, String externalMessage, String date) {
        this.job = job;
        this.idEmployee = idEmployee;
        this.status = status;
        this.runing = runing;
        this.cost = cost;
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
        this.date = date;
    }
    
    
    
    public DecisionsJob(JobPath job, String status, String runing, String cost, String externalMessage) {
        this.job = job;
        this.status = status;
        this.runing = runing;
        this.cost = cost;
        this.externalMessage = externalMessage;
    }

    public DecisionsJob(String status, String runing, String cost, String internalMessage, String externalMessage) {
        this.status = status;
        this.runing = runing;
        this.cost = cost;
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
    }

    public DecisionsJob() {
    }

    public DecisionsJob(int Services_Provided_ID, int Cit_ID, int Service_Citizen_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    
    
    public JobPath getJob() {
        return job;
    }

    public void setJob(JobPath job) {
        this.job = job;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRuning() {
        return runing;
    }

    public void setRuning(String runing) {
        this.runing = runing;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInternalMessage() {
        return internalMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }

    public String getExternalMessage() {
        return externalMessage;
    }

    public void setExternalMessage(String externalMessage) {
        this.externalMessage = externalMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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

    public int getService_Citizen_ID() {
        return Service_Citizen_ID;
    }

    public void setService_Citizen_ID(int Service_Citizen_ID) {
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    public void addToDB() throws SQLException, ClassNotFoundException {
      
            DB data = new DB();
            String q = "INSERT INTO decisions_job (`Dep_ID`, `Sec_ID`, `Job_ID`, `Services_Provided_ID`, `Order_Departmant`, `Order_Section`, `Order_Job`, `Cit_ID`, `Service_Citizen_ID`, `Emp_ID`, `Status`, `Cost`) VALUES ('" + job.DepId + "', '" + job.sectionID + "', '" + job.id + "', '" + Services_Provided_ID + "', '" + job.dOrder + "', '" + job.sOrder + "', '" + job.order + "', '" + Cit_ID + "', '" + Service_Citizen_ID + "', '" + idEmployee + "', 'notdone', 0);";
            System.out.println("qj  =  "+q);
            data.write(q);

       
    }

    @Override
    public String toString() {
        return "DecisionsJob{" + "job=" + job + ", idEmployee=" + idEmployee + ", status=" + status + ", runing=" + runing + ", cost=" + cost + ", internalMessage=" + internalMessage + ", externalMessage=" + externalMessage + ", date=" + date + ", Services_Provided_ID=" + Services_Provided_ID + ", Cit_ID=" + Cit_ID + ", Service_Citizen_ID=" + Service_Citizen_ID + '}';
    }
    
}

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
    Employee employee;
    String status;
    String runing;
    String cost;
    String internalMessage;
    public String externalMessage;
    String date;

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

    
    public JobPath getJob() {
        return job;
    }

    public void setJob(JobPath job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public void addToDB(int Services_Provided_ID, int Cit_ID, int Service_Citizen_ID) {
        try {
            DB data = new DB();
            String q = "INSERT INTO decisions_job (`Dep_ID`, `Sec_ID`, `Job_ID`, `Services_Provided_ID`, `Order_Departmant`, `Order_Section`, `Order_Job`, `Cit_ID`, `Service_Citizen_ID`, `Emp_ID`, `Status`, `Cost`) VALUES ('" + job.DepId + "', '" + job.sectionID + "', '" + job.id + "', '" + Services_Provided_ID + "', '" + job.dOrder + "', '" + job.sOrder + "', '" + job.order + "', '" + Cit_ID + "', '" + Service_Citizen_ID + "', '" + employee.emp_id + "', 'notdone', '0');";
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(DecisionsJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DecisionsJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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

    public DecisionsJob(JobPath job, String status, String runing, String cost,String externalMessage) {
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
    
    
            
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baraakali
 */
public class DecisionsDepartment {
    String internalMessage;
    String externalMessage;
    String status;
    String cost;
    String date;
    int depId;
    int depOrder;
    String depName;
    List<DecisionsJob> jobs =new ArrayList<DecisionsJob>();

    public DecisionsDepartment(String internalMessage, String externalMessage, String status, String cost, int depId, int depOrder, String depName) {
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
        this.status = status;
        this.cost = cost;
        this.depId = depId;
        this.depOrder = depOrder;
        this.depName = depName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getDepOrder() {
        return depOrder;
    }

    public void setDepOrder(int depOrder) {
        this.depOrder = depOrder;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<DecisionsJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<DecisionsJob> jobs) {
        this.jobs = jobs;
    }

    
    
    
    
}

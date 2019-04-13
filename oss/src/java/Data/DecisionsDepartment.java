/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    List<DecisionsJob> jobs = new ArrayList<DecisionsJob>();

    public DecisionsDepartment(String internalMessage, String externalMessage, String status, String cost, int depId, int depOrder, String depName) {
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
        this.status = status;
        this.cost = cost;
        this.depId = depId;
        this.depOrder = depOrder;
        this.depName = depName;
    }

    public DecisionsDepartment(String status, String cost, int depId, int depOrder) {
        this.status = status;
        this.cost = cost;
        this.date = date;
        this.depId = depId;
        this.depOrder = depOrder;
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

    public void addToDB(int Services_Provided_ID , int Cit_ID, int Service_Citizen_ID) {
        try {
            DB data = new DB();
            String q = "INSERT INTO decisions_department (`Dep_ID`, `Order_Departmant`, `Services_Provided_ID`, `Cit_ID`, `Service_Citizen_ID`, `Status`, `Cost`) VALUES ('" + depId + "', '" + depOrder + "', '" + Services_Provided_ID + "', '" + Cit_ID + "', '" + Service_Citizen_ID + "', '"+status+"', '"+cost+"');";
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(DecisionsDepartment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DecisionsDepartment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

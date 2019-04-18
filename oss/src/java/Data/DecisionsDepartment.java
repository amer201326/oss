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

    int depId;
    int depOrder;
    int Services_Provided_ID;
    int Cit_ID;
    int Service_Citizen_ID;

    String status;
    double cost;

    String internalMessage;
    String externalMessage;
    String date;
    String depName;
    
    List<DecisionSection> section = new ArrayList<>();

    public DecisionsDepartment(int depId, int depOrder, int Services_Provided_ID, int Cit_ID, int Service_Citizen_ID, String status, double cost, String internalMessage, String externalMessage, String date) {
        this.depId = depId;
        this.depOrder = depOrder;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.status = status;
        this.cost = cost;
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
        this.date = date;
    }

    public DecisionsDepartment(String internalMessage, String externalMessage, String status, double cost, int depId, int depOrder, String depName) {
        this.internalMessage = internalMessage;
        this.externalMessage = externalMessage;
        this.status = status;
        this.cost = cost;
        this.depId = depId;
        this.depOrder = depOrder;
        this.depName = depName;
    }

    public DecisionsDepartment(String status, double cost, int depId, int depOrder) {
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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

   
    public void addToDB() throws SQLException, ClassNotFoundException {
      
            DB data = new DB();
            String q = "INSERT INTO decisions_department (`Dep_ID`, `Order_Departmant`, `Services_Provided_ID`, `Cit_ID`,"
                    + " `Service_Citizen_ID`, `Status`, `Cost`) VALUES ('" + depId + "', '" + depOrder + "', '" + Services_Provided_ID + "',"
                    + " '" + Cit_ID + "', '" + Service_Citizen_ID + "', '" + status + "', '" + cost + "');";
            System.out.println("q d "+q);
            data.write(q);

        
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

    public List<DecisionSection> getSection() {
        return section;
    }

    public void setSection(List<DecisionSection> section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "DecisionsDepartment{" + "depId=" + depId + ", depOrder=" + depOrder + ", Services_Provided_ID=" + Services_Provided_ID + ", Cit_ID=" + Cit_ID + ", Service_Citizen_ID=" + Service_Citizen_ID + ", status=" + status + ", cost=" + cost + ", internalMessage=" + internalMessage + ", externalMessage=" + externalMessage + ", date=" + date + ", depName=" + depName + ", section=" + section + '}';
    }
    
}

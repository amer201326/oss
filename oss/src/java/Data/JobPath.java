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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class JobPath implements Serializable{
    int DepId;
    int sectionID;
    
    int id;
    int idService;
    String name;
    int dOrder;
    int sOrder;
    int order;
    
    
    String idMarge;
    public JobPath() {
    }

    public JobPath(int sectionID, int id, String name, int order) {
        this.sectionID = sectionID;
        this.id = id;
        this.name = name;
        this.order = order;
        idMarge = id+"-"+sectionID;
    }

    public JobPath(int DepId, int sectionID, int id, int dOrder, int sOrder, int order) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
        idMarge = id+"-"+sectionID;
    }

    public JobPath(int DepId, int sectionID, int id, String name, int dOrder, int sOrder, int order) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.name = name;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
    }
    

    
    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getIdMarge() {
        return idMarge;
    }

    public void setIdMarge(String idMarge) {
        this.idMarge = idMarge;
    }
    
    public void idMarge(){
        idMarge = id+"-"+sectionID;
    }

    public int getDepId() {
        return DepId;
    }

    public void setDepId(int DepId) {
        this.DepId = DepId;
    }
    

    @Override
    public String toString() {
        return "JobPath{" + "sectionID=" + sectionID + ", id=" + id + ", name=" + name + ", order=" + order + '}';
    }
public boolean addToDataBase(int  idService) {
        try {
            DB d = new DB();
            String q = "INSERT INTO steps_job VALUES(" + DepId + "," + sectionID + "," + id + "," + idService + "," + dOrder + "," + sOrder + "," + order + ");";
            System.out.println(q);
            d.write(q);
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentPaths.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartmentPaths.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return false;
    }

    public int getdOrder() {
        return dOrder;
    }

    public void setdOrder(int dOrder) {
        this.dOrder = dOrder;
    }

    public int getsOrder() {
        return sOrder;
    }

    public void setsOrder(int sOrder) {
        this.sOrder = sOrder;
    }
    
    
}

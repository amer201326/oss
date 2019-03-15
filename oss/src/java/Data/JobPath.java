/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;

/**
 *
 * @author me
 */
public class JobPath {

    int sectionID;
    int id;
    String name;
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

    @Override
    public String toString() {
        return "JobPath{" + "sectionID=" + sectionID + ", id=" + id + ", name=" + name + ", order=" + order + '}';
    }
    
}

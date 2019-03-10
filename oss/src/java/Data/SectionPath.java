package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author me
 */
public class SectionPath implements Serializable{
    int id;
    String name;
    int order;
    public List<JobPath> jobs;

    public SectionPath() {
        jobs = new ArrayList<JobPath>();
    }

    public SectionPath(int id, String name, List<JobPath> jobs) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
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

    public List<JobPath> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobPath> jobs) {
        this.jobs = jobs;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    public void getNameFromDataBase() {
      
        try {
            DB db = new DB();
         String sql = "SELECT Sec_Name FROM section where Sec_ID = "+this.id+";";

            ResultSet r = db.read(sql);
            while (r.next()) {
               this.name = r.getString(1);
              
            }
        } catch (Exception e) {
            
        }
       
        
    }
}

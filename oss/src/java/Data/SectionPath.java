package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author me
 */
public class SectionPath implements Serializable {
    int departmentId;
    
    int id;
    int idService;
    String name;
    int orderDepartment;
    int order;
    
    
    public List<JobPath> jobs = new ArrayList<JobPath>();

    public SectionPath() {
      
    }

    public SectionPath(int departmentId, int id, int idService, int orderDepartment, Integer order) {
        this.departmentId = departmentId;
        this.id = id;
        this.idService = idService;
        this.orderDepartment = orderDepartment;
        this.order = order;
    }
    
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public SectionPath(int id, int order) {
        this.id = id;
        this.order = order;
    }

    
    public SectionPath(int departmentId, int id, int idService, String name, int orderDepartment, Integer order) {
        this.departmentId = departmentId;
        this.id = id;
        this.idService = idService;
        this.name = name;
        this.orderDepartment = orderDepartment;
        this.order = order;
    }
    public SectionPath(int departmentId,int orderDepartment,int id,String name, Integer order) {
        this.departmentId = departmentId;
        this.id = id;
        this.name = name;
        this.orderDepartment = orderDepartment;
        this.order = order;
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getOrderDepartment() {
        return orderDepartment;
    }

    public void setOrderDepartment(int orderDepartment) {
        this.orderDepartment = orderDepartment;
    }
 
    
    @Override
    public String toString() {
        return "SectionPath{" + "departmentId=" + departmentId + ", id=" + id + ", name=" + name + ", order=" + order + ", jobs=" + jobs + '}';
    }

    public boolean addToDataBase(int idService) throws SQLException, ClassNotFoundException {
   
            DB d = new DB();
            String q = "INSERT INTO steps_section VALUES ("+departmentId+","+id+","+idService+","+orderDepartment+","+order+");";
            System.out.println(q);
            d.write(q);
            
        
       
        return false;
    }

}

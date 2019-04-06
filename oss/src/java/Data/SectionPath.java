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
public class SectionPath implements Serializable {
    int departmentId;
    int orderDepartment;
    int id;
    String name;
    Integer order;
    
    public List<JobPath> jobs = new ArrayList<JobPath>();

    public SectionPath() {
      
    }

    public SectionPath(int departmentId, int id, String name, List<JobPath> jobs) {
        this.departmentId = departmentId;
        this.id = id;
        this.name = name;
        this.jobs = jobs;
    }

    public SectionPath(int departmentId, int orderDepartment, int id, String name, Integer order) {
        this.departmentId = departmentId;
        this.orderDepartment = orderDepartment;
        this.id = id;
        this.name = name;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
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

   
}

package Data;


import java.io.Serializable;
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
public class Service implements Serializable{
    int id;
    String name;
    int days;
    double cost;
    boolean status;
    Department department;
    SectionPath section;
    List<Department> path;

    public Service() {
    }

    public Service(int id, String name, int days, double cost, boolean status, Department department, SectionPath section, List<Department> path) {
        this.id = id;
        this.name = name;
        this.days = days;
        this.cost = cost;
        this.status = status;
        this.department = department;
        this.section = section;
        this.path = path;
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public SectionPath getSection() {
        return section;
    }

    public void setSection(SectionPath section) {
        this.section = section;
    }
    
    
    public List<Department> getPath() {
        return path;
    }

    public void setPath(List<Department> path) {
        this.path = path;
    }
    
    
}

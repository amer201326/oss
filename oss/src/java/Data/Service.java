package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Service implements Serializable {

    int id;
    String name;
    int days;
    double cost;
    String status;
    Department department;
    Section section;
    List<Department> path;

    public Service() {
        this.department = new Department();
        this.section = new Section();
    }

    public Service(int id, String name, int days, double cost, String status, Department department, Section section, List<Department> path) {
        this.id = id;
        this.name = name;
        this.days = days;
        this.cost = cost;
        this.status = status;
        this.department = department;
        this.section = section;
        this.path = path;
    }

    public Service(int id, String name, double cost, int days, String status) {
        this.id = id;
        this.name = name;
        this.days = days;
        this.cost = cost;
        this.status = status;
        this.department = new Department();
        this.section = new Section();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Department> getPath() {
        return path;
    }

    public void setPath(List<Department> path) {
        this.path = path;
    }

    public void update() {
        try {
            String q = "UPDATE services_provided SET Serv_Name = '" + name + "',Serv_Cost = '" + cost + "', Serv_Days = '" + days + "',Serv_Case = '" + status + "' WHERE (Services_Provided_ID = " + id + ");";
            System.out.println("jjjjjjjjjjjjjjjjjjjjq" + q);
            DB data = new DB();
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addServiceToDB() {

        String q = "INSERT INTO services_provided (`Services_Provided_ID`,`Serv_Name`, `Serv_Cost`, `Serv_Days`, `Serv_Case`, `Dep_ID`,`Sec_ID`) VALUES ('"+id+"','" + name + "','" + cost + "','" + days + "','" + status + "','" + department.id + "','" + section.id + "');";
 System.out.println(q);
        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name=" + name + ", days=" + days + ", cost=" + cost + ", status=" + status + ", department=" + department + ", section=" + section + '}';
    }

}

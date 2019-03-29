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
    List<DepartmentPaths> path;
    List<ServiceAttachmentName> attachmentNames;

    public Service() {
        this.department = new Department();
        this.section = new Section();
    }

    public Service(int id, String name, int days, double cost, String status, Department department, Section section) {
        this.id = id;
        this.name = name;
        this.days = days;
        this.cost = cost;
        this.status = status;
        this.department = department;
        this.section = section;
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

    public List<DepartmentPaths> getPath() {
        return path;
    }

    public void setPath(List<DepartmentPaths> path) {
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
        int idMax = GetFromDB.getMaxIdService();
        this.id = idMax + 1;
        
        try {
            DB data = new DB();
            String q = "start transaction;";
            data.write(q);
            
            System.out.println(q);
            q = "INSERT INTO services_provided VALUES ('" + id + "','" + name + "','" + cost + "','" + days + "','" + status + "','" + department.id + "','" + section.id + "');";
            data.write(q);
            System.out.println(q);
            for (int i = 0; i < path.size(); i++) {
                DepartmentPaths get = path.get(i);
                for (int j = 0; j < get.getSections().size(); j++) {
                    SectionPath get1 = get.getSections().get(j);
                    for (int k = 0; k < get1.getJobs().size(); k++) {
                        JobPath get2 = get1.getJobs().get(k);
                        if (get2.getSectionID() == get1.getId()) {
                            q = "INSERT INTO steps_job VALUES(" + department.id + "," + section.id + "," + get2.id + "," + id + "," + get.order + "," + get1.order + "," + get2.order + ");";
                            data.write(q);
                            System.out.println(q);
                        }

                    }

                }
            }
            for (int i = 0; i < attachmentNames.size(); i++) {
                ServiceAttachmentName get = attachmentNames.get(i);
                q = "INSERT INTO have_serviceattachment VALUES (" + id + ", " + get.id + ");";
                data.write(q);
                System.out.println(q);
            }
            q = "commit;";
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            DB data;
            try {
                data = new DB();
                String q = "rollback;";
            data.write(q);
            } catch (SQLException ex1) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<ServiceAttachmentName> getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(List<ServiceAttachmentName> attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public void simpleUpdate() {
        try {
            String q = "UPDATE services_provided SET Serv_Name = '" + name + "',Serv_Cost = '" + cost + "', Serv_Days = '" + days + "',Serv_Case = '" + status + "' WHERE (Services_Provided_ID = " + id + ");";
            System.out.println(q);
            DB data = new DB();
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;

        if (this.id == other.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        return hash;
    }

}

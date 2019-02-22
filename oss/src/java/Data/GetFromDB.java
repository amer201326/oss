/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amer$_$
 */
public class GetFromDB {
    
    
    public static List<Department> getDepartments() {
        Department d = new Department();
        
        List<Department> departments = new ArrayList<Department>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM department ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new Department(r.getInt(1),r.getString(2),r.getString(3));
                departments.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public static List<JobTitel> getJobTittle() {
        JobTitel d = new JobTitel();
        
        List<JobTitel> job = new ArrayList<JobTitel>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM jobtitle ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new JobTitel(r.getInt(1),r.getString(2));
                job.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return job;
    }
    

    public static List<String> getImageDepartment() {
        List<String> image = new ArrayList<String>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM imagedepartment ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                String ss = r.getString(1);
                image.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return image;
    }
    
    public static List<Section> getSection(){
        List<Section> l = new ArrayList<>();
        Section s ;
        try {
            DB db = new DB();
            String sql = "select s.Dep_ID ,s.Sec_ID,s.Sec_Name ,d.Dep_Name from section as s inner join department as d on s.Dep_ID = d.Dep_ID;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Section(r.getInt(1),r.getInt(2),r.getString(3),r.getString(4));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }
    public static List<String> getDepartmentName() {
        List<String> d = new ArrayList<String>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM department ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                String ss = r.getString(2);
                d.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    public static List<Section> getFsection( int id) {
        List<Section> l = new ArrayList<>();
        Section s ;
        try {
            DB db = new DB();
            String sql = "select s.Dep_ID ,s.Sec_ID,s.Sec_Name ,d.Dep_Name from section as s inner join department as d on s.Dep_ID = d.Dep_ID where d.Dep_ID = " +id+"; ";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Section(r.getInt(1),r.getInt(2),r.getString(3),r.getString(4));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    
    
    
    public static List<Employee> getEmployees() {
       List<Employee> l = new ArrayList<>();
        Employee s ;
        try {
            DB db = new DB();
            String sql = "select * from employees ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Employee(r.getInt(1),r.getInt(2),r.getString(3),r.getString(4),r.getString(5));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }
}

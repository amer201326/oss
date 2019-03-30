/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    
    
    
    
    
    public static double getCostOfThisMonth() {

        try {

            DB db = new DB();
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;

            String sql = "select sum(Cost) from decisions_department where Date between '" + year + "-" + month + "-" + "00' and '" + year + "-" + (month + 1) + "-" + "1';";

            ResultSet r = db.read(sql);

            while (r.next()) {

                return r.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    
    
    
    public static int[] getALLNumber(){
        int[] all = new int[10];
        try{
            DB db = new DB();
            String sql = "select count(Service_Citizen_ID) ,(select count(Service_Citizen_ID) from service_citizen where Status = 'done'),(select count(Service_Citizen_ID) from service_citizen where Status = 'view'),(select count(Service_Citizen_ID) from service_citizen where Status = 'notview'),(select count(Dep_ID) from department ),(select count(Sec_ID) from section ),(select count(Services_Provided_ID) from services_provided),(select count(Cit_ID) from citizen ),(select count(Service_Citizen_ID) from service_citizen),(select count(Emp_ID) from employees ) from service_citizen where Status = 'done' ;";
            ResultSet r = db.read(sql);

            while (r.next()) {
                all[0] = r.getInt(1);
                all[1] = r.getInt(2);
                all[2] = r.getInt(3);
                all[3] = r.getInt(4);
                all[4] = r.getInt(5);
                all[5] = r.getInt(6);
                all[6] = r.getInt(7);
                all[7] = r.getInt(8);
                all[8] = r.getInt(9);
                all[9] = r.getInt(10);
                
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }
    

    public static Integer[] getNumberOfServicePerMonth() {

        Integer[] months = new Integer[13];

        try {

            DB db = new DB();
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH) + 1;

            for (int i = 1; i <= currentMonth; i++) {
                String sql = "select count(Services_Provided_ID) from service_citizen where Date  between '" + year + "-" + i + "-" + "00' and '" + year + "-" + (i + 1) + "-" + "1';";
                System.out.println(sql);
                ResultSet r = db.read(sql);
                while (r.next()) {
                    months[i] = r.getInt(1);
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return months;
    }

    
   

    public  static List<ServiceCount> getMore5ServiceRequest() {
        List<ServiceCount> servicesCount = new ArrayList<ServiceCount>();
        int i = 0;
        double sum = 1;
        try {

            DB db = new DB();
             String sql1 = "select sum(count) from service_count;";
             ResultSet r1 = db.read(sql1);
           while (r1.next()) {
                 sum = r1.getInt(1);

            }
            System.out.println("aaaaaaaaa"+sum);
            String sql = "select * from service_count order by count DESC LIMIT 5;";

            ResultSet r = db.read(sql);

            while (r.next()) {
                servicesCount.add(new ServiceCount(r.getString(3), (int)((r.getInt(2)/sum)*100)));
                
                i++;

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicesCount;
     
    }

    public static List<JobTitel> getJobTittle(String id) {
        JobTitel d = new JobTitel();
        
        List<JobTitel> job = new ArrayList<JobTitel>();
        try {
            DB db = new DB();
            
            String sql = "SELECT * FROM jobtitle where Job_ID in( select Job_ID from  job_of_section where Sec_ID ="+id+");";
            System.out.println(sql);
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
    
    public static List<Employee> GetEmployeeForJobID(String id){
        
        
        List<Employee> emp = new ArrayList<Employee>();
        try {
            DB db = new DB();
            
            String sql = "SELECT * FROM oss.employees where Job_ID ="+id+";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                Employee e = new Employee(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13));
                emp.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emp;
    }
    
    
    
    

    public static List<Screen> getScreens() {
        Screen s = new Screen();

        List<Screen> screen = new ArrayList<Screen>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM screen ;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Screen(r.getInt(1), r.getString(2));
                screen.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return screen;
    }

    public static List<JobOfSection> getJobOfSectio() {
        
        JobOfSection d = new JobOfSection();
        
        List<JobOfSection> job = new ArrayList<JobOfSection>();
        try {
            DB db = new DB();
            
            String sql = "SELECT  js.Job_ID ,js.Sec_ID,s.Sec_Name,j.Job_name   FROM job_of_section as js INNER JOIN section as s ON s.Sec_ID = js.Sec_ID INNER JOIN jobtitle as j ON j.Job_ID = js.Job_ID;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new JobOfSection(r.getInt(1), r.getInt(2),r.getString(3),r.getString(4));
                job.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return job;
    }
    public static List<JobOfSection> getJobOfSectio(String id) {
        
        JobOfSection d = new JobOfSection();
        
        List<JobOfSection> job = new ArrayList<JobOfSection>();
        try {
            DB db = new DB();
            
            String sql = "SELECT  js.Job_ID ,js.Sec_ID,s.Sec_Name,j.Job_name   FROM job_of_section as js INNER JOIN section as s ON s.Sec_ID = js.Sec_ID INNER JOIN jobtitle as j ON j.Job_ID = js.Job_ID INNER JOIN department as d ON s.Dep_ID = d.Dep_ID where d.Dep_ID = "+id+";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new JobOfSection(r.getInt(1), r.getInt(2),r.getString(3),r.getString(4));
                job.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return job;
    }
    
    
    
    
     public static List<ServiceAttachmentName> getServiceAttachmentName() {
        
        ServiceAttachmentName d = new ServiceAttachmentName();
        
        List<ServiceAttachmentName> attach = new ArrayList<ServiceAttachmentName>();
        try {
            DB db = new DB();
            
            String sql = "SELECT * FROM oss.serviceattachmentname;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new ServiceAttachmentName(r.getInt(1), r.getString(2),r.getString(3));
                attach.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return attach;
    }

    static int getMaxIdService() {
        int id = 0;
        try {
            DB db = new DB();
            
            String sql = "SELECT Services_Provided_ID FROM services_provided WHERE Services_Provided_ID = ( SELECT MAX(Services_Provided_ID) FROM services_provided ) ;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                id = r.getInt(1);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    public static List<Service> getAllServices() {

        Service s = new Service();

        List<Service> services = new ArrayList<Service>();
        try {
            DB db = new DB();
            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,d.*,s.* from services_provided  inner join department as d on services_provided.DepartmentID = d.Dep_ID inner join section as s on services_provided.sectionID = s.Sec_ID;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2),r.getInt(1),
                        r.getDouble(3),r.getString(4),new Department(r.getInt(5), r.getString(6),
                                r.getString(7)),new Section(r.getInt(8), r.getInt(9), r.getString(10), r.getString(11)));
                
                services.add(s);
            }
            
            
        } catch (Exception e) {
        }
        return services;
    }

    public static int getMaxIDEmployee() {
        int id = 0;
        try {
            DB db = new DB();
            
            String sql = "SELECT Emp_ID FROM employees WHERE Emp_ID = ( SELECT MAX(Emp_ID) FROM employees ) ;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                id = r.getInt(1);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static Department getDepartmentById(String param) {
        Department d = new Department();
        
       
        try {
            DB db = new DB();
            String sql = "SELECT * FROM department where Dep_ID = "+param+";";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new Department(r.getInt(1),r.getString(2),r.getString(3));
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return d;
    }

    public static List<Employee> GetEmployeeinDepartment(String param) {
         List<Employee> emp = new ArrayList<Employee>();
        try {
            DB db = new DB();
            
            String sql = "SELECT * FROM oss.employees where Dep_ID ="+param+";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                Employee e = new Employee(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13));
                emp.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emp;
    }

    public static Employee GetEmployeeById(String param) {
           Employee emp = new Employee();
        try {
            DB db = new DB();
            
            String sql = "SELECT * FROM oss.employees where Emp_ID ="+param+";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                 emp = new Employee(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13));
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emp;
        
    }
}

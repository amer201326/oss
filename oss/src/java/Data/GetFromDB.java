/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                d = new Department(r.getInt(1), r.getString(2), r.getString(3));
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
                d = new JobTitel(r.getInt(1), r.getString(2));
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

    public static List<Section> getSection() {
        List<Section> l = new ArrayList<>();
        Section s;
        try {
            DB db = new DB();
            String sql = "select s.Dep_ID ,s.Sec_ID,s.Sec_Name ,d.Dep_Name from section as s inner join department as d on s.Dep_ID = d.Dep_ID;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Section(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
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

    public static List<Section> getFsection(int id) {
        List<Section> l = new ArrayList<>();
        Section s;
        try {
            DB db = new DB();
            String sql = "select s.Dep_ID ,s.Sec_ID,s.Sec_Name ,d.Dep_Name from section as s inner join department as d on s.Dep_ID = d.Dep_ID where d.Dep_ID = " + id + "; ";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Section(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
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

    public static int[] getALLNumber() {
        int[] all = new int[10];
        try {
            DB db = new DB();
            String sql = "select count(Service_Citizen_ID) ,(select count(Service_Citizen_ID) from oss.service_citizen where Status = 'done'),(select count(Service_Citizen_ID) from oss.service_citizen where Status = 'view'),(select count(Service_Citizen_ID) from oss.service_citizen where Status = 'notview'),(select count(Dep_ID) from oss.department ),(select count(Sec_ID) from oss.section ),(select count(Services_Provided_ID) from oss.services_provided),(select count(Cit_ID) from oss.citizen ),(select count(Service_Citizen_ID) from oss.service_citizen),(select count(Emp_ID) from oss.employees ) from oss.service_citizen where Status = 'done' ;";
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

    public static List<ServiceCount> getMore5ServiceRequest() {
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
            System.out.println("aaaaaaaaa" + sum);
            String sql = "select * from service_count order by count DESC LIMIT 5;";

            ResultSet r = db.read(sql);

            while (r.next()) {
                servicesCount.add(new ServiceCount(r.getString(3), (int) ((r.getInt(2) / sum) * 100)));

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

            String sql = "SELECT * FROM jobtitle where Job_ID in( select Job_ID from  job_of_section where Sec_ID =" + id + ");";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new JobTitel(r.getInt(1), r.getString(2));
                job.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return job;

    }

    public static List<Employee> GetEmployeeForJobID(String id) {

        List<Employee> emp = new ArrayList<Employee>();
        try {
            DB db = new DB();

            String sql = "SELECT * FROM oss.employees where Job_ID =" + id + ";";
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
                d = new JobOfSection(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
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

            String sql = "SELECT  js.Job_ID ,js.Sec_ID,s.Sec_Name,j.Job_name   FROM job_of_section as js INNER JOIN section as s ON s.Sec_ID = js.Sec_ID INNER JOIN jobtitle as j ON j.Job_ID = js.Job_ID INNER JOIN department as d ON s.Dep_ID = d.Dep_ID where d.Dep_ID = " + id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new JobOfSection(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
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

                d = new ServiceAttachmentName(r.getInt(1), r.getString(2), r.getString(4), r.getBinaryStream(3), r.getString(5), r.getString(6), "");

                attach.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return attach;
    }

    public static int getMaxIdService() {
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
            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,d.*,s.*,note from services_provided  inner join department as d on services_provided.DepartmentID = d.Dep_ID inner join section as s on services_provided.sectionID = s.Sec_ID;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5),
                        new Department(r.getInt(6), r.getString(7), r.getString(8)),
                        new Section(r.getInt(9), r.getInt(10), r.getString(11), r.getString(7)), r.getString(12));

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
            String sql = "SELECT * FROM department where Dep_ID = " + param + ";";

            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new Department(r.getInt(1), r.getString(2), r.getString(3));

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

            String sql = "SELECT * FROM oss.employees where Dep_ID =" + param + ";";
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

            String sql = "SELECT * FROM oss.employees where Emp_ID =" + param + ";";
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

    public static List<Integer> getAttavhmentForserviceById(String id) {
        List<Integer> name = new ArrayList<>();
        try {
            DB db = new DB();

            String sql = "SELECT * FROM have_serviceattachment where Services_Provided_ID =" + id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                name.add(r.getInt(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    public static List<JobPath> getPahtForService(int id) {
        List<JobPath> path = new ArrayList<>();

        try {
            DB db = new DB();

            String sql = "SELECT sj.*,j.Job_name  FROM oss.steps_job as sj inner join jobtitle as j on sj.Job_ID = j.Job_ID where Services_Provided_ID = " + id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                path.add(new JobPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(8), r.getInt(5), r.getInt(6), r.getInt(7)));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return path;
    }

    public static Service getServiceByID(String id) {
        Service s = new Service();

        try {
            DB db = new DB();
            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,d.*,s.*,note from services_provided  inner join department as d on services_provided.DepartmentID = d.Dep_ID inner join section as s on services_provided.sectionID = s.Sec_ID where Services_Provided_ID = " + id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5),
                        new Department(r.getInt(6), r.getString(7), r.getString(8)),
                        new Section(r.getInt(9), r.getInt(10), r.getString(11), r.getString(7)), r.getString(12));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public static List<Department> getDepartmentsWithNService() {
        Department d = new Department();

        List<Department> departments = new ArrayList<Department>();
        try {
            DB db = new DB();
            String sql = "select d.* , (select count(s.Services_Provided_ID)  from services_provided as s where s.DepartmentID =d.Dep_ID) from department as d;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new Department(r.getInt(1), r.getString(2), r.getString(3));
                d.numberService = r.getInt(4);
                departments.add(d);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public static List<Service> geServicesInDepartment(int idDep) {

        Service s = new Service();

        List<Service> services = new ArrayList<Service>();
        try {
            DB db = new DB();

            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,note  from services_provided where DepartmentID = " + idDep + " ;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5), r.getString(6));

                services.add(s);
            }

        } catch (Exception e) {
        }
        return services;
    }

    public static Service getServiceByID2(String id) {
        Service s = new Service();

        try {
            DB db = new DB();
            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,d.*,s.*,note from services_provided  inner join department as d on services_provided.DepartmentID = d.Dep_ID inner join section as s on services_provided.sectionID = s.Sec_ID where Services_Provided_ID=" + id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5),
                        new Department(r.getInt(6), r.getString(7), r.getString(8)),
                        new Section(r.getInt(9), r.getInt(10), r.getString(11), r.getString(7)), r.getString(12));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public static List<ServiceAttachmentName> getAttavhmentByserviceById(int id) {
        List<ServiceAttachmentName> name = new ArrayList<>();
        try {
            DB db = new DB();
            ServiceAttachmentName a;
            String sql = "select * from serviceattachmentname where ServiceAttachmentName_ID in (select ServiceAttachmentName_ID from have_serviceattachment where Services_Provided_ID = " + id + ");";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                a = new ServiceAttachmentName(r.getInt(1), r.getString(2), r.getString(4), r.getBinaryStream(3), r.getString(5), r.getString(6), "");
                System.out.println(a + "   " + r.getString(6));
                name.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
//    public static Citizen getCitizenById(String id) {
//        Citizen cit = new Citizen();
//        
//        
//        try {
//            DB db = new DB();
//            String sql = "select c.* from citizen as c inner join citizenaccount"
//                    + " as a on c.Cit_ID = a.Cit_ID where ;";
//
//            ResultSet r = db.read(sql);
//            while (r.next()) {
//                cit = new Citizen(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), 
//                        r.getString(6), r.getString(7), r.getString(8), r.getString(9));
//                c.add(cit);
//            }
//        } catch (Exception e) {
//            System.out.println("bb"+e.getMessage());
//        }
//        return c;
//    }

    static int getLastIdOfAttatchment() {
        int id = 0;
        try {
            DB db = new DB();

            String sql = "SELECT MAX(ServiceAttachmentName_ID) FROM serviceattachmentname  ;";
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

    public static List<DepartmentPaths> getDepartmentsPath(int idService) {
        DepartmentPaths d = new DepartmentPaths();

        List<DepartmentPaths> departments = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM oss.steps_department as s inner join department as d on s.Dep_ID = d.Dep_ID  where Services_Provided_ID = " + idService + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new DepartmentPaths(r.getInt(1), r.getString(6), r.getString(7), r.getInt(2), r.getInt(3), r.getString(4));
                departments.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public static List<SectionPath> getSectionPath(int idService) {
        SectionPath s = new SectionPath();

        List<SectionPath> sections = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM steps_section as ss inner join section as s on s.Sec_ID = ss.Sec_ID where Services_Provided_ID =  " + idService + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new SectionPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getString(8), r.getInt(4), r.getInt(5));
                sections.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("size is " + sections.size());
        return sections;
    }
    public static String k = "foreanderDowntop";

    public static Manager getManagerAccount(String username, String passWord) {
        Manager m;
        passWord = Crypto.encPas(k, passWord);
        String q = "SELECT * FROM manager where username = '" + username + "' and password = '" + passWord + "';";
        try {
            DB db = new DB();

            System.out.println(q);
            ResultSet r = db.read(q);
            while (r.next()) {
                m = new Manager(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getString(7),
                        r.getString(8), r.getString(9), r.getString(10), r.getString(11));
                return m;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Citizen getCitizenAccount(String username, String passWord) {

        Citizen c;
        passWord = Crypto.encPas(k, passWord);
        String q = "SELECT * FROM citizen as c inner join citizenaccount as ca on c.Cit_ID = ca.Cit_ID where ca.Username =  '" + username + "' and ca.Password = '" + passWord + "';";
        try {
            DB db = new DB();

            System.out.println(q);
            ResultSet r = db.read(q);
            while (r.next()) {
                c = new Citizen(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getInt(7), r.getString(8),
                        r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13),
                        r.getString(14), r.getString(15), r.getString(16), r.getString(17), r.getString(18), r.getString(19),
                        r.getString(20), r.getString(21));
                c.setAccount(new CitizenAccount(r.getInt(22), r.getString(23), r.getString(24)));
                return c;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    public static List<AttachmentArchiveCitizen> getAttachmantsArchive(int CitID) {

        AttachmentArchiveCitizen s;

        List<AttachmentArchiveCitizen> at = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT ac.*,an.ServA_Name FROM attachment_archive_citizen as ac inner join serviceattachmentname as an on ac.ServiceAttachmentName_ID = an.ServiceAttachmentName_ID where Cit_ID =   " + CitID + " order by ServiceAttachmentName_ID ;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new AttachmentArchiveCitizen(r.getInt(1), r.getInt(2), r.getInt(3), r.getBinaryStream(4), r.getString(5), r.getString(6));

                at.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return at;
    }

    public static List<AttachmentArchiveCitizen> getAttachmantsArchiveJustFile(int CitID) {

        AttachmentArchiveCitizen s;

        List<AttachmentArchiveCitizen> at = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT ac.*,an.ServA_Name FROM attachment_archive_citizen as ac inner join serviceattachmentname as an on ac.ServiceAttachmentName_ID = an.ServiceAttachmentName_ID where Cit_ID =   " + CitID + " order by ServiceAttachmentName_ID ;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new AttachmentArchiveCitizen(r.getInt(1), r.getInt(2), r.getInt(3), r.getBinaryStream(4), r.getString(5), r.getString(6));
                if (s.fileDownload != null) {
                    at.add(s);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("size is " + at.size());
        return at;
    }

    public static Employee getEmployeeAccount(String username, String passWord) {
        Employee em;
        passWord = Crypto.encPas(k, passWord);
        String q = "SELECT * FROM employees as e inner join employeeaccount as ea on e.Emp_ID= ea.Emp_ID where ea.UserName =  '" + username + "' and ea.Password = '" + passWord + "';";
        try {
            DB db = new DB();

            System.out.println(q);
            ResultSet r = db.read(q);
            while (r.next()) {
                em = new Employee(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13));
                em.account = new EmployeeAccount(r.getString(15), r.getString(16));
                return em;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static List<DecisionsDepartment> getDecisionsDepartment(Employee employee) {
        List<DecisionsDepartment> dds = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM oss.decisions_department  where Dep_ID = " + employee.getDep_id() + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                dds.add(new DecisionsDepartment(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getInt(5), r.getString(6), r.getDouble(7), r.getString(8), r.getString(9), r.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dds;
    }

    public static List<DecisionSection> getDecisionsSection(Employee employee, DecisionsDepartment d) {
        List<DecisionSection> dss = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM oss.dicisions_section  where Sec_ID = " + employee.sec_id + " and Dep_ID = "
                    + d.depId + " and Order_Departmant = " + d.depOrder + " and Cit_ID = " + d.Cit_ID + " and Service_Citizen_ID = " + d.Service_Citizen_ID + "; ";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                dss.add(new DecisionSection(new SectionPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getInt(5)), r.getInt(6), r.getInt(8), r.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dss;
    }

    public static List<DecisionsJob> getDecisionsJob(Employee employee, DecisionSection s) {
        List<DecisionsJob> djs = new ArrayList<>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM oss.decisions_job where Job_ID = " + employee.job_id + " and Dep_ID = " + s.section.departmentId + " and  Sec_ID = " + s.section.id + " and Order_Departmant = "
                    + s.section.orderDepartment + " and Order_Section = " + s.section.order + " and Cit_ID = " + s.Cit_ID + " and Service_Citizen_ID = " + s.Service_Citizen_ID + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                djs.add(new DecisionsJob(new JobPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), null, r.getInt(5), r.getInt(6), r.getInt(7)), r.getInt(10),
                        r.getString(11), r.getString(12), r.getDouble(13), r.getString(14), r.getString(15), r.getString(16)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return djs;
    }

    static List<Section> getSection(int depId) {
        List<Section> l = new ArrayList<>();
        Section s;
        try {
            DB db = new DB();
            String sql = "select s.Dep_ID ,s.Sec_ID,s.Sec_Name ,d.Dep_Name from section as s inner join department as d on s.Dep_ID = d.Dep_ID where s.Dep_ID = " + depId + ";";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Section(r.getInt(1), r.getInt(2), r.getString(3), r.getString(4));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    public static List<ServiceCitizen_1> getAllRequestService(Employee emp) {
        ServiceCitizen_1 cit = new ServiceCitizen_1();

        List<ServiceCitizen_1> c = new ArrayList<ServiceCitizen_1>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM services_provided as sp inner join service_citizen as sc on sc.Services_Provided_ID = sp.Services_Provided_ID "
                    + " inner join citizen as cit on sc.Cit_ID = cit.Cit_ID inner join service_jobs as sj  on sc.Service_Citizen_ID = sj.Service_Citizen_ID and  sc.Cit_ID = sj.Cit_ID  "
                    + "  where sj.Job_ID = " + emp.job_id + " and  sj.Sec_ID = " + emp.sec_id + " and sj.Dep_ID = " + emp.dep_id + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                Service s = new Service(r.getInt(1), r.getString(2), r.getDouble(3), r.getInt(4), r.getString(5), r.getString(8));
                Citizen c1 = new Citizen(r.getInt(15), r.getString(16), r.getString(17), r.getString(18), r.getString(19), r.getString(20), r.getInt(21),
                        r.getString(22), r.getString(23), r.getString(24), r.getString(25), r.getString(26), r.getString(27), r.getString(28), r.getString(29), r.getString(30),
                        r.getString(31), r.getString(32), r.getString(33), r.getString(34), r.getString(35));
                Service_Job service_Job = new Service_Job(r.getInt(36), r.getInt(37), r.getInt(38), r.getInt(39), r.getInt(40), r.getInt(41), r.getInt(42), r.getInt(43), r.getInt(44), r.getString(45));
                cit = new ServiceCitizen_1(s, r.getInt(9), r.getInt(10), r.getInt(11), r.getString(12), r.getString(13), r.getString(14), c1, service_Job);
                c.add(cit);
            }
        } catch (Exception e) {
            System.out.println("cccccc" + e.getMessage());
        }
        return c;
    }

    public static List<ServiceAttachmentName> getAttachmentByserviceCitizen(int idServise, int idCitizen) {
        List<ServiceAttachmentName> name = new ArrayList<>();
        try {
            DB db = new DB();
            ServiceAttachmentName a;

            String sql = "SELECT * FROM attachment_service_citizen as ac inner join attachment_archive_citizen as aac"
                    + " on ac.Atta_ArchiveC_ID = aac.Atta_ArchiveC_ID inner join serviceattachmentname as sa "
                    + "on aac.ServiceAttachmentName_ID = sa.ServiceAttachmentName_ID where  Services_Provided_ID ="+idServise+" and ac.Cit_ID ="+idCitizen+";";

            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                a = new ServiceAttachmentName(r.getInt(7), r.getString(12), r.getString(14), r.getBinaryStream(8), r.getString(16), "", r.getString(10));
                System.out.println(a + "   " + r.getString(6));
                name.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetFromDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

}

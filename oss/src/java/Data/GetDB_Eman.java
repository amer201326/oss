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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amer$_$
 */
public class GetDB_Eman {

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

    public static List<Employee> getEmployee() {
        Employee em = new Employee();

        List<Employee> emp = new ArrayList<Employee>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM employees ;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                em = new Employee(r.getInt(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getString(5), r.getString(6), r.getString(7), r.getString(8), r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13));
                emp.add(em);
            }
        } catch (Exception e) {
            System.out.println("bb" + e.getMessage());
        }
        return emp;
    }

    public static List<Employee> getTableEmployee() {
        Employee em = new Employee();

        List<Employee> emp = new ArrayList<Employee>();
        try {
            DB db = new DB();
            String sql = "select Emp_Id_Card, Emp_ID, Emp_Name, Emp_Mobile, Emp_Email from employees;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                em = new Employee(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5));
                emp.add(em);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return emp;
    }

    public static List<Employee> getFEmployee(int id) {
        List<Employee> l = new ArrayList<>();
        Employee s;
        try {
            DB db = new DB();
            String sql = "select Emp_ID_Card, Emp_ID, Emp_Name, Emp_Mobile, Emp_Email from employees where Dep_ID =" + id + "; ";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Employee(r.getString(1), r.getInt(2), r.getString(3), r.getString(4), r.getString(5));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

//     public static List<ServiceAttachmentName> getAllAttachment() {
//        ServiceAttachmentName serviceAttch = new ServiceAttachmentName();
//        
//        List<ServiceAttachmentName> serviceAttachment = new ArrayList<ServiceAttachmentName>();
//        try {
//            DB db = new DB();
//            String sql = "SELECT * FROM serviceattachmentname ;";
//
//            ResultSet r = db.read(sql);
//            while (r.next()) {
//                serviceAttch = new ServiceAttachmentName(r.getInt(1),r.getString(2),r.getString(4),r.getString(5),r.getBlob(3));
//                serviceAttachment.add(serviceAttch);
//            }
//        } catch (Exception e) {
//            System.out.println("bb"+e.getMessage());
//        }
//        return serviceAttachment;
//    }
    static int getMaxIdCitizen() {
        int id = 0;
        try {
            DB db = new DB();

            String sql = "SELECT Cit_ID FROM citizen WHERE Cit_ID = ( SELECT MAX(Cit_ID) FROM citizen ) ;";
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

    public static List<Citizen> getallCitizen() {
        Citizen cit = new Citizen();

        List<Citizen> c = new ArrayList<Citizen>();
        try {
            DB db = new DB();
            String sql = "select c.Cit_ID, c.Cit_FirstName, c.Cit_FatherName, c.Cit_GrandfatherName, c.Cit_LastName, "
                    + "c.Cit_ID_Card, c.Cit_Mobile, c.Cit_Email, c.Cit_Region from citizen as c inner join citizenaccount"
                    + " as a on c.Cit_ID = a.Cit_ID;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                cit = new Citizen(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),
                        r.getString(6), r.getString(7), r.getString(8), r.getString(9));
                c.add(cit);
            }
        } catch (Exception e) {
            System.out.println("bb" + e.getMessage());
        }
        return c;
    }

    public static Citizen GetCitizenById(String param) {
        Citizen cit = new Citizen();
        try {
            DB db = new DB();

            String sql = "SELECT c.*,ca.* FROM oss.citizen as c inner join  citizenaccount as ca on c.Cit_ID=ca.Cit_ID where c.Cit_ID =" + param + ";";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                cit = new Citizen(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5), r.getString(6), r.getInt(7), r.getString(8),
                        r.getString(9), r.getString(10), r.getString(11), r.getString(12), r.getString(13),
                        r.getString(14), r.getString(15), r.getString(16), r.getString(17), r.getString(18), r.getString(19),
                        r.getString(20), r.getString(21));
                cit.setAccount(new CitizenAccount(r.getInt(22), r.getString(23), r.getString(24)));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cit;

    }

    public static String[] getHomePageDetails() {

        String[] all = new String[10];
        try {
            DB db = new DB();
            String sql = "SELECT homepage_ID, images, address, telephone, fax, email, description, munName, munCity FROM oss.homepage_data;";
            ResultSet r = db.read(sql);

            while (r.next()) {
                all[0] = r.getString(1);
                all[1] = r.getString(2);
                all[2] = r.getString(3);
                all[3] = r.getString(4);
                all[4] = r.getString(5);
                all[5] = r.getString(6);
                all[6] = r.getString(7);
                all[7] = r.getString(8);
                all[8] = r.getString(9);

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetDB_Eman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return all;

    }

    public static int[] getAllParameters() {

        int[] all = new int[4];
        try {
            DB db = new DB();
            String sql = "SELECT count(*),(select count(*) from oss.citizenaccount), (select count(*) from oss.employeeaccount), ((select count(Service_Citizen_ID) from oss.service_citizen where Status = 'done')) FROM oss.services_provided;";
            ResultSet r = db.read(sql);

            while (r.next()) {
                all[0] = r.getInt(1);
                all[1] = r.getInt(2);
                all[2] = r.getInt(3);
                all[3] = r.getInt(4);
System.out.println(all[2]);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetDB_Eman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return all;

    }

    public static List<HomePage> getServicesByDep() {

        HomePage cit = new HomePage();

        List<HomePage> c = new ArrayList<HomePage>();
        try {
            DB db = new DB();
            String sql = "select d.Dep_Name, s.Serv_Name from oss.department as d inner join oss.services_provided as s where d.Dep_ID = s.DepartmentID; ";

            ResultSet r = db.read(sql);
            while (r.next()) {
                cit = new HomePage(r.getString(1), r.getString(2));
                c.add(cit);
            }
        } catch (Exception e) {
            System.out.println("bb" + e.getMessage());
        }
        return c;

    }

}

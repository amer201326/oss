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
            System.out.println("bb"+e.getMessage());
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
        Employee s ;
        try {
            DB db = new DB();
            String sql = "select Emp_ID_Card, Emp_ID, Emp_Name, Emp_Mobile, Emp_Email from employees where Dep_ID =" + id +"; ";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Employee(r.getString(1),r.getInt(2),r.getString(3),r.getString(4), r.getString(5));
                l.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

}

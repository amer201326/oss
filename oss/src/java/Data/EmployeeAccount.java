/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class EmployeeAccount implements Serializable{

    public int Emp_ID;
    String UserName;
    String Password;

    public EmployeeAccount(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public EmployeeAccount() {
    }

    
    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

        public void addEmpAccountToDB() {
        String q = "INSERT INTO employeeaccount (Emp_ID, UserName, Password) VALUES (" + Emp_ID + ",'" + UserName + "','" + Password + "');";
        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
        
        public String updateEmp(){
        String q  =" UPDATE employeeaccount SET Password = '" + Password + "' WHERE `Emp_ID` = " + Emp_ID + ";" ;
        try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (Exception ex) {
            
            Logger.getLogger(CitizenAccount.class.getName()).log(Level.SEVERE, null, ex);
            return "اسم المستخدم موجود , ادخل اسم اخر";
        }
        return "1";
        
    }
        
        
}

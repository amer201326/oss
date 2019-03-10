/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class EmployeeScreen {
    int Emp_ID;
    int Screen_ID;

    
    public int getEmp_ID() {
        return Emp_ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public int getScreen_ID() {
        return Screen_ID;
    }

    public void setScreen_ID(int Screen_ID) {
        this.Screen_ID = Screen_ID;
    }
    
     public void addEmpScreenToDB() {
        String q = "INSERT INTO employeescreen (Emp_ID, Screen_ID) VALUES ("+Emp_ID+"," +Screen_ID+");";

        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EmployeeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

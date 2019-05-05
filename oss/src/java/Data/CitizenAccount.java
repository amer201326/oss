/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import static Data.GetFromDB.k;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class CitizenAccount implements Serializable{
    int Cit_ID;
    String userName;
    String password;

    public CitizenAccount() {
    }
  
    public CitizenAccount(int Cit_ID, String userName, String password) {
        this.Cit_ID = Cit_ID;
        this.userName = userName;
        this.password = password;
    }

    
    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public void addCitizenAccountToDB() {
        password = Crypto.encPas(k, password);
        String q = "INSERT INTO oss.citizenaccount VALUES (" + Cit_ID + ",'" + userName + "','" + password + "');";
        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("errorrrrrrrrrrrrrrrrrrr");
            Logger.getLogger(CitizenAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void addCitizenAccountReuqestToDB() throws SQLException, ClassNotFoundException {
         password = Crypto.encPas(k, password);
          String q = "INSERT INTO oss.citizenaccount VALUES (" + Cit_ID + ",'" + userName + "','" + password + "');";
    
            DB data = new DB();
            System.out.println(q);
            data.write(q);

      

    }
    
     public String updateCit(){
          password = Crypto.encPas(k, password);
        String q  =" UPDATE citizenaccount SET Password = '" + password + "' WHERE `Cit_ID` = " + Cit_ID + ";" ;
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

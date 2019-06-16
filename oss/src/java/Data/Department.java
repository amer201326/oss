/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amer$_$
 */
public class Department implements Serializable {

    public int id;
    public String nameA;
    public String image;
     public int numberService;
     String managerName;

    public Department() {
        
        
    }

    public Department(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Department(String nameA) {
        this.nameA = nameA;
    }

//    public Department(int id, String nameA) {
//        this.id = id;
//        this.nameA = nameA;
//    }

    public Department(int id, String nameA, String image) {
        this.id = id;
        this.nameA = nameA;
        this.image = image;
    }

    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   
   

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

 

    public boolean addToDataBase() {
        System.out.println(nameA);
        DB db;
        
        try {
            db = new DB();
            //db.write(q);
            String q = "INSERT INTO department(Dep_ID,Dep_Name,image)VALUES(null,'" + nameA +"','"+image+"');";
            System.out.println(q);
            try {
                db.write(q);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", nameA=" + nameA + ", image=" + image + ", numberService=" + numberService + '}';
    }

    

    public int getNumberService() {
        return numberService;
    }

    public void setNumberService(int numberService) {
        this.numberService = numberService;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    
}

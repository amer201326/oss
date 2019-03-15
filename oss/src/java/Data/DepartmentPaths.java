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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class DepartmentPaths implements Serializable {

    public int id;
    public String nameA;
    public String image;
    public Integer order;
    public List<SectionPath> sections;

    public DepartmentPaths() {
        sections = new ArrayList<SectionPath>();
        
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public DepartmentPaths(String nameA) {
        this.nameA = nameA;
    }


    public DepartmentPaths(int id, String nameA, String image) {
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

  
    public void getNameFromDataBase() {
      
        try {
            DB db = new DB();
         String sql = "SELECT Dep_Name FROM department where Dep_ID = "+this.id+";";

            ResultSet r = db.read(sql);
            while (r.next()) {
               this.nameA = r.getString(1);
              
            }
        } catch (Exception e) {
            
        }
        
       
        
    }

    public List<SectionPath> getSections() {
        return sections;
    }

    public void setSections(List<SectionPath> sections) {
        this.sections = sections;
    }

    

    

    
}

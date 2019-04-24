package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author me
 */
public class Section implements Serializable{
    String departmentId;
    String id;
    String name;
    String departmentNmae;
    

    public Section() {
    }

    public Section(String id) {
        this.id = id;
    }

    public Section(int departmentId, int id, String name, String departmentNmae) {
        this.departmentId = departmentId+"";
        this.id = id+"";
        this.name = name;
        this.departmentNmae = departmentNmae;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getDepartmentNmae() {
        return departmentNmae;
    }

    public void setDepartmentNmae(String departmentNmae) {
        this.departmentNmae = departmentNmae;
    }
    
    public void update(){
        String q  =" UPDATE section SET Dep_ID = "+departmentId+",Sec_ID = "+id+",Sec_Name = '"+name+"' WHERE `Sec_ID` = "+id+";";
        try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addToDB(){
        String  q = "INSERT INTO section (Dep_ID, Sec_ID, Sec_Name) VALUES("+departmentId+" ,null, '"+name+"');";
         try {
            DB data = new DB();
            data.write(q);
             System.out.println(q);
            
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void deleteFromDB(){
        
        String  q = "DELETE FROM section WHERE (Sec_ID = "+id+")";
        System.out.println(q);
         try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public String toString() {
        return "Section{" + "departmentId=" + departmentId + ", id=" + id + ", name=" + name + ", departmentNmae=" + departmentNmae + '}';
    }
    
    
   
    
}

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class ServiceAttachmentName implements Serializable{
    int id;
    String name;
    String srcFile;

    public ServiceAttachmentName() {
    }

    public ServiceAttachmentName(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public ServiceAttachmentName(int id, String name, String srcFile) {
        this.id = id;
        this.name = name;
        this.srcFile = srcFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }
    
    public void update(){
        String  q = "UPDATE serviceattachmentname SET ServA_Name = '"+ name +"' WHERE (ServiceAttachmentName_ID = " + id + ");";
        System.out.println(q);
         try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public boolean delete() {
        
        String  q = "DELETE FROM serviceattachmentname WHERE (ServiceAttachmentName_ID = "+id+");";
        System.out.println(q);
         try {
            DB data = new DB();
            data.write(q);
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return false;
    }
    
    public void addJobToDB(){
        String  q = "INSERT INTO serviceattachmentname VALUES(null,'" + name + "');";
         try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceAttachmentName.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

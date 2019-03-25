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
 * @author Amer$_$
 */
public class JobOfSection implements Serializable{
      int idJob;
      int idSEction;
      String SEctionName;
      String name;
      

    public JobOfSection() {
    }

    public JobOfSection(int idJob, int idSEction) {
        this.idJob = idJob;
        this.idSEction = idSEction;
    }

    public JobOfSection(int idJob, int idSEction, String SEctionName, String name) {
        this.idJob = idJob;
        this.idSEction = idSEction;
        this.SEctionName = SEctionName;
        this.name = name;
    }

    

    
    

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public int getIdSEction() {
        return idSEction;
    }

    public void setIdSEction(int idSEction) {
        this.idSEction = idSEction;
    }
      
    public boolean delete() {
        
        String  q = "DELETE FROM job_of_section WHERE Job_ID = "+idJob+" and Sec_ID = "+idSEction+";";
        System.out.println(q);
         try {
            DB data = new DB();
            data.write(q);
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return false;
    }

    public boolean addToDB() {
        String  q = "INSERT INTO job_of_section VALUES ("+idJob+","+idSEction+");";
        System.out.println(q);
         try {
            DB data = new DB();
            data.write(q);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
              return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
              return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSEctionName() {
        return SEctionName;
    }

    public void setSEctionName(String SEctionName) {
        this.SEctionName = SEctionName;
    }
    
    
}

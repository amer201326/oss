package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
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
public class JobTitel implements Serializable {

    String id;
    String name;
    

    public JobTitel() {
    }

    public JobTitel(int id, String name) {
        this.id = id + "";
        this.name = name;
    }

    
    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id + "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public void addJobToDB() {
        String q = "INSERT INTO jobtitle VALUES(null,'" + name + "');";
        try {
            DB data = new DB();
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() {
        String q = "UPDATE jobtitle SET Job_name = '" + name + "' WHERE (Job_ID = " + id + ");";
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

    public boolean delete() {

        String q = "DELETE FROM jobtitle WHERE (Job_ID = " + id + ");";
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

    

}

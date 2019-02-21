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
public class JobTitel implements Serializable{
    int id;
    String name;

    public JobTitel() {
    }

    public JobTitel(int id, String name) {
        this.id = id;
        this.name = name;
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
    
    public void addJobToDB(){
        String  q = "INSERT INTO jobtitle VALUES(" + id + ",'" + name + "');";
         try {
            DB data = new DB();
            data.write(q);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}

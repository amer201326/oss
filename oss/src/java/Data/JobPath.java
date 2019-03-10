/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;

/**
 *
 * @author me
 */
public class JobPath {
   int id;
   String name;
   int order;

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
   
   public void getNameFromDataBase() {
      
        try {
            DB db = new DB();
         String sql = "SELECT Job_name FROM jobtitle where Job_ID = "+this.id+";";

            ResultSet r = db.read(sql);
            while (r.next()) {
               this.name = r.getString(1);
              
            }
        } catch (Exception e) {
            
        }
        
       
        
    }
}

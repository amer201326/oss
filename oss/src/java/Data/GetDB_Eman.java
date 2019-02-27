/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amer$_$
 */
public class GetDB_Eman {
    
     public static List<Screen> getScreens() {
        Screen s = new Screen();
        
        List<Screen> screen = new ArrayList<Screen>();
        try {
            DB db = new DB();
            String sql = "SELECT * FROM screen ;";
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Screen(r.getInt(1),r.getString(2));
                screen.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return screen;
    }

    
}

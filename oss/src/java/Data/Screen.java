/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;

/**
 *
 * @author Amer$_$
 */

public class Screen {
    int screenId;
    String screenName;

    
    public Screen(int screenId, String screenName) {
        this.screenId = screenId;
        this.screenName = screenName;
    }

    public Screen() {
    }
    
    
    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    void addToDB(String userName) throws SQLException, ClassNotFoundException {
         DB data = new DB();
         String q = "INSERT INTO `oss`.`employeescreen` (`UserName`, `Screen_ID`) VALUES ( '"+userName+"' , "+screenId+");";
         System.out.println(q);
         data.write(q);
    }

}

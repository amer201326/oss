/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class NotificationUser implements Serializable {

    int Service_Citizen_ID;
    int Cit_ID;
    String type ;
    String action ;
    String from ;
    String date ;
    String show ;

    public NotificationUser(int Service_Citizen_ID, int Cit_ID, String type, String action, String from, String date, String show) {
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Cit_ID = Cit_ID;
        this.type = type;
        this.action = action;
        this.from = from;
        this.date = date;
        this.show = show;
    }
    

    public int getService_Citizen_ID() {
        return Service_Citizen_ID;
    }

    public void setService_Citizen_ID(int Service_Citizen_ID) {
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
    
    public String convertAction(){
        if(action.equals("accept"))
            return "تم قبول الخدمة بواسطة";
        else
            return "تم رفض الخدمة بواسطة";
    }

    public void updateShow() {
        try {
            String q = "UPDATE `oss`.`notificationuser` SET `show` = 'no' WHERE (`Service_Citizen_ID` = '"+Service_Citizen_ID+"') and (`Cit_ID` = '"+Cit_ID+"');";
            DB db = new DB();
            System.out.println(q);
            db.write(q);
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificationUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NotificationUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

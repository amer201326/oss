/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baraakali
 */
public class Service_Job {

    int Service_Citizen_ID;
    int Services_Provided_ID;
    int Cit_ID;
    int Dep_ID;
    int Sec_ID;
    int Job_ID;
    int Order_Departmant;
    int Order_Section;
    int Order_Job;
    String status;
    String show;

    public Service_Job() {
    }

    public Service_Job(int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID, int Dep_ID, int Sec_ID, int Job_ID, int Order_Departmant, int Order_Section, int Order_Job, String status,String show) {
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.Dep_ID = Dep_ID;
        this.Sec_ID = Sec_ID;
        this.Job_ID = Job_ID;
        this.Order_Departmant = Order_Departmant;
        this.Order_Section = Order_Section;
        this.Order_Job = Order_Job;
        this.status = status;
        this.show = show;
    }

    public int getService_Citizen_ID() {
        return Service_Citizen_ID;
    }

    public void setService_Citizen_ID(int Service_Citizen_ID) {
        this.Service_Citizen_ID = Service_Citizen_ID;
    }

    public int getServices_Provided_ID() {
        return Services_Provided_ID;
    }

    public void setServices_Provided_ID(int Services_Provided_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public int getDep_ID() {
        return Dep_ID;
    }

    public void setDep_ID(int Dep_ID) {
        this.Dep_ID = Dep_ID;
    }

    public int getSec_ID() {
        return Sec_ID;
    }

    public void setSec_ID(int Sec_ID) {
        this.Sec_ID = Sec_ID;
    }

    public int getJob_ID() {
        return Job_ID;
    }

    public void setJob_ID(int Job_ID) {
        this.Job_ID = Job_ID;
    }

    public int getOrder_Departmant() {
        return Order_Departmant;
    }

    public void setOrder_Departmant(int Order_Departmant) {
        this.Order_Departmant = Order_Departmant;
    }

    public int getOrder_Section() {
        return Order_Section;
    }

    public void setOrder_Section(int Order_Section) {
        this.Order_Section = Order_Section;
    }

    public int getOrder_Job() {
        return Order_Job;
    }

    public void setOrder_Job(int Order_Job) {
        this.Order_Job = Order_Job;
    }

    @Override
    public String toString() {
        return "Service_Job{" + "Service_Citizen_ID=" + Service_Citizen_ID + ", Services_Provided_ID=" + Services_Provided_ID + ", Cit_ID=" + Cit_ID + ", Dep_ID=" + Dep_ID + ", Sec_ID=" + Sec_ID + ", Job_ID=" + Job_ID + ", Order_Departmant=" + Order_Departmant + ", Order_Section=" + Order_Section + ", Order_Job=" + Order_Job + '}';
    }

    public void addToDataBase() throws SQLException, ClassNotFoundException {

        DB d = new DB();
        String q = "INSERT INTO service_jobs (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Dep_ID`, `Sec_ID`, `Job_ID`, `Order_Departmant`, `Order_Section`, `Order_Job`,`status`)"
                + " VALUES (" + Service_Citizen_ID + ", " + Services_Provided_ID + ", " + Cit_ID
                + ", " + Dep_ID + ", " + Sec_ID + ", " + Job_ID + ", " + Order_Departmant + ", " + Order_Section + ", " + Order_Job + ",'" + status + "');";

        System.out.println("servic job " + q);

        d.write(q);

    }

    public void deleteFromDataBase() throws SQLException, ClassNotFoundException {

        DB d = new DB();
        String q = "DELETE FROM service_jobs WHERE (`Dep_ID` = " + Dep_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Sec_ID` = " + Sec_ID + ") and (`Job_ID` = " + Job_ID + ") and (`Order_Departmant` = " + Order_Departmant + ") and (`Order_Section` = " + Order_Section + ") and (`Order_Job` = " + Order_Job + ") and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Service_Citizen_ID` = " + Service_Citizen_ID + ")";

        System.out.println("servic job " + q);

        d.write(q);

    }

    public void updateDataBase() {

        try {
            DB d = new DB();
            String q = "UPDATE service_jobs SET status = 'done' WHERE (`Dep_ID` = " + Dep_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Sec_ID` = " + Sec_ID + ") and (`Job_ID` = " + Job_ID + ") and (`Order_Departmant` = " + Order_Departmant + ") and (`Order_Section` = " + Order_Section + ") and (`Order_Job` = " + Order_Job + ") and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Service_Citizen_ID` = " + Service_Citizen_ID + ");";
            System.out.println(q);

            d.write(q);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Service_Job.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void done() throws SQLException, ClassNotFoundException {
        DB db;

        db = new DB();
        String q = "";
        if (Order_Job != 0) {

            q = "UPDATE `oss`.`service_jobs` SET `status` = 'done' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Dep_ID` = " + getDep_ID() + ")"
                    + " and (`Sec_ID` = " + Sec_ID + ") and (`Job_ID` = " + Job_ID + ") and (`Order_Departmant` = " + Order_Departmant + ") and"
                    + " (`Order_Section` = " + Order_Section + ") and (`Order_Job` = " + Order_Job + ");";

            System.out.println(q);
            db.write(q);
        } else if (Order_Section != 0) {
            q = "UPDATE `oss`.`service_jobs` SET `status` = 'done' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Dep_ID` = " + getDep_ID() + ")"
                    + " and (`Sec_ID` = " + Sec_ID + ")  and (`Order_Departmant` = " + Order_Departmant + ") and"
                    + " (`Order_Section` = " + Order_Section + ") ;";
            System.out.println(q);
            db.write(q);
        } else {
            q = "UPDATE `oss`.`service_jobs` SET `status` = 'done' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ")"
                    + " and (`Dep_ID` = " + getDep_ID() + ")"
                    + "   and (`Order_Departmant` = " + Order_Departmant + ")  ;";
            System.out.println(q);
            db.write(q);
        }

    }
    
    public void notDone() throws SQLException, ClassNotFoundException {
        DB db;

        db = new DB();
        String q = "";
        if (Order_Job != 0) {

            q = "UPDATE `oss`.`service_jobs` SET `status` = 'notdone' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Dep_ID` = " + getDep_ID() + ")"
                    + " and (`Sec_ID` = " + Sec_ID + ") and (`Job_ID` = " + Job_ID + ") and (`Order_Departmant` = " + Order_Departmant + ") and"
                    + " (`Order_Section` = " + Order_Section + ") and (`Order_Job` = " + Order_Job + ");";

            System.out.println(q);
            db.write(q);
        } else if (Order_Section != 0) {
            q = "UPDATE `oss`.`service_jobs` SET `status` = 'notdone' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ") and (`Dep_ID` = " + getDep_ID() + ")"
                    + " and (`Sec_ID` = " + Sec_ID + ")  and (`Order_Departmant` = " + Order_Departmant + ") and"
                    + " (`Order_Section` = " + Order_Section + ") ;";
            System.out.println(q);
            db.write(q);
        } else {
            q = "UPDATE `oss`.`service_jobs` SET `status` = 'notdone' WHERE (`Service_Citizen_ID` = " + Service_Citizen_ID + ")"
                    + " and (`Services_Provided_ID` = " + Services_Provided_ID + ") and (`Cit_ID` = " + Cit_ID + ")"
                    + " and (`Dep_ID` = " + getDep_ID() + ")"
                    + "   and (`Order_Departmant` = " + Order_Departmant + ")  ;";
            System.out.println(q);
            db.write(q);
        }

    }
    public void updateShow(){
        try {
            String q = "UPDATE `oss`.`service_jobs` SET `show` = 'yes' "
                    + "WHERE (`Service_Citizen_ID` = '"+Service_Citizen_ID+"') and (`Services_Provided_ID` = '"+Services_Provided_ID+"') "
                    + "and (`Cit_ID` = '"+Cit_ID+"') and (`Sec_ID` = '"+Sec_ID+"') and (`Job_ID` = '"+Job_ID+"') "
                    + "and (`Order_Departmant` = '" + Order_Departmant + "') and (`Order_Section` = '" + Order_Section + "') "
                    + "and (`Order_Job` = '" + Order_Job + "') and (`Dep_ID` = '"+Dep_ID+"');";
            DB db = new DB();
            System.out.println(q);
            db.write(q);
        } catch (SQLException ex) {
            Logger.getLogger(Service_Job.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Service_Job.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
    
    
    

}

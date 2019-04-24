/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baraakali
 */
public class HaveServiceAttachment implements Serializable{

    int Services_Provided_ID;
    int ServiceAttachmentName_ID;
    String  important;
    String name;

    List<JobPath> jobs = new ArrayList<>();

    public HaveServiceAttachment() {
    }

    public HaveServiceAttachment(int Services_Provided_ID, int ServiceAttachmentName_ID, String important, String name) {
        this.Services_Provided_ID = Services_Provided_ID;
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
        this.important = important;
        this.name = name;
    }
    
    public int getServices_Provided_ID() {
        return Services_Provided_ID;
    }

    public void setServices_Provided_ID(int Services_Provided_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
    }

    public int getServiceAttachmentName_ID() {
        return ServiceAttachmentName_ID;
    }

    public void setServiceAttachmentName_ID(int ServiceAttachmentName_ID) {
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
    }

    public String getImportant() {
        return important;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public void setImportant(String important) {
        this.important = important;
    }

    

    public List<JobPath> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobPath> jobs) {
        this.jobs = jobs;
    }

   
    

     

    public void addToDB() throws SQLException, ClassNotFoundException {

        String q;
        q = "INSERT INTO  have_serviceattachment (`Services_Provided_ID`, `ServiceAttachmentName_ID`, `Important`) VALUES (" + Services_Provided_ID + ", " + ServiceAttachmentName_ID + ", '" + important + "');";

        
            DB data = new DB();
            System.out.println(q);
            data.write(q);

            for (JobPath j : jobs) {
                ViewerAttachment va = new ViewerAttachment(j.DepId, j.sectionID, j.id,ServiceAttachmentName_ID,Services_Provided_ID);
                va.addToDB();
            }

        }
    public String importantASString(){
        if(important.compareTo("yes")==0){
            return "اجباري";
        }else
            return "غير اجباري";
    }
    

    
    
}

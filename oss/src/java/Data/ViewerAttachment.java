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
public class ViewerAttachment {

    int Dep_ID;
    int Sec_ID;
    int Job_ID;
    int ServiceAttachmentName_ID;
    int Services_Provided_ID;

    String nameAtt;
    
    public ViewerAttachment() {
    }

    public ViewerAttachment(int Dep_ID, int Sec_ID, int Job_ID) {
        this.Dep_ID = Dep_ID;
        this.Sec_ID = Sec_ID;
        this.Job_ID = Job_ID;
    }

    
    public ViewerAttachment(int Dep_ID, int Sec_ID, int Job_ID, int ServiceAttachmentName_ID, int Services_Provided_ID) {
        this.Dep_ID = Dep_ID;
        this.Sec_ID = Sec_ID;
        this.Job_ID = Job_ID;
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
        this.Services_Provided_ID = Services_Provided_ID;
    }

    public int getServiceAttachmentName_ID() {
        return ServiceAttachmentName_ID;
    }

    public void setServiceAttachmentName_ID(int ServiceAttachmentName_ID) {
        this.ServiceAttachmentName_ID = ServiceAttachmentName_ID;
    }

    

    
    public int getServices_Provided_ID() {
        return Services_Provided_ID;
    }

    public void setServices_Provided_ID(int Services_Provided_ID) {
        this.Services_Provided_ID = Services_Provided_ID;
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

    public String getNameAtt() {
        return nameAtt;
    }

    public void setNameAtt(String nameAtt) {
        this.nameAtt = nameAtt;
    }
    
    public void addToDB() throws SQLException, ClassNotFoundException {

         
        String q = "INSERT INTO viewer_attachment (`Dep_ID`, `Sec_ID`, `Job_ID`, `ServiceAttachmentName_ID`, `Services_Provided_ID`) VALUES ("+Dep_ID+", "+Sec_ID+", "+Job_ID+", "+ServiceAttachmentName_ID+", "+Services_Provided_ID+");";

        
            DB data = new DB();

            System.out.println(q);
            data.write(q);

         

    }
    
}

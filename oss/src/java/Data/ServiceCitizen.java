/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import static Data.GetFromDBaraa.getMaxId_attachment_archive_citizen;
import static Data.GetFromDBaraa.stepAndDecDep;
import static Data.GetFromDBaraa.stepAndDecJop;
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
public class ServiceCitizen {

    int Service_Citizen_ID;
    int Services_Provided_ID;
    int Cit_ID;
    String Date;
    String status;
    String note = "g";
    public List<ServiceAttachmentName> attachment = new ArrayList<ServiceAttachmentName>();
    public List<ServiceAttachmentName> attwhithFile = new ArrayList<ServiceAttachmentName>();
    public Service thisService = new Service();

    public ServiceCitizen() {

    }

    public boolean addToDataBase() {
        
        Services_Provided_ID = thisService.id;
        
        

        int idMaxSC = getMaxId_service_citizen(Cit_ID);
        idMaxSC++;
        
        int idMaxAAC = getMaxId_attachment_archive_citizen();
        idMaxAAC++;
        DB data;
        try {

            data = new DB();
            String q = "start transaction;";
            data.write(q);
            
            q = "INSERT INTO service_citizen (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Date`, `status`, `note`) VALUES ('" + idMaxSC + "', '" + Services_Provided_ID + "', '" + Cit_ID + "', '1', 'notdone' , '" + note + "');";
            System.out.println(q);
            data.write(q);
            System.out.println("12345");
            
            for (ServiceAttachmentName a : attachment) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, a.id, a.file,a.nameFile,a.name,"no");
                attachmentArchiveCitizen.addToDataBase();
                idMaxAAC++;


            }
            
            System.out.println("ggggggggg");

            for (ServiceAttachmentName af : attwhithFile) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, af.id, af.file,af.nameFile,af.name,"yes");
                attachmentArchiveCitizen.addToDataBase();
                idMaxAAC++;
                

            }
            
             List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(Services_Provided_ID);
        List<SectionPath> sections = GetFromDBaraa.sectionPath(Services_Provided_ID);
        List<JobPath> jobs = GetFromDBaraa.jobPath(Services_Provided_ID);
        
            for (DepartmentPaths d : departments) {
                DecisionsDepartment decisionsDepartment = new DecisionsDepartment(d.id,
                        d.order, Services_Provided_ID, Cit_ID,
                        idMaxSC, status, 0, "", "", Date);
                decisionsDepartment.addToDB();
            }
            for (SectionPath s : sections) {
                DecisionSection decisionSection = new DecisionSection(s, Cit_ID, idMaxSC, Services_Provided_ID, "notdone");           
                decisionSection.addToDB();
            }
            for (JobPath j : jobs) {
                DecisionsJob decisionsJob = new DecisionsJob(Services_Provided_ID, Cit_ID, idMaxSC);
                decisionsJob.job = j;
                decisionsJob.addToDB();
            }
//            System.out.println("333333");
//            pathD = stepAndDecDep(Cit_ID, Services_Provided_ID);
//            for (StepsAndDecsions d : pathD) {
//                System.out.println("  =  "+d.departmentPaths.id+"  bb   "+ d.departmentPaths.order);
//                DecisionsDepartment decisionsDepartment = new DecisionsDepartment(d.departmentPaths.id,
//                        d.departmentPaths.order, Services_Provided_ID, Cit_ID,
//                        idMaxSC, status, 0, "", "", Date);
//                decisionsDepartment.addToDB();
//                
//
//            }
//
//            pathS = GetFromDBaraa.sectionsteps(Cit_ID);
//            for (DecisionSection s : pathS) {
//                DecisionSection decisionSection = new DecisionSection();
//                decisionSection.section = s.section;
//                decisionSection.addToDB(Services_Provided_ID, Cit_ID, Service_Citizen_ID);
//            }
//            
//            pathJ = stepAndDecJop(Cit_ID, Services_Provided_ID);
//            for (StepsAndDecsionsJob j : pathJ) {
//                DecisionsJob decisionsJob = new DecisionsJob(Services_Provided_ID, Cit_ID, idMaxSC);
//                decisionsJob.job = j.jobPath;
//                decisionsJob.idEmployee = j.decisionsJob.idEmployee;
//                decisionsJob.addToDB();
//
//            }
            q = "commit;";
            System.out.println(q);
            data.write(q);
        } catch (SQLException ex) { 
            try {
                data = new DB();
                String q = "rollback;";
                System.out.println(q);
                data.write(q);
                
                
                Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("eennd");
        return false;
    }

    public static int getMaxId_service_citizen(int cid) {
        int id = 0;
        try {
            DB db = new DB();

            String sql = "SELECT MAX(Service_Citizen_ID) FROM service_citizen where Cit_ID = "+cid+";";

            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                id = r.getInt(1);
                System.out.println(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public Service getThisService() {
        return thisService;
    }

    public void setThisService(Service thisService) {
        this.thisService = thisService;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public List<ServiceAttachmentName> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<ServiceAttachmentName> attachment) {
        this.attachment = attachment;
    }

    public List<ServiceAttachmentName> getAttwhithFile() {
        return attwhithFile;
    }

    public void setAttwhithFile(List<ServiceAttachmentName> attwhithFile) {
        this.attwhithFile = attwhithFile;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
}

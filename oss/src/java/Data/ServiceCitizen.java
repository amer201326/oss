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

    int idMaxSC;
            
    int Service_Citizen_ID;
    int Services_Provided_ID;
    int Cit_ID;
    String Date;
    String status;
    String note;
    public List<ServiceAttachmentName> attachment = new ArrayList<ServiceAttachmentName>();
    public List<ServiceAttachmentName> attwhithFile = new ArrayList<ServiceAttachmentName>();
    public Service thisService = new Service();

    public ServiceCitizen() {

    }

    public boolean addToDataBase() {

        Services_Provided_ID = thisService.id;

         idMaxSC = getMaxId_service_citizen(Cit_ID);
        idMaxSC++;
        
             
        int idMaxAAC = getMaxId_attachment_archive_citizen();
        idMaxAAC++;
        DB data;
        try {

            data = new DB();
            String q = "start transaction;";
            data.write(q);

            q = "INSERT INTO service_citizen (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Date`, `status`, `note`) VALUES ('"
                    + idMaxSC + "', '" + Services_Provided_ID + "', '" + Cit_ID + "', '1', 'notdone' , '" + note + "');";
            System.out.println(q);
            data.write(q);
            System.out.println("12345");

            for (ServiceAttachmentName a : attachment) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, a.id, a.file, a.nameFile, a.name, "no");
                attachmentArchiveCitizen.addToDataBase();
                idMaxAAC++;

            }

            System.out.println("ggggggggg");

            for (ServiceAttachmentName af : attwhithFile) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, af.id, af.file, af.nameFile, af.name, "yes");
                attachmentArchiveCitizen.addToDataBase();
                idMaxAAC++;

            }

            List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(Services_Provided_ID);
            List<SectionPath> sections = GetFromDBaraa.sectionPath(Services_Provided_ID);
            List<JobPath> jobs = GetFromDBaraa.jobPath(Services_Provided_ID);

            for (DepartmentPaths d : departments) {
                DecisionsDepartment decisionsDepartment = new DecisionsDepartment(d.id,
                        d.order, Services_Provided_ID, Cit_ID,
                        idMaxSC, "notdone", 0, "", "", Date);
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

           buildjobsPathOfthisService(idMaxSC);
                    
             q = "rollback;";
            
           // q = "commit;";
           
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

            String sql = "SELECT MAX(Service_Citizen_ID) FROM service_citizen where Cit_ID = " + cid + ";";

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

    public ArrayList<DepartmentPaths> firstDepsInPathOfthisService() {
        ArrayList<DepartmentPaths> departments = new ArrayList<>();
        try {
            DB db = new DB();
            DepartmentPaths d;

            String sql = "SELECT * FROM oss.decisions_department as dd inner join (SELECT Dep_ID , min(Order_Departmant) as mind "
                    + "FROM oss.decisions_department where Status = 'notdone' and Cit_ID=" + Cit_ID + " and Service_Citizen_ID=" + idMaxSC + " group by  Dep_ID ) as mdd "
                    + "on dd.Dep_ID = mdd.Dep_ID and dd.Order_Departmant = mind;";
            
            System.out.println("firstDep q ="+sql);
            
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new DepartmentPaths(r.getInt(1), r.getInt(2));
                departments.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public ArrayList<SectionPath> firstSecsInDPathOfthisService(int Dep_ID, int Order_Departmant) {
        ArrayList<SectionPath> sections = new ArrayList<>();
        try {
            DB db = new DB();
            SectionPath s;

            String sql = "SELECT * FROM oss.dicisions_section as dd inner join (SELECT Sec_ID , min(Order_Section) as mind "
                    + "FROM oss.dicisions_section where Status = 'notdone' and Cit_ID=" + Cit_ID + " and Service_Citizen_ID=" + idMaxSC + " and "
                    + "Dep_ID = " + Dep_ID + " and Order_Departmant = " + Order_Departmant + "  group by  Sec_ID ) as mdd "
                    + "on dd.Sec_ID = mdd.Sec_ID and dd.Order_Section = mind;";

            System.out.println("firstSec q ="+sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new SectionPath(r.getInt(1), r.getInt(5));
                sections.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sections;
    }

    public ArrayList<JobPath> firstJobInSPathOfthisService(int Dep_ID, int Order_Departmant,int Sec_ID , int Order_Section) {
        ArrayList<JobPath> jobs = new ArrayList<>();

        try {
            DB db = new DB();
            JobPath j;

            String sql = "SELECT * FROM oss.decisions_job as dd inner join (SELECT Job_ID , min(Order_Job) as mind "
                    + "FROM oss.decisions_job where Status = 'notdone' and Cit_ID=" + Cit_ID + " and Service_Citizen_ID=" + idMaxSC + " and "
                    + "Dep_ID = " + Dep_ID + " and Order_Departmant =" + Order_Departmant + "  and Sec_ID = " + Sec_ID + " and Order_Section =" + Order_Section + " group by  Job_ID ) as mdd "
                    + "on dd.Job_ID = mdd.Job_ID and dd.Order_Job = mind;";

            System.out.println("firstJob q ="+sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                j = new JobPath(r.getInt(3), r.getInt(7));
                jobs.add(j);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    public void buildjobsPathOfthisService(int Service_Citizen_ID ) throws SQLException, ClassNotFoundException {

        ArrayList<DepartmentPaths> departments = firstDepsInPathOfthisService();
        
   System.out.println("d , sise ="+departments.size());
   
        for (DepartmentPaths d : departments) {
          
            ArrayList<SectionPath> sections = firstSecsInDPathOfthisService(d.id, d.order);
            
            System.out.println("d , 1 "+d.toString());
            for (SectionPath s : sections) {
                ArrayList<JobPath> jobs = firstJobInSPathOfthisService(d.id, d.order,s.id, s.order);
                for (JobPath j : jobs) {
                     Service_Job servicejob = new Service_Job(Service_Citizen_ID, Services_Provided_ID, Cit_ID, d.id, s.id, j.id, d.order, s.order, j.dOrder);
                    servicejob.toString();
                    servicejob.addToDataBase();
                }
                
                
            }

        }

    }

}

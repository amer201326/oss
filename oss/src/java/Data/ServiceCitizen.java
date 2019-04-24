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
import java.time.LocalDate;
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
    
    public ServiceCitizen( int Service_Citizen_ID, int Services_Provided_ID, int Cit_ID, String Date, String status, String note) {
       
        this.Service_Citizen_ID = Service_Citizen_ID;
        this.Services_Provided_ID = Services_Provided_ID;
        this.Cit_ID = Cit_ID;
        this.Date = Date;
        this.status = status;
        this.note = note;
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

            q = "INSERT INTO service_citizen (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Date`, `status`, `note`) VALUES ("
                    + idMaxSC + ", " + Services_Provided_ID + ", " + Cit_ID + ", '" + LocalDate.now() + "', 'notdone' , '" + note + "');";
            System.out.println(q);
            data.write(q);
            System.out.println("  size _>" + attachment.size());

            for (ServiceAttachmentName a : attachment) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, a.id, a.file, a.nameFile, a.name, "no");
                attachmentArchiveCitizen.addToDataBase();

                AttachmentServiceCitizen attachmentServiceCitizen = new AttachmentServiceCitizen(idMaxAAC, idMaxSC, Services_Provided_ID, Cit_ID);
                attachmentServiceCitizen.addToDataBase();
                idMaxAAC++;
            }

            System.out.println("ggggggggg");

            for (ServiceAttachmentName af : attwhithFile) {
                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, af.id, af.file, af.nameFile, af.name, "yes");
                attachmentArchiveCitizen.addToDataBase();

                AttachmentServiceCitizen attachmentServiceCitizen = new AttachmentServiceCitizen(idMaxAAC, idMaxSC, Services_Provided_ID, Cit_ID);
                attachmentServiceCitizen.addToDataBase();
                idMaxAAC++;

            }

            List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(Services_Provided_ID);
            List<SectionPath> sections = GetFromDBaraa.sectionPath(Services_Provided_ID);
            List<JobPath> jobs = GetFromDBaraa.jobPath(Services_Provided_ID);

            ArrayList<DecisionsDepartment> decisionsDepartments = new ArrayList<>();
            for (DepartmentPaths d : departments) {
                DecisionsDepartment decisionsDepartment = new DecisionsDepartment(d.id,
                        d.order, Services_Provided_ID, Cit_ID,
                        idMaxSC, "notdone", 0, "", "", Date);

                decisionsDepartment.addToDB();
                decisionsDepartments.add(decisionsDepartment);
            }
            ArrayList<DecisionSection> decisionSections = new ArrayList<>();
            for (SectionPath s : sections) {
                DecisionSection decisionSection = new DecisionSection(s, Cit_ID, idMaxSC, Services_Provided_ID, "notdone");
                decisionSection.addToDB();
                decisionSections.add(decisionSection);
            }
            ArrayList<DecisionsJob> decisionsJobs = new ArrayList<>();
            for (JobPath j : jobs) {
                DecisionsJob decisionsJob = new DecisionsJob(Services_Provided_ID, Cit_ID, idMaxSC);
                decisionsJob.job = j;
                decisionsJob.addToDB();
                decisionsJobs.add(decisionsJob);
            }

            buildjobsPathOfthisService(idMaxSC, decisionsDepartments, decisionSections, decisionsJobs);

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

            System.out.println("firstDep q =" + sql);

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

            System.out.println("firstSec q =" + sql);
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

    public ArrayList<JobPath> firstJobInSPathOfthisService(int Dep_ID, int Order_Departmant, int Sec_ID, int Order_Section) {
        ArrayList<JobPath> jobs = new ArrayList<>();

        try {
            DB db = new DB();
            JobPath j;

            String sql = "SELECT * FROM oss.decisions_job as dd inner join (SELECT Job_ID , min(Order_Job) as mind "
                    + "FROM oss.decisions_job where Status = 'notdone' and Cit_ID=" + Cit_ID + " and Service_Citizen_ID=" + idMaxSC + " and "
                    + "Dep_ID = " + Dep_ID + " and Order_Departmant =" + Order_Departmant + "  and Sec_ID = " + Sec_ID + " and Order_Section =" + Order_Section + " group by  Job_ID ) as mdd "
                    + "on dd.Job_ID = mdd.Job_ID and dd.Order_Job = mind;";

            System.out.println("firstJob q =" + sql);
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

//    public void buildjobsPathOfthisService(int Service_Citizen_ID ) throws SQLException, ClassNotFoundException {
//
//        ArrayList<DepartmentPaths> departments = firstDepsInPathOfthisService();
//        
//   System.out.println("d , sise ="+departments.size());
//   
//        for (DepartmentPaths d : departments) {
//          
//            ArrayList<SectionPath> sections = firstSecsInDPathOfthisService(d.id, d.order);
//            
//            System.out.println("d , 1 "+d.toString());
//            for (SectionPath s : sections) {
//                ArrayList<JobPath> jobs = firstJobInSPathOfthisService(d.id, d.order,s.id, s.order);
//                for (JobPath j : jobs) {
//                     Service_Job servicejob = new Service_Job(Service_Citizen_ID, Services_Provided_ID, Cit_ID, d.id, s.id, j.id, d.order, s.order, j.dOrder);
//                    servicejob.toString();
//                    servicejob.addToDataBase();
//                }
//                
//                
//            }
//
//        }
//
//    }
    private void buildjobsPathOfthisService(int idMaxSC, ArrayList<DecisionsDepartment> decisionsDepartments, ArrayList<DecisionSection> decisionSections, ArrayList<DecisionsJob> decisionsJobs) throws SQLException, ClassNotFoundException {
        System.out.println("===================================================================================================");

        DecisionsDepartment[] dds = new DecisionsDepartment[decisionsDepartments.size()];
        dds = decisionsDepartments.toArray(dds);

        DecisionSection[] dses = new DecisionSection[decisionSections.size()];
        dses = decisionSections.toArray(dses);

        DecisionsJob[] djs = new DecisionsJob[decisionsJobs.size()];
        djs = decisionsJobs.toArray(djs);

        List<DecisionsDepartment> midDecDep = new ArrayList<>();
        List<DecisionSection> minDecSec = new ArrayList<>();
        List<DecisionsJob> minDecjob = new ArrayList<>();
        int order = Integer.MAX_VALUE;
        if (dds.length != 0) {

            for (int i = 0; i < dds.length - 1; i++) {

                for (int j = 0; j < dds.length - 1; j++) {
                    if (dds[j + 1].getDepOrder() < dds[j].getDepOrder()) {
                        DecisionsDepartment temp = dds[j + 1];
                        dds[j + 1] = dds[j];
                        dds[j] = temp;
                    }

                }

            }
            int temp = 0;
            for (int i = 0; i < dds.length; i++) {
                DecisionsDepartment dd = dds[i];
                if (i == 0) {
                    midDecDep.add(dd);
                    temp = dd.depOrder;
                } else {
                    if (dd.depOrder == temp) {
                        midDecDep.add(dd);
                    } else {
                        break;
                    }
                }

            }
            for (DecisionsDepartment decisionsDepartment : midDecDep) {
                System.out.println(decisionsDepartment);
            }
            System.out.println("--- section _________________________________________");

            for (DecisionsDepartment decisionsDepartment : midDecDep) {

                for (int i = 0; i < dses.length; i++) {
                    DecisionSection ds = dses[i];
                    if (ds.section.departmentId == decisionsDepartment.depId && ds.section.order == decisionsDepartment.depOrder) {
                        decisionsDepartment.getSection().add(ds);
                        for (int j = 0; j < djs.length; j++) {
                            DecisionsJob dj = djs[j];
                            if (ds.section.departmentId == dj.job.DepId && ds.section.orderDepartment == dj.job.dOrder && ds.section.id == dj.job.sectionID && ds.section.order == dj.job.sOrder) {
                                ds.getJobs().add(dj);
                            }
                        }

                    }
                }
            }

            for (DecisionsDepartment decisionsDepartment : midDecDep) {
                if (!decisionsDepartment.section.isEmpty()) {
                    DecisionSection[] decisionSections1 = new DecisionSection[decisionsDepartment.section.size()];
                    decisionSections1 = decisionsDepartment.section.toArray(decisionSections1);

                    for (int i = 0; i < decisionSections1.length - 1; i++) {

                        for (int j = 0; j < decisionSections1.length - 1; j++) {
                            if (decisionSections1[j + 1].section.order < decisionSections1[j].section.order) {
                                DecisionSection temps = decisionSections1[j + 1];
                                decisionSections1[j + 1] = decisionSections1[j];
                                decisionSections1[j] = temps;
                            }

                        }

                    }
                    temp = 0;
                    for (int i = 0; i < decisionSections1.length; i++) {
                        DecisionSection dd = decisionSections1[i];
                        if (i == 0) {
                            minDecSec.add(dd);
                            temp = dd.section.order;
                        } else {
                            if (dd.section.order == temp) {
                                minDecSec.add(dd);
                            } else {
                                break;
                            }
                        }

                    }
                } else {
                    List<Section> sections = GetFromDB.getSection(Services_Provided_ID);
                    for (Section section : sections) {
                        List<JobTitel> jobTitels = GetFromDB.getJobTittle(section.id + "");
                        for (JobTitel jobTitel : jobTitels) {
                            DecisionsJob decJ = new DecisionsJob(new JobPath(decisionsDepartment.depId, Integer.parseInt(section.id), Integer.parseInt(jobTitel.id), Services_Provided_ID, jobTitel.name, decisionsDepartment.depOrder, 0, 0),
                                    0, "notdone", "no", 0, "", "", "");
                            minDecjob.add(decJ);
                        }
                    }
                }

            }

            for (DecisionSection decisionSection : minDecSec) {
                System.out.println(decisionSection);
            }

            for (DecisionSection decisionSection : minDecSec) {
                if (!decisionSection.getJobs().isEmpty()) {

                    DecisionsJob[] decisionsJobs1 = new DecisionsJob[decisionSection.jobs.size()];
                    decisionsJobs1 = decisionSection.jobs.toArray(decisionsJobs1);

                    for (int i = 0; i < decisionsJobs1.length - 1; i++) {

                        for (int j = 0; j < decisionsJobs1.length - 1; j++) {
                            if (decisionsJobs1[j + 1].job.order < decisionsJobs1[j].job.order) {
                                DecisionsJob tempsj = decisionsJobs1[j + 1];
                                decisionsJobs1[j + 1] = decisionsJobs1[j];
                                decisionsJobs1[j] = tempsj;
                            }

                        }

                    }
                    temp = 0;
                    for (int i = 0; i < decisionsJobs1.length; i++) {
                        DecisionsJob dd = decisionsJobs1[i];
                        if (i == 0) {
                            minDecjob.add(dd);
                            temp = dd.job.order;
                        } else {
                            if (dd.job.order == temp) {
                                minDecjob.add(dd);
                            } else {
                                break;
                            }
                        }

                    }
                } else {
                    List<JobTitel> jobTitels = GetFromDB.getJobTittle(decisionSection.section.id + "");
                    for (JobTitel jobTitel : jobTitels) {
                        DecisionsJob decJ = new DecisionsJob(new JobPath(decisionSection.section.departmentId, decisionSection.section.id, Integer.parseInt(jobTitel.id), Services_Provided_ID, jobTitel.name, decisionSection.section.orderDepartment, decisionSection.section.order, 0),
                                0, "notdone", "no", 0, "", "", "");
                        minDecjob.add(decJ);
                    }
                }
            }

            System.out.println("_________   JOB _+_++++++++++++++++++++++++++++++++++++++++++++++");

            for (DecisionsJob decisionsJob : minDecjob) {
                System.out.println(decisionsJob);
                Service_Job servicejob = new Service_Job(idMaxSC, Services_Provided_ID, Cit_ID, decisionsJob.job.DepId, decisionsJob.job.sectionID, decisionsJob.job.id, decisionsJob.job.dOrder, decisionsJob.job.sOrder, decisionsJob.job.order, "notdone");

                servicejob.addToDataBase();
            }

        }
    }
///////////////////////////////employee

    public void ContineuInPath(Service_Job j,int Cit_ID, int Service_Citizen_ID) {

        List<DecisionsDepartment> departments = GetFromDB.getDecisionsDepartment(Cit_ID, Service_Citizen_ID);
        List<DecisionSection> sections = GetFromDB.getDecisionsSection(Cit_ID, Service_Citizen_ID);
        List<DecisionsJob> jobs = GetFromDB.getDecisionsJob(Cit_ID, Service_Citizen_ID);

        
        List<Service_Job> service_Jobs = GetFromDB.getAllService_Jobs(j);
        for (Service_Job sj : service_Jobs) {
            if (sj.Order_Section == 0) {

            } else {

            }

        }

    }
}

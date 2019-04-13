/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class GetFromDBaraa {

    public static ArrayList<CitizenService> doneCitizenServices(int idcitizen) {
        ArrayList<CitizenService> services = new ArrayList<>();
        try {
            DB db = new DB();
            CitizenService cs;
            Service s;
            String sql = "SELECT sc.Services_Provided_ID,Serv_Name,sc.Date,sc.Service_Citizen_ID FROM service_citizen as sc inner join services_provided as sp where sc.status = 'done' and Cit_ID=" + idcitizen + " and sc.Services_Provided_ID=sp.Services_Provided_ID;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service();
                s.id = r.getInt(1);
                s.name = r.getString(2);
                cs = new CitizenService(r.getInt(4), s, r.getString(3));

                services.add(cs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return services;

    }

    public static ArrayList<CitizenService> notDoneCitizenServices(int idcitizen) {
        ArrayList<CitizenService> services = new ArrayList<>();
        try {
            DB db = new DB();
            CitizenService cs;
            Service s;
            String sql = "SELECT sc.Services_Provided_ID,Serv_Name,sc.Date,sc.Service_Citizen_ID  FROM service_citizen as sc inner join services_provided as sp where sc.status = 'notdone' and Cit_ID=" + idcitizen + " and sc.Services_Provided_ID=sp.Services_Provided_ID;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service();
                s.id = r.getInt(1);
                s.name = r.getString(2);
                cs = new CitizenService(r.getInt(4), s, r.getString(3));

                services.add(cs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return services;

    }

    public static ArrayList<DepartmentPaths> departmentPath(int idservice) {
        ArrayList<DepartmentPaths> departments = new ArrayList<>();
        try {
            DB db = new DB();
            DepartmentPaths d;
            String sql = "SELECT * FROM steps_department as sd  inner join department as d on sd.Dep_ID =d.Dep_ID where sd.Services_Provided_ID = " + idservice + " order by sd.Order_Departmant;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new DepartmentPaths(r.getInt(1), r.getString(6), r.getInt(3), r.getString(4));
                departments.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public static ArrayList<SectionPath> sectionPath(int idservice) {
        ArrayList<SectionPath> sections = new ArrayList<>();
        try {
            DB db = new DB();
            SectionPath s;
            String sql = "SELECT * FROM steps_section as ss  inner join section as s on ss.Sec_ID = s.Sec_ID where ss.Services_Provided_ID = " + idservice + " ;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new SectionPath(r.getInt(1), r.getInt(4), r.getInt(2), r.getString(8), r.getInt(5));
                sections.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(sections.size());
        return sections;
    }

    public static ArrayList<JobPath> jobPath(int idservice) {
        ArrayList<JobPath> jobs = new ArrayList<>();
        try {
            DB db = new DB();
            JobPath j;
            String sql = "SELECT * FROM steps_job as sj  inner join jobtitle as j on sj.Job_ID = j.Job_ID where sj.Services_Provided_ID = " + idservice + " ;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                j = new JobPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getString(9), r.getInt(5), r.getInt(6), r.getInt(7));
                jobs.add(j);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    public static ArrayList<DecisionsDepartment> MyServicedepartmentPath(int idservice, int idcitizen, int idSerCit) {
        ArrayList<DecisionsDepartment> departments = new ArrayList<>();
        try {
            DB db = new DB();
            DecisionsDepartment d;
            String sql = "SELECT dd.*,d.Dep_Name FROM decisions_department as dd inner join department as d on dd.Dep_ID = d.Dep_ID where Cit_ID = " + idcitizen + " and Service_Citizen_ID = " + idSerCit + " and Services_Provided_ID = " + idservice + ";";
            ResultSet r = db.read(sql);
            while (r.next()) {
                d = new DecisionsDepartment(r.getString(8), r.getString(9), r.getString(6), r.getString(7), r.getInt(1), r.getInt(2), r.getString(11));
                departments.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }

    public static ArrayList<DecisionsJob> MyServiceJobPath(int idservice, int idcitizen, int idSerCit) {
        ArrayList<DecisionsJob> jobs = new ArrayList<>();
        try {
            DB db = new DB();
            DecisionsJob d;
            String sql = "SELECT * FROM decisions_job as dj inner join jobtitle as j on dj.Job_ID = j.Job_ID where Cit_ID = " + idcitizen + " and Service_Citizen_ID = " + idSerCit + " and Services_Provided_ID = " + idservice + ";";
            ResultSet r = db.read(sql);
            while (r.next()) {
                JobPath j = new JobPath(r.getInt(1), r.getInt(2), r.getInt(3), "", r.getInt(5), r.getInt(6), r.getInt(5));
                j.name = r.getString(18);
                d = new DecisionsJob(j, r.getString(11), r.getString(12), r.getString(13), r.getString(15));
                jobs.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    public static ArrayList<StepsAndDecsions> stepAndDecDep(int idcitizen, int idSerCit) {
        ArrayList<StepsAndDecsions> deps = new ArrayList<>();
        try {
            DB db = new DB();
            StepsAndDecsions sd;
            DepartmentPaths s;
            DecisionsDepartment d;

            String sql = "SELECT dd.*,sd.*,d.Dep_Name FROM decisions_department as dd inner join department as d on dd.Dep_ID=d.Dep_ID  inner join steps_department as sd on dd.Dep_ID = sd.Dep_ID and dd.Services_Provided_ID=sd.Services_Provided_ID and  dd.Order_Departmant=sd.Order_Departmant where dd.Cit_ID =" + idcitizen + " and dd.Service_Citizen_ID =" + idSerCit + " order by dd.Order_Departmant;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new DepartmentPaths(r.getInt(1), r.getString(15), r.getInt(2), r.getString(15));
                d = new DecisionsDepartment(r.getString(8), r.getString(9), r.getString(6), r.getString(7), r.getInt(1), r.getInt(2), r.getString(15));
                sd = new StepsAndDecsions(s, d);
                deps.add(sd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return deps;
    }

    public static ArrayList<StepsAndDecsionsJob> stepAndDecJop(int idcitizen, int idSerCit) {
        ArrayList<StepsAndDecsionsJob> Lsdj = new ArrayList<>();
        try {
            DB db = new DB();
            StepsAndDecsionsJob sdj;
            JobPath s;
            DecisionsJob d;

            String sql = "SELECT * FROM decisions_job as dj inner join jobtitle as j on dj.Job_ID = j.Job_ID inner join steps_job as sj on dj.Dep_ID=sj.Dep_ID and dj.Order_Departmant=sj.Order_Departmant and dj.Sec_ID=sj.Sec_ID and dj.Order_Section=sj.Order_Section and dj.Job_ID=sj.Job_ID and dj.Order_Job=sj.Order_Job  where Cit_ID = " + idcitizen + " and dj.Service_Citizen_ID =" + idSerCit + " "
                    + "order by dj.Order_Job;";
            System.out.println(sql);
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new JobPath(r.getInt(1), r.getInt(2), r.getInt(3), r.getString(18), r.getInt(5), r.getInt(6), r.getInt(7));
                d = new DecisionsJob(r.getString(11), r.getString(12), r.getString(13), r.getString(14), r.getString(15));
                d.employee.emp_id=r.getInt(10);
                sdj = new StepsAndDecsionsJob(s, d);
                Lsdj.add(sdj);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("size isL" + Lsdj.size());
        return Lsdj;
    }

    public static ArrayList<DecisionSection> sectionsteps(int idservice) {
        ArrayList<DecisionSection> sections = new ArrayList<>();
        try {
            DB db = new DB();
            SectionPath sp;
            DecisionSection s;
            String sql = "SELECT * FROM steps_section as ss  inner join section as s on ss.Sec_ID = s.Sec_ID where ss.Services_Provided_ID = " + idservice + " ;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                sp = new SectionPath(r.getInt(1), r.getInt(4), r.getInt(2), r.getString(8), r.getInt(5));
                s = new DecisionSection();
                s.setSection(sp);;
                sections.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sections;

    }

    public static void ApplyService(int Cit_ID, int Services_Provided_ID, List<ServiceAttachmentName> allAttachment) {

        List<StepsAndDecsions> pathD;
        List<StepsAndDecsionsJob> pathJ;

        int idMaxAAC = getMaxId_attachment_archive_citizen();
        idMaxAAC++;

        int idMaxSC = getMaxId_service_citizen();
        idMaxSC++;

        try {
            DB data = new DB();
            String q = "start transaction;";

            q = "INSERT INTO service_citizen (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Date`, `status`) VALUES ('" + idMaxSC + "', '" + Services_Provided_ID + "', '" + Cit_ID + "', '1', 'notdone');";
            data.write(q);

            for (ServiceAttachmentName a : allAttachment) {

                q = "INSERT INTO attachment_archive_citizen (`Atta_ArchiveC_ID`, `Cit_ID`, `ServiceAttachmentName_ID`) VALUES ('" + idMaxAAC + "', '" + Cit_ID + "', '" + a.id + "');";
                data.write(q);
                q = "INSERT INTO attachment_service_citizen (`Atta_ArchiveC_ID`, `Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`) VALUES ('" + idMaxAAC + "', '" + idMaxSC + "', '" + Services_Provided_ID + "', '" + Cit_ID + "');";
                data.write(q);

            }

            pathD = stepAndDecDep(Cit_ID, Services_Provided_ID);
            for (StepsAndDecsions d : pathD) {
                q = "INSERT INTO decisions_department (`Dep_ID`, `Order_Departmant`, `Services_Provided_ID`, `Cit_ID`, `Service_Citizen_ID`, `Status`, `Cost`, `Date`) VALUES ('"+d.departmentPaths.id+"', '"+d.departmentPaths.order+"', '"+Services_Provided_ID+"', '"+Cit_ID +"', '"+idMaxSC+"', 'notdone', '0', '1');";

            }
            
            pathJ = stepAndDecJop(Cit_ID, Services_Provided_ID);
            for (StepsAndDecsionsJob j : pathJ) {
                q = "INSERT INTO decisions_job (`Dep_ID`, `Sec_ID`, `Job_ID`, `Services_Provided_ID`, `Order_Departmant`, `Order_Section`, `Order_Job`, `Cit_ID`, `Service_Citizen_ID`, `Emp_ID`, `Status`, `Cost`, `Date`) VALUES ('"+j.jobPath.DepId+"', '"+j.jobPath.sectionID+"', '"+j.jobPath.id+"', '"+Services_Provided_ID +"', '"+j.jobPath.dOrder+"', '"+j.jobPath.sOrder+"', '"+j.jobPath.order+"', '"+Cit_ID+"', '"+idMaxSC+"', '"+j.decisionsJob.employee.emp_id+"', 'notdone', '0', '1');";

            }


            q = "commit;";
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            DB data;
            try {
                data = new DB();
                String q = "rollback;";
                data.write(q);
            } catch (SQLException ex1) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getMaxId_attachment_archive_citizen() {
        int id = 0;
        try {
            DB db = new DB();

            String sql = "SELECT MAX(Atta_ArchiveC_ID) FROM attachment_service_citizen;";

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

    public static int getMaxId_service_citizen() {
        int id = 0;
        try {
            DB db = new DB();

            String sql = "SELECT MAX(Service_Citizen_ID) FROM service_citizen;";

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

}

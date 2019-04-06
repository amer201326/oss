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
 * @author me
 */
public class GetFromDBaraa {

    public static ArrayList<CitizenService> doneCitizenServices(int idcitizen) {
        ArrayList<CitizenService> services = new ArrayList<>();
        try {
            DB db = new DB();
            CitizenService cs;
            Service s;
            String sql = "SELECT sc.Services_Provided_ID,Serv_Name,sc.Date FROM service_citizen as sc inner join services_provided as sp where sc.status = 'done' and Cit_ID=" + idcitizen + " and sc.Services_Provided_ID=sp.Services_Provided_ID;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service();
                s.id = r.getInt(1);
                s.name = r.getString(2);
                cs = new CitizenService();
                cs.providServicee = s;
                cs.date = r.getString(3);
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
            String sql = "SELECT sc.Services_Provided_ID,Serv_Name,sc.Date FROM service_citizen as sc inner join services_provided as sp where sc.status = 'notdone' and Cit_ID=" + idcitizen + " and sc.Services_Provided_ID=sp.Services_Provided_ID;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service();
                s.id = r.getInt(1);
                s.name = r.getString(2);
                cs = new CitizenService();
                cs.providServicee = s;
                cs.date = r.getString(3);
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
            String sql ="SELECT * FROM steps_section as ss  inner join section as s on ss.Sec_ID = s.Sec_ID where ss.Services_Provided_ID = "+idservice+" ;";
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
            String sql ="SELECT * FROM steps_job as sj  inner join jobtitle as j on sj.Job_ID = j.Job_ID where sj.Services_Provided_ID = 1 ;";
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
}

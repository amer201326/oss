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

    public static ArrayList<SectionPath> sectionPath(int id) {
        ArrayList<SectionPath> sections = new ArrayList<>();
        try {
            DB db = new DB();
            SectionPath s;
            String sql ="SELECT * FROM steps_section as ss  inner join section as s on ss.Sec_ID = s.Sec_ID where ss.Services_Provided_ID = "+id+" ;";
                    //"SELECT * FROM steps_section as ss  inner join section as s on ss.Sec_ID = s.Sec_ID where ss.Services_Provided_ID = 1 and ss.Dep_ID=1 and ss.Order_Departmant=1 order by ss.Order_Section;";
            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new SectionPath();
                sections.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sections;
    }

}

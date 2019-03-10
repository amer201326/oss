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
    public static List<Service> getAllServices() {

        Service s = new Service();

        List<Service> services = new ArrayList<Service>();
        try {
            DB db = new DB();
            String sql = "SELECT Services_Provided_ID,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,services_provided.Dep_ID,department.Dep_Name,section.Sec_ID,section.Sec_Name FROM services_provided INNER JOIN department ON services_provided.Dep_ID = department.Dep_ID INNER JOIN section ON services_provided.Dep_ID = department.Dep_ID ;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2), r.getDouble(3),r.getInt(4),r.getString(5));
                s.department.id = r.getInt(6);
                s.department.nameA = r.getString(7);
                s.section.id = r.getString(8);
                s.section.name = r.getString(9);
                System.out.println(s.toString());
                services.add(s);
            }
            
        } catch (Exception e) {
        }
        return services;
    }
    
    public static void addNewService(List<DepartmentPaths>departmentsInPath) {
        for (DepartmentPaths departmentsInPath1 : departmentsInPath) {
            for (SectionPath section1 : departmentsInPath1.sections) {
                for (JobPath job1 : section1.jobs) {
                    System.out.println(departmentsInPath1.nameA + "mmm");
                    System.out.println(section1.getName());
                     System.out.println(job1.getName());
                    
                }
            }
        }
    }
}


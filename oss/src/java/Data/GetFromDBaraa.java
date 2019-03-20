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
            String sql = "select Services_Provided_ID ,Serv_Name,Serv_Cost,Serv_Days,Serv_Case,d.*,s.* from services_provided  inner join department as d on services_provided.DepartmentID = d.Dep_ID inner join section as s on services_provided.sectionID = s.Sec_ID;";

            ResultSet r = db.read(sql);
            while (r.next()) {
                s = new Service(r.getInt(1), r.getString(2),r.getInt(3),
                        r.getDouble(4),r.getString(5),new Department(r.getInt(6), r.getString(7),
                                r.getString(8)),new Section(r.getInt(9), r.getInt(10), r.getString(11), r.getString(7)));
                
                services.add(s);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
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


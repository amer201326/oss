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
    String note;
    public List<ServiceAttachmentName> attachment = new ArrayList<ServiceAttachmentName>();
    public List<ServiceAttachmentName> attwhithFile = new ArrayList<ServiceAttachmentName>();
    public Service thisService = new Service();

    public ServiceCitizen() {
        
//        List<ServiceAttachmentName> allAttachment = GetFromDB.getAttavhmentByserviceById(thisService.getId());
//        System.out.println("Data.ServiceCitizen.<init>()"+allAttachment.size());
//        for (ServiceAttachmentName serviceAttachmentName : allAttachment) {
//
//            if (serviceAttachmentName.getFileDownload() == null) {
//                attachment.add(serviceAttachmentName);
//            } else {
//                attwhithFile.add(serviceAttachmentName);
//            }
//
//        }

    }

    public boolean addToDataBase() {
        List<StepsAndDecsions> pathD;
        List<StepsAndDecsionsJob> pathJ;

        int idMaxSC = getMaxId_service_citizen();
        idMaxSC++;
        int idMaxAAC = getMaxId_attachment_archive_citizen();
        idMaxAAC++;

        try {

            DB data = new DB();
            String q = "start transaction;";
            q = "INSERT INTO service_citizen (`Service_Citizen_ID`, `Services_Provided_ID`, `Cit_ID`, `Date`, `status`, `note`) VALUES ('" + idMaxSC + "', '" + Services_Provided_ID + "', '" + Cit_ID + "', '1', 'notdone' , '" + note + "');";
            data.write(q);

            for (ServiceAttachmentName a : attachment) {

                AttachmentArchiveCitizen attachmentArchiveCitizen = new AttachmentArchiveCitizen(idMaxAAC, Cit_ID, a.id, a.file);
                attachmentArchiveCitizen.addToDataBase();
                idMaxAAC++;

            }
            for (ServiceAttachmentName af : attwhithFile) {
                AttachmentServiceCitizen attachmentServiceCitizen = new AttachmentServiceCitizen(idMaxAAC, Service_Citizen_ID, Services_Provided_ID, Cit_ID, af.file);
                attachmentServiceCitizen.addToDataBase();
                idMaxAAC++;

            }

            pathD = stepAndDecDep(Cit_ID, Services_Provided_ID);
            for (StepsAndDecsions d : pathD) {
                DecisionsDepartment decisionsDepartment = new DecisionsDepartment("notdone", "0", d.departmentPaths.id, d.departmentPaths.order);
                decisionsDepartment.addToDB(Services_Provided_ID, Cit_ID, Service_Citizen_ID);

            }

            pathJ = stepAndDecJop(Cit_ID, Services_Provided_ID);
            for (StepsAndDecsionsJob j : pathJ) {
                DecisionsJob decisionsJob = new DecisionsJob();
                decisionsJob.job = j.jobPath;
                decisionsJob.employee = j.decisionsJob.employee;
                decisionsJob.addToDB(Services_Provided_ID, Cit_ID, Service_Citizen_ID);

            }

        } catch (SQLException | ClassNotFoundException ex) {
            DB data;
            try {
                data = new DB();
                String q = "rollback;";
                data.write(q);
            } catch (SQLException ex1) {
                Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Logger.getLogger(ServiceCitizen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
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

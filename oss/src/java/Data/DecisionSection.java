/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baraakali
 */
public class DecisionSection {

    SectionPath section;
    List<StepsAndDecsionsJob> job = new ArrayList<>();
    String Status;

    public SectionPath getSection() {
        return section;
    }

    public void setSection(SectionPath section) {
        this.section = section;
    }

    public List<StepsAndDecsionsJob> getJob() {
        return job;
    }

    public void setJob(List<StepsAndDecsionsJob> job) {
        this.job = job;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
    public void addToDB(int Services_Provided_ID, int Cit_ID, int Service_Citizen_ID) {
        try {
            DB data = new DB();
            String q = "INSERT INTO dicisions_section (`Dep_ID`, `Sec_ID`, `Services_Provided_ID`, `Order_Departmant`, `Order_Section`, `Cit_ID`, `Status`, `Service_Citizen_ID`) VALUES ('"+section.departmentId+"', '"+section.id+"', '"+Services_Provided_ID+"', '"+section.orderDepartment+"', '"+section.order+"', '"+section.departmentId+"', 'notdone'"+"', '"+Service_Citizen_ID+");";
            data.write(q);

        } catch (SQLException ex) {
            Logger.getLogger(DecisionSection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DecisionSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

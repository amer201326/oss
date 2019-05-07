/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baraakali
 */
public class StepsAndDecsions {

    public DepartmentPaths departmentPaths;
    public DecisionsDepartment decisionsDepartment;
    public List <DecisionSection> sections = new ArrayList<>();
    
    //List<StepsAndDecsionsJob> job = new ArrayList<>();


    public StepsAndDecsions(DepartmentPaths departmentPaths) {
        this.departmentPaths = departmentPaths;
    }

    public StepsAndDecsions(DepartmentPaths departmentPaths, DecisionsDepartment decisionsDepartment) {
        this.departmentPaths = departmentPaths;
        this.decisionsDepartment = decisionsDepartment;
    }

    public DepartmentPaths getDepartmentPaths() {
        return departmentPaths;
    }

    public void setDepartmentPaths(DepartmentPaths departmentPaths) {
        this.departmentPaths = departmentPaths;
    }

    public DecisionsDepartment getDecisionsDepartment() {
        return decisionsDepartment;
    }

    public void setDecisionsDepartment(DecisionsDepartment decisionsDepartment) {
        this.decisionsDepartment = decisionsDepartment;
    }

    
//    public List<StepsAndDecsionsJob> getJob() {
//        return job;
//    }
//
//    public void setJob(List<StepsAndDecsionsJob> job) {
//        this.job = job;
//    }

    public List<DecisionSection> getSections() {
        return sections;
    }

    public void setSections(List<DecisionSection> sections) {
        this.sections = sections;
    }

   
    
     
}

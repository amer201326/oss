/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import Data.ServiceErr;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Amer$_$
 */
@ManagedBean(name = "sessionLists")
@SessionScoped
public class SessionLists implements Serializable{
    Department departmentSelected;
    Section sectionSelected;
    JobTitel jobSelected;
    Service selectedService;
    int id;
    
    public SessionLists() {
        departmentSelected = new Department();
        sectionSelected = new Section();
        jobSelected = new JobTitel();
        selectedService = new Service();
    }

    public Department getDepartmentSelected() {
        return departmentSelected;
    }

    public void setDepartmentSelected(Department departmentSelected) {
        this.departmentSelected = departmentSelected;
    }

    public Section getSectionSelected() {
        return sectionSelected;
    }

    public void setSectionSelected(Section sectionSelected) {
        this.sectionSelected = sectionSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobTitel getJobSelected() {
        return jobSelected;
    }

    public void setJobSelected(JobTitel jobSelected) {
        this.jobSelected = jobSelected;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        
        this.selectedService = selectedService;
    }
    
    

   

    
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.SectionPath;
import Data.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ShowMyServicesManager implements Serializable {
    
    Service thisService = new Service();
    
    public ShowMyServicesManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String param = parameterMap.get("id");
        thisService = GetFromDB.getServiceByID2(param);
        thisService.setPath2(servicePath(thisService.getId()));
    }
    
    public Service getThisService() {
        return thisService;
    }
    
    public void setThisService(Service thisService) {
        this.thisService = thisService;
    }
 
    private  List<DepartmentPaths> servicePath(int idSer) {
        List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(idSer);
        List<SectionPath> sections = GetFromDBaraa.sectionPath(idSer);
        List<JobPath> jobs = GetFromDBaraa.jobPath(idSer);
        for (DepartmentPaths department : departments) {
            for (SectionPath section : sections) {
                if (department.id == section.getDepartmentId() && department.order == section.getOrderDepartment()) {
                  System.out.println("ggggg"+department.toString());
                    department.sections.add(section);
                 
                    for (JobPath job : jobs) {
                        if(section.getId() == job.getSectionID() && section.getOrder() == job.getsOrder()){
                            section.jobs.add(job);
                        }
                            
                    }
                }
            }
            
        }
        
        return departments;
    }
    
}

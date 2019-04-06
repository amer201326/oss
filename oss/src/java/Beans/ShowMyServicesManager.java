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
public class ShowMyServicesManager implements Serializable{
    Service thisService = new Service();

    public ShowMyServicesManager() {
         Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
          String param = parameterMap.get("id");
          thisService = GetFromDB.getServiceByID2(param);
    }
    
    public Service getThisService() {
        return thisService;
    }

    public void setThisService(Service thisService) {
        this.thisService = thisService;
    }
    
    public ArrayList<DepartmentPaths> departmentPath() {
        return GetFromDBaraa.departmentPath(thisService.getId());
        
     }
     public ArrayList<DepartmentPaths> getPath() {
         List<DepartmentPaths> departments = GetFromDBaraa.departmentPath(thisService.getId());
         ArrayList<SectionPath> sections = new ArrayList<>();
         ArrayList<JobPath> jobs = new ArrayList<>();
         for (DepartmentPaths department : departments) {
             
         }
         
     }
}

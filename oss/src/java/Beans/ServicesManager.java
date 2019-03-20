/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author me
 */
@ManagedBean
@ViewScoped
public class ServicesManager implements Serializable {
    
    @ManagedProperty(value = "#{sessionLists}")
    SessionLists sessionLists;
    
    List<Service> allSrvices;
    List<Department> allDep;
    List<Section> allSec;
    Service seviceSelected;

    public ServicesManager() {
        allSrvices = GetFromDBaraa.getAllServices();
        allDep = GetFromDB.getDepartments();
        allSec = GetFromDB.getSection();
        seviceSelected = new Service();

    }
    
    public void onServiceEdit(RowEditEvent event){
        Service s = (Service)event.getObject();
        s.simpleUpdate();
    }
    public void onServiceEditCancel(RowEditEvent event){
        
        
    }
    
    public void saveIdService(int id){
        System.out.println(id +"<<");
        sessionLists.id = id;
    }
    
    
    

    public List<Service> getAllSrvices() {
        return allSrvices;
    }

    public void setAllSrvices(List<Service> allSrvices) {
        this.allSrvices = allSrvices;
    }

    public List<Department> getAllDep() {
        return allDep;
    }

    public void setAllDep(List<Department> allDep) {
        this.allDep = allDep;
    }

    public List<Section> getAllSec() {
        return allSec;
    }

    public void setAllSec(List<Section> allSec) {
        this.allSec = allSec;
    }

    public Service getSeviceSelected() {
        return seviceSelected;
    }

    public void setSeviceSelected(Service seviceSelected) {
        System.out.println("set");
        this.seviceSelected = seviceSelected;
    }

    public SessionLists getSessionLists() {
        return sessionLists;
    }

    public void setSessionLists(SessionLists sessionLists) {
        this.sessionLists = sessionLists;
    }
    
    
    
    
}

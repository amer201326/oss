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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
    
   
    
    List<Service> allSrvices;
    List<Department> allDep;
    List<Section> allSec;
    Service seviceSelected;
    
     @ManagedProperty(value = "#{msession}")
    Session session;
	
	
	
	

    public ServicesManager() {
        allSrvices = GetFromDB.getAllServices();
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
    public void onServiceSelect(SelectEvent event){
        seviceSelected = (Service)event.getObject();
    }
    
    public void gotToEdit(String id){
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("editService.xhtml?id="+id);
        } catch (IOException ex) {
            Logger.getLogger(DepartmentsManage.class.getName()).log(Level.SEVERE, null, ex);
        }
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

      public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
     public String urlSideBar() {
        if (session.employee != null) {
            if (session.employee.checkTypeAdmin()) {
                System.out.println("cheackAdmin is  = " + session.employee.checkTypeAdmin());
                return "../pages/sidebar.xhtml";
            }
        }
        if (session.employee != null) {
            if (session.employee.checkTypeEMP()) {

                System.out.println("cheackemp is  = " + session.employee.checkTypeEMP());

                return "../employeePages/sidebar.xhtml";
            }
        }
        return "";
    }
	
	
    
    
    
}

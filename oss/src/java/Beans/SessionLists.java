/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.Section;
import Data.SectionPath;
import Data.Service;
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
    List<Department> departments;
    List<Section> sections;
    List<Service> services;

    public SessionLists() {
        this.departments = GetFromDB.getDepartments();
        this.sections = GetFromDB.getSection();
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    
 
}

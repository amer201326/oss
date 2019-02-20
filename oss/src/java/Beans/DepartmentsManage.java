/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.GetFromDB;
import Data.Section;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
public class DepartmentsManage implements Serializable{
    
    Department department;
    @ManagedProperty(value = "#{sessionLists}")
    SessionLists sessionLists;
    
    List<String> departmentNames;
    Section sectionSelected;
    List<Section> fiterdSections ;
    
    String imageD;
    
    public DepartmentsManage() {
        department = new Department();
        sectionSelected = new Section();
        fiterdSections = GetFromDB.getSection();
    }
    
    public void addDepartment(){
        if(department.addToDataBase()){
            sessionLists.getDepartments().add(department);
        }
        
    }

    public SessionLists getSessionLists() {
        return sessionLists;
    }

    public void setSessionLists(SessionLists sessionLists) {
        this.sessionLists = sessionLists;
    }
    
    
    
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public List<String> getImageDepartment(){
        return GetFromDB.getImageDepartment();
    }

    public String getImageD() {
        return imageD;
    }

    public void setImageD(String imageD) {
        this.imageD = imageD;
    }

    public Section getSectionSelected() {
        return sectionSelected;
    }

    public void setSectionSelected(Section sectionSelected) {
        print(sectionSelected.toString());
        this.sectionSelected = sectionSelected;
    }

    public List<Section> getFiterdSections() {
        return fiterdSections;
    }

    public void setFiterdSections(List<Section> fiterdSections) {
        this.fiterdSections = fiterdSections;
    }
    
    
    public void print(String s){
        System.out.println("done: "+s);
    }

    public List<String> getDepartmentNames() {
        return departmentNames;
    }

    public void setDepartmentNames(List<String> departmentNames) {
        this.departmentNames = departmentNames;
    }
    
    public List<Section> GetFSection(){
        print("nnnnnnnnnn");
        if(department != null)
            return GetFromDB.getFsection(department.id);
        else
            return  sessionLists.sections;
    }
    
}

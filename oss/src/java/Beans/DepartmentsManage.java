/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Section;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

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
    JobTitel jobtitelSelected;
    List<JobTitel> jobtitle;
    Section newSection;
    String imageD;
    
    List<Employee> employeeList;
    Employee newEmployee;
    
    public DepartmentsManage() {
        department = new Department();
        sectionSelected = new Section();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();
        newEmployee = new Employee();
    }
    
    public void addDepartment(){
        department.addToDataBase();
         
        
    }
    
     public void addEmployee(){
        newEmployee.addEmployeeToDB();
         
        
    }
    public void addSection(){
        newSection.addToDB();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();
        employeeList = GetFromDB.getEmployees();
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
    
    public void fSection(){
        System.out.println("uuuu");
        if(department.id != -1)
       fiterdSections = GetFromDB.getFsection(department.id);
        else
            fiterdSections = GetFromDB.getSection();
    }
    
    public void jTitle(){

    }

 
    public void setJobtitle(List<JobTitel> jobtitle) {
        this.jobtitle = jobtitle;
    }

    public JobTitel getJobtitelSelected() {
        return jobtitelSelected;
    }

    public void setJobtitel(JobTitel jobtitelSelected) {
        this.jobtitelSelected = jobtitelSelected;
    }
    
    public void onSectionEdit(RowEditEvent event) {
        ((Section) event.getObject()).update();
       
    }
    
     public void onEmployeeEdit(RowEditEvent event) {
        ((Employee) event.getObject()).update();
       
    }
    
    public void onSectionCancel(RowEditEvent event) {
        
    }
    
    public void onEmployeeCancel(RowEditEvent event) {
        
    }
    
    
    public Section getNewSection() {
        return newSection;
    }

    public void setNewSection(Section newSection) {
        this.newSection = newSection;
    }
    
    public List<Department> allDepartment(){
        return GetFromDB.getDepartments();
    }
    
     public List<JobTitel> allJobs() {
        return GetFromDB.getJobTittle();
    }
     
    public List<Section> allSection(){
        return GetFromDB.getSection();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }
    
    
}

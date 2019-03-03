/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
public class EmployeeManage implements Serializable {

    List<Employee> employeeList;
    Employee newEmployee;
    
    List<String> emp;
    String[] selectedEmployees;
    List<Screen> screen;
    
    List<Section> allSections;
    List<Department> allDepartments;
    List<JobTitel> allJobs;

    public EmployeeManage() {

        newEmployee = new Employee();
        screen = GetDB_Eman.getScreens();
        allDepartments  = GetFromDB.getDepartments();
        try {
            Department d = allDepartments.get(0);
            allSections = GetFromDB.getFsection(d.getId());
            Section s = allSections.get(0);
            try {
                allJobs = GetFromDB.getJobTittle(s.getId());
            } catch (Exception e) {
               allJobs = new ArrayList<>();
            }
        } catch (Exception e) {
             allSections = new ArrayList<>();
        allJobs = new ArrayList<>();
        }
        
        
        allSections = new ArrayList<>();
        allJobs = new ArrayList<>();
    }

    public EmployeeManage(List<Employee> employeeList, Employee newEmployee, String[] selectedEmployees) {
        this.employeeList = employeeList;
        this.newEmployee = newEmployee;
        this.selectedEmployees = selectedEmployees;
    }

    public List<Department> getAllDepartments() {
        return allDepartments;
    }

    public void setAllDepartments(List<Department> allDepartments) {
        this.allDepartments = allDepartments;
    }

    
    public List<Screen> getScreen() {
        return screen;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
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

    public List<String> getEmp() {
        return emp;
    }

    public void setEmp(List<String> emp) {
        this.emp = emp;
    }

    public String[] getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(String[] selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public List<Section> getAllSections() {
        return allSections;
    }

    public void setAllSections(List<Section> allSections) {
        this.allSections = allSections;
    }

    public List<JobTitel> getAllJobs() {
        return allJobs;
    }

    public void setAllJobs(List<JobTitel> allJobs) {
        this.allJobs = allJobs;
    }
    
    
    
    public void addEmployee() {
        newEmployee.addEmployeeToDB();
        newEmployee = new Employee();

    }
    
    public void putDepartmentSelected(){
       System.out.println("+++++++++++++ + + +  "+newEmployee.getDep_id());
        allSections = GetFromDB.getFsection(newEmployee.getDep_id());
        
    }
    public void putSectionSelected(){
        System.out.println("+++++++++++++ + + +  "+newEmployee.getSec_id());
        allJobs = GetFromDB.getJobTittle(newEmployee.getSec_id()+"");
    }
}

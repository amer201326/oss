/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.Employee;
import Data.EmployeeAccount;
import Data.EmployeeScreen;
import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.JobOfSection;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import Data.ServiceAttachmentName;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class EmployeeManage implements Serializable {

    List<Employee> employeeList;
    Employee newEmployee;
    EmployeeAccount newEmployeeAccount;
    EmployeeScreen newEmpScreen;
    List<String> emp;
    String[] selectedEmployees;
    List<Screen> screen;
    DualListModel<String> screensNamesAndResaults;
    String[] selectedScreens;
    List<Section> allSections;
    List<Department> allDepartments;
    List<JobTitel> allJobs;
    List<Employee> allemployees;
    DualListModel<String> screenSel;
    List<JobOfSection> jobsOfSections;

    public EmployeeManage() {

        allDepartments = GetFromDB.getDepartments();
        allSections = GetFromDB.getSection();
       
        newEmployee = new Employee();
        
        screen = GetDB_Eman.getScreens();
        
        screensNamesAndResaults = new DualListModel<>();
        for (int i = 0; i < screen.size(); i++) {
            Screen get = screen.get(i);
            screensNamesAndResaults.getSource().add(get.getScreenName());
        }
       
        newEmployeeAccount = new EmployeeAccount();
        
        jobsOfSections = GetFromDB.getJobOfSectio();
///////
///

       
        
        newEmpScreen = new EmployeeScreen();
        

      
        
       
        
        allemployees = GetDB_Eman.getEmployee();

       
    }
    public List<JobOfSection> filterJob() {
        System.out.println("filter job");
        List<JobOfSection> list = new ArrayList<>();
        for (int i = 0; i < jobsOfSections.size(); i++) {
            JobOfSection get = jobsOfSections.get(i);
            if (newEmployee.getSec_id() == get.getIdSEction()) {
                list.add(get);
            }

        }
        return list;
    }

    public List<Section> filterSections() {
        System.out.println("filter section");
        List<Section> list = new ArrayList<>();
        for (int i = 0; i < allSections.size(); i++) {
            Section get = allSections.get(i);
            if (newEmployee.getDep_id() == Integer.parseInt(get.getDepartmentId())) {
                list.add(get);
            }

        }
        return list;
    }

    public EmployeeManage(List<Employee> employeeList, Employee newEmployee, String[] selectedEmployees) {
        this.employeeList = employeeList;
        this.newEmployee = newEmployee;
        this.selectedEmployees = selectedEmployees;
    }

    public List<Employee> getAllemployees() {
        System.out.println("fffffffffffff");
        System.out.println(allemployees.size());
        return allemployees;
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

    public EmployeeAccount getNewEmployeeAccount() {
        return newEmployeeAccount;
    }

    public void setNewEmployeeAccount(EmployeeAccount newEmployeeAccount) {
        this.newEmployeeAccount = newEmployeeAccount;
    }

    public EmployeeScreen getNewEmpScreen() {
        return newEmpScreen;
    }

    public void setNewEmpScreen(EmployeeScreen newEmpScreen) {
        this.newEmpScreen = newEmpScreen;
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

    public List<Section> allSections() {
        return GetFromDB.getSection();
    }

    public void setAllSections(List<Section> allSections) {
        this.allSections = allSections;
    }

    public List<JobTitel> allJobs() {
        return GetFromDB.getJobTittle();
    }

    public void setAllJobs(List<JobTitel> allJobs) {
        this.allJobs = allJobs;
    }

    public void addEmployee() {
      int id = GetFromDB.getMaxIDEmployee();
        
        
        newEmployee.setEmp_id(id+1);
              
        newEmployee.addEmployeeToDB();
        List<Screen> scre = new ArrayList<>();
        for (int i = 0; i < screensNamesAndResaults.getTarget().size(); i++) {
            String get = screensNamesAndResaults.getTarget().get(i);
            for (int j = 0; j < screen.size(); j++) {
                Screen get1 = screen.get(j);
                if(get.equals(get1.getScreenName())){
                    scre.add(get1);
                }
            }
            
        }
        newEmployee.setScreens(screen);
        

        newEmployee = new Employee();
        newEmployeeAccount = new EmployeeAccount();
        newEmpScreen = new EmployeeScreen();
    }

    public void putDepartmentSelected() {
        System.out.println("+++++++++++++ + + +  " + newEmployee.getDep_id());
        allSections = GetFromDB.getFsection(newEmployee.getDep_id());

    }

    public void putSectionSelected() {
        System.out.println("+++++++++++++ + + +  " + newEmployee.getSec_id());
        allJobs = GetFromDB.getJobTittle(newEmployee.getSec_id() + "");
    }

    public void filterEmployee() {
        if (newEmployee.getDep_id() != -1) {
            allemployees = GetDB_Eman.getFEmployee(newEmployee.getDep_id());
        } else {
            allemployees = GetDB_Eman.getTableEmployee();
        }

    }

    public String[] getSelectedScreens() {
        return selectedScreens;
    }

    public void setSelectedScreens(String[] selectedScreens) {
        this.selectedScreens = selectedScreens;
    }

    public List<Department> getAllDepartments() {
        return allDepartments;
    }

    public void setAllDepartments(List<Department> allDepartments) {
        this.allDepartments = allDepartments;
    }

    public DualListModel<String> getScreenSel() {
        return screenSel;
    }

    public void setScreenSel(DualListModel<String> screenSel) {
        this.screenSel = screenSel;
    }

    public DualListModel<String> getScreensNamesAndResaults() {
        return screensNamesAndResaults;
    }

    public void setScreensNamesAndResaults(DualListModel<String> screensNamesAndResaults) {
        this.screensNamesAndResaults = screensNamesAndResaults;
    }

    public List<JobOfSection> getJobsOfSections() {
        return jobsOfSections;
    }

    public void setJobsOfSections(List<JobOfSection> jobsOfSections) {
        this.jobsOfSections = jobsOfSections;
    }
    

}

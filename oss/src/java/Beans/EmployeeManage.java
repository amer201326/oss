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
import org.primefaces.model.DualListModel;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
public class EmployeeManage implements Serializable {

    List<Employee> employeeList;
    Employee newEmployee;
    EmployeeAccount newEmployeeAccount;
    EmployeeScreen newEmpScreen;
    List<String> emp;
    String[] selectedEmployees;
    List<Screen> screen;
    String[] selectedScreens;
    List<Section> allSections;
    List<Department> allDepartments;
    List<JobTitel> allJobs;
    List<Employee> allemployees;
    DualListModel<String> screenSel;

    public EmployeeManage() {

        newEmployee = new Employee();
        newEmployeeAccount = new EmployeeAccount();
        newEmpScreen = new EmployeeScreen();
        screen = GetDB_Eman.getScreens();
        List<String> scre = new ArrayList<>();
        for (int i = 0; i < screen.size(); i++) {
            Screen get = screen.get(i);
            scre.add(get.getScreenName());
        }
        allDepartments = GetFromDB.getDepartments();
        allemployees = new ArrayList<Employee>();
        List<String> screenTarget = new ArrayList<String>();
        screenSel =  new DualListModel<String>(scre, screenTarget);
        allSections = new ArrayList<Section>();
        allemployees = GetDB_Eman.getEmployee();
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

    public List<Department> allDepartments() {
        return GetFromDB.getDepartments();
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
        newEmployee.setEmp_id((int) System.currentTimeMillis());

        newEmployee.addEmployeeToDB();

        newEmployeeAccount.Emp_ID = newEmployee.getEmp_id();

        newEmployeeAccount.addEmpAccountToDB();
        newEmpScreen.setEmp_ID(newEmployee.getEmp_id());

        newEmpScreen.addEmpScreenToDB();

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

  
}

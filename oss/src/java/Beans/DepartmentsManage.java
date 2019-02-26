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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
public class DepartmentsManage implements Serializable {

    Department department;
    @ManagedProperty(value = "#{sessionLists}")
    SessionLists sessionLists;

    List<String> departmentNames;
    Section sectionSelected;
    List<Section> fiterdSections;
    List<JobTitel> jobTitels;
    
    Section newSection;
    String imageD;

    JobTitel newJob;

    List<Employee> employeeList;
    Employee newEmployee;
    
    List<Screen> screen;
    Screen newScreen;
    String[] selectedScreens;

    public JobTitel j;
    
    Department departmentEdit;
    

    public DepartmentsManage() {

        department = new Department();
        sectionSelected = new Section();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();
        newEmployee = new Employee();
        
        newJob = new JobTitel();
        jobTitels = GetFromDB.getJobTittle();
        departmentEdit = new Department();
        j = new JobTitel();
        
        
    }

    public void addDepartment() {
        department.addToDataBase();
        department = new Department();

    }

    public void addEmployee() {
        newEmployee.addEmployeeToDB();
        newEmployee = new Employee();

    }

    public void addJobTitle() {
        newJob.addJobToDB();
        newJob = new JobTitel();
        jobTitels = GetFromDB.getJobTittle();

    }

    public void showDepartment(Department d) throws IOException{
        System.out.println("go");
        sessionLists.departmentSelected = d;
        FacesContext.getCurrentInstance().getExternalContext().redirect("department.xhtml");
    }
    
    public JobTitel getNewJob() {
        return newJob;
    }

    public void setNewJob(JobTitel newJob) {
        this.newJob = newJob;
    }

    public void addSection() {
        newSection.addToDB();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();

    }
    public void addSection(String id) {
        newSection.setDepartmentId(id);
        newSection.addToDB();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();

    }

    public void deleteSection() {
        sectionSelected.deleteFromDB();
        fiterdSections.remove(sectionSelected);
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

    public List<String> getImageDepartment() {
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

    public void print(String s) {
        System.out.println("done: " + s);
    }

    public List<String> getDepartmentNames() {
        return departmentNames;
    }

    public void setDepartmentNames(List<String> departmentNames) {
        this.departmentNames = departmentNames;
    }

    public void fSection() {
        System.out.println("uuuu");
        if (department.id != -1) {
            fiterdSections = GetFromDB.getFsection(department.id);
        } else {
            fiterdSections = GetFromDB.getSection();
        }
    }

    public void onSectionEdit(RowEditEvent event) {
        ((Section) event.getObject()).update();

    }

    public void onJopTitleEdit(RowEditEvent event) {

        ((JobTitel) event.getObject()).update();

    }

    public void onEmployeeEdit(RowEditEvent event) {
        ((Employee) event.getObject()).update();

    }

    public void onSectionCancel(RowEditEvent event) {

    }

    public void onEmployeeCancel(RowEditEvent event) {

    }

    public void onSectionSelected(SelectEvent event) {
        System.out.println("form al ajax " + ((Section) event.getObject()).getId());
        sectionSelected = (Section) event.getObject();
    }

    public void onJobSelected(SelectEvent event) {
        System.out.println("form al ajax " + ((JobTitel) event.getObject()).getId());
        j = (JobTitel) event.getObject();
    }

    public Section getNewSection() {
        return newSection;
    }

    public void setNewSection(Section newSection) {
        this.newSection = newSection;
    }

    public List<Department> allDepartment() {
        return GetFromDB.getDepartments();
    }

    public List<JobTitel> allJobs() {

        return GetFromDB.getJobTittle();
    }

    public List<Section> allSection() {
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

    public void setJobTitels(List<JobTitel> jobTitels) {
        this.jobTitels = jobTitels;

    }

    public void deleteJopTitle() {
        j.delete();
        jobTitels.remove(j);
    }

    public List<JobTitel> getJobTitels() {
        System.out.println("get method");
        return jobTitels;
    }

    public JobTitel getJ() {
        return j;
    }

    public void setJ(JobTitel j) {
        this.j = j;
    }

    public Department getDepartmentEdit() {
        return departmentEdit;
    }

    public void setDepartmentEdit(Department departmentEdit) {
        this.departmentEdit = departmentEdit;
    }

    public List<Screen> getScreen() {
        return screen;
    }

    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }

    public Screen getNewScreen() {
        return newScreen;
    }

    public void setNewScreen(Screen newScreen) {
        this.newScreen = newScreen;
    }
    
    
    
}

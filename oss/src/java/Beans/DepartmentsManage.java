/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DB.DB;
import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import Data.ServiceCount;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    List<ServiceCount> servicesCount;
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
  

    List<Screen> screen;
    Screen newScreen;
    String[] selectedScreens;

    int[] allParameter;

    Integer[] servicePerMonth;
    public JobTitel j;

    Department departmentEdit;

    public DepartmentsManage() {

        servicesCount = GetFromDB.getMore5ServiceRequest();
        department = new Department();
        sectionSelected = new Section();
        fiterdSections = GetFromDB.getSection();
        newSection = new Section();
        
        servicePerMonth = GetFromDB.getNumberOfServicePerMonth();
        newJob = new JobTitel();
        jobTitels = GetFromDB.getJobTittle();
        departmentEdit = new Department();
        j = new JobTitel();

        allParameter = GetFromDB.getALLNumber();
    }

    public void addDepartment() {
        department.addToDataBase();
        department = new Department();

    }

    

    public void addJobTitle() {
        newJob.addJobToDB();
        newJob = new JobTitel();
        jobTitels = GetFromDB.getJobTittle();

    }

    public void showDepartment(Department d) throws IOException {
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
        sessionLists.sectionSelected.deleteFromDB();
        fiterdSections.remove(sessionLists.sectionSelected);
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
        sessionLists.sectionSelected = (Section) event.getObject();
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

    public String[] getSelectedScreens() {
        return selectedScreens;
    }

    public void setSelectedScreens(String[] selectedScreens) {
        this.selectedScreens = selectedScreens;
    }

    //////////////////////
    public double costOfThisMonth() {
        return GetFromDB.getCostOfThisMonth();
    }

    public Integer[] numberOfServicePerMonth() {

        return GetFromDB.getNumberOfServicePerMonth();
    }

    public int[] getAllParameter() {
        return allParameter;
    }

    public void setAllParameter(int[] allParameter) {
        this.allParameter = allParameter;
    }

    public List<ServiceCount> getServicesCount() {
        return servicesCount;
    }

    public void setServicesCount(List<ServiceCount> servicesCount) {
        this.servicesCount = servicesCount;
    }

    public Integer[] getServicePerMonth() {
        return servicePerMonth;
    }

    public void setServicePerMonth(Integer[] servicePerMonth) {
        this.servicePerMonth = servicePerMonth;
    }

    public void putSectionSelectedToSession(Section s) {
        System.out.println(s.getName());
    }

    public void gotToSection() {
        sessionLists.sectionSelected = sectionSelected;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>.........>>>>>>>>>>>>>>>>>>>>>>>>> > > >  " + sectionSelected.getName());
        System.out.println(sessionLists.sectionSelected.getId());
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("section.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DepartmentsManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.JobOfSection;
import Data.JobPath;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import Data.ServiceAttachmentName;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class EditServiceManager {

    Boolean boolSection = false;
    Boolean boolJob = false;
    boolean pleaseSelectDepartment = true;
    boolean pleaseSelectsection = true;
    List<ServiceAttachmentName> attachmentNames;
    List<Department> departments;
    List<DepartmentPaths> departmentsInPath = new ArrayList<DepartmentPaths>();
    //List<SectionPath> sectionsInPath = new ArrayList<SectionPath>();
    SectionPath sectionPath_new;
    SectionPath selectSectionPath;
    //List<JobPath> jobInPath = new ArrayList<JobPath>();
    Service newService;
    List<Section> sections;
    List<JobTitel> jobs;
    DepartmentPaths selectDepartmentPath;
    JobPath selectedJobPath;
    DepartmentPaths departmentPaths;
    JobPath jobPath;

    List<JobOfSection> jobsOfSections;
    List<JobOfSection> filterJobsOfSections;

    DualListModel<String> attachmentNamesAndResaults;
    @ManagedProperty(value = "#{sessionLists}")
    SessionLists sessionLists;
    
    public EditServiceManager() {
        attachmentNames = GetFromDB.getServiceAttachmentName();
        attachmentNamesAndResaults = new DualListModel<>();
        for (int i = 0; i < attachmentNames.size(); i++) {
            ServiceAttachmentName get = attachmentNames.get(i);
            attachmentNamesAndResaults.getSource().add(get.getName());
        }

        departments = GetFromDB.getDepartments();
        sections = GetFromDB.getSection();
        jobsOfSections = GetFromDB.getJobOfSectio();
        sectionPath_new = new SectionPath();
        selectSectionPath = new SectionPath();
        newService = new Service();
        selectDepartmentPath = new DepartmentPaths();
        departmentPaths = new DepartmentPaths();
        jobPath = new JobPath();
        selectedJobPath = new JobPath();
    }
    @PostConstruct
    public void init() {
       newService = sessionLists.selectedService;
       departmentsInPath = newService.getPath();
       
        
    }

    public DepartmentPaths getDepartmentPaths() {
        return departmentPaths;
    }

    public void setDepartmentPaths(DepartmentPaths departmentPaths) {
        this.departmentPaths = departmentPaths;
    }

    public void addDepartmentToPath() {
        for (int i = 0; i < departments.size(); i++) {
            Department get = departments.get(i);
            if (departmentPaths.id == get.id) {
                departmentPaths.nameA = get.nameA;
                break;
            }
        }
        departmentsInPath.add(departmentPaths);
        System.out.println("Beans.ServiceManager.addPathDepartment()" + departmentsInPath.size());
        departmentPaths = new DepartmentPaths();
    }

    public void addSectionToPath() {
        for (int i = 0; i < sections.size(); i++) {
            Section get = sections.get(i);
            if (sectionPath_new.getId() == Integer.parseInt(get.getId())) {
                sectionPath_new.setName(get.getName());
                sectionPath_new.setDepartmentId(Integer.parseInt(get.getDepartmentId()));
                break;
            }

        }
        
        selectDepartmentPath.getSections().add(sectionPath_new);
        sectionPath_new = new SectionPath();

    }

    public void addJobToPath() {
        for (int i = 0; i < jobsOfSections.size(); i++) {
            JobOfSection get = jobsOfSections.get(i);
            if (jobPath.getId() == get.getIdJob()) {
                jobPath.setName(get.getName());
                jobPath.setDepId(selectDepartmentPath.id);
                jobPath.setSectionID(selectSectionPath.getId());
                jobPath.idMarge();
                break;
            }

        }
        System.out.println(jobPath.toString());
        
        selectSectionPath.getJobs().add(jobPath);
        jobPath = new JobPath();
    }

    public void addService() {
        
        List<ServiceAttachmentName> l = new ArrayList<>();
        for (int i = 0; i < attachmentNamesAndResaults.getTarget().size(); i++) {
            String get = attachmentNamesAndResaults.getTarget().get(i);
            for (int j = 0; j < attachmentNames.size(); j++) {
                ServiceAttachmentName get1 = attachmentNames.get(j);
                if(get.equals(get1.getName())){
                    l.add(get1);
                }
                
            }
        }
        newService.setAttachmentNames(l);
        newService.setPath(departmentsInPath);
        newService.addServiceToDB();

    }

    public List<Section> filterSections() {
        List<Section> list = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            Section get = sections.get(i);
            if (selectDepartmentPath.id == Integer.parseInt(get.getDepartmentId())) {
                list.add(get);
            }

        }
        return list;
    }

    public List<Section> filterSectionsForSer() {
        List<Section> list = new ArrayList<>();
        for (int i = 0; i < sections.size(); i++) {
            Section get = sections.get(i);
            if (newService.getDepartment().id == Integer.parseInt(get.getDepartmentId())) {
                list.add(get);
            }

        }
        return list;
    }

    public List<JobOfSection> filterJop() {
        List<JobOfSection> list = new ArrayList<>();
        for (int i = 0; i < jobsOfSections.size(); i++) {
            JobOfSection get = jobsOfSections.get(i);
            if (selectSectionPath.getId() == get.getIdSEction()) {
                list.add(get);
            }

        }
        return list;
    }

    public List<SectionPath> filterSectionPath() {
        List<SectionPath> list = new ArrayList<>();
        for (int i = 0; i < selectDepartmentPath.getSections().size(); i++) {
            SectionPath get = selectDepartmentPath.getSections().get(i);
            if (selectDepartmentPath.id == get.getDepartmentId()) {
                list.add(get);
            }

        }
        return list;
    }

    public List<JobPath> filterJopPath() {
        List<JobPath> list = new ArrayList<>();
        for (int i = 0; i < selectSectionPath.getJobs().size(); i++) {
            JobPath get = selectSectionPath.getJobs().get(i);

            System.out.println(get + "   " + selectSectionPath);
            if (selectSectionPath.getId() == get.getSectionID()) {
                list.add(get);
            }

        }
        return list;
    }

    public void onRowSelectFromDepartment(SelectEvent event) {
        System.out.println((DepartmentPaths) event.getObject());
        boolSection = true;
        pleaseSelectDepartment = false;
        boolJob = false;
        pleaseSelectsection = true;

        selectSectionPath = new SectionPath();
        selectedJobPath = new JobPath();

    }

    public void onRowUnselectFromDepartment(UnselectEvent event) {
        boolSection = false;
        pleaseSelectDepartment = true;
    }

    public void onRowSelectFromSection(SelectEvent event) {
        System.out.println(selectSectionPath.getName());
        boolJob = true;
        pleaseSelectsection = false;

    }

    public void onRowUnselectFromSection(UnselectEvent event) {
        boolJob = false;
        pleaseSelectsection = true;
    }

    public void onRowSelectFromJob(SelectEvent event) {

    }

    public void onRowUnselectFromJob(UnselectEvent event) {

    }

    public void deleteJobPath() {
        System.out.println("delete job");
        System.out.println(selectedJobPath);
        
        selectSectionPath.getJobs().remove(selectedJobPath);
        selectedJobPath = new JobPath();
    }

    public void deleteSelectedDepartmentPath() {
        System.out.println("delete department");
        System.out.println(selectedJobPath);

        departmentsInPath.remove(selectDepartmentPath);
        
        boolSection = false;
        pleaseSelectDepartment = true;
        boolJob = false;
        pleaseSelectsection = true;

        selectDepartmentPath = new DepartmentPaths();
        selectSectionPath = new SectionPath();
        selectedJobPath = new JobPath();
    }

    public void deleteSelectedSectionPath() {
        System.out.println("delete section");
        System.out.println(selectedJobPath);

        selectDepartmentPath.getSections().remove(selectSectionPath);
        

        boolJob = false;
        pleaseSelectsection = true;

        selectSectionPath = new SectionPath();
        selectedJobPath = new JobPath();
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public SectionPath getSectionPath_new() {
        return sectionPath_new;
    }

    public void setSectionPath_new(SectionPath sectionPath_new) {
        this.sectionPath_new = sectionPath_new;
    }

    public SectionPath getSelectSectionPath() {
        return selectSectionPath;
    }

    public void setSelectSectionPath(SectionPath selectSectionPath) {
        this.selectSectionPath = selectSectionPath;
    }

    public JobPath getJobPath() {
        return jobPath;
    }

    public void setJobPath(JobPath jobPath) {
        this.jobPath = jobPath;
    }

    public List<JobTitel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobTitel> jobs) {
        this.jobs = jobs;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Boolean getBoolSection() {
        return boolSection;
    }

    public void setBoolSection(Boolean boolSection) {
        this.boolSection = boolSection;
    }

    public Boolean getBoolJob() {
        return boolJob;
    }

    public void setBoolJob(Boolean boolJob) {
        this.boolJob = boolJob;
    }

    public boolean isPleaseSelectDepartment() {
        return pleaseSelectDepartment;
    }

    public void setPleaseSelectDepartment(boolean pleaseSelectDepartment) {
        this.pleaseSelectDepartment = pleaseSelectDepartment;
    }

    public boolean isPleaseSelectsection() {
        return pleaseSelectsection;
    }

    public void setPleaseSelectsection(boolean pleaseSelectsection) {
        this.pleaseSelectsection = pleaseSelectsection;
    }

    public List<DepartmentPaths> getDepartmentsInPath() {
        return departmentsInPath;
    }

    public void setDepartmentsInPath(List<DepartmentPaths> departmentsInPath) {
        this.departmentsInPath = departmentsInPath;
    }

  

    public DepartmentPaths getSelectDepartmentPath() {
        return selectDepartmentPath;
    }

    public void setSelectDepartmentPath(DepartmentPaths selectDepartmentPath) {
        this.selectDepartmentPath = selectDepartmentPath;
    }

    public Service getNewService() {
        return newService;
    }

    public void setNewService(Service newService) {
        this.newService = newService;
    }

    public List<JobOfSection> getJobsOfSections() {
        return jobsOfSections;
    }

    public void setJobsOfSections(List<JobOfSection> jobsOfSections) {
        this.jobsOfSections = jobsOfSections;
    }

    public List<JobOfSection> getFilterJobsOfSections() {
        return filterJop();
    }

    public void setFilterJobsOfSections(List<JobOfSection> filterJobsOfSections) {
        this.filterJobsOfSections = filterJobsOfSections;
    }

    public JobPath getSelectedJobPath() {
        return selectedJobPath;
    }

    public void setSelectedJobPath(JobPath selectedJobPath) {
        this.selectedJobPath = selectedJobPath;
    }

    public List<ServiceAttachmentName> getAttachmentNames() {
        return attachmentNames;
    }

    public void setAttachmentNames(List<ServiceAttachmentName> attachmentNames) {
        this.attachmentNames = attachmentNames;
    }

    public DualListModel<String> getAttachmentNamesAndResaults() {
        return attachmentNamesAndResaults;
    }

    public void setAttachmentNamesAndResaults(DualListModel<String> attachmentNamesAndResaults) {
        this.attachmentNamesAndResaults = attachmentNamesAndResaults;
    }

    public SessionLists getSessionLists() {
        return sessionLists;
    }

    public void setSessionLists(SessionLists sessionLists) {
        this.sessionLists = sessionLists;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Department;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.HaveServiceAttachment;
import Data.JobOfSection;
import Data.JobPath;
import Data.JobTitel;
import Data.Screen;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import Data.ServiceAttachmentName;
import Data.ViewerAttachment;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class AddServiceManager implements Serializable {

    Boolean boolSection = false;
    Boolean boolJob = false;
    boolean pleaseSelectDepartment = true;
    boolean pleaseSelectsection = true;
    List<ServiceAttachmentName> attachmentNames;
    List<Department> departments;
    List<DepartmentPaths> departmentsInPath = new ArrayList<DepartmentPaths>();
    SectionPath sectionPath_new;
    SectionPath selectSectionPath;
    Service newService;
    List<Section> sections;
    List<JobTitel> jobs;
    DepartmentPaths selectDepartmentPath;
    JobPath selectedJobPath;
    DepartmentPaths departmentPaths;
    JobPath jobPath;

    List<JobOfSection> jobsOfSections;
    List<JobOfSection> filterJobsOfSections;

    HaveServiceAttachment haveServiceAttachment;
    HaveServiceAttachment selectHavServAttachment;

    List<ServiceAttachmentName> Newattachment = new ArrayList<ServiceAttachmentName>();
    List<ServiceAttachmentName> allattachment;

    List<JobPath> stringToJop;
    ServiceAttachmentName selectAttachment;
    HaveServiceAttachment newSelectAttachment;

    List<SelectItem> viewerJob;
    private String[] selectedAtts;

    public AddServiceManager() {

         newService = new Service();
        departments = GetFromDB.getDepartments();
        sections = GetFromDB.getSection();
        jobsOfSections = GetFromDB.getJobOfSectio();
        sectionPath_new = new SectionPath();
        selectSectionPath = new SectionPath();
        selectDepartmentPath = new DepartmentPaths();
        
        newSelectAttachment = new HaveServiceAttachment();
        haveServiceAttachment = new HaveServiceAttachment();
        selectHavServAttachment = new HaveServiceAttachment();
        selectDepartmentPath = new DepartmentPaths();
        departmentPaths = new DepartmentPaths();
        jobPath = new JobPath();
        selectedJobPath = new JobPath();
        allattachment = GetFromDB.getServiceAttachmentNamewhithoutfile();
        stringToJop = new ArrayList<>();
        viewerJob = new ArrayList<SelectItem>();

        Newattachment = new ArrayList<ServiceAttachmentName>();

                
    }

    public String[] getSelectedAtts() {
        return selectedAtts;
    }

    public void setSelectedAtts(String[] selectedAtts) {
        this.selectedAtts = selectedAtts;
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
        boolean isexist = false;
        for (DepartmentPaths departmentPaths1 : departmentsInPath) {
            if (departmentPaths1.id == departmentPaths.id && departmentPaths1.order == departmentPaths.order) {
                isexist = true;
            }

        }
        if (!isexist) {
            departmentsInPath.add(departmentPaths);
        } else {

        }
        departmentPaths = new DepartmentPaths();
    }

    public void addSectionToPath() {
        for (int i = 0; i < sections.size(); i++) {
            Section get = sections.get(i);
            if (sectionPath_new.getId() == Integer.parseInt(get.getId())) {
                sectionPath_new.setName(get.getName());
                sectionPath_new.setDepartmentId(selectDepartmentPath.id);
                sectionPath_new.setOrderDepartment(selectDepartmentPath.order);
                break;
            }

        }
        boolean isexist = false;
        for (SectionPath sectionPath : selectDepartmentPath.getSections()) {
            if (sectionPath.getDepartmentId() == sectionPath_new.getDepartmentId()
                    && sectionPath.getOrderDepartment() == sectionPath_new.getOrderDepartment()
                    && sectionPath.getId() == sectionPath_new.getId()
                    && sectionPath.getOrder() == sectionPath_new.getOrder()) {
                isexist = true;
            }

        }
        if (!isexist) {
            System.out.println("not exist");
            selectDepartmentPath.getSections().add(sectionPath_new);
        } else {
            System.out.println(" exist");
        }

        sectionPath_new = new SectionPath();

    }

    public void addJobToPath() {
        for (int i = 0; i < jobsOfSections.size(); i++) {
            JobOfSection get = jobsOfSections.get(i);
            if (jobPath.getId() == get.getIdJob()) {
                jobPath.setName(get.getName());
                jobPath.setDepId(selectDepartmentPath.id);
                jobPath.setdOrder(selectDepartmentPath.order);
                jobPath.setSectionID(selectSectionPath.getId());
                jobPath.setsOrder(selectSectionPath.getOrder());
                jobPath.idMarge();
                break;
            }

        }

        boolean isexist = false;
        for (JobPath job : selectSectionPath.getJobs()) {
            if (job.getDepId() == jobPath.getDepId() && jobPath.getdOrder() == job.getdOrder()
                    && jobPath.getSectionID() == job.getSectionID()
                    && jobPath.getsOrder() == job.getsOrder()
                    && jobPath.getId() == job.getId()
                    && jobPath.getOrder() == job.getdOrder()) {
                isexist = true;
            }
        }
        if (!isexist) {
            selectSectionPath.getJobs().add(jobPath);
        } else {

        }

        jobPath = new JobPath();
    }

    public void addService() {

        try {
            newService.setPath(departmentsInPath);
            newService.addServiceToDB();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../managerService/allServices.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(AddServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        return selectDepartmentPath.sections;
    }

    public List<JobPath> filterJopPath() {

        return selectSectionPath.jobs;
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

    public void onRowSelectFromAtt(SelectEvent event) {
        selectHavServAttachment = (HaveServiceAttachment)event.getObject();

    }

    public void onRowUnselectFromAtt(UnselectEvent event) {

    }

    public void deleteSelectedAtt() {
      
        System.out.println("delete Att");
        System.out.println(selectHavServAttachment);
        
        newService.getHaveServiceAttachments().remove(selectHavServAttachment);

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

    public HaveServiceAttachment getHaveServiceAttachment() {
        return haveServiceAttachment;
    }

    public void setHaveServiceAttachment(HaveServiceAttachment haveServiceAttachment) {
        this.haveServiceAttachment = haveServiceAttachment;
    }

    public List<ServiceAttachmentName> getNewattachment() {
        return Newattachment;
    }

    public void setNewattachment(List<ServiceAttachmentName> Newattachment) {
        this.Newattachment = Newattachment;
    }

    public List<ServiceAttachmentName> getAllattachment() {
        return allattachment;
    }

    public void setAllattachment(List<ServiceAttachmentName> allattachment) {
        this.allattachment = allattachment;
    }

    public void onSelect(SelectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Echoes in eternity.");
        PrimeFaces.current().dialog().showMessageDynamic(message);

    }

    public List<JobPath> getStringToJop() {
        return stringToJop;
    }

    public void setStringToJop(List<JobPath> stringToJop) {
        this.stringToJop = stringToJop;
    }

    public ServiceAttachmentName getSelectAttachment() {
        return selectAttachment;
    }

    public void setSelectAttachment(ServiceAttachmentName selectAttachment) {
        this.selectAttachment = selectAttachment;
    }

    public HaveServiceAttachment getSelectHavServAttachment() {
        return selectHavServAttachment;
    }

    public void setSelectHavServAttachment(HaveServiceAttachment selectHavServAttachment) {
        this.selectHavServAttachment = selectHavServAttachment;
    }

    public HaveServiceAttachment getNewSelectAttachment() {
        return newSelectAttachment;
    }

    public void setNewSelectAttachment(HaveServiceAttachment newSelectAttachment) {
        this.newSelectAttachment = newSelectAttachment;
    }

   

    public List<SelectItem> jobShowThisService() {
//
//        List<JobPath> job = new ArrayList<JobPath>();
        List<SelectItem> ds = new ArrayList<SelectItem>();

        System.out.println("selecttttt");
        if (!departmentsInPath.isEmpty()) {
            for (DepartmentPaths d : departmentsInPath) {
                if (d.sections.isEmpty()) {

                    for (Section section : sections) {

                        if (section.getDepartmentId().compareTo(d.id + "") == 0) {
                            SelectItemGroup sec = new SelectItemGroup(section.getName());
                            List<JobPath> jobs = new ArrayList<>();
                            for (JobOfSection jobsOfSection : jobsOfSections) {
                                if (jobsOfSection.getIdSEction() == Integer.parseInt(section.getId())) {
                                    jobs.add(new JobPath(d.id, Integer.parseInt(section.getId()),
                                            jobsOfSection.getIdJob(), jobsOfSection.getName()));

                                }
                            }
                            SelectItem[] jobarray = new SelectItem[jobs.size()];
                            for (int i = 0; i < jobs.size(); i++) {
                                JobPath get = jobs.get(i);
                                jobarray[i] = new SelectItem(get.kes(), get.getName(), d.nameA);

                            }

                            sec.setSelectItems(jobarray);
                            ds.add(sec);
                        }
                    }

                } else {
                    for (SectionPath section : d.sections) {
                        if (section.jobs.isEmpty()) {
                            SelectItemGroup sec = new SelectItemGroup(section.getName());
                            List<JobPath> jobs = new ArrayList<>();
                            for (JobOfSection jobsOfSection : jobsOfSections) {
                                if (jobsOfSection.getIdSEction() == section.getId()) {

                                    jobs.add(new JobPath(d.id, section.getId(), jobsOfSection.getIdJob(), jobsOfSection.getName()));

                                }
                            }
                            SelectItem[] jobarray = new SelectItem[jobs.size()];
                            for (int i = 0; i < jobs.size(); i++) {
                                JobPath get = jobs.get(i);
                                jobarray[i] = new SelectItem(get.kes(), get.getName(), d.nameA);

                            }

                            sec.setSelectItems(jobarray);
                            ds.add(sec);
                        } else {
                            SelectItemGroup sec = new SelectItemGroup(section.getName());
                            SelectItem[] jobarray = new SelectItem[section.jobs.size()];

                            for (int i = 0; i < section.jobs.size(); i++) {
                                JobPath get = section.jobs.get(i);
                                jobarray[i] = new SelectItem(get.kes(), get.getName(), d.nameA);

                            }

                            sec.setSelectItems(jobarray);
                            ds.add(sec);
                        }
                    }
                }

            }
        }
        viewerJob = ds;
        return ds;

    }

    public void addJobViewerToServise() {
        
               
        for (int i = 0; i < selectedAtts.length; i++) {

            System.out.println("-- here job --" + selectedAtts[i]);
            String[] split = selectedAtts[i].split("-");
            JobPath j = new JobPath(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), "");
            newSelectAttachment.getJobs().add(j);

        }
        for (ServiceAttachmentName nn : allattachment) {
            if(nn.getId() == newSelectAttachment.getServiceAttachmentName_ID()){
                newSelectAttachment.setName(nn.getName());
                break;
            }
        }
        newService.getHaveServiceAttachments().add(newSelectAttachment);
        newSelectAttachment = new HaveServiceAttachment();

    }

    public List<SelectItem> getViewerJob() {
        return viewerJob;
    }

    public void setViewerJob(List<SelectItem> viewerJob) {
        this.viewerJob = viewerJob;
    }

}

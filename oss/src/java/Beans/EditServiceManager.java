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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class EditServiceManager implements Serializable{

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

    public EditServiceManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");
        System.out.println(id);
        attachmentNames = GetFromDB.getServiceAttachmentName();
        List<Integer> attachmentIds = GetFromDB.getAttavhmentForserviceById(id);
        attachmentNamesAndResaults = new DualListModel<>();

        for (int i = 0; i < attachmentNames.size(); i++) {
            ServiceAttachmentName get = attachmentNames.get(i);
            
            boolean b = false;
            for (int j = 0; j < attachmentIds.size(); j++) {
                Integer idat = attachmentIds.get(j);
                if (get.getId() == idat) {
                    
                    attachmentNamesAndResaults.getTarget().add(get.getName());
                    b = true;
                }

            }
            if (!b) {
                attachmentNamesAndResaults.getSource().add(get.getName());
            }
        }

        departments = GetFromDB.getDepartments();
        sections = GetFromDB.getSection();
        jobsOfSections = GetFromDB.getJobOfSectio();
        sectionPath_new = new SectionPath();
        selectSectionPath = new SectionPath();
        newService = GetFromDB.getServiceByID(id);
        selectDepartmentPath = new DepartmentPaths();
        departmentPaths = new DepartmentPaths();
        jobPath = new JobPath();
        selectedJobPath = new JobPath();
        departmentsInPath = getPathASDep();
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
        

        selectSectionPath.getJobs().add(jobPath);
        jobPath = new JobPath();
    }

    public void editService() {

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
        
        newService.update();

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

                        if (selectSectionPath.getId() == get.getSectionID()) {
                list.add(get);
            }

        }
        return list;
    }

    public void onRowSelectFromDepartment(SelectEvent event) {
        
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

    private List<DepartmentPaths> getPathASDep() {
        List<DepartmentPaths> debp = new ArrayList<>();
        List<JobPath> jobs = GetFromDB.getPahtForService(newService.getId());
        for (int i = 0; i < jobs.size(); i++) {
            JobPath jp = jobs.get(i);
            boolean dbol = false;
            for (int j = 0; j < debp.size(); j++) {
                DepartmentPaths d = debp.get(j);
                if (d.id == jp.getDepId() && d.order == jp.getdOrder()) {
                    dbol = true;
                    boolean sbol = false;
                    for (int k = 0; k < d.sections.size(); k++) {
                        SectionPath sec = d.sections.get(k);
                        if (sec.getId() == jp.getSectionID() && sec.getOrder() == jp.getsOrder()) {
                            sbol = true;
                            JobPath jpath = new JobPath(d.id, sec.getId(), jp.getId(), d.order, sec.getOrder(), jp.getOrder());
                            jpath.setName(getJobName(jp.getId()));
                            sec.getJobs().add(jpath);

                            break;
                        }
                    }
                    if (!sbol) {

                        SectionPath spath = new SectionPath();
                        spath.setId(jp.getSectionID());
                        spath.setOrder(jp.getsOrder());
                        spath.setName(getnameForSection(spath.getId()));
                        spath.setDepartmentId( d.id);
                        JobPath jpath = new JobPath(d.id, spath.getId(), jp.getId(), d.order, spath.getOrder(), jp.getOrder());
                        jpath.setName(getJobName(jp.getId()));
                        spath.getJobs().add(jpath);
                        d.sections.add(spath);

                    }
                    break;
                }
            }
            if (!dbol) {
                DepartmentPaths dpath = new DepartmentPaths();
                dpath.id = jp.getDepId();
                dpath.order = jp.getdOrder();
                dpath.nameA = getNameDep(dpath.id);
                SectionPath spath = new SectionPath();
                spath.setId(jp.getSectionID());
                spath.setOrder(jp.getsOrder());
                spath.setName(getnameForSection(spath.getId()));
                spath.setDepartmentId( dpath.id);
                JobPath jpath = new JobPath(dpath.id, spath.getId(), jp.getId(), dpath.order, spath.getOrder(), jp.getOrder());
                jpath.setName(getJobName(jp.getId()));
                spath.getJobs().add(jpath);
                dpath.sections.add(spath);
                debp.add(dpath);
                System.out.println(dpath.sections.size());
            }
            
        }
        return  debp;
    }

    private String getNameDep(int id) {
        for (int i = 0; i < departments.size(); i++) {
            Department get = departments.get(i);
            if (get.getId() == id) {
                return get.getNameA();
            }
        }
        return null;
    }

    private String getnameForSection(int id) {
        for (int i = 0; i < sections.size(); i++) {
            Section get = sections.get(i);
            if (Integer.parseInt(get.getId()) == id) {
                return get.getName();
            }
        }
        return null;
    }

    private String getJobName(int id) {
        for (int i = 0; i < jobsOfSections.size(); i++) {
            JobOfSection get = jobsOfSections.get(i);
            if(get.getIdJob() == id){
                return  get.getName();
            }
        }
        return "";
    }

}

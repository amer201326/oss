/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetFromDB;
import Data.JobOfSection;
import Data.JobTitel;
import Data.Section;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Amer$_$
 */
@ManagedBean
@ViewScoped
public class SectionManage {

    List<JobTitel> jobOfSection;
    JobTitel jobSelected;
    JobOfSection jobOfSectionSelected;
    List<JobTitel> allJob;
    JobOfSection newJobOfsection;
    @ManagedProperty(value = "#{sessionLists}")
    SessionLists sessionLists;
    
    public SectionManage() {
        

    }
     @PostConstruct
    public void init() {
       jobOfSection = GetFromDB.getJobTittle(sessionLists.sectionSelected.getId());
       allJob = GetFromDB.getJobTittle();

        newJobOfsection = new JobOfSection();
        
    }

    public List<JobTitel> getJobOfSection() {

        

        return jobOfSection;
    }

    public void setJobOfSection(List<JobTitel> jobOfSection) {
        this.jobOfSection = jobOfSection;
    }

    public SessionLists getSessionLists() {
        return sessionLists;
    }

    public void setSessionLists(SessionLists sessionLists) {
        this.sessionLists = sessionLists;
    }

    public JobTitel getJobSelected() {
        return jobSelected;
    }

    public void setJobSelected(JobTitel jobSelected) {
        this.jobSelected = jobSelected;
    }

    public void onRowSelect(SelectEvent event) {
        sessionLists.jobSelected = (JobTitel) event.getObject();
    }

    public void onRowUnselect(UnselectEvent event) {
        sessionLists.jobSelected = new JobTitel();

    }

    public void deleteJobFromSection() {
        jobOfSectionSelected = new JobOfSection(Integer.parseInt(sessionLists.jobSelected.getId()),Integer.parseInt(sessionLists.sectionSelected.getId()));
        jobOfSectionSelected.delete();
    }

    public JobOfSection getNewJobOfsection() {
        return newJobOfsection;
    }

    public void setNewJobOfsection(JobOfSection newJobOfsection) {
        this.newJobOfsection = newJobOfsection;
    }

    public List<JobTitel> getAllJob() {
        return allJob;
    }

    public void setAllJob(List<JobTitel> allJob) {
        this.allJob = allJob;
    }

    public void addJobForSec() {
        newJobOfsection.setIdSEction(Integer.parseInt(sessionLists.sectionSelected.getId()));
        if(!newJobOfsection.addToDB()){
            //FacesContext.getCurrentInstance().addMessage("formSection:eventsDT", new FacesMessage( "Warning!", "لا يمكن اضافة نفس الوظيفة مرتين"));
            FacesContext.getCurrentInstance().addMessage("formSection:eventsDT", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "لا يمكن اضافة نفس الوظيفة مرتين"));
        }
    }

    public JobOfSection getJobOfSectionSelected() {
        return jobOfSectionSelected;
    }

    public void setJobOfSectionSelected(JobOfSection jobOfSectionSelected) {
        this.jobOfSectionSelected = jobOfSectionSelected;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentServiceEmployee;
import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.StepsAndDecsions;
import Data.SectionPath;
import Data.Service;
import Data.ServiceCitizen;
import Data.StepsAndDecsionsJob;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ShowMyServicesManager implements Serializable {

    ServiceCitizen thisServiceCitizen;
    List<StepsAndDecsions> stepsAndDecsions;
    List<AttachmentServiceEmployee> attachmentServiceEmployee;

    public ShowMyServicesManager() {

    }
    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {
        if (session.serviceCitizenShow != null) {
            this.thisServiceCitizen = session.serviceCitizenShow;
            attachmentServiceEmployee = GetFromDBaraa.AttachmentServiceEmployee(thisServiceCitizen.getCit_ID(), thisServiceCitizen.getService_Citizen_ID(), thisServiceCitizen.getServices_Provided_ID());
            this.stepsAndDecsions = StepsAndDesion(thisServiceCitizen.getCit_ID(), thisServiceCitizen.getService_Citizen_ID(), thisServiceCitizen.getServices_Provided_ID());
        }

    }

    public List<StepsAndDecsions> StepsAndDesion(int idcitizen, int idSerCit, int idService) {
        List<StepsAndDecsions> pathD = GetFromDBaraa.stepAndDecDep(idcitizen, idSerCit);
        List<DecisionSection> pathS = GetFromDBaraa.sectionsteps(idService);
        List<StepsAndDecsionsJob> pathJ = GetFromDBaraa.stepAndDecJop(idcitizen, idSerCit);

        System.out.println("lllll" + pathJ.size());
        for (StepsAndDecsions d : pathD) {
            for (DecisionSection s : pathS) {
                if (d.getDepartmentPaths().id == s.getSection().getDepartmentId() && d.getDepartmentPaths().order == s.getSection().getOrderDepartment()) {
                    d.getSections().add(s);
                    for (StepsAndDecsionsJob j : pathJ) {
                        if (s.getSection().getId() == j.getJobPath().getSectionID() && s.getSection().getOrder() == j.getJobPath().getsOrder()) {
                            s.getJob().add(j);
                            for (AttachmentServiceEmployee att : attachmentServiceEmployee) {
                                if (att.getEmp_ID() == j.decisionsJob.getIdEmployee()) {
                                    j.setAttachmentServiceEmployee(att);
                                }
                            }

                        }
                    }
                }
            }
        }
        return pathD;
    }

    public ServiceCitizen getThisServiceCitizen() {
        return thisServiceCitizen;
    }

    public void setThisServiceCitizen(ServiceCitizen thisServiceCitizen) {
        this.thisServiceCitizen = thisServiceCitizen;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<StepsAndDecsions> getStepsAndDecsions() {
        return stepsAndDecsions;
    }

    public void setStepsAndDecsions(List<StepsAndDecsions> stepsAndDecsions) {
        this.stepsAndDecsions = stepsAndDecsions;
    }

    public List<AttachmentServiceEmployee> getAttachmentServiceEmployee() {
        return attachmentServiceEmployee;
    }

    public void setAttachmentServiceEmployee(List<AttachmentServiceEmployee> attachmentServiceEmployee) {
        this.attachmentServiceEmployee = attachmentServiceEmployee;
    }

}

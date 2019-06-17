package Beans;

import Data.AttachmentServiceEmployee;
import Data.DecisionSection;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.ServiceCitizen;
import Data.StepsAndDecsions;
import Data.StepsAndDecsionsJob;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class MessagesEmployesManager implements Serializable {

    ServiceCitizen thisServiceCitizen;
    List<StepsAndDecsions> stepsAndDecsions;
  
    double totalCost;

    public MessagesEmployesManager() {
    }

    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {

        if (session.serviceCitizenShow != null) {
            this.thisServiceCitizen = session.serviceCitizenShow;
            this.stepsAndDecsions = StepsAndDesion(thisServiceCitizen.getCit_ID(), thisServiceCitizen.getService_Citizen_ID(), thisServiceCitizen.getServices_Provided_ID());

        }

    }

    public List<StepsAndDecsions> StepsAndDesion(int idcitizen, int idSerCit, int idService) {
        List<StepsAndDecsions> pathD = GetFromDBaraa.stepAndDecDep(idcitizen, idSerCit);
        List<DecisionSection> pathS = GetFromDBaraa.sectionsteps( idcitizen,  idSerCit,  idService);
        List<StepsAndDecsionsJob> pathJ = GetFromDBaraa.stepAndDecJop(idcitizen, idSerCit);
        List<AttachmentServiceEmployee> att = GetFromDB.getAttachmentServiceEmployee( idcitizen, idSerCit, idService);
        for (StepsAndDecsionsJob j : pathJ) {
            for (AttachmentServiceEmployee att2 : att) {
                if(att2.getJobID()  == j.jobPath.getId()){
                    j.getAttachmentServiceEmployee().add(att2);
                    System.out.println("lingth is [] = "+att2.outputfinal);
                    break;
                }
            }
        }
        System.out.println("lllll" + pathJ.size());
        for (StepsAndDecsions d : pathD) {
            d.decisionsDepartment.setTotalDepCost(d.decisionsDepartment.getTotalDepCost() + d.decisionsDepartment.getCost());
            for (DecisionSection s : pathS) {
                if (d.getDepartmentPaths().id == s.getSection().getDepartmentId() && d.getDepartmentPaths().order == s.getSection().getOrderDepartment()) {
                    d.getSections().add(s);
                    for (StepsAndDecsionsJob j : pathJ) {
                        if (s.getSection().getId() == j.getJobPath().getSectionID() && s.getSection().getOrder() == j.getJobPath().getsOrder()) {
                            s.getJob().add(j);
                             d.decisionsDepartment.setTotalDepCost(d.decisionsDepartment.getTotalDepCost() + j.decisionsJob.getCost());
                        }
                    }
                }
            }
            totalCost = totalCost + d.decisionsDepartment.getTotalDepCost();
        }
        return pathD;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ServiceCitizen getThisServiceCitizen() {
        return thisServiceCitizen;
    }

    public void setThisServiceCitizen(ServiceCitizen thisServiceCitizen) {
        this.thisServiceCitizen = thisServiceCitizen;
    }

    public List<StepsAndDecsions> getStepsAndDecsions() {
        return stepsAndDecsions;
    }

    public void setStepsAndDecsions(List<StepsAndDecsions> stepsAndDecsions) {
        this.stepsAndDecsions = stepsAndDecsions;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

   
    

}

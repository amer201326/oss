/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentServiceEmployee;
import Data.DecisionSection;
import Data.Employee;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen;
import Data.StepsAndDecsions;
import Data.StepsAndDecsionsJob;
import Data.ViewerAttachment;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ShowServiceHeadManeger implements Serializable {

    ServiceCitizen serviseCitizen;
    Employee employee;
    List<StepsAndDecsions> stepsAndDecsions;

    @ManagedProperty(value = "#{msession}")
    Session session;

    boolean haveService = false;
    List<AttachmentServiceEmployee> attachmentServiceEmployees = new ArrayList<>();
    List<ServiceAttachmentName> att = new ArrayList<>();
    List<ServiceAttachmentName> attform = new ArrayList<>();
    double totalCost;
    private AttachmentServiceEmployee selectAttEMP;

    @PostConstruct
    public void init() {
        serviseCitizen = session.serviceCitizenShow;
        employee = session.employee;

        if (serviseCitizen != null) {
            serviseCitizen.getDecisionsDepartment().updateRunning();
            haveService = true;
            filterFormOrNot();
            this.stepsAndDecsions = StepsAndDesion(serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID(), serviseCitizen.getServices_Provided_ID());

        }

    }

    public void filterFormOrNot() {
        List<ServiceAttachmentName> allAtt = GetFromDB.getAttachmentByserviceCitizen(serviseCitizen.getServices_Provided_ID(), serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID());
        att = new ArrayList<ServiceAttachmentName>();
        attform = new ArrayList<ServiceAttachmentName>();
        for (ServiceAttachmentName serviceAttachmentName : allAtt) {

            if ("yes".equals(serviceAttachmentName.getForm())) {
                attform.add(serviceAttachmentName);
            } else {
                att.add(serviceAttachmentName);
            }

        }

        serviseCitizen.attachment = att;
        serviseCitizen.attwhithFile = attform;
    }

    public List<StepsAndDecsions> StepsAndDesion(int idcitizen, int idSerCit, int idService) {
        List<StepsAndDecsions> pathD = GetFromDBaraa.stepAndDecDep(idcitizen, idSerCit);
        List<DecisionSection> pathS = GetFromDBaraa.sectionsteps( idcitizen,  idSerCit,  idService);
        List<StepsAndDecsionsJob> pathJ = GetFromDBaraa.stepAndDecJop(idcitizen, idSerCit);
        attachmentServiceEmployees = GetFromDBaraa.AttachmentServiceEmployee(serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID(), serviseCitizen.getServices_Provided_ID());

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
                            for (AttachmentServiceEmployee att : attachmentServiceEmployees) {
                                if (att.getEmp_ID() == j.decisionsJob.getIdEmployee()) {
                                    //j.setAttachmentServiceEmployee(att);
                                    j.getAttachmentServiceEmployee().add(att);

                                }
                            }
                        }
                    }
                }
            }
            totalCost = totalCost + d.decisionsDepartment.getTotalDepCost();
        }
        return pathD;
    }

    public ServiceCitizen getServiseCitizen() {
        return serviseCitizen;
    }

    public void setServiseCitizen(ServiceCitizen serviseCitizen) {
        this.serviseCitizen = serviseCitizen;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isHaveService() {
        return haveService;
    }

    public void setHaveService(boolean haveService) {
        this.haveService = haveService;
    }

    public void accept() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
        serviseCitizen.getDecisionsDepartment().setStatus("done");
        serviseCitizen.getDecisionsDepartment().setDecision("accept");
        serviseCitizen.desdepartment(employee);
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizennNotDone.xhtml");

    }

    public void reject() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
        serviseCitizen.getDecisionsDepartment().setStatus("notdone");
        serviseCitizen.getDecisionsDepartment().setDecision("reject");
        serviseCitizen.desdepartment(employee);
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizennNotDone.xhtml");

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<AttachmentServiceEmployee> getAllFileEmployee() {
        return attachmentServiceEmployees;
    }

    public void setAllFileEmployee(List<AttachmentServiceEmployee> attachmentServiceEmployees) {
        this.attachmentServiceEmployees = attachmentServiceEmployees;
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            System.out.println("upload fileeeeeeeeene" + event.getFile().getFileName() + "   SIZE " + event.getFile().getSize());

            AttachmentServiceEmployee att = new AttachmentServiceEmployee(employee.getEmp_id(),
                    serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID(),
                    serviseCitizen.getServices_Provided_ID(), event.getFile().getInputstream(), event.getFile().getFileName());
            attachmentServiceEmployees.add(att);

            System.out.println("after add  = " + attachmentServiceEmployees.size());
        } catch (IOException ex) {
            Logger.getLogger(ShoeServiceCitizemEmpManeger.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public List<AttachmentServiceEmployee> getAttachmentServiceEmployees() {
        return attachmentServiceEmployees;
    }

    public void setAttachmentServiceEmployees(List<AttachmentServiceEmployee> attachmentServiceEmployees) {
        this.attachmentServiceEmployees = attachmentServiceEmployees;
    }

    public List<ServiceAttachmentName> getAtt() {
        return att;
    }

    public void setAtt(List<ServiceAttachmentName> att) {
        this.att = att;
    }

    public List<ServiceAttachmentName> getAttform() {
        return attform;
    }

    public void setAttform(List<ServiceAttachmentName> attform) {
        this.attform = attform;
    }
    
    public void onServiceSelect(SelectEvent event) {
        selectAttEMP = (AttachmentServiceEmployee) event.getObject();
    }

    public AttachmentServiceEmployee getSelectAttEMP() {
        return selectAttEMP;
    }

    public void setSelectAttEMP(AttachmentServiceEmployee selectAttEMP) {
        this.selectAttEMP = selectAttEMP;
    }
    
    

}

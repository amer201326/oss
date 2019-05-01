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

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ShowServiceHeadManeger {

    ServiceCitizen serviseCitizen;
    Employee employee;
    List<StepsAndDecsions> stepsAndDecsions;

    @ManagedProperty(value = "#{msession}")
    Session session;

    boolean haveService = false;
    List<AttachmentServiceEmployee> attachmentServiceEmployees = new ArrayList<>();

    @PostConstruct
    public void init() {
        serviseCitizen = session.serviceCitizenShow;
        employee = session.employee;

        if (serviseCitizen != null) {
            haveService = true;
            filterFormOrNot();
            this.stepsAndDecsions = StepsAndDesion(serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID(), serviseCitizen.getServices_Provided_ID());

        }

    }

    public void filterFormOrNot() {
        List<ServiceAttachmentName> allAtt = GetFromDB.getAttachmentByserviceCitizen(serviseCitizen.getServices_Provided_ID(), serviseCitizen.getCit_ID(), serviseCitizen.getService_Citizen_ID());
        List<ServiceAttachmentName> att = new ArrayList<ServiceAttachmentName>();
        List<ServiceAttachmentName> attform = new ArrayList<ServiceAttachmentName>();

        List<ViewerAttachment> jobViewer = GetFromDB.getJobviewerByserviceById(serviseCitizen.getServices_Provided_ID());

        for (ServiceAttachmentName serviceAttachmentName : allAtt) {
            for (ViewerAttachment viewer : jobViewer) {
                if (serviceAttachmentName.getId() == viewer.getServiceAttachmentName_ID()
                        && employee.getDep_id() == viewer.getDep_ID() && employee.getSec_id() == viewer.getSec_ID() && employee.getJob_id() == viewer.getJob_ID()) {

                    if ("yes".equals(serviceAttachmentName.getForm())) {
                        attform.add(serviceAttachmentName);
                    } else {
                        att.add(serviceAttachmentName);
                    }

                }
            }
        }

        serviseCitizen.attachment = att;
        serviseCitizen.attwhithFile = attform;
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

                        }
                    }
                }
            }
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
        serviseCitizen.desdepartment();
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizzen.xhtml");

    }

    public void reject() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
         serviseCitizen.getDecisionsDepartment().setStatus("notdone");
        serviseCitizen.getDecisionsDepartment().setDecision("reject");
        serviseCitizen.desdepartment();
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizzen.xhtml");

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

}

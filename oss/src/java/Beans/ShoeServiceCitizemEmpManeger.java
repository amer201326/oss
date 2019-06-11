/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Amer
 */
import DB.DB;
import Data.AttachmentServiceEmployee;
import Data.Employee;
import Data.GetFromDB;
import Data.JobPath;
import Data.Service;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
import Data.Service_Job;
import Data.ViewerAttachment;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ShoeServiceCitizemEmpManeger implements Serializable{

    ServiceCitizen serviseCitizen;
    Employee employee;

    @ManagedProperty(value = "#{msession}")
    Session session;

    boolean haveService = false;
    List<AttachmentServiceEmployee> attachmentServiceEmployees = new ArrayList<>();
    AttachmentServiceEmployee selectAtt;

    @PostConstruct
    public void init() {
        selectAtt = new AttachmentServiceEmployee();
        serviseCitizen = session.serviceCitizenShow;
        employee = session.employee;

        if (serviseCitizen != null) {
            haveService = true;
            serviseCitizen.getService_Job().updateShow();
            
            filterFormOrNot();
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
    

    public ServiceCitizen getServiseCitizen() {
        return serviseCitizen;
    }

    public void setServiseCitizen(ServiceCitizen serviseCitizen) {
        this.serviseCitizen = serviseCitizen;
    }
    
    public void deleteAttEMP(){
        attachmentServiceEmployees.remove(selectAtt);
        
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

    public void submit() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
        serviseCitizen.ContineuInPath(employee.getEmp_id());
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizzen.xhtml");

    }

    public void accept() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
        serviseCitizen.ContineuInPath(employee.getEmp_id());
        FacesContext.getCurrentInstance().getExternalContext().redirect("serviceCitizzen.xhtml");

    }

    public void reject() throws IOException {
        System.out.println("size files = " + attachmentServiceEmployees.size());
        serviseCitizen.attachmentServiceEmployees = attachmentServiceEmployees;
        serviseCitizen.ContineuInPathReject(employee.getEmp_id());
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
            att.setSel(System.currentTimeMillis());
            attachmentServiceEmployees.add(att);

            System.out.println("after add  = " + attachmentServiceEmployees.size());
        } catch (IOException ex) {
            Logger.getLogger(ShoeServiceCitizemEmpManeger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<AttachmentServiceEmployee> getAttachmentServiceEmployees() {
        return attachmentServiceEmployees;
    }

    public void setAttachmentServiceEmployees(List<AttachmentServiceEmployee> attachmentServiceEmployees) {
        this.attachmentServiceEmployees = attachmentServiceEmployees;
    }

    public AttachmentServiceEmployee getSelectAtt() {
        return selectAtt;
    }

    public void setSelectAtt(AttachmentServiceEmployee selectAtt) {
        this.selectAtt = selectAtt;
    }
    
    public void onServiceSelect(SelectEvent event){
        selectAtt = (AttachmentServiceEmployee) event.getObject();
    }
    
}

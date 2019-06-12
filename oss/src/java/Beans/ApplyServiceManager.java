/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentArchiveCitizen;
import Data.AttachmentServiceCitizen;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.Service;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen;
import java.io.IOException;
import java.io.Serializable;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ApplyServiceManager implements Serializable {

    ServiceAttachmentName selectAtt;
    ServiceCitizen serviceCitizen;

    List<ServiceAttachmentName> allAttachment;
    List<AttachmentArchiveCitizen> attaCitizen;
    List<AttachmentArchiveCitizen> attaCitizenNotForm;
    AttachmentArchiveCitizen selectAttCitizen;
    StreamedContent fileDownload;
    int idCitizen;
    int idatta_ArchiveC_ID;

    boolean fromArcheve;
    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {
        if (session.citizen != null) {
            idCitizen = session.citizen.getId();
            attaCitizenNotForm = new ArrayList<>();
            Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String param = parameterMap.get("id");
            System.out.println(param + "===================================================================");
            serviceCitizen = new ServiceCitizen();
            serviceCitizen.service = GetFromDB.getServiceByID2(param);
            serviceCitizen.setCit_ID(idCitizen);

            allAttachment = GetFromDB.getAttavhmentByserviceById(serviceCitizen.service.getId());
            System.out.println("Bdddddd" + allAttachment.size());
            attaCitizen = GetFromDB.getAttachmantsArchive(idCitizen);
            for (AttachmentArchiveCitizen att : attaCitizen) {
                if(att.getForm().compareTo("no")==0){
                    attaCitizenNotForm.add(att);
                }
            }
            for (ServiceAttachmentName serviceAttachmentName : allAttachment) {

                if (serviceAttachmentName.getFileDownload() == null) {
                    serviceCitizen.attachment.add(serviceAttachmentName);
                } else {
                    serviceCitizen.attwhithFile.add(serviceAttachmentName);
                }

            }

        }
    }

    public ApplyServiceManager() {

    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }

    public void submit() {
        boolean b = false;

        for (ServiceAttachmentName serviceAttachmentName : serviceCitizen.attachment) {
            if (serviceAttachmentName.haveFileToupload()) {
                if (serviceAttachmentName.getFile().getSize() == 0 && !serviceAttachmentName.isFormArchevCitizen()) {
                    
                    b = true;
                    break;
                }

            }
        }

        if (!b) {
            try {
                serviceCitizen.addToDataBase();
                FacesContext.getCurrentInstance().getExternalContext().redirect("notDoneCitizenServices.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ApplyServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else //GetFromDBaraa.ApplyService(idCitizen, thisService.getId(), allAttachment,note);
        {

            FacesContext.getCurrentInstance().addMessage("sub", new FacesMessage(FacesMessage.SEVERITY_ERROR, " ", "بوجد مرفقات مطلوبة لم يتم ارفاقها"));
        }
    }

    public void selectfileForAtt(int idAttACitizen) {
        for (ServiceAttachmentName serviceAttachmentName : serviceCitizen.attachment) {
            if(serviceAttachmentName.getId() == selectAtt.getId()){
                serviceAttachmentName.setFormArchevCitizen(true);
                serviceAttachmentName.setIdAttachmentArcheveCitisen(idAttACitizen);
                break;
            }
        }
        System.out.println("afte select "+idAttACitizen);
    }

    public ServiceCitizen getServiceCitizen() {
        return serviceCitizen;
    }

    public void setServiceCitizen(ServiceCitizen serviceCitizen) {
        this.serviceCitizen = serviceCitizen;
    }

    public int getIdCitizen() {
        return idCitizen;
    }

    public void setIdCitizen(int idCitizen) {
        this.idCitizen = idCitizen;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<AttachmentArchiveCitizen> getAttaCitizen() {
        return attaCitizen;
    }

    public void setAttaCitizen(List<AttachmentArchiveCitizen> attaCitizen) {
        this.attaCitizen = attaCitizen;
    }

    public boolean isFromArcheve() {
        return fromArcheve;
    }

    public void setFromArcheve(boolean fromArcheve) {
        this.fromArcheve = fromArcheve;
    }

    

    public List<ServiceAttachmentName> getAllAttachment() {
        return allAttachment;
    }

    public void setAllAttachment(List<ServiceAttachmentName> allAttachment) {
        this.allAttachment = allAttachment;
    }

    public int getIdatta_ArchiveC_ID() {
        return idatta_ArchiveC_ID;
    }

    public void setIdatta_ArchiveC_ID(int idatta_ArchiveC_ID) {
        this.idatta_ArchiveC_ID = idatta_ArchiveC_ID;
    }

    public ServiceAttachmentName getSelectAtt() {
        return selectAtt;
    }

    public void setSelectAtt(ServiceAttachmentName selectAtt) {
        this.selectAtt = selectAtt;
    }

    public AttachmentArchiveCitizen getSelectAttCitizen() {
        return selectAttCitizen;
    }

    public void setSelectAttCitizen(AttachmentArchiveCitizen selectAttCitizen) {
        this.selectAttCitizen = selectAttCitizen;
    }

    public List<AttachmentArchiveCitizen> getAttaCitizenNotForm() {
        return attaCitizenNotForm;
    }

    public void setAttaCitizenNotForm(List<AttachmentArchiveCitizen> attaCitizenNotForm) {
        this.attaCitizenNotForm = attaCitizenNotForm;
    }
    
    
}

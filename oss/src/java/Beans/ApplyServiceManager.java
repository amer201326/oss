/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetFromDB;
import Data.Service;
import Data.ServiceAttachmentName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class ApplyServiceManager implements Serializable{

    Service thisService = new Service();
    List<ServiceAttachmentName> attachment= new ArrayList<ServiceAttachmentName>();
    List<ServiceAttachmentName> attwhithFile=new ArrayList<ServiceAttachmentName>();
    
    public ApplyServiceManager() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
          String param = parameterMap.get("id");
          thisService = GetFromDB.getServiceByID2(param);
    }

    public Service getThisService() {
        return thisService;
    }

    public void setThisService(Service thisService) {
        this.thisService = thisService;
    }
    
     public void attavhmentByserviceById() {
          List<ServiceAttachmentName> allAttachment = GetFromDB.getAttavhmentByserviceById(thisService.getId());
         for (ServiceAttachmentName serviceAttachmentName :  allAttachment) {
             if(serviceAttachmentName.getSrcFile() != null)
                 attwhithFile.add(serviceAttachmentName);
            else
                 attachment.add(serviceAttachmentName);
         }

         
     }

    public List<ServiceAttachmentName> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<ServiceAttachmentName> attachment) {
        this.attachment = attachment;
    }

    public List<ServiceAttachmentName> getAttwhithFile() {
        return attwhithFile;
    }

    public void setAttwhithFile(List<ServiceAttachmentName> attwhithFile) {
        this.attwhithFile = attwhithFile;
    }
    
    public UploadedFile file ;
     ServiceAttachmentName thisAttachment = new ServiceAttachmentName();

    public ServiceAttachmentName getThisAttachment() {
        return thisAttachment;
    }

    public void setThisAttachment(ServiceAttachmentName thisAttachment) {
        this.thisAttachment = thisAttachment;
        if(file != null){System.out.println("bbbbbbbbb");
        this.thisAttachment.setSrcFile(file.getFileName());}
       
    }
     
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
//    public void upload() {
//        if(file != null) {
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//     
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }  
    
    public void submit(){
        System.out.println("jjjjj"+thisAttachment.getSrcFile()+"lll"+thisAttachment.getName()+file);
    }
}

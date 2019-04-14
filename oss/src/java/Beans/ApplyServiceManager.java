/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.Service;
import Data.ServiceAttachmentName;
import Data.ServiceCitizen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class ApplyServiceManager implements Serializable{
    
    
    ServiceCitizen serviceCitizen ;
    

    List<ServiceAttachmentName> allAttachment;
    
    //Service thisService = new Service();
    
//    List<ServiceAttachmentName> attachment= new ArrayList<ServiceAttachmentName>();
//    List<ServiceAttachmentName> attwhithFile=new ArrayList<ServiceAttachmentName>();
    
    StreamedContent fileDownload;
    int idCitizen ;
    String note = "";
    
    public ApplyServiceManager() {
         idCitizen = 1;
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
          String param = parameterMap.get("id");
            serviceCitizen = new ServiceCitizen();
            serviceCitizen.thisService = GetFromDB.getServiceByID2(param);
          serviceCitizen.setCit_ID(1);
            allAttachment = GetFromDB.getAttavhmentByserviceById(serviceCitizen.thisService.getId());
            System.out.println("Bdddddd"+allAttachment.size());
            
            for (ServiceAttachmentName serviceAttachmentName :  allAttachment) {
            
             if(serviceAttachmentName.getFileDownload() == null)
                 serviceCitizen.attachment.add(serviceAttachmentName);
            else
                 serviceCitizen.attwhithFile.add(serviceAttachmentName);
                 
         }
            
              
    }
    
    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(StreamedContent fileDownload) {
        this.fileDownload = fileDownload;
    }
    
    
//    public Service getThisService() {
//        return thisService;
//    }
//
//    public void setThisService(Service thisService) {
//        this.thisService = thisService;
//    }
//    
//     private void attavhmentByserviceById() {
//         allAttachment = GetFromDB.getAttavhmentByserviceById(thisService.getId());
//         for (ServiceAttachmentName serviceAttachmentName :  allAttachment) {
//            
//             if(serviceAttachmentName.getFileDownload() == null)
//                 attachment.add(serviceAttachmentName);
//            else
//                 attwhithFile.add(serviceAttachmentName);
//                 
//         }
//
//         
//     }
//
//    public List<ServiceAttachmentName> getAttachment() {
//        return attachment;
//    }
//
//    public void setAttachment(List<ServiceAttachmentName> attachment) {
//        this.attachment = attachment;
//    }
//
//    public List<ServiceAttachmentName> getAttwhithFile() {
//        return attwhithFile;
//    }
//
//    public void setAttwhithFile(List<ServiceAttachmentName> attwhithFile) {
//        this.attwhithFile = attwhithFile;
//    }
    
//    public void putFileForDownload(int i){
//        System.out.println("index "+i);
//        fileDownload = attwhithFile.get(i).getFileDownload();
//    }
//    
//    public UploadedFile file ;
//     ServiceAttachmentName thisAttachment = new ServiceAttachmentName();
//
//    public ServiceAttachmentName getThisAttachment() {
//        return thisAttachment;
//    }
//
//    public void setThisAttachment(ServiceAttachmentName thisAttachment) {
//        this.thisAttachment = thisAttachment;
//        if(file != null){System.out.println("bbbbbbbbb");
//        this.thisAttachment.setSrcFile(file.getFileName());}
//       
//    }
//     
//    public UploadedFile getFile() {
//        return file;
//    }
// 
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
     
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
//
//    public List<ServiceAttachmentName> getAllAttachment() {
//        return allAttachment;
//    }
//
//    public void setAllAttachment(List<ServiceAttachmentName> allAttachment) {
//        this.allAttachment = allAttachment;
//    }
    
    
    public void submit(){
         serviceCitizen.addToDataBase();
        
        //GetFromDBaraa.ApplyService(idCitizen, thisService.getId(), allAttachment,note);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ServiceCitizen getServiceCitizen() {
        return serviceCitizen;
    }

    public void setServiceCitizen(ServiceCitizen serviceCitizen) {
        this.serviceCitizen = serviceCitizen;
    }

   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.ServiceAttachmentName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import static org.primefaces.component.contextmenu.ContextMenu.PropertyKeys.event;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class ServiceAttachemntManage implements Serializable {

    List<ServiceAttachmentName> allServicesAttach;
    List<ServiceAttachmentName> allServicesAttachwithFile;
    List<ServiceAttachmentName> allServicesAttachwithoutFile;
    ServiceAttachmentName serv;
    
    ServiceAttachmentName newService;

    public ServiceAttachemntManage() {
        allServicesAttach = GetFromDB.getServiceAttachmentName();
        
        allServicesAttachwithoutFile = new ArrayList<>();
        allServicesAttachwithFile = new ArrayList<>();
        for (int i = 0; i < allServicesAttach.size(); i++) {
            ServiceAttachmentName get = allServicesAttach.get(i);
            if (get.getFileDownload() == null) {
                allServicesAttachwithoutFile.add(get);
            } else {
                allServicesAttachwithFile.add(get);
            }
        }
        
        newService = new ServiceAttachmentName();
        serv = new ServiceAttachmentName();
    }

    public ServiceAttachmentName getServ() {
        return serv;
    }

    public void setServ(ServiceAttachmentName serv) {
        System.out.println(serv+" is set ------------");
        this.serv = serv;
    }

    public List<ServiceAttachmentName> getAllServicesAttach() {
        return allServicesAttach;
    }

    public void setAllServicesAttach(List<ServiceAttachmentName> allServicesAttach) {
        this.allServicesAttach = allServicesAttach;
    }

    public void onAttachEdit(RowEditEvent event) {

        ((ServiceAttachmentName) event.getObject()).update();

    }
    public void editAttachment(){
        
        serv.updatewithFile();
        //serv = new ServiceAttachmentName();
    }
     public void editAttachmentNoFile(){
        
        serv.update();
        //serv = new ServiceAttachmentName();
    }
        

    public void onAttachCancel(RowEditEvent event) {

    }

    public void onAttachSelected(SelectEvent event) {
        System.out.println("form al ajax " + ((ServiceAttachmentName) event.getObject()).getId());
        serv = (ServiceAttachmentName) event.getObject();
        System.out.println(serv);

    }

    public void deleteAttachment() {
        serv.deleteFromDB();
        allServicesAttachwithFile.remove(serv);
        allServicesAttachwithoutFile.remove(serv);
        
    }

    public ServiceAttachmentName getNewService() {
        return newService;
    }

    public void setNewService(ServiceAttachmentName newService) {
        this.newService = newService;
    }

    public void addAttachment() {
       
        newService.addAttachToDBwithFile();
       
        allServicesAttachwithFile.add(newService);
        newService = new ServiceAttachmentName();

        

    }

    public void addAttachmentWitoutFile() {
        
        newService.addAttachToDBWitoutFile();
      
        allServicesAttachwithoutFile.add(newService);
        
        newService = new ServiceAttachmentName();

        

    }

    public List<ServiceAttachmentName> getAllServicesAttachwithFile() {
        return allServicesAttachwithFile;
    }

    public void setAllServicesAttachwithFile(List<ServiceAttachmentName> allServicesAttachwithFile) {
        this.allServicesAttachwithFile = allServicesAttachwithFile;
    }

    public List<ServiceAttachmentName> getAllServicesAttachwithoutFile() {
        return allServicesAttachwithoutFile;
    }

    public void setAllServicesAttachwithoutFile(List<ServiceAttachmentName> allServicesAttachwithoutFile) {
        this.allServicesAttachwithoutFile = allServicesAttachwithoutFile;
    }

}

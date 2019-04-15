/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.GetDB_Eman;
import Data.Service;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class ServiceCitizenManager implements Serializable {

    List<ServiceCitizen_1> allRequestService;
    List<ServiceCitizen_1> allRequestServiceView;
    List<ServiceCitizen_1> allRequestServiceNotView;

    ServiceCitizen_1 serviceSelected;

    public ServiceCitizenManager() {
        
        allRequestService = GetDB_Eman.getAllRequestService();
        
        allRequestServiceView = new ArrayList<>();
        allRequestServiceNotView = new ArrayList<>();
        
        for (int i = 0; i < allRequestService.size(); i++) {
            ServiceCitizen_1 get = allRequestService.get(i);
            if (get.getStatus().compareTo("done")==0) {
                allRequestServiceView.add(get);
            } else {
                allRequestServiceNotView.add(get);
            }
        }
        
        System.out.println(allRequestServiceNotView);
        System.out.println(allRequestServiceView);
        System.out.println(allRequestService + "dddddddddddddddddddddddddddd");
        serviceSelected = new ServiceCitizen_1();
    }

    public List<ServiceCitizen_1> getAllRequestServiceView() {
        return allRequestServiceView;
    }

    public void setAllRequestServiceView(List<ServiceCitizen_1> allRequestServiceView) {
        this.allRequestServiceView = allRequestServiceView;
    }

    public List<ServiceCitizen_1> getAllRequestServiceNotView() {
        return allRequestServiceNotView;
    }

    public void setAllRequestServiceNotView(List<ServiceCitizen_1> allRequestServiceNotView) {
        this.allRequestServiceNotView = allRequestServiceNotView;
    }

    public List<ServiceCitizen_1> getAllRequestService() {
        return allRequestService;
    }

    public void setAllRequestService(List<ServiceCitizen_1> allRequestService) {
        this.allRequestService = allRequestService;
    }
    

    public ServiceCitizen_1 getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(ServiceCitizen_1 serviceSelected) {
        this.serviceSelected = serviceSelected;
    }

    public void onServiceSelect(SelectEvent event) {
        serviceSelected = (ServiceCitizen_1) event.getObject();
    }
}

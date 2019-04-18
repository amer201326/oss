/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.Employee;
import Data.GetDB_Eman;
import Data.GetFromDB;
import Data.Service;
import Data.ServiceCitizen;
import Data.ServiceCitizen_1;
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

    @ManagedProperty(value = "#{msession}")
    Session session;

    public ServiceCitizenManager() {

        serviceSelected = new ServiceCitizen_1();
    }

    @PostConstruct
    public void init() {
        if (session.employee != null) {
            allRequestService = new ArrayList<>();
            DecisionsDepartment decisionsDepartments = getDecisionsDepartmentsforEmployee(session.employee);

            List<ServiceCitizen_1> temp = GetDB_Eman.getAllRequestService(session.employee.getJob_id());
            for (ServiceCitizen_1 serviceCitizen_1 : temp) {
                if (serviceCitizen_1.getCit_ID() == decisionsDepartments.getCit_ID() && serviceCitizen_1.getService_Citizen_ID() == decisionsDepartments.getService_Citizen_ID()) {
                    if (!decisionsDepartments.getSection().isEmpty()) {

                        for (DecisionSection decisionSection : decisionsDepartments.getSection()) {
                            if (serviceCitizen_1.getCit_ID() == decisionSection.getCit_ID()
                                    && serviceCitizen_1.getService_Citizen_ID() == decisionSection.getService_Citizen_ID()) {
//                                if (!decisionSection.getJobs().isEmpty()) {
//
//                                    for (DecisionsJob job : decisionSection.getJobs()) {
//                                        if (job.getCit_ID() == decisionSection.getCit_ID()
//                                                && serviceCitizen_1.getService_Citizen_ID() == job.getService_Citizen_ID()) {
//                                            allRequestService.add(serviceCitizen_1);
//                                        }
//                                    }
//                                } else {
//                                    allRequestService.add(serviceCitizen_1);
//                                }
                                allRequestService.add(serviceCitizen_1);
                            }
                        }

                    } else {
                        allRequestService.add(serviceCitizen_1);
                    }
                }
                allRequestServiceView = new ArrayList<>();
                allRequestServiceNotView = new ArrayList<>();

                for (int i = 0; i < allRequestService.size(); i++) {
                    ServiceCitizen_1 get = allRequestService.get(i);
                    if (get.getStatus().compareTo("done") == 0) {
                        allRequestServiceView.add(get);
                    } else {
                        allRequestServiceNotView.add(get);
                    }
                }
            }

            serviceSelected = new ServiceCitizen_1();
        }
    }

    public void showServise(String serviceCid, String servicePid) {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("ShowService.xhtml?Cid=" + serviceCid + "&SCid" + servicePid);
        } catch (IOException ex) {
            Logger.getLogger(ServiceCitizenManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private DecisionsDepartment getDecisionsDepartmentsforEmployee(Employee employee) {
        DecisionsDepartment dds = GetFromDB.getDecisionsDepartment(employee);
        System.out.println(dds);
        DecisionSection dses = GetFromDB.getDecisionsSection(employee, dds);
        System.out.println(dses);
        DecisionsJob djs = GetFromDB.getDecisionsJob(employee, dses);
        System.out.println(djs);
        dses.getJobs().add(djs);

        dds.getSection().add(dses);

        return dds;
    }

}

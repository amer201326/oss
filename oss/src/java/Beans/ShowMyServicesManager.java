/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.AttachmentServiceEmployee;
import Data.DecisionSection;
import Data.DecisionsDepartment;
import Data.DecisionsJob;
import Data.DepartmentPaths;
import Data.GetFromDB;
import Data.GetFromDBaraa;
import Data.JobPath;
import Data.StepsAndDecsions;
import Data.SectionPath;
import Data.Service;
import Data.ServiceCitizen;
import Data.StepsAndDecsionsJob;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author baraakali
 */
@ManagedBean(name = "showMyServicesManager")
@ViewScoped

public class ShowMyServicesManager implements Serializable {

    ServiceCitizen thisServiceCitizen;
    List<StepsAndDecsions> stepsAndDecsions;
    List<AttachmentServiceEmployee> attachmentServiceEmployee;
    AttachmentServiceEmployee selectAttEMP;
    double totalCost;

    DefaultDiagramModel model;

    public ShowMyServicesManager() { 
        selectAttEMP = new AttachmentServiceEmployee();
    }
    @ManagedProperty(value = "#{msession}")
    Session session;

    @PostConstruct
    public void init() {

        if (session.serviceCitizenShow != null) {
            this.thisServiceCitizen = session.serviceCitizenShow;

            attachmentServiceEmployee = GetFromDBaraa.AttachmentServiceEmployee(thisServiceCitizen.getCit_ID(), thisServiceCitizen.getService_Citizen_ID(), thisServiceCitizen.getServices_Provided_ID());

            this.stepsAndDecsions = StepsAndDesion(thisServiceCitizen.getCit_ID(), thisServiceCitizen.getService_Citizen_ID(), thisServiceCitizen.getServices_Provided_ID());
            this.thisServiceCitizen.notificationUser.updateShow();
            creatPath();
        }

    }

    public List<StepsAndDecsions> StepsAndDesion(int idcitizen, int idSerCit, int idService) {
        List<StepsAndDecsions> pathD = GetFromDBaraa.stepAndDecDep(idcitizen, idSerCit);
        List<DecisionSection> pathS = GetFromDBaraa.sectionsteps(idcitizen, idSerCit, idService);
        List<StepsAndDecsionsJob> pathJ = GetFromDBaraa.stepAndDecJop(idcitizen, idSerCit);

        System.out.println("lllll" + pathJ.size());
        for (StepsAndDecsions d : pathD) {
            d.decisionsDepartment.setTotalDepCost(d.decisionsDepartment.getTotalDepCost() + d.decisionsDepartment.getCost());

            for (DecisionSection s : pathS) {
                System.out.println(s);
                if (d.getDepartmentPaths().id == s.getSection().getDepartmentId() && d.getDepartmentPaths().order == s.getSection().getOrderDepartment()) {
                    d.getSections().add(s);
                    for (StepsAndDecsionsJob j : pathJ) {
                        if (s.getSection().getId() == j.getJobPath().getSectionID() && s.getSection().getOrder() == j.getJobPath().getsOrder()) {
                            s.getJob().add(j);
                            d.decisionsDepartment.setTotalDepCost(d.decisionsDepartment.getTotalDepCost() + j.decisionsJob.getCost());

                            for (AttachmentServiceEmployee att : attachmentServiceEmployee) {
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

    public ServiceCitizen getThisServiceCitizen() {
        return thisServiceCitizen;
    }

    public void setThisServiceCitizen(ServiceCitizen thisServiceCitizen) {
        this.thisServiceCitizen = thisServiceCitizen;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<StepsAndDecsions> getStepsAndDecsions() {
        return stepsAndDecsions;
    }

    public void setStepsAndDecsions(List<StepsAndDecsions> stepsAndDecsions) {
        this.stepsAndDecsions = stepsAndDecsions;
    }

    public List<AttachmentServiceEmployee> getAttachmentServiceEmployee() {
        return attachmentServiceEmployee;
    }

    public void setAttachmentServiceEmployee(List<AttachmentServiceEmployee> attachmentServiceEmployee) {
        this.attachmentServiceEmployee = attachmentServiceEmployee;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public AttachmentServiceEmployee getSelectAttEMP() {
        return selectAttEMP;
    }

    public void setSelectAttEMP(AttachmentServiceEmployee selectAttEMP) {
        this.selectAttEMP = selectAttEMP;
    }

    public void onServiceSelect(SelectEvent event) {
        selectAttEMP = (AttachmentServiceEmployee) event.getObject();
    }

    private void creatPath() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        int height = 400;

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        Element start = new Element("", "20px", (80) + "px");
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        start.setStyleClass("ui-diagram-start");

        Element temp = start;
        model.addElement(start);

        StepsAndDecsions[] listD = orderDeb();

        for (int i = 0; i < listD.length; i++) {
            StepsAndDecsions d = listD[i];

            String x = temp.getX().split("p")[0];
            Element trouble = new Element(d.departmentPaths.nameA, (Integer.parseInt(x) + ((String) temp.getData()).length() * 6 + 100) + "px", temp.getY());
            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
            trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
            if (d.decisionsDepartment.getDecision() != null) {
                if (d.decisionsDepartment.getDecision().equals("accept")) {
                    trouble.setStyleClass("ui-diagram-successDEP");
                } else if (d.decisionsDepartment.getDecision().equals("reject")) {
                    trouble.setStyleClass("ui-diagram-failDEP");
                }
            }

            model.addElement(trouble);
            model.connect(createConnection(temp.getEndPoints().get(0), trouble.getEndPoints().get(1), null));
            temp = trouble;

            DecisionSection[] listS = ordersec(d.sections);
            for (int j = 0; j < listS.length; j++) {
                DecisionSection s = listS[j];

                x = temp.getX().split("p")[0];
                String y = temp.getY().split("p")[0];
                Element troubles = new Element(s.getSection().getName(), Integer.parseInt(x) + "px", Integer.parseInt(y) + 120 + "px");
                troubles.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                troubles.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                troubles.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                if (s.getStatus() != null) {
                    if (s.getStatus().equals("done")) {
                        troubles.setStyleClass("ui-diagram-success");
                    }
                }

                model.addElement(troubles);
                model.connect(createConnection(temp.getEndPoints().get(2), troubles.getEndPoints().get(1), null));
                temp = troubles;
                Element tempJob = temp;
                x = tempJob.getX().split("p")[0];
                StepsAndDecsionsJob[] listj = orderJob(s.getJob());
                for (int k = 0; k < listj.length; k++) {
                    StepsAndDecsionsJob job = listj[k];

                    y = tempJob.getY().split("p")[0];
                    Element troublej = new Element(job.jobPath.getName(), Integer.parseInt(x) + "px", Integer.parseInt(y) + 120 + "px");
                    troublej.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                    troublej.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                    troublej.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                    if (job.decisionsJob.getStatus() != null) {
                        if (job.decisionsJob.getStatus().equals("done")) {
                            troublej.setStyleClass("ui-diagram-success");
                        } else if (job.decisionsJob.getStatus().equals("reject")) {
                            troublej.setStyleClass("ui-diagram-fail");
                        }
                    }
                    model.addElement(troublej);
                    model.connect(createConnection(tempJob.getEndPoints().get(2), troublej.getEndPoints().get(1), null));

                    tempJob = troublej;
                }
                temp = trouble;

            }

        }
        String x = temp.getX().split("p")[0];
        Element end = new Element("", (Integer.parseInt(x) + ((String) temp.getData()).length() * 6 + 200) + "px", temp.getY());
        end.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
        end.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
        end.setStyleClass("ui-diagram-end");
        model.connect(createConnection(temp.getEndPoints().get(0), end.getEndPoints().get(1), null));

        model.addElement(end);

//        Element trouble = new Element("Do you meet some trouble?", "20em", "18em");
//        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
//        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
//        trouble.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//         
//        Element giveup = new Element("Do you give up?", "20em", "30em");
//        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
//        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//        giveup.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//         
//        Element succeed = new Element("Succeed", "50em", "18em");
//        succeed.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//        succeed.setStyleClass("ui-diagram-success");
//         
//        Element fail = new Element("Fail", "50em", "30em");
//        fail.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//        fail.setStyleClass("ui-diagram-fail");
//        model.addElement(trouble);
//        model.addElement(giveup);
//        model.addElement(succeed);
//        model.addElement(fail);
//        model.connect(createConnection(start.getEndPoints().get(0), trouble.getEndPoints().get(0), null));
//        model.connect(createConnection(trouble.getEndPoints().get(1), giveup.getEndPoints().get(0), "Yes"));
//        model.connect(createConnection(trouble.getEndPoints().get(2), succeed.getEndPoints().get(0), "No"));
//        model.connect(createConnection(giveup.getEndPoints().get(2), fail.getEndPoints().get(0), "Yes"));
    }

    public DiagramModel getModel() {
        return model;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    private StepsAndDecsions[] orderDeb() {
        StepsAndDecsions[] deps = new StepsAndDecsions[stepsAndDecsions.size()];
        int i = 0;
        for (StepsAndDecsions stepsAndDecsion : stepsAndDecsions) {
            deps[i] = stepsAndDecsion;
            i++;
        }
        for (int j = 0; j < deps.length - 1; j++) {
            for (int k = 0; k < deps.length - 1; k++) {
                if (deps[k].departmentPaths.order > deps[k + 1].departmentPaths.order) {
                    StepsAndDecsions temp = deps[k];
                    deps[k] = deps[k + 1];
                    deps[k + 1] = temp;
                }
            }

        }
        return deps;
    }

    private DecisionSection[] ordersec(List<DecisionSection> s) {
        DecisionSection[] sec = new DecisionSection[s.size()];
        int i = 0;
        for (DecisionSection ds : s) {
            sec[i] = ds;
            i++;
        }
        for (int j = 0; j < sec.length - 1; j++) {
            for (int k = 0; k < sec.length - 1; k++) {
                if (sec[k].getSection().getOrder() > sec[k + 1].getSection().getOrder()) {
                    DecisionSection temp = sec[k];
                    sec[k] = sec[k + 1];
                    sec[k + 1] = temp;
                }
            }

        }
        return sec;
    }

    private StepsAndDecsionsJob[] orderJob(List<StepsAndDecsionsJob> jo) {
        StepsAndDecsionsJob[] job = new StepsAndDecsionsJob[jo.size()];
        int i = 0;
        for (StepsAndDecsionsJob dj : jo) {
            job[i] = dj;
            i++;
        }
        for (int j = 0; j < job.length - 1; j++) {
            for (int k = 0; k < job.length - 1; k++) {
                System.out.println(job[k]);
                if (job[k].jobPath.getOrder() > job[k + 1].jobPath.getOrder()) {
                    StepsAndDecsionsJob temp = job[k];
                    job[k] = job[k + 1];
                    job[k + 1] = temp;
                }
            }

        }
        return job;
    }

}

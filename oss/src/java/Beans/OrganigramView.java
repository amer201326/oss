package Beans;

import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Section;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.component.organigram.OrganigramHelper;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

@ManagedBean
@ViewScoped
public class OrganigramView implements Serializable {

    

    private OrganigramNode rootNode;
    private OrganigramNode selection;

    private boolean zoom = false;
    private String style = "width: 800px";
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;
    Department d;
    private String employeeName;

    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = parameterMap.get("id");
        d = GetFromDB.getDepartmentById(id);
        selection = new DefaultOrganigramNode(null, "Ridvan Agar", null);

        rootNode = new DefaultOrganigramNode("department", "دائرة : " + d.nameA, null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(false);
        rootNode.setSelectable(false);

        List<Section> sec = GetFromDB.getFsection(d.id);
        String p[] = new String[sec.size()];

        for (int i = 0; i < p.length; i++) {
            Section s = sec.get(i);

            OrganigramNode rootNodee2 = new DefaultOrganigramNode("section", "قسم : " + s.getName(), null);

            rootNode.getChildren().add(rootNodee2);

            try {
                List<JobTitel> jobs = GetFromDB.getJobTittle(s.getId());
//                for (int j = 0; j < jobs.size(); j++) {
//                    JobTitel get = jobs.get(j);
//                    List<Employee> emp = GetFromDB.GetEmployeeForJobID(get.getId());
//                    String[] empName = new String[emp.size()];
//                    OrganigramNode divisionNode = new DefaultOrganigramNode("job", get.getName(), rootNodee2);
//
//                    System.out.println(divisionNode.getType());
//                    for (int k = 0; k < emp.size(); k++) {
//                        Employee get1 = emp.get(k);
//                        empName[k] = get1.getEmp_name();
//                        OrganigramNode employeeNode = new DefaultOrganigramNode("employee", empName[k], divisionNode);
//
//                    }
//
//                }

            } catch (Exception e) {
                break;
            }

        }

    }

    

    protected OrganigramNode addDivision(OrganigramNode parent, String name, String... employees) {
        OrganigramNode divisionNode = new DefaultOrganigramNode("division", name, parent);
        divisionNode.setDroppable(true);
        divisionNode.setDraggable(false);
        divisionNode.setSelectable(false);

        if (employees != null) {
            for (String employee : employees) {
                OrganigramNode employeeNode = new DefaultOrganigramNode("employee", employee, divisionNode);
                employeeNode.setDraggable(true);
                employeeNode.setSelectable(true);
            }
        }

        return divisionNode;
    }

    public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' moved from " + event.getSourceOrganigramNode().getData() + " to '" + event.getTargetOrganigramNode().getData() + "'");
        message.setSeverity(FacesMessage.SEVERITY_INFO);

        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeSelectListener(OrganigramNodeSelectEvent event) {
        System.out.println("event se;le");

        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);

        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeExpandListener(OrganigramNodeExpandEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);

        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void removeDivision() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        setMessage(currentSelection.getData() + " will entfernt werden.", FacesMessage.SEVERITY_INFO);
    }

    public void removeEmployee() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);
        currentSelection.getParent().getChildren().remove(currentSelection);
    }

    public void addEmployee() {
        // re-evaluate selection - might be a differenct object instance if viewstate serialization is enabled
        OrganigramNode currentSelection = OrganigramHelper.findTreeNode(rootNode, selection);

        OrganigramNode employee = new DefaultOrganigramNode("employee", employeeName, currentSelection);
        employee.setDraggable(true);
        employee.setSelectable(true);
    }

    private void setMessage(String msg, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(severity);
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public OrganigramNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(OrganigramNode rootNode) {
        this.rootNode = rootNode;
    }

    public OrganigramNode getSelection() {
        return selection;
    }

    public void setSelection(OrganigramNode selection) {
        this.selection = selection;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getLeafNodeConnectorHeight() {
        return leafNodeConnectorHeight;
    }

    public void setLeafNodeConnectorHeight(int leafNodeConnectorHeight) {
        this.leafNodeConnectorHeight = leafNodeConnectorHeight;
    }

    public boolean isAutoScrollToSelection() {
        return autoScrollToSelection;
    }

    public void setAutoScrollToSelection(boolean autoScrollToSelection) {
        this.autoScrollToSelection = autoScrollToSelection;
    }

   
}

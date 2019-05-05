/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class JobPath implements Serializable {

    int DepId;
    int sectionID;

    Integer id;
    int idService;
    String name;
    Integer dOrder;
    int sOrder;
    int order;
    public String importantComment;
    String idMarge;

    public JobPath() {
    }

//    public JobPath(Integer id, int order) {
//        this.id = id;
//        this.order = order;
//    }

//    public JobPath(int sectionID, int id, String name, int order) {
//        this.sectionID = sectionID;
//        this.id = id;
//        this.name = name;
//        this.order = order;
//        idMarge = id + "-" + sectionID;
//    }

    public JobPath(int DepId, int sectionID, int id, int dOrder, int sOrder, int order) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
        idMarge = id + "-" + sectionID;
    }

    public JobPath(int DepId, int sectionID, int id, String name, int dOrder, int sOrder, int order) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.name = name;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
        
    }
  public JobPath(int DepId, int sectionID, int id, String name) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.name = name;
    }
    

    public JobPath(int DepId, int sectionID, Integer id, int idService, String name, Integer dOrder, int sOrder, int order, String importantComment) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.idService = idService;
        this.name = name;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
        this.importantComment = importantComment;
    }

    public JobPath(int DepId, int sectionID, Integer id, int idService, Integer dOrder, int sOrder, int order) {
        this.DepId = DepId;
        this.sectionID = sectionID;
        this.id = id;
        this.idService = idService;
        this.dOrder = dOrder;
        this.sOrder = sOrder;
        this.order = order;
    }
    

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getIdMarge() {
        return idMarge;
    }

    public void setIdMarge(String idMarge) {
        this.idMarge = idMarge;
    }

    public void idMarge() {
        idMarge = id + "-" + sectionID;
    }

    public int getDepId() {
        return DepId;
    }

    public void setDepId(int DepId) {
        this.DepId = DepId;
    }

    @Override
    public String toString() {
        return "JobPath{" + "sectionID=" + sectionID + ", id=" + id + ", name=" + name + ", order=" + order + '}';
    }

    public boolean addToDataBase(int idService) throws SQLException, ClassNotFoundException {
   
            DB d = new DB();
            String q = "INSERT INTO steps_job VALUES(" + DepId + "," + sectionID + "," + id + "," + idService + "," + dOrder + "," + sOrder + "," + order + "," + importantComment +");";
            System.out.println(q);
            d.write(q);

        

        return false;
    }

        public String getImportantCommentFromDataBase() throws SQLException, ClassNotFoundException {
   
            DB d = new DB();
            String q = "SELECT important_Comment FROM oss.steps_job where Dep_ID="+DepId+" and Sec_ID="+sectionID+" and Job_ID="+id +" and Services_Provided_ID="+idService +" and Order_Departmant="+dOrder+" and Order_Section ="+sOrder+" and Order_Job="+order+" ;";
            System.out.println(q);
            ResultSet r = d.read(q);
            while(r.next()){
                return r.getString(1);
            }

        return null;
    }
  
      
    public int getdOrder() {
        return dOrder;
    }

    public void setdOrder(int dOrder) {
        this.dOrder = dOrder;
    }

    public int getsOrder() {
        return sOrder;
    }

    public void setsOrder(int sOrder) {
        this.sOrder = sOrder;
    }
    public String kes(){
        return this.DepId+"-"+this.sectionID+"-"+this.id;
    }

    public String getImportantComment() {
        return importantComment;
    }

    public void setImportantComment(String importantComment) {
        this.importantComment = importantComment;
    }
    

}

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me
 */
public class DepartmentPaths implements Serializable {

    public int id;
    public String nameA;
    public String image;
    public int idService;
    public Integer order;
    public String importantComment;
    public List<SectionPath> sections = new ArrayList<>();

    
    public DepartmentPaths() {
        sections = new ArrayList<>();
           
    }

    public DepartmentPaths(int id, String nameA, String image, int idService, Integer order, String importantComment) {
        this.id = id;
        this.nameA = nameA;
        this.image = image;
        this.idService = idService;
        this.order = order;
        this.importantComment = importantComment;
    }
    
    

    public DepartmentPaths(int id, String nameA, Integer order, String importantComment) {
        this.id = id;
        this.nameA = nameA;
        this.order = order;
        this.importantComment = importantComment;
    }

    public DepartmentPaths(int id, Integer order ) {
        this.id = id;
        this.order = order;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public DepartmentPaths(String nameA) {
        this.nameA = nameA;
    }


    public DepartmentPaths(int id, String nameA, String image) {
        this.id = id;
        this.nameA = nameA;
        this.image = image;
    }

    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   
   

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    
 

    public boolean addToDataBase(int idService) throws SQLException, ClassNotFoundException {
   
            DB d = new DB();
            String q = "INSERT INTO steps_department VALUES ("+id+","+idService+","+order+",'"+importantComment+"');";
            System.out.println(q);
            d.write(q);
            
        
       
        return false;
    }

  
    
    public List<SectionPath> getSections() {
        return sections;
    }

    public void setSections(List<SectionPath> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "DepartmentPaths{" + "id=" + id + ", nameA=" + nameA + ", image=" + image + ", order=" + order + ", sections=" + sections + '}';
    }

    public String getImportantComment() {
        return importantComment;
    }

    public void setImportantComment(String importantComment) {
        this.importantComment = importantComment;
    }

    

    

    
}

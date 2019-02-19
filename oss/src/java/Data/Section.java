package Data;

import java.io.Serializable;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author me
 */
public class Section implements Serializable{
    String departmentId;
    String id;
    String name;
    String departmentNmae;
    

    public Section() {
    }

    public Section(int departmentId, int id, String name, String departmentNmae) {
        this.departmentId = departmentId+"";
        this.id = id+"";
        this.name = name;
        this.departmentNmae = departmentNmae;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    

    

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getDepartmentNmae() {
        return departmentNmae;
    }

    public void setDepartmentNmae(String departmentNmae) {
        this.departmentNmae = departmentNmae;
    }

    @Override
    public String toString() {
        return "Section{" + "departmentId=" + departmentId + ", id=" + id + ", name=" + name + ", departmentNmae=" + departmentNmae + '}';
    }
    
    
   
    
}

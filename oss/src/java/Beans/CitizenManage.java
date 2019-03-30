/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Citizen;
import Data.CitizenAccount;
import Data.GetDB_Eman;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Eman
 */
@ManagedBean
@ViewScoped
public class CitizenManage implements Serializable {

    Citizen newCitizen;
    List<Citizen> allCitizen;
    Citizen citizenSelected;

    public CitizenManage() {

        newCitizen = new Citizen();
        allCitizen = GetDB_Eman.getallCitizen();
        citizenSelected = new Citizen();

    }

    public void addCitizen() {

        newCitizen.addCitizenToDB();

        //انتقال الي صفحة كل المواطنين
    }

    public List<Citizen> getAllCitizen() {
        return allCitizen;
    }

    public void setAllCitizen(List<Citizen> allCitizen) {
        this.allCitizen = allCitizen;
    }

    public Citizen getCitizenSelected() {
        return citizenSelected;
    }

    public void setCitizenSelected(Citizen citizenSelected) {
        this.citizenSelected = citizenSelected;
    }

    public Citizen getNewCitizen() {
        return newCitizen;
    }

    public void setNewCitizen(Citizen newCitizen) {
        this.newCitizen = newCitizen;
    }
    
    public void onCitizenEdit(RowEditEvent event){
        Citizen c = (Citizen)event.getObject();
        c.citizenUpdate();
    }
    public void onCitizenEditCancel(RowEditEvent event){
        
        
    }

}

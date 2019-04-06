/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.CitizenService;
import Data.GetFromDBaraa;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author baraakali
 */
@ManagedBean
@ViewScoped
public class NotDoneCiticenServiceManeger implements Serializable{
    
    public ArrayList<CitizenService> NotDoneCitizenServices(int idCitizen){
          return GetFromDBaraa.notDoneCitizenServices(idCitizen);
     }
}

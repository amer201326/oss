/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Data.Citizen;
import Data.Department;
import Data.Employee;
import Data.GetFromDB;
import Data.JobTitel;
import Data.Manager;
import Data.Section;
import Data.SectionPath;
import Data.Service;
import Data.ServiceErr;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Amer$_$
 */
@ManagedBean(name = "msession")
@SessionScoped
public class Session implements Serializable{
    
    boolean login;
    String typeAccount;
    
    String username;
    String passWord;
    public Session() {
        login = false;
        typeAccount = "";
        
    }
    
    public void login(){
        System.out.println("login");
        if(username.startsWith("a-")){
            Manager m = GetFromDB.getManagerAccount(username,passWord);
        }
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    
    

   

    
    
   

   

   
    
    
 
}

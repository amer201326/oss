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
 * @author Eman
 */
public class CitizenProfile implements Serializable {
    int id;
    String FirstName;
    String FatherName;
    String GrandFatherName;
    String LastName;
    String Gender;
   
    int familyMember;
    String idCard;
    String telephone;
    String mobile;
    String email;

    String fax;
    String birthday;

    String placeOfBirth;

    String region;
    String quarter;
    String street;

    String address;
    String citizenJob;
    String passportNumber;
    String passportType;
    CitizenAccount account;

    public CitizenProfile() {
    }
    
    

    public CitizenProfile(int id, String FirstName, String FatherName, String GrandFatherName, String LastName, 
            String Gender, int familyMember, String idCard, String telephone, String mobile, String email, 
            String fax, String birthday, String placeOfBirth, String region, String quarter, String street,
            String address, String citizenJob, String passportNumber, String passportType) {
        this.id = id;
        this.FirstName = FirstName;
        this.FatherName = FatherName;
        this.GrandFatherName = GrandFatherName;
        this.LastName = LastName;
        this.Gender = Gender;
        this.familyMember = familyMember;
        this.idCard = idCard;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.fax = fax;
        this.birthday = birthday;
        this.placeOfBirth = placeOfBirth;
        this.region = region;
        this.quarter = quarter;
        this.street = street;
        this.address = address;
        this.citizenJob = citizenJob;
        this.passportNumber = passportNumber;
        this.passportType = passportType;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }

    public String getGrandFatherName() {
        return GrandFatherName;
    }

    public void setGrandFatherName(String GrandFatherName) {
        this.GrandFatherName = GrandFatherName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(int familyMember) {
        this.familyMember = familyMember;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCitizenJob() {
        return citizenJob;
    }

    public void setCitizenJob(String citizenJob) {
        this.citizenJob = citizenJob;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public CitizenAccount getAccount() {
        return account;
    }

    public void setAccount(CitizenAccount account) {
        this.account = account;
    }
    
    
     public String updateCitizenProfile() {
        String q = "UPDATE citizen SET Cit_FirstName = '" + FirstName + "',Cit_FatherName = '" + FatherName
                + "', Cit_GrandfatherName = '" + GrandFatherName + "',Cit_LastName = '" + LastName
                + "', Cit_Gender = '" + Gender + "',Cit_FamilyMembers = '" + familyMember
                + "', Cit_ID_Card = '" + idCard + "',Cit_Telephone = '" + telephone + "', Cit_Mobile = '"
                + mobile + "',Cit_Email = '" + email + "', Cit_Fax = '" + fax + "',Cit_Birthday = '" + birthday
                + "',Cit_PlaceOfBirth = '" + placeOfBirth + "', Cit_Region = '" + region + "',Cit_Quarter = '" + quarter
                + "',Cit_Street = '" + street + "', Cit_Address = '" + address + "',Cit_Job = '" + citizenJob
                + "',Cit_PassportNumber = '" + passportNumber + "', Cit_PassportType = '" + passportNumber
                + "' WHERE (Cit_ID = " + id + ");";
        try {
            DB data = new DB();

            data.write(q);
            
            

        } catch (Exception ex) {
            Logger.getLogger(Citizen.class.getName()).log(Level.SEVERE, null, ex);
            return "يوجد مشكلة في  بيانات المستخدم";
        }
        return account.updateCit();

    }
     
    
    
    
}

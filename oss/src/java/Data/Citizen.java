/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class Citizen implements Serializable {

    int id;
    String FirstName;
    String FatherName;
    String GrandFatherName;
    String LastName;
    String Gender;

    int last = 0;
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

    public Citizen(int id, String FirstName, String FatherName, String GrandFatherName, String LastName, String Gender, int familyMember,
            String idCard, String telephone, String mobile, String email, String fax, String birthday, String placeOfBirth, String region,
            String quarter, String street, String address, String citizenJob, String passportNumber, String passportType) {
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

    public Citizen(int id, String FirstName, String FatherName, String GrandFatherName, String LastName, String idCard, String mobile, String email, String region) {
        this.id = id;
        this.FirstName = FirstName;
        this.FatherName = FatherName;
        this.GrandFatherName = GrandFatherName;
        this.LastName = LastName;
        this.idCard = idCard;
        this.mobile = mobile;
        this.email = email;
        this.region = region;
    }

    public Citizen() {
        account = new CitizenAccount();
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public CitizenAccount getAccount() {
        return account;
    }

    public void setAccount(CitizenAccount account) {
        this.account = account;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public void addCitizenToDB() {

        int idMax = GetDB_Eman.getMaxIdCitizen();
        this.id = idMax + 1;
        String q = "INSERT INTO citizen (`Cit_ID`, `Cit_FirstName`, `Cit_FatherName`, `Cit_GrandfatherName`, `Cit_LastName`, `Cit_Gender`, "
                + "`Cit_FamilyMembers`, `Cit_ID_Card`, `Cit_Telephone`, `Cit_Mobile`, `Cit_Email`,`Cit_Fax`, `Cit_Birthday`,`Cit_PlaceOfBirth`, "
                + "`Cit_Region`,`Cit_Quarter`, `Cit_Street`,`Cit_Address`, `Cit_Job`, `Cit_PassportNumber`,`Cit_PassportType`) "
                + "VALUES (" + id + ",'" + FirstName + "','" + FatherName + "','" + GrandFatherName + "','" + LastName + "','" + Gender + "',"
                + familyMember + ",'" + idCard + "','" + telephone + "','" + mobile + "','" + email
                + "','" + fax + "','" + birthday + "','" + placeOfBirth + "','"
                + region + "','" + quarter + "','" + street + "','" + address + "','" + citizenJob + "','" + passportNumber + "','"
                + passportType + "');";

        try {
            DB data = new DB();

            System.out.println(q);
            data.write(q);
            account.Cit_ID = this.id;
            account.addCitizenAccountToDB();

        } catch (SQLException ex) {
            System.out.println("error Add citizen");
            Logger.getLogger(Citizen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("error Add citizen");
            Logger.getLogger(Citizen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void citizenUpdate() {
        try {

            String q = "UPDATE citizen SET Cit_Email = '" + email + "',Cit_ID_Card = '" + idCard + "', Cit_Mobile = '" + mobile + "',Cit_Region = '" + region + "' WHERE (Cit_ID = " + id + ");";
            System.out.println(q);
            DB data = new DB();
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Citizen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String updateCitizen() {
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

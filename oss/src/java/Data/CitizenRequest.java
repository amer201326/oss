/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import static Data.GetFromDB.k;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eman
 */
public class CitizenRequest implements Serializable {

    int Cit_ID;
    String Cit_FirstName;
    String Cit_FatherName;
    String Cit_GrandfatherName;
    String Cit_LastName;
    String Cit_Gender;
    int Cit_FamilyMembers;
    String Cit_ID_Card;
    String Cit_Telephone;
    String Cit_Mobile;
    String Cit_Email;
    String Cit_Fax;
    String Cit_Birthday;
    String Cit_PlaceOfBirth;
    String Cit_Region;
    String Cit_Quarter;
    String Cit_Street;
    String Cit_Address;
    String Cit_Job;
    String Cit_PassportNumber;
    String Cit_PassportType;
    String Cit_Username;
    String Cit_Password;
    String Cit_Status;

    CitizenAccount account = new CitizenAccount();

    public CitizenRequest() {

    }

    public CitizenRequest(int Cit_ID, String Cit_FirstName, String Cit_FatherName, String Cit_GrandfatherName, String Cit_LastName, String Cit_Gender, int Cit_FamilyMembers, String Cit_ID_Card, String Cit_Telephone, String Cit_Mobile, String Cit_Email, String Cit_Fax, String Cit_Birthday, String Cit_PlaceOfBirth, String Cit_Region, String Cit_Quarter, String Cit_Street, String Cit_Address, String Cit_Job, String Cit_PassportNumber, String Cit_PassportType, String Cit_Username, String Cit_Password) {
        this.Cit_ID = Cit_ID;
        this.Cit_FirstName = Cit_FirstName;
        this.Cit_FatherName = Cit_FatherName;
        this.Cit_GrandfatherName = Cit_GrandfatherName;
        this.Cit_LastName = Cit_LastName;
        this.Cit_Gender = Cit_Gender;
        this.Cit_FamilyMembers = Cit_FamilyMembers;
        this.Cit_ID_Card = Cit_ID_Card;
        this.Cit_Telephone = Cit_Telephone;
        this.Cit_Mobile = Cit_Mobile;
        this.Cit_Email = Cit_Email;
        this.Cit_Fax = Cit_Fax;
        this.Cit_Birthday = Cit_Birthday;
        this.Cit_PlaceOfBirth = Cit_PlaceOfBirth;
        this.Cit_Region = Cit_Region;
        this.Cit_Quarter = Cit_Quarter;
        this.Cit_Street = Cit_Street;
        this.Cit_Address = Cit_Address;
        this.Cit_Job = Cit_Job;
        this.Cit_PassportNumber = Cit_PassportNumber;
        this.Cit_PassportType = Cit_PassportType;
        this.Cit_Username = Cit_Username;
        this.Cit_Password = Cit_Password;
    }

    public CitizenRequest(int Cit_ID, String Cit_FirstName, String Cit_FatherName, String Cit_GrandfatherName, String Cit_LastName, String Cit_Gender, int Cit_FamilyMembers, String Cit_ID_Card, String Cit_Telephone, String Cit_Mobile, String Cit_Email, String Cit_Fax, String Cit_Birthday, String Cit_PlaceOfBirth, String Cit_Region, String Cit_Quarter, String Cit_Street, String Cit_Address, String Cit_Job, String Cit_PassportNumber, String Cit_PassportType, String Cit_Username, String Cit_Password, String Cit_Status) {
        this.Cit_ID = Cit_ID;
        this.Cit_FirstName = Cit_FirstName;
        this.Cit_FatherName = Cit_FatherName;
        this.Cit_GrandfatherName = Cit_GrandfatherName;
        this.Cit_LastName = Cit_LastName;
        this.Cit_Gender = Cit_Gender;
        this.Cit_FamilyMembers = Cit_FamilyMembers;
        this.Cit_ID_Card = Cit_ID_Card;
        this.Cit_Telephone = Cit_Telephone;
        this.Cit_Mobile = Cit_Mobile;
        this.Cit_Email = Cit_Email;
        this.Cit_Fax = Cit_Fax;
        this.Cit_Birthday = Cit_Birthday;
        this.Cit_PlaceOfBirth = Cit_PlaceOfBirth;
        this.Cit_Region = Cit_Region;
        this.Cit_Quarter = Cit_Quarter;
        this.Cit_Street = Cit_Street;
        this.Cit_Address = Cit_Address;
        this.Cit_Job = Cit_Job;
        this.Cit_PassportNumber = Cit_PassportNumber;
        this.Cit_PassportType = Cit_PassportType;
        this.Cit_Username = Cit_Username;
        this.Cit_Password = Cit_Password;
        this.Cit_Status = Cit_Status;
    }

    
    
    public CitizenAccount getAccount() {
        return account;
    }

    public void setAccount(CitizenAccount account) {
        this.account = account;
    }

    public int getCit_ID() {
        return Cit_ID;
    }

    public void setCit_ID(int Cit_ID) {
        this.Cit_ID = Cit_ID;
    }

    public String getCit_FirstName() {
        return Cit_FirstName;
    }

    public void setCit_FirstName(String Cit_FirstName) {
        this.Cit_FirstName = Cit_FirstName;
    }

    public String getCit_FatherName() {
        return Cit_FatherName;
    }

    public void setCit_FatherName(String Cit_FatherName) {
        this.Cit_FatherName = Cit_FatherName;
    }

    public String getCit_GrandfatherName() {
        return Cit_GrandfatherName;
    }

    public void setCit_GrandfatherName(String Cit_GrandfatherName) {
        this.Cit_GrandfatherName = Cit_GrandfatherName;
    }

    public String getCit_LastName() {
        return Cit_LastName;
    }

    public void setCit_LastName(String Cit_LastName) {
        this.Cit_LastName = Cit_LastName;
    }

    public String getCit_Gender() {
        return Cit_Gender;
    }

    public void setCit_Gender(String Cit_Gender) {
        this.Cit_Gender = Cit_Gender;
    }

    public int getCit_FamilyMembers() {
        return Cit_FamilyMembers;
    }

    public void setCit_FamilyMembers(int Cit_FamilyMembers) {
        this.Cit_FamilyMembers = Cit_FamilyMembers;
    }

    public String getCit_ID_Card() {
        return Cit_ID_Card;
    }

    public void setCit_ID_Card(String Cit_ID_Card) {
        this.Cit_ID_Card = Cit_ID_Card;
    }

    public String getCit_Telephone() {
        return Cit_Telephone;
    }

    public void setCit_Telephone(String Cit_Telephone) {
        this.Cit_Telephone = Cit_Telephone;
    }

    public String getCit_Mobile() {
        return Cit_Mobile;
    }

    public void setCit_Mobile(String Cit_Mobile) {
        this.Cit_Mobile = Cit_Mobile;
    }

    public String getCit_Email() {
        return Cit_Email;
    }

    public void setCit_Email(String Cit_Email) {
        this.Cit_Email = Cit_Email;
    }

    public String getCit_Fax() {
        return Cit_Fax;
    }

    public void setCit_Fax(String Cit_Fax) {
        this.Cit_Fax = Cit_Fax;
    }

    public String getCit_Birthday() {
        return Cit_Birthday;
    }

    public void setCit_Birthday(String Cit_Birthday) {
        this.Cit_Birthday = Cit_Birthday;
    }

    public String getCit_PlaceOfBirth() {
        return Cit_PlaceOfBirth;
    }

    public void setCit_PlaceOfBirth(String Cit_PlaceOfBirth) {
        this.Cit_PlaceOfBirth = Cit_PlaceOfBirth;
    }

    public String getCit_Region() {
        return Cit_Region;
    }

    public void setCit_Region(String Cit_Region) {
        this.Cit_Region = Cit_Region;
    }

    public String getCit_Quarter() {
        return Cit_Quarter;
    }

    public void setCit_Quarter(String Cit_Quarter) {
        this.Cit_Quarter = Cit_Quarter;
    }

    public String getCit_Street() {
        return Cit_Street;
    }

    public void setCit_Street(String Cit_Street) {
        this.Cit_Street = Cit_Street;
    }

    public String getCit_Address() {
        return Cit_Address;
    }

    public void setCit_Address(String Cit_Address) {
        this.Cit_Address = Cit_Address;
    }

    public String getCit_Job() {
        return Cit_Job;
    }

    public void setCit_Job(String Cit_Job) {
        this.Cit_Job = Cit_Job;
    }

    public String getCit_PassportNumber() {
        return Cit_PassportNumber;
    }

    public void setCit_PassportNumber(String Cit_PassportNumber) {
        this.Cit_PassportNumber = Cit_PassportNumber;
    }

    public String getCit_PassportType() {
        return Cit_PassportType;
    }

    public void setCit_PassportType(String Cit_PassportType) {
        this.Cit_PassportType = Cit_PassportType;
    }

    public String getCit_Username() {
        return Cit_Username;
    }

    public void setCit_Username(String Cit_Username) {
        this.Cit_Username = Cit_Username;
    }

    public String getCit_Password() {
        return Cit_Password;
    }

    public void setCit_Password(String Cit_Password) {
        this.Cit_Password = Cit_Password;
    }

    public String getCit_Status() {
        return Cit_Status;
    }

    public void setCit_Status(String Cit_Status) {
        this.Cit_Status = Cit_Status;
    }

    public void citizenRequestUpdate() {
        try {

            String q = "UPDATE oss.requestcitizen SET Cit_Email = '" + Cit_Email + "',Cit_ID_Card = '" + Cit_ID_Card
                    + "', Cit_Mobile = '" + Cit_Mobile + "',Cit_Region = '" + Cit_Region
                    + "' WHERE (Cit_ID = " + Cit_ID + ");";
            System.out.println(q);
            DB data = new DB();
            data.write(q);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCitizenRequestToDB() {

        int idMax = GetDB_Eman.getMaxIdCitizenRequest();
        this.Cit_ID = idMax + 1;
        Cit_Password = Crypto.encPas(k, Cit_Password);
        String q = "INSERT INTO  oss.requestcitizen (`Cit_ID`, `Cit_FirstName`, `Cit_FatherName`, `Cit_GrandfatherName`, `Cit_LastName`, `Cit_Gender`, "
                + "`Cit_FamilyMembers`, `Cit_ID_Card`, `Cit_Telephone`, `Cit_Mobile`, `Cit_Email`,`Cit_Fax`, `Cit_Birthday`,`Cit_PlaceOfBirth`, "
                + "`Cit_Region`,`Cit_Quarter`, `Cit_Street`,`Cit_Address`, `Cit_Job`, `Cit_PassportNumber`,`Cit_PassportType`, `Cit_Username`, `Cit_Password`) \n"
                + "VALUES (" + Cit_ID + ",'" + Cit_FirstName + "','" + Cit_FatherName + "','" + Cit_GrandfatherName + "','" + Cit_LastName + "','" + Cit_Gender + "',"
                + Cit_FamilyMembers + ",'" + Cit_ID_Card + "','" + Cit_Telephone + "','" + Cit_Mobile + "','" + Cit_Email
                + "','" + Cit_Fax + "','" + Cit_Birthday + "','" + Cit_PlaceOfBirth + "','"
                + Cit_Region + "','" + Cit_Quarter + "','" + Cit_Street + "','" + Cit_Address + "','" + Cit_Job + "','" + Cit_PassportNumber + "','"
                + Cit_PassportType + "','" + Cit_Username + "','" + Cit_Password + "');";

        try {
            DB data = new DB();

            System.out.println(q);
            data.write(q);

        } catch (SQLException ex) {
            System.out.println("error Add citizen");
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("error Add citizen");
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void acceptCitizenRequestAddToDB() {

        int idMax = GetDB_Eman.getMaxIdCitizen();
        this.Cit_ID = idMax + 1;

        try {
            DB data = new DB();
            String q = "start transaction;";
            data.write(q);

            q = "INSERT INTO  oss.citizen (`Cit_ID`, `Cit_FirstName`, `Cit_FatherName`, `Cit_GrandfatherName`, `Cit_LastName`, `Cit_Gender`, "
                    + "`Cit_FamilyMembers`, `Cit_ID_Card`, `Cit_Telephone`, `Cit_Mobile`, `Cit_Email`,`Cit_Fax`, `Cit_Birthday`,`Cit_PlaceOfBirth`, "
                    + "`Cit_Region`,`Cit_Quarter`, `Cit_Street`,`Cit_Address`, `Cit_Job`, `Cit_PassportNumber`,`Cit_PassportType`)"
                    + "VALUES (" + Cit_ID + ",'" + Cit_FirstName + "','" + Cit_FatherName + "','" + Cit_GrandfatherName + "','" + Cit_LastName + "','" + Cit_Gender + "',"
                    + Cit_FamilyMembers + ",'" + Cit_ID_Card + "','" + Cit_Telephone + "','" + Cit_Mobile + "','" + Cit_Email
                    + "','" + Cit_Fax + "','" + Cit_Birthday + "','" + Cit_PlaceOfBirth + "','"
                    + Cit_Region + "','" + Cit_Quarter + "','" + Cit_Street + "','" + Cit_Address + "','" + Cit_Job + "','" + Cit_PassportNumber + "','"
                    + Cit_PassportType + "');";

            System.out.println(q);
            data.write(q);

            account.Cit_ID = this.Cit_ID;
            account.userName = Cit_Username;
            account.password = Cit_Password;
            System.out.println(Cit_ID + "222222222");
            account.addCitizenAccountReuqestToDB();

            q = "DELETE FROM `oss`.`requestcitizen` WHERE (`Cit_Username` = '" + Cit_Username + "');";
            System.out.println(q);
            data.write(q);

            q = "commit;";
            //q = "rollback;";
            System.out.println(q);
            data.write(q);

        } catch (SQLException ex) {
            try {
                System.out.println("error Add citizen");
                DB data = new DB();
                String q = "rollback;";
                data.write(q);
                Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("error Add citizen");
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCitizenRequest() {

        String q = "UPDATE oss.requestcitizen SET Cit_FirstName = '" + Cit_FirstName + "',Cit_FatherName = '" + Cit_FatherName
                + "', Cit_GrandfatherName = '" + Cit_GrandfatherName + "',Cit_LastName = '" + Cit_LastName
                + "', Cit_Gender = '" + Cit_Gender + "',Cit_FamilyMembers = '" + Cit_FamilyMembers
                + "', Cit_ID_Card = '" + Cit_ID_Card + "',Cit_Telephone = '" + Cit_Telephone + "', Cit_Mobile = '"
                + Cit_Mobile + "',Cit_Email = '" + Cit_Email + "', Cit_Fax = '" + Cit_Fax + "',Cit_Birthday = '" + Cit_Birthday
                + "',Cit_PlaceOfBirth = '" + Cit_PlaceOfBirth + "', Cit_Region = '" + Cit_Region + "',Cit_Quarter = '" + Cit_Quarter
                + "',Cit_Street = '" + Cit_Street + "', Cit_Address = '" + Cit_Address + "',Cit_Job = '" + Cit_Job
                + "',Cit_PassportNumber = '" + Cit_PassportNumber + "', Cit_PassportType = '" + Cit_PassportType
                + "',Cit_Username = '" + Cit_Username + "', Cit_Password = '" + Cit_Password
                + "' WHERE (Cit_ID = " + Cit_ID + ");";
        try {
            DB data = new DB();
            data.write(q);
            System.out.println(q);
        } catch (SQLException ex) {
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rejectCitizenRequestDeleteFromDB() {

        String q = "UPDATE oss.requestcitizen SET Cit_Status = 'rejected' WHERE (Cit_ID = " + Cit_ID + ");";
        try {
            DB data = new DB();
            data.write(q);
            System.out.println(q);
        } catch (SQLException ex) {
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CitizenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}

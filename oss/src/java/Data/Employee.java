/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DB.DB;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author E
 */
public class Employee implements Serializable {

    //Table employee
    int dep_id;
    int sec_id;
    int job_id;
    int emp_id;
    int last = 0;
    String emp_name;
    String emp_idCard;
    String emp_email;
    String emp_tel;
    String emp_mobile;
    String emp_gender;
    String emp_birth;
    Date birthDate;
    String emp_StartDate;
    String emp_EndDate;
    List<Screen> screens;
    EmployeeAccount account;
    
    public Employee() {
    }

    public Employee(int dep_id, int sec_id, int job_id, int emp_id, String emp_name, String emp_idCard, String emp_email, String emp_tel, String emp_birth, String emp_StartDate, String emp_EndDate, String emp_mobile, String emp_gender) {
        this.dep_id = dep_id;
        this.sec_id = sec_id;
        this.job_id = job_id;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_idCard = emp_idCard;
        this.emp_email = emp_email;
        this.emp_tel = emp_tel;
        this.emp_birth = emp_birth;
        this.emp_StartDate = emp_StartDate;
        this.emp_EndDate = emp_EndDate;
        this.emp_mobile = emp_mobile;
        this.emp_gender = emp_gender;

    }

    public Employee(int dep_id, int emp_id, String emp_name, String emp_email, String emp_mobile) {
        this.dep_id = dep_id;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_email = emp_email;
        this.emp_mobile = emp_mobile;
    }

    public Employee(String emp_idCard, int emp_id, String emp_name, String emp_mobile, String emp_email) {
        this.emp_idCard = emp_idCard;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_mobile = emp_mobile;
        this.emp_email = emp_email;

    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {

        this.dep_id = dep_id;
    }

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id(int sec_id) {
        this.sec_id = sec_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_idCard() {
        return emp_idCard;
    }

    public void setEmp_idCard(String emp_idCard) {
        this.emp_idCard = emp_idCard;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_tel() {
        return emp_tel;
    }

    public void setEmp_tel(String emp_tel) {
        this.emp_tel = emp_tel;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_birth() {
        return emp_birth;
    }

    public void setEmp_birth(String emp_birth) {
        this.emp_birth = emp_birth;
    }

    public String getEmp_StartDate() {
        return emp_StartDate;
    }

    public void setEmp_StartDate(String emp_StartDate) {
        this.emp_StartDate = emp_StartDate;
    }

    public String getEmp_EndDate() {
        return emp_EndDate;
    }

    public void setEmp_EndDate(String emp_EndDate) {
        this.emp_EndDate = emp_EndDate;
    }

    public void addEmployeeToDB() {
        
        last += 1;
        String q = "INSERT INTO employees (`Dep_ID`, `Sec_ID`, `Job_ID`, `Emp_ID`, `Emp_Name`, `Emp_ID_Card`, `Emp_Email`, `Emp_Telephone`, `Emp_Birthday`, `Emp_StartDate`, `Emp_Mobile`,`Emp_Gender`) \n"
                + "VALUES (" + dep_id + "," + sec_id + "," + job_id + "," + emp_id + ",'" + emp_name + "','" + emp_idCard + "','" + emp_email + "','"
                + emp_tel + "','" + emp_birth + "','" + LocalDate.now() + "','" + emp_mobile + "','" + emp_gender + "');";

        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);
//            for (int i = 0; i < screens.size(); i++) {
//                Screen get = screens.get(i);
//                
//                
//            }

        } catch (SQLException ex) {
            System.out.println("error Add Employee");
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("error Add Employee");
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void addEmpAccountToDB() {
//        String q = "INSERT INTO employeeaccount (`Emp_ID`, `UserName`, `Password`) VALUES (" + emp_id + ",'" + emp_username + "','" + emp_password + "');";
//
//        try {
//            DB data = new DB();
//            data.write(q);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public void update() {
        String q = "UPDATE employees SET Emp_Email = '" + emp_email + "',Emp_Mobile = '" + emp_mobile + "',Emp_Name = '" + emp_name + "' WHERE Emp_ID = " + emp_id + ";";
        try {
            DB data = new DB();
            data.write(q);
            System.out.println(q);
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
     public String updateEmployee() {
        String q = "UPDATE employees SET Emp_Name = '" + emp_name + "',Emp_ID_Card = '" + emp_idCard
                + "', Emp_Email = '" + emp_email + "',Emp_Telephone = '" + emp_tel
                + "', Emp_Birthday = '" + emp_birth + "',Emp_StartDate = '" + emp_StartDate
                + "', Emp_EndDate = '" + emp_EndDate + "',Emp_Mobile = '" + emp_mobile + "', Emp_Gender = '"
                + emp_gender + "' WHERE (Emp_ID = " + emp_id + ");";
        try {
            DB data = new DB();

            data.write(q);
            
            

        } catch (Exception ex) {
            Logger.getLogger(Citizen.class.getName()).log(Level.SEVERE, null, ex);
            return "يوجد مشكلة في  بيانات المستخدم";
        }
        return account.updateEmp();

    }
    
    

    @Override
    public String toString() {
        return "Employee{" + "dep_id=" + dep_id + ", sec_id=" + sec_id + ", job_id=" + job_id + ", emp_id=" + emp_id + ", last=" + last + ", emp_name=" + emp_name + ", emp_idCard=" + emp_idCard + ", emp_email=" + emp_email + ", emp_tel=" + emp_tel + ", emp_mobile=" + emp_mobile + ", emp_gender=" + emp_gender + ", emp_birth=" + emp_birth + ", birthDate=" + birthDate + ", emp_StartDate=" + emp_StartDate + ", emp_EndDate=" + emp_EndDate + '}';
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format(birthDate);
        this.emp_birth = dateString;
        this.birthDate = birthDate;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public EmployeeAccount getAccount() {
        return account;
    }

    public void setAccount(EmployeeAccount account) {
        this.account = account;
    }
    
    

}

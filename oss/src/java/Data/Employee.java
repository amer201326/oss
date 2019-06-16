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

    String dep_name;
    String sec_name;
    String job_name;

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
    String type;
    List<Screen> screens;
    
    EmployeeAccount account = new EmployeeAccount();

    JobTitel jobTitel = new JobTitel();
    public Employee() {
    }

    public Employee(String emp_name) {
        this.emp_name = emp_name;
    }

    
    
    public Employee(int dep_id, int sec_id, int job_id, int emp_id, String emp_name, 
            String emp_idCard, String emp_email, String emp_tel, String emp_birth, 
            String emp_StartDate, String emp_EndDate, String emp_mobile, String emp_gender, String type,
            String dep_name, String sec_name, String job_name) {
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
        this.type = type;
        this.dep_name = dep_name;
        this.sec_name = sec_name;
        this.job_name = job_name;
       
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

    public Employee(int emp_id, String emp_name, String emp_idCard, String emp_email, String emp_tel, String emp_birth,
            String emp_StartDate, String emp_EndDate, String emp_mobile, String emp_gender, String dep_name,
            String sec_name, String job_name) {
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
        this.dep_name = dep_name;
        this.sec_name = sec_name;
        this.job_name = job_name;

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

        try {
            DB data = new DB();
            String q = "start transaction;";
            System.out.println(q);
            data.write(q);

            q = "INSERT INTO employees (`Dep_ID`, `Sec_ID`, `Job_ID`, `Emp_ID`, `Emp_Name`, `Emp_ID_Card`, `Emp_Email`, `Emp_Telephone`, `Emp_Birthday`, `Emp_StartDate`, `Emp_Mobile`,`Emp_Gender`,`type`)"
                    + "VALUES (" + dep_id + "," + sec_id + "," + job_id + "," + emp_id + ",'" + emp_name + "','" + emp_idCard + "','" + emp_email + "','"
                    + emp_tel + "','" + emp_birth + "','" + LocalDate.now() + "','" + emp_mobile + "','" + emp_gender + "','" + type + "');";
            System.out.println(q);
            data.write(q);
           
            account.addEmpAccountToDB();

            for (int i = 0; i < screens.size(); i++) {
                Screen get = screens.get(i);
                get.addToDB(account.UserName);
            }

             q = "commit;";
            //q = "rollback;";
            System.out.println(q);
            data.write(q);
            
        } catch (SQLException | ClassNotFoundException ex) {
              
            try {
                DB data = new DB();
                String q = "rollback;";
                System.out.println(q);
                data.write(q);
                
            } catch (SQLException ex1) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex1);
            }
                
                
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
        String q = "UPDATE oss.employees SET Emp_Name = '" + emp_name + "',Emp_ID_Card = '" + emp_idCard
                + "', Emp_Email = '" + emp_email + "',Emp_Telephone = '" + emp_tel
                + "' ,Emp_Mobile = '" + emp_mobile + "' WHERE (Emp_ID = " + emp_id + ");";
        try {
            DB data = new DB();
            System.out.println(q);
            data.write(q);

        } catch (Exception ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            return "يوجد مشكلة في  بيانات المستخدم";
        }
        return account.updateEmp();

    }
    
    
    public String updateEmployeePassword() {
        
        return account.updateEmp();

    }
    

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getSec_name() {
        return sec_name;
    }

    public void setSec_name(String sec_name) {
        this.sec_name = sec_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    @Override
    public String toString() {
        return "Employee{" + "dep_id=" + dep_id + ", sec_id=" + sec_id + ", job_id=" + job_id + ", emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_idCard=" + emp_idCard + ", emp_email=" + emp_email + ", emp_tel=" + emp_tel + ", emp_mobile=" + emp_mobile + ", emp_gender=" + emp_gender + ", emp_birth=" + emp_birth + ", birthDate=" + birthDate + ", emp_StartDate=" + emp_StartDate + ", emp_EndDate=" + emp_EndDate + '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public EmployeeAccount getAccount() {
        return account;
    }

    public void setAccount(EmployeeAccount account) {
        this.account = account;
    }

    public JobTitel getJobTitel() {
        return jobTitel;
    }

    public void setJobTitel(JobTitel jobTitel) {
        this.jobTitel = jobTitel;
    }

   
    public boolean checkTypeEMP() {
        return type.compareTo("e") == 0;
    }

    public boolean checkTypeHed() {
        return type.compareTo("h") == 0;
    }

        public boolean checkTypeAdmin() {
        return type.compareTo("a") == 0;
    }
        
}

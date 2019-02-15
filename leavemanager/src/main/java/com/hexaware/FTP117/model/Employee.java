package com.hexaware.FTP117.model;

import com.hexaware.FTP117.persistence.DbConnection;
import com.hexaware.FTP117.persistence.EmployeeDAO;
import java.text.SimpleDateFormat;

import java.util.Objects;
import java.util.Date;
import java.util.List;


/**
 * Employee class to store employee personal details.
 * @author hexware
 */
public class Employee {
  /**
   * empId to store employee Id.
   */
  private int empId;
  /**
   * empFullName to store employee FullName.
   */
  private String empFullName;
  /**
   * empEmail to store employee Email.
   */
  private String empEmail;
  /**
   * empMobile to store employee Mobile.
   */
  private String empMobile;
  /**
   * empDateOfJoining to store employee Date of joining.
   */
  private Date empDateOfJoining;
  /**
   * empDeparmentName to store employee Department.
   */
  private String empDeparment;
  /**
   * empMgrId to store employee manager id.
   */
  private int empMgrId;
  /**
   * empAvailableLeave to store employee AvailableLeaves.
   */
  private int empAvailableLeave;


  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee emp = (Employee) obj;
    if (Objects.equals(empId, emp.empId)
        && Objects.equals(empFullName, emp.empFullName)
        && Objects.equals(empMobile, emp.empMobile)
        && Objects.equals(empEmail, emp.empEmail)
        && Objects.equals(empDeparment, emp.empDeparment)
        && Objects.equals(empDateOfJoining, emp.empDateOfJoining)
        && Objects.equals(empMgrId, emp.empMgrId)
        && Objects.equals(empAvailableLeave, emp.empAvailableLeave)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(empId, empFullName, empMobile, empEmail, empDeparment, empDateOfJoining, empMgrId,
                        empAvailableLeave);
  }
  @Override
  public final String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String doj = sdf.format(empDateOfJoining);
    return " EmpId:" + empId + " EmpName:" + empFullName + " EmpPhoneNo:"
             + empMobile + " EmpEmail:" + empEmail + " EmpDept:"
             + empDeparment + " EmpJoinDate:" + doj + " EmpMgrId:"
             + empMgrId + " EmpAvailableLeave:" + empAvailableLeave;
  }
  /**
   * Default Constructor of Employee.
   */
  public Employee() {
  }
  /**
   * @param argEmpId to initialize employee id.
   * @param argEmpFullName to initialize employee FullName.
   * @param argEmpMobile to initialize employee mobile.
   * @param argEmpEmail to initialize employee email.
   * @param argEmpDepartment to initialize employee Department.
   * @param argEmpDateOfJoining to initialize employee Date of Joining.
   * @param argEmpMgrId to initialize employee manager Id.
   * @param argEmpAvailableLeave to initialize employee AvailableLeave.
   */
  public Employee(final int argEmpId, final String argEmpFullName, final String argEmpEmail,
                 final String argEmpMobile,
                 final String argEmpDepartment, final Date argEmpDateOfJoining,
                 final int argEmpMgrId, final int argEmpAvailableLeave) {
    this.empId = argEmpId;
    this.empFullName = argEmpFullName;
    this.empMobile = argEmpMobile;
    this.empEmail = argEmpEmail;
    this.empDeparment = argEmpDepartment;
    this.empDateOfJoining = argEmpDateOfJoining;
    this.empMgrId = argEmpMgrId;
    this.empAvailableLeave = argEmpAvailableLeave;
  }
  /**
   * Gets the EmployeeId.
   * @return this Employee's ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   *
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }
  /**
   * Gets the EmployeeName.
   * @return this Employee's Name.
   */
  public final String getEmpFullName() {
    return empFullName;
  }
  /**
   * @param argEmpFullName to set employee name.
   */
  public final void setEmpFullName(final String argEmpFullName) {
    this.empFullName = argEmpFullName;
  }
  /** Gets the EmployeeEmail.
   * @return this Employee's Email.
   */
  public final String getEmpEmail() {
    return empEmail;
  }
  /**
   * @param argEmpEmail to set employee email.
   */
  public final void setEmpEmail(final String argEmpEmail) {
    this.empEmail = argEmpEmail;
  }
  /**
   * Gets the EmployeeMobile.
   * @return this Employee's Mobile.
   */
  public final String getEmpMobile() {
    return empMobile;
  }
  /**
   * @param argEmpMobile to set employee mobile.
   */
  public final void setEmpMobile(final String argEmpMobile) {
    this.empMobile = argEmpMobile;
  }
  /**
   * Gets the EmployeeDateOfJoining.
   * @return this Employee's DateofJoining.
   */
  public final Date getEmpDateOfJoining() {
    return empDateOfJoining;
  }
  /**
   * @param argEmpDateOfJoining to set employee DateOfJoining.
   */
  public final void setEmpDateOfJoining(final Date argEmpDateOfJoining) {
    this.empDateOfJoining = argEmpDateOfJoining;
  }
  /**
   * Gets the EmployeeDeparment.
   * @return this Employee's Department Name.
   */
  public final String getEmpDepartment() {
    return empDeparment;
  }
  /**
   * @param argEmpDepartment to set employee Department.
   */
  public final void setEmpDepartment(final String argEmpDepartment) {
    this.empDeparment = argEmpDepartment;
  }
  /**
   * @Gets the Empolyee Manager.
   * @return this Employee's manager Id.
   */
  public final int getEmpMgrId() {
    return empMgrId;
  }
  /**
   * @param argEmpMgrId to set employee managerId.
   */
  public final void setEmpMgrId(final int argEmpMgrId) {
    this.empMgrId = argEmpMgrId;
  }
  /**
   * @Gets the Employee Avaliable leave.
   * @return this Employee's avaliable leave.
   */
  public final int getEmpAvailabeLeave() {
    return empAvailableLeave;
  }
  /**
   * @param argEmpAvailabeLeave to set employee AvailabeLeave.
   */
  public final void setEmpAvailabeLeave(final int argEmpAvailabeLeave) {
    this.empAvailableLeave = argEmpAvailabeLeave;
  }

  /**
   * The dao for employee.
   * @return returning connection.
   */
  public static EmployeeDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

  /**
   * list all employee details.
   * @return all employees' details
   */
  public static Employee[] listAll() {

    List<Employee> es = dao().list();
    return es.toArray(new Employee[es.size()]);
  }

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static Employee listById(final int empID) {
    return dao().find(empID);
  }

}

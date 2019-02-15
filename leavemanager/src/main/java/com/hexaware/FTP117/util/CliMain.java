package com.hexaware.FTP117.util;
import java.util.Scanner;
import java.text.ParseException;
import com.hexaware.FTP117.model.Employee;
import com.hexaware.FTP117.model.LeaveType;
import com.hexaware.FTP117.model.LeaveDetails;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("-----------------------");
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply Leave");
    System.out.println("4. Approve/Deny");
    System.out.println("5. Leave History");
    System.out.println("6. Pending Leave");
    System.out.println("7. Exit");
    System.out.println("Enter your choice:");
    int menuOption = 0;
    try {
      menuOption = Integer.parseInt(option.next());
      mainMenuDetails(menuOption);
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the Correct Option");
      mainMenu();
    }
  }
  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        applyLeave();
        break;
      case 4:
        approveDeny();
        break;
      case 5:
        leaveHistory();
        break;
      case 6:
        listPending();
        break;
      case 7:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose from 1 to 7");
    }
    mainMenu();
  }

  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    System.out.println("---------------------------------------------------------------------"
                      +
                      "----------------------------------------------------------------------");
    System.out.println("EMP_ID\tEMP_FULL_NAME\tEMP_EMAIL\tEMP_MOBILE_NUMBER\t "
                      +
                      "EMP_DATE_OF_JOINING\tEMP_DEPARTMENT\tEMP_MGR_ID\tEMP_AVAILABLE_LEAVE");
    System.out.println("---------------------------------------------------------------------"
                      +
                      "----------------------------------------------------------------------");
    for (Employee e : employee) {
      System.out.println(e.getEmpId() + "\t" + e.getEmpFullName() + "\t" + e.getEmpEmail()
                        + "\t" + e.getEmpMobile() + "\t" + e.getEmpDateOfJoining() + "\t"
                        + "\t" + e.getEmpDepartment() + "\t\t" + e.getEmpMgrId() + "\t\t\t"
                        + e.getEmpAvailabeLeave());
    }
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    //int empId = option.nextInt();
    int empId = 0;
    try {
      empId = Integer.parseInt(option.next());
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the Correct EmpId");
      listEmployeeDetail();
    }
    if (empId != 0) {
      Employee employee = Employee.listById(empId);
      if (employee == null) {
        System.out.println("Sorry, No such employee");
      } else {
        System.out.println("----------------------------------------------------------------------"
                          +
                          "---------------------------------------------------------------------");
        System.out.println("EMP_ID\tEMP_FULL_NAME\tEMP_EMAIL\tEMP_MOBILE_NUMBER\t "
                          +
                          "EMP_DATE_OF_JOINING\tEMP_DEPARTMENT\tEMP_MGR_ID\tEMP_AVAILABLE_LEAVE");
        System.out.println("---------------------------------------------------------------------"
                          +
                          "----------------------------------------------------------------------");
        System.out.println(employee.getEmpId() + "\t" + employee.getEmpFullName() + "\t" + employee.getEmpEmail()
                          + "\t" + employee.getEmpMobile() + "\t" + employee.getEmpDateOfJoining() + "\t"
                          + "\t" + employee.getEmpDepartment() + "\t\t" + employee.getEmpMgrId() + "\t\t\t"
                          + employee.getEmpAvailabeLeave());
      }
    }
  }


  private void applyLeave() {
    System.out.println("Enter the Employee Id: ");
    int empId = 0;
    try {
      empId = Integer.parseInt(option.next());
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the Correct EmpId");
      applyLeave();
    }
    System.out.println("Enter StartDate :(yyyy-MM-dd)");
    String levStartDate = option.next();
    System.out.println("Enter EndDate :(yyyy-MM-dd)");
    String levEndDate = option.next();
    System.out.println("Enter no of leave days");
    int levNoOfDays = option.nextInt();
    System.out.println("Enter leaveType: ");
    try {
      String levType = option.next();
      levType = levType.toUpperCase();
      LeaveType lt = LeaveType.valueOf(levType);
      if (lt == LeaveType.EL || lt == LeaveType.PL || lt == LeaveType.ML) {
        System.out.println("Enter leave Reason: ");
        String levReason = option.next();
        String res = "";
        try {
          res = LeaveDetails.applyLeave(empId, lt, levStartDate, levEndDate, levReason, levNoOfDays);
          System.out.println(res);
        } catch (ParseException e) {
          System.out.println("Please Enter Proper Date");
        }
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Please Enter the EL/PL/ML");
    }
  }
  private void approveDeny() {
    System.out.println("Enter Leave Id : ");
    int leavId = 0;
    try {
      leavId = Integer.parseInt(option.next());
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the leavId in Integer");
      approveDeny();
    }
    System.out.println("Enter Manager Id : ");
    int mgrId = option.nextInt();
    System.out.println("Approved (YES/NO)");
    String status = option.next();
    status = status.toUpperCase();
    if (status.equals("YES") || status.equals("NO")) {
      System.out.println("Manager Comments : ");
      String mgrCon =  option.next();
      String res = LeaveDetails.approveDeny(leavId, mgrId, status, mgrCon);
      System.out.println(res);
    } else {
      approveDeny();
    }
  }

  private void listPending() {
    System.out.println("Enter Employee(Manager)ID");
    //int empId = option.nextInt();
    int empId = 0;
    try {
      empId = Integer.parseInt(option.next());
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the Correct EmpId");
      listPending();
    }
    if (empId != 0) {
      LeaveDetails[] listPending = LeaveDetails.listPending(empId);
      System.out.println("----------------------------------------------------------------------"
                        +
                        "----------------------------------------------------------------------");
      System.out.println("LEA_EMP_ID\tLEA_ID\tLEA_LEAVE_TYPE\tLEA_START_DATE\tLEA_END_DATE\t"
                        +
                        "LEA_APPLIED_ON\tLEA_REASON\tLEA_NO_OF_DAYS\tLEA_LEAVE_STATUS\tLEA_MGR_COMMENTS");
      System.out.println("---------------------------------------------------------------------"
                        +
                        "----------------------------------------------------------------------");
      for (LeaveDetails e : listPending) {
        System.out.println(e.getLeaveEmpId() + "\t\t" + e.getLeaveId() + "\t\t" + e.getLeaveType()
                          + "\t" + e.getLeaveStartDate() + "\t" + e.getLeaveEndDate() + "\t"
                          + "  " + e.getLeaveAppliedOn() + "\t" + e.getLeaveReason() + "\t\t"
                          + e.getLeaveNoOfDays() + "\t" + e.getLeaveStatus() + "\t" + e.getLeaveMgrComments());
      }
    }
  }
  private void leaveHistory() {
    System.out.println("Enter Employee ID");
    //int empId = option.nextInt();
    int empId = 0;
    try {
      empId = Integer.parseInt(option.next());
    } catch (NumberFormatException  e) {
      System.out.println("Please Enter the Correct EmpId");
      leaveHistory();
    }
    if (empId != 0) {
      LeaveDetails[] leaveHistory = LeaveDetails.listAll(empId);
      System.out.println("----------------------------------------------------------------------"
                        +
                        "----------------------------------------------------------------------");
      System.out.println("LEA_EMP_ID\tLEA_ID\tLEA_LEAVE_TYPE\tLEA_START_DATE\tLEA_END_DATE\t"
                        +
                        "LEA_APPLIED_ON\tLEA_REASON\tLEA_NO_OF_DAYS\tLEA_LEAVE_STATUS\tLEA_MGR_COMMENTS");
      System.out.println("---------------------------------------------------------------------"
                        +
                        "----------------------------------------------------------------------");
      for (LeaveDetails e : leaveHistory) {
        System.out.println(e.getLeaveEmpId() + "\t\t" + e.getLeaveId() + "\t\t" + e.getLeaveType()
                        + "\t" + e.getLeaveStartDate() + "\t" + e.getLeaveEndDate() + "\t"
                        + "  " + e.getLeaveAppliedOn() + "\t" + e.getLeaveReason() + "\t\t"
                        + e.getLeaveNoOfDays() + "\t" + e.getLeaveStatus() + "\t" + e.getLeaveMgrComments());
      }
    }
  }
  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}

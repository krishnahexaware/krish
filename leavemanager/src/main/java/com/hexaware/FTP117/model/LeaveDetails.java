package com.hexaware.FTP117.model;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.hexaware.FTP117.persistence.DbConnection;
import com.hexaware.FTP117.persistence.EmployeeDAO;
import com.hexaware.FTP117.persistence.LeaveDetailsDAO;
import java.util.Objects;
import java.util.List;
import java.text.ParseException;


/**
 * LeaveDetails class to store Leave Details of employee.
 * @author hexaware.
 */
public class LeaveDetails {
  /**
   * leaveEmpId to store employee id.
   */
  private int leaveEmpId;
  /**
   * leaveId to store employee leave.
   */
  private int leaveId;
  /**
   * leavetype to store employee leavetype.
   */
  private LeaveType leaveType;
  /**
   * LeaveStartDate to store employee Leave Start Date.
   */
  private Date leaveStartDate;
  /**
   * LeaveEndDate to Store employee leave End date.
   */
  private Date leaveEndDate;
  /**
   * LeaveAppliedOn to Store employee leaved applie date.
   */
  private Date leaveAppliedOn;
  /**
   * leaveReason to store employee leave Reason.
   */
  private String leaveReason;
  /**
   * LeaveNoOfDays to store employee applied for no of days.
   */
  private int leaveNoOfDays;
  /**
   * leaStatus to store employee status of applied leave.
   */
  private LeaveStatus leaveStatus;
  /**
   * LeaveMgrComments to store managers comments on leave.
   */
  private String leaveMgrComments;

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LeaveDetails lev = (LeaveDetails) obj;
    if (Objects.equals(leaveEmpId, lev.leaveEmpId) && Objects.equals(leaveId, lev.leaveId)
        && Objects.equals(leaveType, lev.leaveType) && Objects.equals(leaveStartDate, lev.leaveStartDate)
        && Objects.equals(leaveEndDate, lev.leaveEndDate) && Objects.equals(leaveAppliedOn, lev.leaveAppliedOn)
        && Objects.equals(leaveReason, lev.leaveReason) && Objects.equals(leaveNoOfDays, lev.leaveNoOfDays)
        && Objects.equals(leaveStatus, lev.leaveStatus) && Objects.equals(leaveMgrComments, lev.leaveMgrComments)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveEmpId, leaveId, leaveType, leaveStartDate, leaveEndDate, leaveAppliedOn, leaveReason,
                        leaveNoOfDays, leaveStatus, leaveMgrComments);
  }
  @Override
  public final String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String sDate = sdf.format(leaveStartDate);
    String eDate = sdf.format(leaveEndDate);
    String aDate = sdf.format(leaveAppliedOn);

    return " LeaveEmpId: " + leaveEmpId + " Leave Id: " + leaveId + " Leave Type: "
             + leaveType + " leave Start Date: " + sDate + " leave End Date: "
             + eDate + " LeaveAppliedOn: " + aDate + "Leave Reason: "
             + leaveReason + " LeaveNoOfDays: " + leaveNoOfDays + "Leave Status: " + leaveStatus
             + " Leave Manager Comments: " + leaveMgrComments;
  }
  /**
   * Default Constructor.
   */
  public LeaveDetails() {
  }

  /**
   * @param argLeaveEmpId to initialize employee id.
   * @param argLeaveId to initialize Leave Id .
   * @param argLeaveType to initialize Leave Type.
   * @param argLeaveStartDate to initialize Start Date Of Leave.
   * @param argLeaveEndDate to initialize End date of Leave.
   * @param argLeaveAppliedOn to initialize Applied on.
   * @param argLeaveReason to initialize Leave  Reason.
   * @param argLeaveNoOfDays to initialize No of days Leave applied.
   * @param argLeaveStatus to initialize Status of applied Leave.
   * @param argLeaveMgrComments to initialize Manager Comments.
   */
  public LeaveDetails(final int argLeaveEmpId, final int argLeaveId, final LeaveType argLeaveType,
                      final Date argLeaveStartDate, final Date argLeaveEndDate, final Date argLeaveAppliedOn,
                      final String argLeaveReason, final int argLeaveNoOfDays, final LeaveStatus argLeaveStatus,
                      final String argLeaveMgrComments) {
    this.leaveEmpId = argLeaveEmpId;
    this.leaveId = argLeaveId;
    this.leaveType = argLeaveType;
    this.leaveStartDate = argLeaveStartDate;
    this.leaveEndDate = argLeaveEndDate;
    this.leaveAppliedOn = argLeaveAppliedOn;
    this.leaveReason = argLeaveReason;
    this.leaveNoOfDays = argLeaveNoOfDays;
    this.leaveStatus = argLeaveStatus;
    this.leaveMgrComments = argLeaveMgrComments;

  }
  /**
   * Gets the Em.
   * @return this Employee's ID.
   */
  public final int getLeaveEmpId() {
    return leaveEmpId;
  }
  /**
   * @param argLeaveEmpId to set Leave id.
   */
  public final void setLeaveEmpId(final int argLeaveEmpId) {
    this.leaveEmpId = argLeaveEmpId;
  }
  /**
   * Gets the LeaveID.
   * @return this Leave ID.
   */
  public final int getLeaveId() {
    return leaveId;
  }
  /**
   * @param argLeaveId to set Leave id.
   */
  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }
  /**
   * Gets the LeaveTYpe.
   * @return this Employee's ID.
   */
  public final LeaveType getLeaveType() {
    return leaveType;
  }
  /**
   * @param argLeaveType to set Leave Type.
   */
  public final void setLeaveType(final LeaveType argLeaveType) {
    this.leaveType = argLeaveType;
  }
  /**
   * Gets the Leave Start Date.
   * @return this Leave Start Date.
   */
  public final Date getLeaveStartDate() {
    return leaveStartDate;
  }
  /**
   * @param argLeaveStartDate to set Leave StartDate.
   */
  public final void setLeaveStartDate(final Date argLeaveStartDate) {
    this.leaveStartDate = argLeaveStartDate;
  }
  /**
   * Gets the Leave End Date.
   * @return this Leave End Date.
   */
  public final Date getLeaveEndDate() {
    return leaveEndDate;
  }
  /**
   * @param argLeaveEndDate to set Leave EndDate.
   */
  public final void setLeaveEndDate(final Date argLeaveEndDate) {
    this.leaveEndDate = argLeaveEndDate;
  }
  /**
   * Gets the Leave Applied Date.
   * @return this Leave Applied Date.
   */
  public final Date getLeaveAppliedOn() {
    return leaveAppliedOn;
  }
  /**
   * @param argLeaveAppliedOn to set Leave Applied On.
   */
  public final void setLeaveAppliedOn(final Date argLeaveAppliedOn) {
    this.leaveAppliedOn = argLeaveAppliedOn;
  }
  /**
   * Gets the Leave Reason.
   * @return this Leave Reason.
   */
  public final String getLeaveReason() {
    return leaveReason;
  }
  /**
   * @param argLeaveReason to set Leave Reason.
   */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }
  /**
   * Gets the No of Leaves Applied.
   * @return this No Of leaves Applied.
   */
  public final int getLeaveNoOfDays() {
    return leaveNoOfDays;
  }
  /**
   * @param argLeaveNoOfDays to set No of Days.
   */
  public final void setLeaveNoOfDays(final int argLeaveNoOfDays) {
    this.leaveNoOfDays = argLeaveNoOfDays;
  }
  /**
   * Gets the Leave Status.
   * @return this Leave Status.
   */
  public final LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }
  /**
   * @param argLeaveStatus to set Leave Status.
   */
  public final void setLeaveStatus(final LeaveStatus argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }
  /**
   * Gets the Manager Comments on Leave.
   * @return this Manager Comments on Leave.
   */
  public final String getLeaveMgrComments() {
    return leaveMgrComments;
  }
  /**
   * @param argLeaveMgrComments to set Leave Manager Comments.
   */
  public final void setLeaveMgrComments(final String argLeaveMgrComments) {
    this.leaveMgrComments = argLeaveMgrComments;
  }

  /**
   * @return the connection.
   */
  public static EmployeeDAO edao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }

   /**
   * @return the connection..
   */
  public static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }

  /**
   * @param argLeaveId to set leave Id.
   * @param argEmpMgrId to set manager Id.
   * @param argStatus to set Approve or Deny.
   * @param argMgrComments to set manager Response.
   * @return leave appiled Statement.
   */

  public static String approveDeny(final int argLeaveId,
                                    final int argEmpMgrId,
                                    final String argStatus,
                                    final String argMgrComments) {
    String res = "";
    LeaveDetails ld = LeaveDetails.listById(argLeaveId);
    if (ld != null) {
      int noDays = ld.getLeaveNoOfDays();
      int empId = ld.getLeaveEmpId();
      Employee ed = Employee.listById(empId);
      int leaveAvail = ed.getEmpAvailabeLeave();
      leaveAvail = leaveAvail + noDays;
      int mgrId = ed.getEmpMgrId();
      String leaveStatus = "";
      if (mgrId != argEmpMgrId) {
        res = "You are unauthorised to approve the leave..";
      } else {
        if (argStatus.equals("YES")) {
          res = "Leave Approved Successfully";
          leaveStatus = "APPROVED";
          dao().approveDeny(leaveStatus, argMgrComments, argLeaveId);
        } else {
          res = "Leave Rejected";
          leaveStatus = "DENIED";
          dao().approveDeny(leaveStatus, argMgrComments, argLeaveId);
          edao().increment(leaveAvail, empId);
        }
      }

    } else {
      res = "Leave ID IS NOT Found";
    }

    return res;
  }
  /**
  *list all LeaveHistory Details.
  *@param empId id to get Leave details.
  *@return all Leave details.
   */
  public static LeaveDetails[] listAll(final int empId) {
    List<LeaveDetails> es = dao().leaveHistory(empId);
    return es.toArray(new LeaveDetails[es.size()]);
  }
  /**
  * list all LeaveHistory details.
  * @param empId id to get Leave details.
  * @return all Leave details.
  */
  public static LeaveDetails[] listPending(final int empId) {
    List<LeaveDetails> es = dao().pending(empId);
    return es.toArray(new LeaveDetails[es.size()]);
  }
   /**
  * list LeaveHistory by leaveid.
  * @param leavId to get Leave details.
  * @return One Record of Leave details.
  */
  public static LeaveDetails listById(final int leavId) {
    LeaveDetails es = dao().find(leavId);
    return es;
  }
    /**
   * list all LeaveDetails details.
   * @return all LeaveDetails' details
   */
  public static LeaveDetails[] listAllData() {
    List<LeaveDetails> es = dao().list();
    return es.toArray(new LeaveDetails[es.size()]);
  }
  /**
   * To apply for leave.
   * @param argLeaveEmpId to set leave ID.
   * @param argLeaveType to set leave type.
   * @param argLeaveStartDate to set start date of leave.
   * @param argLeaveEndDate to set end date of leave.
   * @param argLeaveReason to set reason for leave.
   * @param argLeaveNoOfDays to set no of days of leave.
   * @throws IllegalArgumentException to handle parse exception.
   * @throws ParseException to handle parse exception.
   * @return string from apply leave.
   */

  public static String applyLeave(final int argLeaveEmpId,
                                 final LeaveType argLeaveType,
                                 final String argLeaveStartDate,
                                 final String argLeaveEndDate,
                                 final String argLeaveReason,
                                 final int argLeaveNoOfDays) throws ParseException {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Date sDate = sdf.parse(argLeaveStartDate);
    Date eDate = sdf.parse(argLeaveEndDate);
    long diff = eDate.getTime() - sDate.getTime();
    int noDays = (int) diff / (1000 * 60 * 60 * 24);
    //System.out.println(noDays);
    String res = "";
    //Employee e = edao().find(argLeaveEmpId);
    Employee e = Employee.listById(argLeaveEmpId);
    if (e != null) {
      int levAvail = e.getEmpAvailabeLeave();
      int mgrId = e.getEmpMgrId();
      int  dayDif = levAvail - argLeaveNoOfDays;
      noDays = noDays + 1;
     // Date cur = new Date();
      final Date curDate = new Date();
      String appliedOn = sdf.format(curDate);
      int count = dao().count(argLeaveEmpId, argLeaveStartDate, argLeaveEndDate);
      Calendar calendar = Calendar.getInstance();
      Calendar calendar1 = Calendar.getInstance();
      calendar.setTime(sDate);
      calendar1.setTime(eDate);
      if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
          || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        res = "StartDate cannot be SaturDay or Sunday";
      } else if (calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
          || calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        res = "End Date cannot be SaturDay or Sunday";
      } else if (noDays <= 0) {
        res = "End date Should be greater than Start date";
      } else if (sDate.compareTo(curDate) < 0) {
        res = "You can't Apply on Past Date.";
      } else if (dayDif < 0) {
        res = "Insufficent Leave Balance...";
      } else if (noDays != argLeaveNoOfDays) {
        res = "Enter the Correct No Of days " + noDays;
      }  else if (count == 1) {
        res = "Already Applied on Given Dates...";
      } else if (mgrId == 0) {
        LeaveStatus leaveStatus = LeaveStatus.APPROVED;
        dao().insert(argLeaveEmpId, argLeaveType, leaveStatus, argLeaveStartDate,
                     argLeaveEndDate, appliedOn, argLeaveReason, argLeaveNoOfDays);
        levAvail = levAvail - argLeaveNoOfDays;
        edao().decrement(levAvail, argLeaveEmpId);
        res = "Leave Auto_Approved";
      } else {
        LeaveStatus leaveStatus = LeaveStatus.PENDING;
        dao().insert(argLeaveEmpId, argLeaveType, leaveStatus, argLeaveStartDate,
                     argLeaveEndDate, appliedOn, argLeaveReason, argLeaveNoOfDays);
        levAvail = levAvail - argLeaveNoOfDays;
        edao().decrement(levAvail, argLeaveEmpId);
        res = "Leave Applied Successfully....";
      }
    } else {
      res = "EmpId not Found";
    }

    return res;
  }
}


package com.hexaware.FTP117.persistence;

import com.hexaware.FTP117.model.LeaveDetails;
import com.hexaware.FTP117.model.LeaveType;
import com.hexaware.FTP117.model.LeaveStatus;


import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for LeaveDetails.
 */
public interface LeaveDetailsDAO  {
  /**
   * return all the details of the selected employee.
   *@param leaEmpId the id of the employee.
   * @return the LeaveDetails array.
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEA_EMP_ID= :leaEmpId")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> leaveHistory(@Bind("leaEmpId") int leaEmpId);
  /**
   * return all the details of the selected LeaveDetails.
   * @param leaId the id of the LeaveDetails.
   * @return the LeaveDetails object.
   */

  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEA_ID= :leaId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails find(@Bind("leaId") int leaId);


    /**
   * return all the details of the selected LeaveDetails.
   * @return the LeaveDetails object.
   */

  @SqlQuery("SELECT * FROM LEAVE_DETAILS")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> list();


  /**
   * return Count for Already Applied LeaveHistory.
   * @param empID the id of the Employee
   * @param leavStartDate of the StartDate.
   * @param leavEndDate of the EndDate.
   * @return total records.
   */

  @SqlQuery("SELECT COUNT(*) FROM Leave_Details WHERE LEA_EMP_ID = :empID "
      +
      " AND LEA_LEAVE_STATUS='PENDING' AND "
      +
      " (LEA_START_DATE <= :leavStartDate AND LEA_END_DATE >= :leavStartDate OR "
      +
      " LEA_START_DATE <= :leavEndDate AND LEA_END_DATE >= :leavEndDate)")
  int count(@Bind("empID") int empID,
                   @Bind("leavStartDate") String leavStartDate,
                   @Bind("leavEndDate") String leavEndDate);


  /**
   * insert all the details of the employee.
   * @param leaempid the employee id.
   * @param lealeavetype the type of leave.
   * @param lealeavestatus the leave status.
   * @param leastartdate the starting date of leave.
   * @param leaenddate the ending date of leave.
   * @param leaappliedon the date leave is applied on.
   * @param leareason the reason for leave.
   * @param leanoofdays the no of days of leave.
   */


  @SqlUpdate("INSERT INTO LEAVE_DETAILS"
            +
            "             (LEA_EMP_ID, "
            +
            "              LEA_LEAVE_TYPE, "
            +
            "              LEA_LEAVE_STATUS, "
            +
            "              LEA_START_DATE, "
            +
            "              LEA_END_DATE, "
            +
            "              LEA_APPLIED_ON, "
            +
            "              LEA_REASON, "
            +
            "              LEA_NO_OF_DAYS) "
            +
            "values       ( "
            +
            "              :leaempid, "
            +
            "              :lealeavetype, "
            +
            "              :lealeavestatus, "
            +
            "              :leastartdate, "
            +
            "              :leaenddate, "
            +
            "              :leaappliedon, "
            +
            "              :leareason, "
            +
            "              :leanoofdays)")
  void insert(@Bind("leaempid") int leaempid,
         @Bind("lealeavetype") LeaveType lealeavetype,
         @Bind("lealeavestatus") LeaveStatus lealeavestatus,
         @Bind("leastartdate") String leastartdate,
         @Bind("leaenddate") String leaenddate,
         @Bind("leaappliedon") String leaappliedon,
         @Bind("leareason") String leareason,
         @Bind("leanoofdays") int leanoofdays);
  /**
  *return all the details of the selected employee.
  * @param empId the id of the manager.
  * @return the pending Leaves of manager.
  */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEA_EMP_ID IN"
         +
         " (SELECT E2.EMP_ID FROM EMPLOYEE E1 INNER JOIN EMPLOYEE E2"
         +
         " ON E1.EMP_ID = E2.EMP_MGR_ID"
         +
         " WHERE E1.EMP_ID=:empId)"
         +
         " AND LEA_LEAVE_STATUS = 'PENDING'"
         )
       @Mapper(LeaveDetailsMapper.class)
     List<LeaveDetails> pending(@Bind("empId") int empId);

  /**
   * return all the details of the selected LeaveDetails.
   * @param leaId the id of the LeaveDetails.
   * @return the LeaveDetails object.
   */

  @SqlQuery("SELECT * FROM Leave_Details WHERE LEA_ID = :leaId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails listById(@Bind("leaId") int leaId);

  /**
   * return all the details of the selected LeaveDetails.
   * @param leaveStatus leave status of applied leave.
   * @param leaId the id of the LeaveDetails.
   * @param mgrComments the comments of Mgr.
   */

  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEA_LEAVE_STATUS = :leaveStatus, "
             +
              " LEA_MGR_COMMENTS = :mgrComments "
             +
              " WHERE LEA_ID = :leaId ")
  @Mapper(LeaveDetailsMapper.class)
  void approveDeny(@Bind("leaveStatus") String leaveStatus,
                    @Bind("mgrComments") String mgrComments,
                    @Bind("leaId") int leaId);





  /**
  * close with no args is used to close the connection.
  */
  void close();
}

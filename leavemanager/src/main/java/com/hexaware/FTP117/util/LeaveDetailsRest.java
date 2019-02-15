package com.hexaware.FTP117.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import com.hexaware.FTP117.model.Employee;
import com.hexaware.FTP117.model.LeaveDetails;


/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/LeaveDetails")
public class LeaveDetailsRest {

 /**
   * Returns a list of all the employees.
   * @return a list of all the employees
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leaveDetailsList() {
    final LeaveDetails[] leaves = LeaveDetails.listAllData();
    return leaves;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails leaveDetailsListById(@PathParam("id") final int id) {
    final LeaveDetails ld = LeaveDetails.listById(id);
    if (ld == null) {
      throw new NotFoundException("No such LeaveDetails ID: " + id);
    }
    return ld;
  }
  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("LeavePending/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leaveDetailsListPending(@PathParam("id") final int id) {
    final LeaveDetails[] ld = LeaveDetails.listPending(id);
    if (ld == null) {
      throw new NotFoundException("No such LeaveDetails ID: " + id);
    }
    return ld;
  }

  /**
   * Returns a specific employee's details.
   * @param id the id of the employee
   * @return the employee details
   */
  @GET
  @Path("Leavehistory/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leaveDetailsListHistory(@PathParam("id") final int id) {
    final LeaveDetails[] ld = LeaveDetails.listAll(id);
    if (ld == null) {
      throw new NotFoundException("No such LeaveDetails ID: " + id);
    }
    return ld;
  }

  /**
  *@param empid used to get empid.
  *@param l is used to get all leave details.
  *@return message.
  *@throws ParseException to handle parse exception.
  */
  @Path("/ApplyLeave/{empid}")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public final String applyLeave(@PathParam("empid") final int empid, final LeaveDetails l) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String sDate = sdf.format(l.getLeaveStartDate());
    String eDate = sdf.format(l.getLeaveEndDate());
    String res = LeaveDetails.applyLeave(empid, l.getLeaveType(),
                                        sDate, eDate, l.getLeaveReason(), l.getLeaveNoOfDays());
    return res;
  }
   /**
  *@param mgrid to get employee id.
  *@param status to get status.
  *@param l is used to get all leave details.
  *@return a message.
  */

  @Path("/approvedeny/{mgrid}/{status}")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public final String approveDeny(@PathParam("mgrid") final int mgrid,
                                 @PathParam("status") final String status, final LeaveDetails l) {
    String res = LeaveDetails.approveDeny(l.getLeaveId(), mgrid, status, l.getLeaveMgrComments());
    return res;
  }

}

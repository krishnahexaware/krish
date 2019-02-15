package com.hexaware.FTP117.model;

import com.hexaware.FTP117.persistence.LeaveDetailsDAO;

import com.hexaware.FTP117.persistence.EmployeeDAO;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;

//import org.junit.Before;
//import org.junit.Test;
import org.junit.runner.RunWith;


import mockit.integration.junit4.JMockit;

import java.util.ArrayList;

import org.junit.Test;
/**
 * Unit test for simple App.
 */
@RunWith(JMockit.class)

/**
 * testing for leave details.
 */
public class LeaveDetailsTest {
    /**
     * Rigorous Test.
     * @throws ParseException to handle parse exception.
     */
  @Test
  public final void defaulttest() throws ParseException {
    LeaveDetails obj1 = null;
    assertNull(obj1);
    LeaveDetails obj2 = new LeaveDetails();
    assertNotNull(obj2);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String s1 = "2019-06-12";
    String s2 = "2019-06-13";
    String s3 = "2019-06-12";
    Date sDate = sdf.parse(s1);
    Date eDate = sdf.parse(s2);
    Date aDate = sdf.parse(s3);
    LeaveType l1 = LeaveType.valueOf("EL");
    LeaveStatus l2 = LeaveStatus.valueOf("PENDING");
    LeaveDetails obj3 = new LeaveDetails(3000, 1, LeaveType.EL, sDate, eDate, aDate, "Sick", 19,
                                         LeaveStatus.PENDING, "Work");
    assertEquals(3000, obj3.getLeaveEmpId());
    assertEquals(1, obj3.getLeaveId());
    assertEquals(l1, obj3.getLeaveType());
    assertEquals(sDate, obj3.getLeaveStartDate());
    assertEquals(eDate, obj3.getLeaveEndDate());
    assertEquals(sDate, obj3.getLeaveAppliedOn());
    assertEquals("Sick", obj3.getLeaveReason());
    assertEquals(19, obj3.getLeaveNoOfDays());
    assertEquals(l2, obj3.getLeaveStatus());
    assertEquals("Work", obj3.getLeaveMgrComments());
    obj2.setLeaveEmpId(5000);
    obj2.setLeaveId(4);
    obj2.setLeaveType(l1);
    obj2.setLeaveStartDate(sDate);
    obj2.setLeaveEndDate(eDate);
    obj2.setLeaveAppliedOn(sDate);
    obj2.setLeaveReason("sick");
    obj2.setLeaveNoOfDays(6);
    obj2.setLeaveStatus(l2);
    obj2.setLeaveMgrComments("takecare");
    assertEquals(5000, obj2.getLeaveEmpId());
    assertEquals(4, obj2.getLeaveId());
    assertEquals(l1, obj2.getLeaveType());
    assertEquals(sDate, obj2.getLeaveStartDate());
    assertEquals(eDate, obj2.getLeaveEndDate());
    assertEquals(sDate, obj2.getLeaveAppliedOn());
    assertEquals("sick", obj2.getLeaveReason());
    assertEquals(6, obj2.getLeaveNoOfDays());
    assertEquals(l2, obj2.getLeaveStatus());
    assertEquals("takecare", obj2.getLeaveMgrComments());
    LeaveDetails obj4 = new LeaveDetails(3000, 1, l1, sDate, eDate, sDate, "Sick", 19, l2, "Work");
    LeaveDetails obj6 = new LeaveDetails(3000, 1, l1, sDate, eDate, sDate, "Sick", 19, l2, "Work");
    LeaveDetails obj7 = new LeaveDetails(5000, 8, l1, sDate, eDate, sDate, "Sick", 20, l2, "Work");
    assertEquals(obj3.hashCode(), obj4.hashCode());
    String res1 = " LeaveEmpId: 3000 Leave Id: 1 Leave Type: EL leave Start Date: 2019-06-12"
                 +
                 " leave End Date: 2019-06-13"
                 +
                 " LeaveAppliedOn: 2019-06-12Leave Reason: Sick"
                 +
                 " LeaveNoOfDays: 19Leave Status: PENDING Leave Manager Comments: Work";
    assertEquals(obj3.toString(), res1);
    LeaveDetails obj5 = null;
    Employee obj10 = new Employee();
    assertNotEquals(obj4, obj5);
    assertNotEquals(obj7, obj4);
    assertEquals(obj4, obj6);
    assertNotEquals(obj3, obj10);
    LeaveDetailsDAO dao = LeaveDetails.dao();
    assertNotNull(dao);
    EmployeeDAO dao1 = LeaveDetails.edao();
    assertNotNull(dao1);
  }


/**
   * tests that empty leave details list is handled correctly.
   * @param edao mocking the EmployeeDAO class.
   * @param ldao mocking the LeaveDetailsDAO class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testApplyLeave(@Mocked final EmployeeDAO edao,
                        @Mocked final LeaveDetailsDAO ldao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String s1 = "1997-08-12";
    final Date doj = sdf.parse(s1);
    final Date today = new Date();
    final String strToday = sdf.format(today);
    new Expectations() {
      {
        final Employee e = new Employee(1, "Dhivya", "Dhivya@gmail.com", "987667855", "FTP", doj, 0, 12);
        final Employee e2 = new Employee(2, "Krish", "Krish@gmail.com", "987667855", "FTP", doj, 1, 12);
        edao.find(1); result = e;
        edao.find(2); result = e2;
        edao.find(3); result = null;
      }
    };
    new Expectations() {
      {
        ldao.count(1, "2019-04-16", "2019-04-17");
        result = 1;
        ldao.count(1, "2019-04-30", "2019-04-30");
        result = 0;
      }
    };
    new Expectations() {
      {
        ldao.insert(1, LeaveType.EL, LeaveStatus.APPROVED, "2019-04-30", "2019-04-30", strToday, "Sick", 1);
        ldao.insert(2, LeaveType.EL, LeaveStatus.PENDING, "2019-04-30", "2019-04-30", strToday, "Sick", 1);
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return edao;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return ldao;
      }
    };
    String res1 = LeaveDetails.applyLeave(1, LeaveType.EL, "2019-04-30", "2019-04-30", "Sick", 1);
    assertEquals(res1, "Leave Auto_Approved");
    String res2 = LeaveDetails.applyLeave(1, LeaveType.EL, "2019-04-16", "2019-04-17", "Sick", 2);
    assertEquals(res2, "Already Applied on Given Dates...");
    String res3 = LeaveDetails.applyLeave(1, LeaveType.EL, "2019-04-16", "2019-04-17", "Sick", 1);
    assertEquals(res3, "Enter the Correct No Of days 2");
    String res4 = LeaveDetails.applyLeave(3, LeaveType.EL, "2019-04-16", "2019-04-17", "Sick", 2);
    assertEquals(res4, "EmpId not Found");
    String res5 = LeaveDetails.applyLeave(1, LeaveType.EL, "2019-04-16", "2019-04-30", "Sick", 15);
    assertEquals(res5, "Insufficent Leave Balance...");
    String res6 = LeaveDetails.applyLeave(1, LeaveType.EL, "2019-04-18", "2019-04-16", "Sick", 3);
    assertEquals(res6, "End date Should be greater than Start date");
    String res7 = LeaveDetails.applyLeave(2, LeaveType.EL, "2019-04-30", "2019-04-30", "Sick", 1);
    assertEquals(res7, "Leave Applied Successfully....");
    String res8 = LeaveDetails.applyLeave(2, LeaveType.EL, "2019-02-07", "2019-02-08", "Sick", 2);
    assertEquals(res8, "You can't Apply on Past Date.");
    String res9 = LeaveDetails.applyLeave(2, LeaveType.EL, "2019-03-30", "2019-03-31", "Sick", 2);
    assertEquals(res9, "StartDate cannot be SaturDay or Sunday");
    String res10 = LeaveDetails.applyLeave(2, LeaveType.EL, "2019-03-29", "2019-03-30", "Sick", 2);
    assertEquals(res10, "End Date cannot be SaturDay or Sunday");
  }
  /**
   * tests that empty leave details list is handled correctly.
   * @param edao mocking the EmployeeDAO class.
   * @param ldao mocking the LeaveDetailsDAO class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testApproveDeny(@Mocked final LeaveDetailsDAO  ldao,
                        @Mocked final EmployeeDAO edao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String s1 = "2019-06-12";
    final String s2 = "2019-06-13";
    final Date sDate = sdf.parse(s1);
    final Date eDate = sdf.parse(s2);
    final LeaveDetails l1 = new LeaveDetails(2, 1, LeaveType.EL, sDate, eDate, sDate, "Sick", 2,
        LeaveStatus.PENDING, "Work");
    final Employee e1 = new Employee(2, "dhivya", "dhivya@gmail.com", "245567875", "FTP", sDate, 1, 12);
    new Expectations() {
      {
        ldao.find(1); result = l1;
        ldao.find(50); result = null;
      }
    };
    new Expectations() {
      {
        edao.find(2); result = e1;
      }
    };
    new Expectations() {
      {
        ldao.approveDeny("APPROVED", "WORK_FIRST", 1);
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return edao;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return ldao;
      }
    };
    String res1 = LeaveDetails.approveDeny(1, 1, "YES", "WORK_FIRST");
    assertEquals(res1, "Leave Approved Successfully");
    String res2 = LeaveDetails.approveDeny(1, 1, "NO", "IMPOSSIBLE");
    assertEquals(res2, "Leave Rejected");
    String res3 = LeaveDetails.approveDeny(1, 3, "NO", "IMPOSSIBLE");
    assertEquals(res3, "You are unauthorised to approve the leave..");
    String res4 = LeaveDetails.approveDeny(50, 1, "NO", "IMPOSSIBLE");
    assertEquals(res4, "Leave ID IS NOT Found");
  }
   /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testPending(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
          {
            dao.pending(100);
            result = new ArrayList<LeaveDetails>();
          }
      };
    new MockUp<LeaveDetails>() {
          @Mock
          LeaveDetailsDAO dao() {
            return dao;
          }
      };
    LeaveDetails[] es = LeaveDetails.listPending(100);
    assertEquals(0, es.length);
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException when exception occurs.
   */

  public final void testListAllSome(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
          {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s1 = "2019-05-05";
            Date doj = sdf.parse(s1);
            String s2 = "2019-05-05";
            Date sd = sdf.parse(s2);
            String s3 = "2019-05-07";
            Date ed = sdf.parse(s3);
            String s4 = "2019-05-03";
            Date ad = sdf.parse(s4);
            ArrayList<LeaveDetails> es = new ArrayList<LeaveDetails>();
            LeaveType lt1 = LeaveType.valueOf("EL");
            LeaveStatus ls1 = LeaveStatus.valueOf("PENDING");
            es.add(new LeaveDetails(1, 1, lt1, doj, ed, ad, "sick", 5, ls1, "TakeCare"));
            es.add(new LeaveDetails(1, 3, lt1, sd, ed, ad, "function", 5, ls1, "enjoy"));
            es.add(new LeaveDetails(1, 2, lt1, ed, sd, ad, "marraige", 5, ls1, "enjoy"));
            es.add(new LeaveDetails(2, 4, lt1, ed, ed, ad, "sick", 5, ls1, "TakeCare"));
            dao.pending(1);
            result = es;
          }
      };
    new MockUp<LeaveDetails>() {
          @Mock
          LeaveDetailsDAO dao() {
            return dao;
          }
      };
    LeaveDetails[] es = LeaveDetails.listPending(1);
    assertEquals(4, es.length);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String s1 = "2019-05-05";
    Date doj = sdf.parse(s1);
    String s2 = "2019-05-05";
    Date sd = sdf.parse(s2);
    String s3 = "2019-05-07";
    Date ed = sdf.parse(s3);
    String s4 = "2019-05-03";
    Date ad = sdf.parse(s4);
    LeaveType lt1 = LeaveType.valueOf("EL");
    LeaveStatus ls1 = LeaveStatus.valueOf("PENDING");
    assertEquals(new LeaveDetails(1, 1, lt1, doj, ed, ad, "sick", 5, ls1, "TakeCare"), es[0]);
    assertEquals(new LeaveDetails(1, 3, lt1, sd, ed, ad, "function", 5, ls1, "enjoy"), es[1]);
    assertEquals(new LeaveDetails(1, 2, lt1, ed, sd, ad, "marraige", 5, ls1, "enjoy"), es[2]);
    assertEquals(new LeaveDetails(2, 4, lt1, ed, ed, ad, "sick", 5, ls1, "TakeCare"), es[3]);
  }

   /**
   * testing for listall.
   * @param ldao for listall.
   * @throws ParseException when exception occurs.
   */
  @Test
     public final void leaveHistory(@Mocked final LeaveDetailsDAO ldao) throws ParseException {
    new Expectations() {
          {
            ArrayList<LeaveDetails> ls1 = new ArrayList<LeaveDetails>();
            ArrayList<LeaveDetails> ls2 = new ArrayList<LeaveDetails>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s0 = "2019-05-05";
            String s1 = "2019-05-07";
            String s2 = "2019-05-09";
            Date sobj = sdf.parse(s0);
            Date sobj1 = sdf.parse(s1);
            Date sobj2 = sdf.parse(s2);
            Date sobj3 = sdf.parse(s1);
            Date sobj4 = sdf.parse(s0);
            String e0 = "2019-05-21";
            String e1 = "2019-05-22";
            String e2 = "2019-05-23";
            Date eobj = sdf.parse(e0);
            Date eobj1 = sdf.parse(e1);
            Date eobj2 = sdf.parse(e2);
            Date eobj3 = sdf.parse(e1);
            Date eobj4 = sdf.parse(e0);
            LeaveType lt = LeaveType.EL;
            LeaveStatus s = LeaveStatus.PENDING;
            ls1.add(new LeaveDetails(1, 10, lt, sobj, eobj, sobj, "sick", 5, s, "TakeCare"));
            ls1.add(new LeaveDetails(1, 11, lt, sobj1, eobj1, sobj1, "sick", 3, s, "TakeCare"));
            ls1.add(new LeaveDetails(1, 12, lt, sobj2, eobj2, sobj2, "sick", 5, s, "TakeCare"));
            ls2.add(new LeaveDetails(2, 13, lt, sobj3, eobj3, sobj3, "sick", 6, s, "TakeCare"));
            ls2.add(new LeaveDetails(2, 14, lt, sobj4, eobj4, sobj4, "sick", 1, s, "TakeCare"));
            ldao.leaveHistory(1);
            result = ls1;
            ldao.leaveHistory(2);
            result = ls2;
          }
      };
    new MockUp<LeaveDetails>() {
      @Mock
    LeaveDetailsDAO dao() {
        return ldao;
      }
    };
    LeaveDetails[] lsr1 = LeaveDetails.listAll(1);
    assertEquals(3, lsr1.length);
    LeaveDetails[] lsr2 = LeaveDetails.listAll(2);
    assertEquals(2, lsr2.length);
  }
   /**
   * tests that empty LeaveDetails list is handled correctly.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testListAllLeaveId(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
          {
            dao.list();
            result = new ArrayList<LeaveDetails>();
          }
      };
    new MockUp<LeaveDetails>() {
          @Mock
          LeaveDetailsDAO dao() {
            return dao;
          }
      };
    LeaveDetails[] es = LeaveDetails.listAllData();
    assertEquals(0, es.length);
  }
}



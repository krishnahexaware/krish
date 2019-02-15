package com.hexaware.FTP117.model;

import com.hexaware.FTP117.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Test class for Employee.
 */
import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
//import mockit.integration.junit4.JMockit;
/**
 * Class for Employee Test.
 */
//@RunWith(JMockit.class);
public class EmployeeTest {

/**
 * setup method.
 */
  @Before
  public void initInput() {

  }

/**
 * @Test Tests the default Constructor methods of the employee class.
 */
  @Test
  public final void testDefaultConstructor() {
    Employee e100 = new Employee();
    assertNotNull(e100);
    Employee e101 = null;
    assertNull(e101);
  }
  /**
   * Tests the Parametrized Constructors and Get/Set method.
   * @throws ParseException in case there is an error in constructors.
   */
  @Test
  public final void testParamConstructor() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String s1 = "1997-08-12";
    Date doj = sdf.parse(s1);
    Employee e100 = new Employee(1, "Darshana", "darsh@gmail.com", "9876543210", "FTP", doj, 0, 12);
    assertEquals(1, e100.getEmpId());
    assertEquals("Darshana", e100.getEmpFullName());
    assertEquals("darsh@gmail.com", e100.getEmpEmail());
    assertEquals("9876543210", e100.getEmpMobile());
    assertEquals(doj, e100.getEmpDateOfJoining());
    assertEquals("FTP", e100.getEmpDepartment());
    assertEquals(0, e100.getEmpMgrId());
    assertEquals(12, e100.getEmpAvailabeLeave());
    Employee e101 = new Employee();
    // String s2 = "1997-09-02";
    // Date doj2 = sdf.parse(s2);
    e101.setEmpId(3);
    e101.setEmpFullName("Deepak");
    e101.setEmpEmail("deepakr@gmail.com");
    e101.setEmpMobile("8974651230");
    e101.setEmpDateOfJoining(doj);
    e101.setEmpDepartment("FTP");
    e101.setEmpMgrId(5);
    e101.setEmpAvailabeLeave(30);
    assertEquals(3, e101.getEmpId());
    assertEquals("Deepak", e101.getEmpFullName());
    assertEquals("deepakr@gmail.com", e101.getEmpEmail());
    assertEquals("8974651230", e101.getEmpMobile());
    assertEquals(doj, e101.getEmpDateOfJoining());
    assertEquals("FTP", e101.getEmpDepartment());
    assertEquals(5, e101.getEmpMgrId());
    assertEquals(30, e101.getEmpAvailabeLeave());
    // String s3 = "1997-09-02";
    // Date doj = sdf.parse(s3);
    Employee e102 = new Employee(5, "deep", "walklikeking@gmail.com", "6985741230", "FTP", doj, 1, 10);
    Employee e103 = new Employee(5, "deep", "walklikeking@gmail.com", "6985741230", "FTP", doj, 1, 10);
    assertEquals(e102.hashCode(), e103.hashCode());
    String res = " EmpId:5 EmpName:deep EmpPhoneNo:6985741230 EmpEmail:walklikeking@gmail.com"
                 + " EmpDept:FTP EmpJoinDate:1997-08-12 EmpMgrId:1 EmpAvailableLeave:10";
    assertEquals(res, e102.toString());
    EmployeeDAO edao = Employee.dao();
    assertNotNull(edao);
    Employee e104 = null;
    Employee e105 = new Employee();
    assertNotEquals(e105, e104);
    assertEquals(e102, e103);
    assertNull(e104);
    assertNotEquals(e103, e101);
    LeaveDetails l = new LeaveDetails();
    assertNotEquals(e105, l);


  }


  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException to handle parse exception.
   */

  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String s1 = "1997-08-12";
    final Date doj = sdf.parse(s1);
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        es.add(new Employee(5, "Dhivya", "Dhivya@gmail.com", "6985741230", "FTP", doj, 1, 10));
        es.add(new Employee(6, "Krish", "Krish@gmail.com", "121557563", "FTP", doj, 1, 10));
        es.add(new Employee(7, "Darsha", "Darsh@gmail.com", "9874545554", "FTP", doj, 1, 10));
        dao.list(); result = es;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(3, es.length);
    assertEquals(new Employee(5, "Dhivya", "Dhivya@gmail.com", "6985741230", "FTP", doj, 1, 10), es[0]);
    assertEquals(new Employee(6, "Krish", "Krish@gmail.com", "121557563", "FTP", doj, 1, 10), es[1]);
    assertEquals(new Employee(7, "Darsha", "Darsh@gmail.com", "9874545554", "FTP", doj, 1, 10), es[2]);
  }

    /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class.
   * @throws ParseException to handle parse exception.
   */

  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String s1 = "2019-02-07";
    final Date doj = sdf.parse(s1);
    final Employee e100 = new Employee(5, "Dhivya", "Dhivya@gmail.com", "6985741230", "FTP", doj, 1, 10);
    new Expectations() {
      {
        dao.find(5); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(5);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }
}


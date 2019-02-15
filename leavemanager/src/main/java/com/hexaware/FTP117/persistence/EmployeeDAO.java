package com.hexaware.FTP117.persistence;

import com.hexaware.FTP117.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);

  /**
   * Update Employee.
   * @param balanceDays applied leave days.
   * @param empID of particular.
   */
  @SqlUpdate("UPDATE EMPLOYEE "
            +
            " SET EMP_AVAILABLE_LEAVE = :balanceDays "
            +
            " WHERE EMP_ID =:empID")
  @Mapper(LeaveDetailsMapper.class)
  void increment(@Bind("balanceDays") int balanceDays,
             @Bind("empID") int empID);
  /**
   * Update Employee.
   * @param balanceDays applied leave days.
   * @param empID of particular.
   */
  @SqlUpdate("UPDATE EMPLOYEE "
            +
            " SET EMP_AVAILABLE_LEAVE = :balanceDays "
            +
            " WHERE EMP_ID =:empID")
  //@Mapper(LeaveDetailsMapper.class)
  void decrement(@Bind("balanceDays") int balanceDays,
             @Bind("empID") int empID);


  /**
  * close with no args is used to close the connection.
  */
  void close();
}

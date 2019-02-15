-- change to working database 
USE FTP117;

--drop the LEAVE_DETAILS table 
drop table LEAVE_DETAILS;

--drop the EMPLOYEE Table 
DROP TABLE EMPLOYEE;

-- Create EMPLOYEE table 

CREATE TABLE EMPLOYEE
(
EMP_ID INT,
EMP_FULL_NAME VARCHAR(40),
EMP_EMAIL VARCHAR(40),
EMP_MOBILE_NUMBER VARCHAR(40),
EMP_DATE_OF_JOINING DATE,
EMP_DEPARTMENT VARCHAR(20),
EMP_MGR_ID INT,
EMP_AVAILABLE_LEAVE INT,
CONSTRAINT PK_EMPLOYEE_EMP_ID PRIMARY KEY (EMP_ID),
CONSTRAINT FK_EMPLOYEE_MGR_ID FOREIGN KEY (EMP_MGR_ID) REFERENCES EMPLOYEE(EMP_ID)
);

-- Create LEAVE_DETAILS Table 

CREATE TABLE LEAVE_DETAILS
(
LEA_EMP_ID INT,
LEA_ID INT AUTO_INCREMENT,
LEA_LEAVE_TYPE ENUM('EL','PL','ML'),
LEA_START_DATE DATE,
LEA_END_DATE DATE,
LEA_APPLIED_ON DATE,
LEA_REASON VARCHAR(40),
LEA_NO_OF_DAYS INT,
LEA_LEAVE_STATUS ENUM('APPROVED','DENIED','PENDING'),
LEA_MGR_COMMENTS VARCHAR(40),
CONSTRAINT PK_LEAVE_DETAILS_LEAVE_ID PRIMARY KEY (LEA_ID),
CONSTRAINT FK_LEAVE_DETAILS_EMP_ID FOREIGN KEY (LEA_EMP_ID) REFERENCES EMPLOYEE(EMP_ID)
);




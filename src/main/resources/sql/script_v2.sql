create database quartz_demo;
SELECT * FROM sailorsdb.reserves;
DROP database musicalsystem;
DROP database mydb;

SELECT * FROM
Employee e
LEFT JOIN Employee_Cham_Cong ecc
ON e.employeeNo = ecc.employeeNo
WHERE e.employeeNo = 1;

USE quartz_demo;

CREATE TABLE SCHEDULER_JOB_INFO
(
  JOB_ID                  INT,
  JOB_NAME                VARCHAR(200),
  JOB_GROUP               VARCHAR(200) NOT NULL,
  JOB_STATUS              VARCHAR(5),
  JOB_PACKAGE 			  VARCHAR(200) NOT NULL,
  JOB_CLASS               VARCHAR(255) NOT NULL,
  CRON_EXPRESSION         VARCHAR(200),
  PRIORITY                INT,
  INTERFACE_NAME          VARCHAR(255),
  REPEAT_TIME             LONG,
  CRON_JOB                BOOLEAN,
  DISABLED                BOOLEAN,
  PRIMARY KEY             (JOB_ID)
);

ALTER TABLE SCHEDULER_JOB_INFO
ADD COLUMN JOB_PACKAGE VARCHAR(200) NOT NULL;

ALTER TABLE SCHEDULER_JOB_INFO
ADD COLUMN PRIORITY INT;

ALTER TABLE SCHEDULER_JOB_INFO
ADD COLUMN DISABLED BOOLEAN;
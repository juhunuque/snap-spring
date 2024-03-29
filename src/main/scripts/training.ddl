DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

CREATE TABLE DEPARTMENT (
  ID INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
  NAME VARCHAR(30) NOT NULL
);

CREATE TABLE EMPLOYEE (
  ID INT GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
  FIRST_NAME VARCHAR(30) NOT NULL,
  LAST_NAME VARCHAR(30) NOT NULL,
  DEPT_ID INT NOT NULL,
  FOREIGN KEY (DEPT_ID) REFERENCES DEPARTMENT(ID)
);

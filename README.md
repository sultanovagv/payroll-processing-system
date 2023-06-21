# payroll-processing-system

### Problem description
● You must use Java (>= 1..8) to solve the problem.
● There must be a way to supply the application with the input data via text or csv file
● The application must run without any error.

PPC is a Payroll solution provider company who manages the payroll of the various companies from
small scale to large scale company.
PPC accepts the employees data from the client in either plain text format (.txt) or csv (.csv) format to
manage the employee life cycle starting from date of onboarding to date of exit.
Following are the data points of employees which the client has to send to PPC in either text format or
csv format. One record is a comma (,) separate collection of these data points and one file can contain
thousands of such records of any employees for various events. PPC handles following events and manages the payroll accordingly. Each even has specific fixed format
and client has to strictly follow it while sending data.
ONBOARD  When employee join the  organization
            Format: SequenceNo, EmpID,
            EmpFName, EmpLName, Designation,
            Event, Value, EventDate, Notes
            1, emp101, Bill, Gates, Software Engineer,
            ONBOARD, 1-11-2022, 10-10-2022, “Bill
            Gates is going to join DataOrb on 1st
            November as a SE.”
SALARY  For monthly salary Format: SequenceNo, EmpID, Event,
            Value, EventDate, Notes
            1, emp101, SALARY, 4000, 10-10-2022,
            “Salary of OCT 2020 month”
            Note: Month of EventDate is Salary  month.
BONUS  For yearly bonus declared Format: SequenceNo, EmpID, Event,
            Value, EventDate, Notes
            1, emp101, BONUS, 1000, 10-10-2022,
            “Performance bonus of year 2022”
            Note: Month of EventDate is when  Bonus will be paid.
EXIT  When employee exit the  organization
      Format: SequenceNo, EmpID, Event, Value, EventDate, Notes
      1, emp101, EXIT, 1-11-2022, 10-10-2022, “Leaving for further study”
REIMBURSEMENT When the expenses has to reimburse along with monthly  salary
                Format: SequenceNo, EmpID, Event, Value, EventDate, Notes
                1, emp101, REIMBURSEMENT, 100, 10- 10-2020, “Traveling expenses”
Note: Month of EventDate is when
reimbursement will be paid.

Following questions should be answered as an output of the program.
1. Total number of employees in an organization.
2. Month wise following details
   a. Total number of employees joined the organization with employee details like emp id,
   designation, name, surname.
   b. Total number of employees exit organization with employee details like name, surname.
3. Monthly salary report in following format
   a. Month, Total Salary, Total employees
4. Employee wise financial report in the following format
   a. Employee Id, Name, Surname, Total amount paid
5. Monthly amount released in following format
   a. Month, Total Amount (Salary + Bonus + REIMBURSEMENT), Total employees
6. Yearly financial report in the following format
   a. Event, Emp Id, Event Date, Event value

### Solution:

In this assignment I mainly used Singleton and Factory design pattern. Referring to the csv and txt file requirements, I used the FileParserFactory class to implement the factory design pattern.
I created an Employee class with Events to store employees and events and added an EmployeeManager class to manage all employees.
Because of its thread-safe functionality, I used the Bill Pugh Singleton design pattern in the EmployeeManager class.
In the PayrollProcessingSystem class, I added a couple of methods to print the required questions on the console.
you can see the Factory DP implementation under parser folder.
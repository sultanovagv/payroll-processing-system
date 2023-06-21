package org.processing.system;

import java.time.Month;

public class Main {
    public static void main(String[] args) {
        EmployeeManager employeeManager = EmployeeManager.getInstance();
        PayrollProcessingSystem payrollProcessingSystem = new PayrollProcessingSystem(employeeManager);
        payrollProcessingSystem.loadEmployeesByFile("src\\main\\resources\\static\\Employees.txt");
        payrollProcessingSystem.printMonthlyReleasedTotalAmountByMonthAndYear(Month.JANUARY.getValue(), 2022);
        payrollProcessingSystem.printTotalNumberOfEmployees();
        payrollProcessingSystem.printMonthlySalaryReportByMonthAndYear(Month.FEBRUARY.getValue(), 2022);
        payrollProcessingSystem.printMonthlyReleasedTotalAmountByMonthAndYear(Month.OCTOBER.getValue(), 2022);
        payrollProcessingSystem.printYearlyFinancialReportByYear(2022);
        payrollProcessingSystem.printMonthlyQuitEmployeesByMonthAndYear(10,2022);
        payrollProcessingSystem.printEmployeeWiseReport();
    }
}
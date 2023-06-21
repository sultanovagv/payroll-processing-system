package org.processing.system;

import org.processing.system.parser.FileParserFactory;
import org.processing.system.parser.Parser;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PayrollProcessingSystem {
    private final EmployeeManager employeeManager;

    public PayrollProcessingSystem(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }


    public void loadEmployeesByFile(String file) {
        var filePath = new File(file).getAbsolutePath();
        FileParserFactory fileParserFactory = new FileParserFactory();
        Parser parser = fileParserFactory.createFileParser(filePath);
        List<EmployeeDto> employees = parser.parse(filePath);
        employees.forEach(employeeManager::addEmployee);
    }

    public void printTotalNumberOfEmployees() {
        System.out.println(employeeManager.getEmployeesSize());
    }

    public void printMonthlyJoinedEmployeesByMonthAndYear(int month, int year) {
        var employees = employeeManager.getEmployeesByEventTypes(List.of(EventType.ONBOARD))
                .stream()
                .filter(employee -> employee.getEventList().stream()
                        .map(Event::getEventDate)
                        .anyMatch(date -> date.getMonthValue() == month && date.getYear() == year))
                .collect(Collectors.toList());
        employees.forEach(employee ->
                System.out.println("empId: " + employee.getEmpId() + "designation: " + employee.getDesignation() +
                        " firstName: " + employee.getFirstName() + " lastName: " + employee.getLastName()));
    }

    public void printMonthlyQuitEmployeesByMonthAndYear(int month, int year) {
        var employees = employeeManager.getEmployeesByEventTypes(List.of(EventType.EXIT))
                .stream()
                .filter(employee -> employee.getEventList().stream()
                        .map(Event::getEventDate)
                        .anyMatch(date -> date.getMonthValue() == month && date.getYear() == year))
                .collect(Collectors.toList());
        employees.forEach(employee ->
                System.out.println("empId: " + employee.getEmpId() + "firstName: " + employee.getFirstName() + " lastName: " + employee.getLastName()));
    }

    public void printEmployeeWiseReport() {
        employeeManager.getEmployeesByEventTypes(List.of(EventType.SALARY, EventType.REIMBURSEMENT, EventType.BONUS))
                .forEach(employee -> {
                    int sum = employee.getEventList()
                            .stream()
                            .filter(event -> event.getEventType().equals(EventType.BONUS) ||
                                    event.getEventType().equals(EventType.SALARY) ||
                                    event.getEventType().equals(EventType.REIMBURSEMENT))
                            .map(Event::getValue)
                            .mapToInt(Integer::valueOf)
                            .sum();
                    System.out.println("empId: " + employee.getEmpId() + " firstName:" + employee.getFirstName()
                            + "lastName: " + employee.getLastName() + " total amount paid: " + sum);
                });
    }

    public void printMonthlyReleasedTotalAmountByMonthAndYear(int month, int year) {
        var employees = employeeManager.getEmployees();
        long totalAmount = employees.stream()
                .filter(employee -> employee.getEventList().stream()
                        .anyMatch(event -> event.getEventType().equals(EventType.BONUS) || event.getEventType().equals(EventType.SALARY) ||
                                event.getEventType().equals(EventType.REIMBURSEMENT)))
                .filter(employee -> employee.getEventList().stream()
                        .anyMatch(event -> {
                            LocalDate eventDate = event.getEventDate();
                            return eventDate.getMonthValue() == month && eventDate.getYear() == year;
                        }))
                .flatMap(employee -> employee.getEventList().stream())
                .mapToInt(value -> Integer.parseInt(value.getValue()))
                .sum();
        System.out.println("month: " + month + " employees size:  " + employees.size() + " totalAmount: " + totalAmount);
    }

    public void printMonthlySalaryReportByMonthAndYear(int month, int year) {
        var employees = employeeManager.getEmployeesByEventTypes(List.of(EventType.SALARY));
        int sum = employees.stream()
                .flatMap(employee -> employee.getEventList().stream())
                .filter(event -> event.getEventType().equals(EventType.SALARY))
                .filter(event -> event.getEventDate().getMonthValue() == month && event.getEventDate().getYear() == year)
                .mapToInt(event -> Integer.parseInt(event.getValue()))
                .sum();
        System.out.println("month: " + month + " totalSalary: " + sum + " employees size: " + employees.size());
    }


    public void printYearlyFinancialReportByYear(int year) {
        var employees = employeeManager.getEmployees()
                .stream()
                .filter(employee -> employee.getEventList().stream().anyMatch(event -> event.getEventDate().getYear() == year))
                .collect(Collectors.toList());
        for (Employee employee : employees) {
            System.out.println(employee.getEventList());
        }
    }


}

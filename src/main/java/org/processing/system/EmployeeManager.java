package org.processing.system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    private EmployeeManager() {
    }

    private static class InstanceHolder {
        private static final EmployeeManager INSTANCE = new EmployeeManager();
    }

    public static EmployeeManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addEmployee(EmployeeDto employeeDto) {
        var employee = findEmployeeById(employeeDto.getEmpId());
        if (employee == null) {
            employee = new Employee(employeeDto.getEmpId(), employeeDto.getFirstName(),
                    employeeDto.getLastName(), employeeDto.getDesignation());
            employees.add(employee);
        }
        var eventDate = employeeDto.getEventDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(eventDate, formatter);
        employee.getEventList().add(new Event(employeeDto.getEventType(), employeeDto.getValue(), date, employeeDto.getNotes()));
    }

    public List<Employee> getEmployeesByEventTypes(List<EventType> eventTypeList) {
        if (eventTypeList == null) {
            return new ArrayList<>();
        }
        return employees.stream()
                .filter(employee -> employee.getEventList()
                        .stream().anyMatch(event -> eventTypeList.contains(event.getEventType())))
                .collect(Collectors.toUnmodifiableList());
    }


    public long getEmployeesSize() {
        return employees.stream()
                .filter(employee -> employee.getEventList().stream().anyMatch(event -> !event.getEventType().equals(EventType.EXIT)))
                .count();
    }

    public List<Employee> getEmployees() {
        return employees.stream()
                .filter(employee -> employee.getEventList().stream().anyMatch(event -> !event.getEventType().equals(EventType.EXIT)))
                .collect(Collectors.toList());
    }

    public Employee findEmployeeById(String empId) {
        return employees.stream()
                .filter(employee -> employee.getEmpId().equals(empId))
                .findFirst()
                .orElse(null);
    }
}

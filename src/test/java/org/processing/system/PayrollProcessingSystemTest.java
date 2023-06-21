package org.processing.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.MONTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayrollProcessingSystemTest {
    @Mock
    private EmployeeManager employeeManager;
    @InjectMocks
    private PayrollProcessingSystem payrollProcessingSystem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        payrollProcessingSystem = new PayrollProcessingSystem(employeeManager);
    }

    @Test
    void loadEmployeesByFile_InvalidFile_DoesNotCallEmployeeManager() {
        String filePath = "invalid.text";
        var exception = assertThrows(IllegalArgumentException.class,
                () -> payrollProcessingSystem.loadEmployeesByFile(filePath));

        assertThat(exception.getMessage()).isEqualTo("this file format not supported");
    }

    @Test
    void loadEmployeesByValidFile_CallEmployeeManager() {
        String file = "src\\main\\resources\\static\\Employees.txt";
        String absolutePath = new File(file).getAbsolutePath();
        payrollProcessingSystem.loadEmployeesByFile(absolutePath);
        verify(employeeManager, times(3)).addEmployee(any(EmployeeDto.class));
    }


    @Test
    void testPrintMonthlyJoinedEmployeesByMonthAndYear() {
        List<Event> eventList = List.of(new Event(EventType.ONBOARD, "", LocalDate.now(), ""));
        Employee employee = new Employee("emp1", "John", "Doe", "Manager");
        employee.setEventList(eventList);
        List<Employee> employees = List.of(employee);
        when(employeeManager.getEmployeesByEventTypes(anyList())).thenReturn(employees);

        payrollProcessingSystem.printMonthlyJoinedEmployeesByMonthAndYear(MONTH, 2022);
        verify(employeeManager).getEmployeesByEventTypes(List.of(EventType.ONBOARD));
    }

    @Test
    void testPrintMonthlyQuitedEmployeesByMonthAndYear() {
        List<Event> eventList = List.of(new Event(EventType.EXIT, "", LocalDate.of(2022, 11, 1), ""));
        Employee employee = new Employee("emp2", "Alex", "Doe", "Software Engineer");
        employee.setEventList(eventList);
        List<Employee> employees = List.of(employee);
        when(employeeManager.getEmployeesByEventTypes(anyList())).thenReturn(employees);

        payrollProcessingSystem.printMonthlyQuitEmployeesByMonthAndYear(MONTH, 2022);
        verify(employeeManager).getEmployeesByEventTypes(List.of(EventType.EXIT));
    }

    @Test
    void testPrintTotalNumberOfEmployees() {
        when(employeeManager.getEmployeesSize()).thenReturn(10L);

        payrollProcessingSystem.printTotalNumberOfEmployees();
        verify(employeeManager, times(1)).getEmployeesSize();
    }
}







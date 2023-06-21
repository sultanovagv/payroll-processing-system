package org.processing.system;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
    private String empId;
    private String firstName;
    private String lastName;
    private String designation;
    private List<Event> eventList = new ArrayList<>();

    public Employee(String empId, String firstName, String lastName, String designation) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
    }
}

package org.processing.system;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private String empId;
    private String firstName;
    private String lastName;
    private String designation;
    private EventType eventType;
    private String value;
    private String eventDate;
    private String notes;
}

package org.processing.system.parser;

import org.processing.system.EmployeeDto;
import org.processing.system.EventType;

public class EmployeeParserHelper {
    public static EmployeeDto parseLineToEmployeeDto(String[] lines) {
        String[] line;
        if (lines[0].contains(",")) {
            line = lines[0].split(",");
        } else {
            line = lines;
        }
        var employee =  EmployeeDto.builder();
        employee.empId(line[1].trim());
        employee.firstName(line[2].trim());
        employee.lastName(line[3].trim());
        employee.designation(line[4].trim());
        employee.eventType(EventType.valueOf(line[5].trim()));
        employee.value(line[6].trim());
        employee.eventDate(line[7].trim());
        employee.notes(line[8].trim());
        return employee.build();
    }
}

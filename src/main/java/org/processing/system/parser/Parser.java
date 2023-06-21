package org.processing.system.parser;

import org.processing.system.EmployeeDto;

import java.util.List;

public interface Parser {
    List<EmployeeDto> parse(String file);

}

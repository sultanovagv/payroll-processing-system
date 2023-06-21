package org.processing.system.parser;

import com.opencsv.CSVReader;
import org.processing.system.EmployeeDto;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements Parser {

    @Override
    public List<EmployeeDto> parse(String file) {
        List<EmployeeDto> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                employees.add(EmployeeParserHelper.parseLineToEmployeeDto(line));
            }
        } catch (Exception ex) {
            System.out.println("Exception happened on csv file " + ex.getMessage());
        }
        return employees;
    }


}

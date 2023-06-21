package org.processing.system.parser;

import lombok.SneakyThrows;
import org.processing.system.EmployeeDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextParser implements Parser {
    @SneakyThrows
    @Override
    public List<EmployeeDto> parse(String fileName) {
        FileReader fileReader = new FileReader(fileName);
        List<EmployeeDto> employees = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                employees.add(EmployeeParserHelper.parseLineToEmployeeDto(new String[]{line}));
            }
        } catch (Exception ex) {
            System.out.println("Exception happened on text file " + ex.getMessage());
        }
        return employees;
    }


}

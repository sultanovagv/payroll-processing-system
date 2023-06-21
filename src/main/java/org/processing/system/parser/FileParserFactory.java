package org.processing.system.parser;

public class FileParserFactory {
    public Parser createFileParser(String file) {
        if (file.endsWith(".csv")) {
            return new CsvParser();
        }
        if (file.endsWith(".txt")) {
            return new TextParser();
        }
        throw new IllegalArgumentException("this file format not supported");
    }
}

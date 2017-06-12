package com.ubs.sudoku.validation.dataaccess;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataAccessException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class DataReader {
    public CSVReader build(String filename) {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL url = classLoader.getResource(filename);
        try {
            return new CSVReader(new FileReader(url.getPath()));
        } catch (FileNotFoundException | NullPointerException e) {
            throw new DataAccessException(DataAccessException.MISSING_FILE_ERROR_CODE,filename +" is not found.", e);
        }
    }
}

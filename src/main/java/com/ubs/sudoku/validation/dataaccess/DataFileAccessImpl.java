package com.ubs.sudoku.validation.dataaccess;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataAccessException;
import java.io.IOException;
import java.util.List;

final public class DataFileAccessImpl implements DataAccessService {
    private final CSVReader reader;
    public DataFileAccessImpl(final CSVReader csvReader) {
        this.reader = csvReader;
    }
    final public List<String[]> retrieve() {
        try {
            return reader.readAll();
        } catch (IOException ioe) {
            throw new DataAccessException(DataAccessException.READING_FILE_ERROR_CODE, "Error in reading input file.",ioe);
        }
    }
}

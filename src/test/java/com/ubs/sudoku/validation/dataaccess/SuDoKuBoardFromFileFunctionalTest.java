package com.ubs.sudoku.validation.dataaccess;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataAccessException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SuDoKuBoardFromFileFunctionalTest {

    @Test
    public void retrieveNofile() throws Exception {
        try {
            final CSVReader cvsReader = new DataReader().build("");
            new DataFileAccessImpl(cvsReader);
        } catch (DataAccessException e) {
            assert (e.getStatusCode() == DataAccessException.MISSING_FILE_ERROR_CODE);
        }
    }

    @Test
    public void retrieveIOException() throws Exception {
        try {
            final CSVReader cvsReader = new DataReader().build("io.pdf");
            new DataFileAccessImpl(cvsReader).retrieve();
        } catch (DataAccessException e) {
            assert (e.getStatusCode() == DataAccessException.READING_FILE_ERROR_CODE);
        }
    }

    @Test  //integration test
    public void retrieveFullTable() throws Exception {
        final CSVReader cvsReader = new DataReader().build("invalid.txt");
        final DataFileAccessImpl suDoKuBoardFromFile = new DataFileAccessImpl(cvsReader);
        suDoKuBoardFromFile.retrieve().forEach(strings -> assertTrue(String.join("",strings).equals("123456789")));
    }
}

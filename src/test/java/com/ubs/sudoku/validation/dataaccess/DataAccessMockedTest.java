package com.ubs.sudoku.validation.dataaccess;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataAccessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataAccessMockedTest {

    @Mock
    private CSVReader csvReader;

    @Test
    public void retrieveEmpty() throws Exception {
        final DataAccessService dataAccessService = new DataFileAccessImpl(csvReader);
        when(csvReader.readAll()).thenReturn(new ArrayList<>());
        assert(dataAccessService.retrieve().equals(new ArrayList<>()));
    }

    @Test
    public void retrieveSome() throws Exception {
        final String[][] dummyData  ={{"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}};
        DataAccessService dataAccessService = new DataFileAccessImpl(csvReader);
        when(csvReader.readAll()).thenReturn(Arrays.asList(dummyData));
        assert(dataAccessService.retrieve().equals(Arrays.asList(dummyData)));
    }

    @Test (expected = DataAccessException.class)
    public void retrieveExcpetion() throws Exception {
        DataAccessService dataAccessService = new DataFileAccessImpl(csvReader);
        when(csvReader.readAll()).thenThrow(IOException.class);
        dataAccessService.retrieve();
    }
}
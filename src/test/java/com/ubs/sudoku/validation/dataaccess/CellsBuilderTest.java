package com.ubs.sudoku.validation.dataaccess;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataContentException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//use dummy data to do the functional test, not mocked data for the unit test
public class CellsBuilderTest {

    @Test (expected = DataContentException.class)
    public void buildNoCells () throws Exception {
        final DataPreparationService dataPreparationService = new CellBuilder();
        assert(dataPreparationService.buildCells(new ArrayList<String[]>()).isEmpty());
    }

    @Test (expected = DataContentException.class)
    public void testMissingColumn () throws Exception {
        final String[][] dummyData  ={{"1","2","3","4","5","6","7","8"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"}};
        final DataPreparationService dataPreparationService = new CellBuilder();
        assert(dataPreparationService.buildCells(Arrays.asList(dummyData)).isEmpty());
    }

    @Test (expected = DataContentException.class)
    public void testOutRange () throws Exception {
        final String[][] dummyData  ={{"1","2","3","4","5","6","7","8","10"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"},
                {"1","2","3","4","5","6","7","8","9"},{"1","2","3","4","5","6","7","8","9"}};
        final DataPreparationService dataPreparationService = new CellBuilder();
        assert(dataPreparationService.buildCells(Arrays.asList(dummyData)).isEmpty());
    }

}
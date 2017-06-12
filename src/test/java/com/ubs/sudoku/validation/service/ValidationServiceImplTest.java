package com.ubs.sudoku.validation.service;

import com.ubs.sudoku.validation.datamodel.Cell;
import com.ubs.sudoku.validation.datamodel.View;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static junit.framework.Assert.assertFalse;

public class ValidationServiceImplTest {
    @Test
    public void testIsValidateEmpty () throws Exception {
        final ValidationService validationService = new ValidationServiceImpl();
        assertFalse (validationService.isValidate(new HashMap<>()));
    }

    @Test
    public void testConstructValidationGroupsEmpty () throws Exception {
        final ValidationService validationService = new ValidationServiceImpl();
        final Map<String, List<View>> map =  validationService.constructValidationGroups(new ArrayList<>());
        assert(map.keySet().size()== 0);
    }

    @Test
    public void testConstructValidationGroups () throws Exception {
        final List<Cell> cells = new ArrayList<>();
        IntStream.range(0,ValidationServiceImpl.NUMBER_VIEWS_PER_VALIDATION_GROUP).forEach(i -> cells.add(new Cell(0,i,"1")));
        IntStream.range(0,ValidationServiceImpl.NUMBER_VIEWS_PER_VALIDATION_GROUP).forEach(i -> cells.add(new Cell(1,i,"1")));
        final ValidationService validationService = new ValidationServiceImpl();
        final Map<String, List<View>> map =  validationService.constructValidationGroups(cells);
        assert(map.keySet().size()== 9 + 2 + 3); //two rows of raw data
    }
}

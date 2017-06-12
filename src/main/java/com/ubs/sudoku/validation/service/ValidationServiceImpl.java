package com.ubs.sudoku.validation.service;

import com.ubs.sudoku.validation.datamodel.Cell;
import com.ubs.sudoku.validation.datamodel.View;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationServiceImpl implements ValidationService {
    static final int NUMBER_VIEWS_PER_VALIDATION_GROUP = 9;
    static final private int NUMBER_VALIDATION_GROUP = 9 * 3; //row validation, column validation and square validation

    //data preparation to convert one Cell into 3 Views then group into 27 validation groups
    public Map<String, List<View>> constructValidationGroups(List<Cell> cells) {
        return cells.stream()
                .map(cell -> Arrays.asList(generateViews(cell))) //DE normalize one cell to three views
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(View::getValidationGroupId)); //group by group id
    }

    private View[] generateViews(Cell cell) {
        final View rowView = new View("ROW_VIEW_ID" + cell.getRowViewId(), cell.getValue());
        final View columnView = new View("COLUMN_VIEW_ID" + cell.getColumnViewId(), cell.getValue());
        final View squareView = new View("AREA_VIEW_ID" + cell.getAreaViewId(), cell.getValue());
        return new View[]{columnView, rowView, squareView};
    }

    public boolean isValidate(final Map<String, List<View>> validationGroups) {
        final boolean allGroupValid = validationGroups.values().stream().allMatch(this::validateOneGroup);
        return validationGroups.keySet().size() == NUMBER_VALIDATION_GROUP && allGroupValid;
    }

    private boolean validateOneGroup(final List<View> nineViewsInOneValiadtionGroup) {
        return nineViewsInOneValiadtionGroup.stream().map(View::getValue)
                .collect(Collectors.toSet()).size() == NUMBER_VIEWS_PER_VALIDATION_GROUP;
    }
}
package com.ubs.sudoku.validation.service;

import com.ubs.sudoku.validation.datamodel.Cell;
import com.ubs.sudoku.validation.datamodel.View;

import java.util.List;
import java.util.Map;

public interface ValidationService {
    Map<String, List<View>> constructValidationGroups(final List<Cell> cells);
    boolean isValidate (final Map<String, List<View>> groupsToBeValidated);
}
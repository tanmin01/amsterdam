package com.ubs.sudoku.validation.dataaccess;

import com.ubs.sudoku.validation.datamodel.Cell;
import java.util.List;

public interface DataPreparationService {
    List<Cell> buildCells(final List<String[]> input);
}

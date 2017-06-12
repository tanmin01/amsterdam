package com.ubs.sudoku.validation.dataaccess;

import com.ubs.sudoku.validation.datamodel.Cell;
import com.ubs.sudoku.validation.exception.DataContentException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final public class CellBuilder implements DataPreparationService {
    public List<Cell> buildCells(final List<String[]> input) {
        if (input.size() != DataAccessService.ROW_SIZE) {
            throw new DataContentException(DataContentException.MISSING_ROWS_ERROR_CODE,
                    "missing rows...",null);
        }
        return IntStream.range(0, input.size())
                .mapToObj(i -> buildCellsForOneRow(i, input.get(i)))
                .flatMap(List::stream).collect(Collectors.toList());
    }

    private List<Cell> buildCellsForOneRow(int rowId, final String[] values) {
        if (values.length != DataAccessService.COLUMN_SIZE)
            throw new DataContentException(DataContentException.MISSING_COLUMNS_ERROR_CODE,
                    "missing column at row " + rowId, null);

        return IntStream.range(0, values.length)
                .mapToObj(i -> new Cell(i, rowId, values[i]))
                .collect(Collectors.toList());
    }
}

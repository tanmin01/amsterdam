package com.ubs.sudoku.validation.datamodel;

import com.ubs.sudoku.validation.exception.DataContentException;
import static com.ubs.sudoku.validation.exception.DataContentException.OUT_RANGE_ERROR_CODE;

final public class Cell {
    final private static int SQUARE_SIDE_SIZE = 3;
    final private static int MAX_VALUE = 9;
    final private static int MIN_VALUE = 1;
    final private int rowId;
    final private int columnId;
    final private int value;

    public Cell(int column, int row, String string) {
        this.columnId = column;
        this.rowId = row;
        this.value = Integer.parseInt(string);
        if (outRange(value)) {
            throw new DataContentException(OUT_RANGE_ERROR_CODE, "Wrong cell value:" + value + " at position:" + column + "," + row, null);
        }
    }

    private boolean outRange(int input) {
        return input < MIN_VALUE || input > MAX_VALUE;
    }

    public int getRowViewId() {
        return this.rowId;
    }

    public int getColumnViewId() {
        return this.columnId;
    }

    public int getValue() {
        return this.value;
    }

    public int getAreaViewId() {
        int x = columnId / SQUARE_SIDE_SIZE;
        int y = rowId / SQUARE_SIDE_SIZE;
        return x + y * SQUARE_SIDE_SIZE;
    }
}

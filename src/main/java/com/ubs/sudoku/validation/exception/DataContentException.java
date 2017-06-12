package com.ubs.sudoku.validation.exception;

final public class DataContentException extends DataException { //second layer error
    final public static int MISSING_ROWS_ERROR_CODE = 701;
    final public static int MISSING_COLUMNS_ERROR_CODE = 702;
    final public static int OUT_RANGE_ERROR_CODE = 703;
    public DataContentException(int code, String s, final Throwable t) {
        super(code,s,t);
    }
}

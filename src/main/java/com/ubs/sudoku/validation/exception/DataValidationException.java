package com.ubs.sudoku.validation.exception;

public class DataValidationException extends DataException { //third layer error
    final public static int DATA_VALIDATION_ERROR_CODE = 800;
    public DataValidationException(int code, String s, final Throwable t) {
        super(code,s,t);
    }
}

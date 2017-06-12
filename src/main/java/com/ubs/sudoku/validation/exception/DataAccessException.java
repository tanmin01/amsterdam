package com.ubs.sudoku.validation.exception;

final public class DataAccessException extends DataException { //first layer error

    final public static int MISSING_FILE_ERROR_CODE = 601;
    final public static int READING_FILE_ERROR_CODE = 602;

    public DataAccessException(int code, String s, final Throwable t) {
        super(code,s,t);
    }
}

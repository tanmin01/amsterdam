package com.ubs.sudoku.validation.exception;

abstract public class DataException extends RuntimeException{
    final private int statusCode;

    DataException(int code, String s, Throwable t) {
        super(s,t);
        this.statusCode = code;
    }

    public int getStatusCode () {return this.statusCode;}
}

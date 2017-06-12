package com.ubs.sudoku.validation.datamodel;

final public class View {
    final private String validationGroupId;
    final private int value;

    public View(String id, int value) {
        this.validationGroupId = id;
        this.value = value;
    }

    public String getValidationGroupId() { return this.validationGroupId;}
    public int getValue () { return this.value;}
}

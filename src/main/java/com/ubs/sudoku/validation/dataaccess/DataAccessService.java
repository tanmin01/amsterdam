package com.ubs.sudoku.validation.dataaccess;

import java.util.List;

public interface DataAccessService {
    int ROW_SIZE = 9;
    int COLUMN_SIZE = 9;
    List<String[]> retrieve();
}

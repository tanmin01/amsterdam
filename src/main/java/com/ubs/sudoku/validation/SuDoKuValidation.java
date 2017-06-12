package com.ubs.sudoku.validation;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.dataaccess.*;
import com.ubs.sudoku.validation.datamodel.Cell;
import com.ubs.sudoku.validation.datamodel.View;
import com.ubs.sudoku.validation.exception.DataException;
import com.ubs.sudoku.validation.exception.DataValidationException;
import com.ubs.sudoku.validation.service.ValidationService;
import com.ubs.sudoku.validation.service.ValidationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

//one directional partitions: data access -> data preparation -> data validation
//no dependency wired together by Java interfaces but by pass or exchange messages, therefore no requirement for mocks in testing
//all the data are immutable and final, no set methods, only views or business prospective on the raw data
public class SuDoKuValidation {
    final private static Logger logger = LoggerFactory.getLogger(SuDoKuValidation.class);

    public static void main(final String[] args){
        try {
            final List<String[]> rawData = accessOriginalData(args[0]);
            final List<Cell> preparedData = prepareStructuredData(rawData);
            validateDataByBusinessPerspectives(preparedData);
        } catch (DataException e) { //using exceptions to handle business errors is a bad idea
            logger.info("INVALID ERROR code is " + e.getStatusCode() + " and the description is " + e.getMessage());
        }
    }

    private static void validateDataByBusinessPerspectives(final List<Cell> preparedData) {
        final ValidationService validationService = new ValidationServiceImpl();
        final Map<String, List<View>> validationGroups = validationService.constructValidationGroups(preparedData);
        if(validationService.isValidate(validationGroups)){
            logger.info("VALID");
        } else {
            throw new DataValidationException(DataValidationException.DATA_VALIDATION_ERROR_CODE, "a or more validations fail....",null);
        }
    }

    private static List<Cell> prepareStructuredData(final List<String[]> rawData) {
        final DataPreparationService dataPreparationService = new CellBuilder();
        return dataPreparationService.buildCells(rawData);
    }

    private static List<String[]> accessOriginalData(String filename) {
        final CSVReader dataReader = new DataReader().build(filename);
        final DataAccessService dataAccessService = new DataFileAccessImpl(dataReader);
        return dataAccessService.retrieve();
    }
}
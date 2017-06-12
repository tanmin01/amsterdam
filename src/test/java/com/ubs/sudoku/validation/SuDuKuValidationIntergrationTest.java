package com.ubs.sudoku.validation;

import com.opencsv.CSVReader;
import com.ubs.sudoku.validation.exception.DataContentException;
import org.junit.Test;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SuDuKuValidationIntergrationTest {
    @Test
    public void testOutDataRange () throws Exception {
        try {
            SuDoKuValidation.main(new String[]{"out-data-range.txt"});
        } catch (DataContentException e) {
            assert(e.getStatusCode() == DataContentException.OUT_RANGE_ERROR_CODE);
        }
    }

    @Test
    public void testMissingLines () throws Exception {
        try {
            SuDoKuValidation.main(new String[]{"missing-lines.txt"});
        }  catch (DataContentException e) {
            assert(e.getStatusCode() == DataContentException.MISSING_ROWS_ERROR_CODE);
        }
    }

    @Test
    public void testValid () throws Exception {
        SuDoKuValidation.main(new String[]{"valid.txt"});
        assert(getLastStringInLogs().contains(" VALID"));
    }

    @Test
    public void testInValid () throws Exception {
        assert(new SuDoKuValidation().hashCode()>0);
        SuDoKuValidation.main(new String[]{"invalid.txt"});
        assert(getLastStringInLogs().contains(" INVALID"));
    }

    @Test
    public void testInValid1 () throws Exception {
        assert(new SuDoKuValidation().hashCode()>0);
        SuDoKuValidation.main(new String[]{"invalid1.txt"});
        assert(getLastStringInLogs().contains(" INVALID"));
    }


    private String getLastStringInLogs() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL url = classLoader.getResource("");
        final CSVReader reader =new CSVReader(new FileReader(url.getPath()+"../../logs/sudoku-application.log"));
        final List<String[]> logs = reader.readAll();
        final String[] lastline =  logs.get(logs.size()-1);
        return lastline[lastline.length-1];
    }
}

package com.gorb.texthandling.reader;


import com.gorb.texthandling.exception.TextException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class TextReaderTest {
    TextFileReader reader;

    @BeforeClass
    public void createReader() {
        reader = new TextFileReader();
    }

    @DataProvider(name = "files_data")
    public Object[][] createFileRecords() {
        generateResourceAbsolutePath("files/testFile1.txt");
        return new Object[][]{
                {generateResourceAbsolutePath("files/testFile1.txt"), List.of("line1", "line2", "line3")}
        };
    }

    @Test(dataProvider = "files_data")
    public void readArrayLineTest(String fileName, List<String> expectedValue) throws TextException {
        List<String> value = reader.readFile(fileName);
        assertEquals(value, expectedValue);
    }

    @DataProvider(name = "files_data_invalid")
    public Object[][] createFileRecordsInvalid() {
        return new Object[][]{
                {"doesntExist.txt"},
                {generateResourceAbsolutePath("files/testFile2.txt")}
        };
    }

    @Test(dataProvider = "files_data_invalid", expectedExceptions = TextException.class)
    public void readArrayLineTestException(String fileName) throws TextException {
        reader.readFile(fileName);
    }

    private String generateResourceAbsolutePath(String filePath) {
        File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
        return file.getAbsolutePath();
    }
}

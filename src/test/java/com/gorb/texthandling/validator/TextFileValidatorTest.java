package com.gorb.texthandling.validator;

import org.testng.annotations.Test;

import java.io.File;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class TextFileValidatorTest {
    @Test
    public void validateValidFile() {
        File file = new File(getClass().getClassLoader().getResource("files/testFile1.txt").getFile());
        String path = file.getAbsolutePath();
        assertTrue(TextFileValidator.isValidFile(path));
    }

    @Test
    public void validateInvalidFile() {
        String path = "some_path";
        assertFalse(TextFileValidator.isValidFile(path));
    }
}

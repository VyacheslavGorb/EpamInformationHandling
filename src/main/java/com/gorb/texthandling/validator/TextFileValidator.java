package com.gorb.texthandling.validator;

import java.io.File;

public class TextFileValidator {
    private TextFileValidator() {
    }

    public static boolean isValidFile(String filePathString) {
        if (filePathString == null) {
            return false;
        }
        File file = new File(filePathString);
        if (!file.isFile()) {
            return false;
        }
        return file.length() != 0;
    }

}

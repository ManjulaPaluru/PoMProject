package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    private static String TEST_DATA_SHEET_PATH="./src/test/resources/testdata/DemoCartTestData.xlsx";

    public  static void getTestData(String sheetName) throws FileNotFoundException {
        try {
            FileInputStream fi= new FileInputStream(TEST_DATA_SHEET_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

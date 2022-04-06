package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelUtil {
    private static String TEST_DATA_SHEET_PATH="./src/test/resources/testdata/DemoCartTestData.xlsx";

    public  static void getTestData(String sheetName) throws FileNotFoundException {
        try{
            FileInputStream fi= new FileInputStream(TEST_DATA_SHEET_PATH);

        } catch (Exception e) {
            e.printStackTrace();
        }




    }


}

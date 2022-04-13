package com.qa.opencart.utils;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {

    private static Workbook book;
    private static Sheet sheet;
/*
    public static Object[][] getTestData(String sheetName) throws FileNotFoundException {
        Object data[][] = null;
        try {
            FileInputStream ip = new FileInputStream(Constants.TEST_DATA_SHEET_PATH);
            book = WorkbookFactory.create(ip);
            sheet = (Sheet) book.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for (int i = 0; i < sheet.getLastRowNum; i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }*/
}

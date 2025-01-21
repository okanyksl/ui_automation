package com.beymen.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.Platform;

public class Excel {
    public static final String       testDataExcelFileName = "Data.xlsx"; //Global test data excel file
    public static final String       currentDir            = System.getProperty("user.dir");  //Main Directory of the project
    public static       String       testDataExcelPath     = null; //Location of Test data excel file
    private static      XSSFWorkbook excelWBook; //Excel WorkBook
    private static      XSSFSheet    excelWSheet; //Excel Sheet
    private static      XSSFCell     cell; //Excel cell
    private static      XSSFRow      row; //Excel row
    public static       int          rowNumber; //Row Number
    public static       int          columnNumber; //Column Number

public static void setExcelFileSheet(String sheetName) throws IOException {
    if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
        testDataExcelPath = currentDir + "/src/test/resources/";
    } else if (Platform.getCurrent().toString().contains("Win")) {
        testDataExcelPath = currentDir + "\\src\\test\\resources\\";
    }
    
    // Dosya yolunu kontrol et
    String filePath = testDataExcelPath + testDataExcelFileName;
    System.out.println("Excel file path: " + filePath);
    File file = new File(filePath);
    if (!file.exists()) {
        throw new FileNotFoundException("Excel file not found at: " + file.getAbsolutePath());
    }
    
    // Excel dosyasını yükle
    FileInputStream ExcelFile = new FileInputStream(file);
    excelWBook = new XSSFWorkbook(ExcelFile);
    excelWSheet = excelWBook.getSheet(sheetName);
    if (excelWSheet == null) {
        throw new RuntimeException("Sheet not found: " + sheetName);
    }
}
    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public static String getCellData(int RowNum, int ColNum) {
        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) {
        row = excelWSheet.getRow(RowNum);
        return row;
    }
}

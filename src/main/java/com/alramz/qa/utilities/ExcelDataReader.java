package com.alramz.qa.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alramz.qa.base.TestBase;

public class ExcelDataReader extends TestBase{
	
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName, String path) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
			// System.out.println("File path is: " + TESTDATA_SHEET_PATH );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		// System.out.println("Total no. of row count: "+ sheet.getLastRowNum());
		short colno = sheet.getRow(0).getLastCellNum();
		//System.out.println("Total no. of column count: " + colno);
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

}

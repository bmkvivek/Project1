package utility;

import java.io.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitiy {

	private XSSFWorkbook excelWBook;
	private XSSFSheet excelWSheet;

	private void setExcelFile(String excelFilePath, String sheetName) {
		try {
			FileInputStream excelFile = new FileInputStream(excelFilePath);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCellData(String excelFilePath, String sheetName, int rowNum, int ColNum) {
		setExcelFile(excelFilePath, sheetName);
		XSSFCell excelCell = excelWSheet.getRow(rowNum).getCell(ColNum);
		return excelCell.getStringCellValue();
	}

	public void setCellData(String excelFilePath, String sheetName, String cellData, int rowNum, int colNum) {
		setExcelFile(excelFilePath, sheetName);
		XSSFRow excelRow = excelWSheet.getRow(rowNum);
		if (excelRow != null) {
			XSSFCell excelCell = excelWSheet.getRow(rowNum).getCell(colNum);
			if (excelCell != null) {
				excelCell.setCellValue(cellData);
			}
		}
	}

	public String[][] copyExcelDataToArray(String excelFilePath, String sheetName) {

		int i, j;
		String[][] dataArray = null;

		try {
			setExcelFile(excelFilePath, sheetName);
			int rowCount = excelWSheet.getPhysicalNumberOfRows();

			System.out.println("rowCount is :  " + rowCount);
			int colCount = excelWSheet.getRow(0).getLastCellNum();
			System.out.println("colCount is : " + colCount);
			dataArray = new String[rowCount - 1][colCount];
			for (i = 1; i < rowCount; i++) {
				for (j = 0; j < colCount; j++) {
					dataArray[i - 1][j] = excelWSheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println(dataArray[i - 1][j]);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				excelWBook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dataArray;
	}

}

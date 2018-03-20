package utility;

import java.io.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitiy {

	static XSSFWorkbook excelWBook;
	static XSSFSheet excelWSheet;
	static XSSFCell excelCell;
	static XSSFRow excelRow;
	static int rowCount,colCount;
	
	

	public static void main(String[] args) {
		String excelFilePath = "F:\\selenium\\TestData.xlsx";
		String sheetName = "LoginDetail";
		setExcelFile(excelFilePath,sheetName);
		copyExcelDataToArray(excelFilePath, sheetName);
	}

	public static void setExcelFile(String excelFilePath,String sheetName) {		
		
		excelFilePath = "F:\\selenium\\TestData.xlsx";
		
		try {
			FileInputStream excelFile = new FileInputStream(excelFilePath);			
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static String getCellData(int rowNum, int ColNum) {
		
		String cellData;
		excelCell  = excelWSheet.getRow(rowNum).getCell(ColNum);
		cellData = excelCell.getStringCellValue();
		return cellData;	
	}
	
		
	public static void setCellData(String cellData, int rowNum, int ColNum) {
		excelRow = excelWSheet.getRow(rowNum);
		excelCell = excelWSheet.getRow(rowNum).getCell(ColNum);
		if (excelCell.equals("")){
			excelCell = null;				
		}
				
		if(excelCell.equals(null)) {
			excelCell = excelRow.createCell(ColNum)	;
			excelCell.setCellValue(cellData);
		}
			else {
				excelCell.setCellValue(cellData);
			}
					
	}
	
	
	public static String[][] copyExcelDataToArray(String excelFilePath,String sheetName) {
		
		int i,j;
		String[][] dataArray = null;
		
		try {
			//FileInputStream exlObj = new FileInputStream(excelFilePath);
			excelWBook = new XSSFWorkbook(excelFilePath);
			excelWSheet = excelWBook.getSheet(sheetName);
			rowCount = excelWSheet.getPhysicalNumberOfRows();
				
			
			System.out.println("rowCount is :  "+rowCount);			
			colCount = 	excelWSheet.getRow(1).getLastCellNum();
			System.out.println("colCount is : "+colCount);
			dataArray = new String[rowCount-1][colCount];
			for(i=1 ; i<rowCount; i++) {
				for(j=0 ; j<colCount; j++) {					
					dataArray[i-1][j] = excelWSheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println(dataArray[i-1][j] );				
				}
			}
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataArray;	
	}

	
	
	
		
	}
	

		


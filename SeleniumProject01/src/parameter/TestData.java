package parameter;

import org.testng.annotations.DataProvider;

import utility.ExcelUtilitiy;
import utility.Util;

public class TestData {	 

	@DataProvider(name = "LoginData")
	public static Object[][] getLoginData() {
		return new Object[][] { { "mngr122239", "pAdugaz" }, { "abcd", "pAdugaz" }, { "mngr122239", "xyz" },
				{ "dsds", "xssfyz" } };
	}

	@DataProvider(name = "LoginDataFromExcel")
	public static String[][] getLoginDataFromExcel() {
		ExcelUtilitiy excelUtility = new ExcelUtilitiy();
		return excelUtility.copyExcelDataToArray(Util.EXCEL_FILEPATH, Util.SHEET_NAME);
	}

}
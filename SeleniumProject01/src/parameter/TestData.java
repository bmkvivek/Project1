package parameter;

import org.testng.annotations.DataProvider;

import utility.ExcelUtilitiy;
import utility.Util;



public class TestData {
	static String currDir;	
	
	@DataProvider(name="LoginData")
    public static Object[][] getLoginData(){
        return new Object[][] {
            { "mngr122239", "pAdugaz" },
            { "abcd", "pAdugaz" },
            { "mngr122239", "xyz"},
            { "dsds", "xssfyz" }
        };  
  }

@DataProvider(name = "LoginDataFromExcel")
public static String[][] getLoginDataFfromExcel(){
	currDir = System.getProperty("user.dir");
	return ExcelUtilitiy.copyExcelDataToArray(currDir+Util.TEST_DATA_FILE, Util.LOGIN_DATASHEET);
}



}
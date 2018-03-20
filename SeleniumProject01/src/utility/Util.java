package utility;

import org.openqa.selenium.WebDriver;

public class Util {
	
	String currDir;

	public static final String CHROME_PATH = "\\lib\\chromedriver\\chromedriver.exe" ;
	public static final String BASE_URL = "http://www.demo.guru99.com/V4/";
	public static final int WAIR_TIME = 10;
	public static final String EXPECTED_BROWSER_TEXT = "Guru99 Bank Manager HomePage";
	public static final String TEST_DATA_FILE = "\\src\\testdata\\testdata.xlsx";
	public static final String LOGIN_DATASHEET = "logindata";
	public static final String EXPECTED_ERROR = "User or Password is not valid";
	public static final String LOGOUT_MESSAGE = "You Have Succesfully Logged Out!!";
	
	
	public static void saveScreenshot(WebDriver drv , String filePath) {
		
		
	}
	
	
}
 
package Test;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import parameter.TestData;
import utility.Util;

/*This tescase does the following :
 * Login to the Website  http://www.demo.guru99.com/V4/
 * Get Test data from the Excel file and then enter each set of data 
 * If the login succeeds, then take screenshot
 * Perform logout operation
 */

public class LoginTest2 {

	public static WebDriver driver;
	public static WebDriverWait waitForObject;
	public static WebDriverWait wiatForObj;
	public static WebElement testObj;
	public static String currDir,userName,passWord;
	public static String dataArray[][];
	public static JavascriptExecutor jse;	
				
	@BeforeTest
	public void setBrowserConfig() {		
		
		currDir = System.getProperty("user.dir");		
		System.setProperty("webdriver.chrome.driver", currDir+Util.CHROME_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();	
	}
	
	@AfterTest
	public void closeBrowser() {		
		driver.close();	
	}
	

	@Test(priority=1)
	public void accessUrl() {
				
		driver.get(Util.BASE_URL);
		System.out.println("yurl is "+Util.BASE_URL);	
	}
	
	
	//In this test case data is taken from the excel sheet, which contains the test data
	@Test(priority = 2, dataProvider="LoginDataFromExcel", dataProviderClass = TestData.class)	
	public void performLogin(String username, String password) throws Exception {		
		
		String actualTitle;
		String actualBoxMsg;
		waitForObject= new WebDriverWait(driver, 3);				
		waitForObject.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
				
		String timestamp = new SimpleDateFormat("dd_mm_yyyy_hh_ss").format(new Date());
		String fileName = currDir+"\\screenshot\\Screenshot"+timestamp+".png";
    	
		/*If the login fails then alert will be displayed
		 * If the login is successful then home page will be displayed 
		 */
    	
    	try{ 
	    
	       	Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); // get content of the Alter Message
			alt.accept();
			 // Compare Error Text with Expected Error Value
			assertEquals(actualBoxMsg, Util.EXPECTED_ERROR);			
			
		}    
	    catch (NoAlertPresentException Ex){ 
	    	actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title	    	
	    	assertEquals(actualTitle, Util.EXPECTED_BROWSER_TEXT);    	    			
	    	
	    	//take screenshot
	    	//Convert  webDriver object to TakeScreenshot
	    	TakesScreenshot srcShot = ((TakesScreenshot)driver);
	    	//Call getscreenshotas method to take the screenshot
	    	File scrFile = srcShot.getScreenshotAs(OutputType.FILE);   	
	    	
	    	File destFile = new File(fileName);	    			
	    	FileUtils.copyFile(scrFile, destFile);	    	
	    	
	    	//Now logout
	    	jse = (JavascriptExecutor)driver;
	    	jse.executeScript("scroll(0,600)", "");
	    	driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a")).click();  
	    	
	    	String logoutMsg = driver.switchTo().alert().getText();
	    	assertEquals(logoutMsg, Util.LOGOUT_MESSAGE);
	    	driver.switchTo().alert().accept();
	    	
        } 
	}
    
}

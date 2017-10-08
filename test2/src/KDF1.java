import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KDF1 {
  WebDriver driver;
  // Variables for Test Data
  String dBrowser, dURL, dFName, dLName, dEmail, dPswd, dConfirmationMsg;
  // Variables for Element Identifications
  String eJoinUs, eGetPlanSingleUser, eGetPlanSmallTeam, eFName, eLName, eEmail, ePswd, eProceed;


  @Before
  public void setUp(){
	  // Initialize Test Data
	  dBrowser = "Chrome";
	  dURL = "http://dev.atomic77.in/ANATv1/#";
	  dFName = "Karthik";
	  dLName = "K";
	  dEmail = "kk@atomic77.com";
	  dPswd = "123qwe$R";
	  dConfirmationMsg = "Successfully Registered";

	  // Initialize Element Identifiers
	  eJoinUs = "Join US";
	  eGetPlanSingleUser = "Get this plan";
	  eGetPlanSmallTeam = "(//a[text()='Get this plan'])[2]";
	  eFName = "//input[@id='firstname']";
	  eLName = "//input[@id='lastname']";
	  eEmail = "//input[@id='email']";
	  ePswd = "//input[@id='password']";
	  eProceed = "//button[@id='singlebutton']";
	  
	// 1	Open the Browser
	  if (dBrowser.equals("FireFox")) {
		  System.setProperty("webdriver.gecko.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\geckodriver.exe");
		  driver = new FirefoxDriver(); 	  
	  } else if (dBrowser.equals("Chrome")){
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\chromedriver.exe");
		  driver = new ChromeDriver();
	  } else { // Default Browser
		  System.setProperty("webdriver.gecko.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\geckodriver.exe");
		  driver = new FirefoxDriver();
	  }
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @Test
  public void mainTest() throws InterruptedException{
	  
	  // TEST CASE : AA_UR_001	Single User Sign Up
	  // 2	Navigate to the AUT
	  driver.get(dURL);
 
	  // 3	Click on Join Us link
	  driver.findElement(By.linkText(eJoinUs)).click();
	  
	  Thread.sleep(2000);
	  // 4	Click on Get this plan for a single user
	  driver.findElement(By.linkText(eGetPlanSingleUser)).click();
	  
	  // 5	Enter 1st Name
	  driver.findElement(By.xpath(eFName)).clear();
	  driver.findElement(By.xpath(eFName)).sendKeys(dFName);
	  
	  // 6	Enter Last Name
	  driver.findElement(By.xpath(eLName)).clear();
	  driver.findElement(By.xpath(eLName)).sendKeys(dLName);
	  
	  // 7	Enter Email
	  driver.findElement(By.xpath(eEmail)).clear();
	  driver.findElement(By.xpath(eEmail)).sendKeys(dEmail);

	  // 8	Enter Password
	  driver.findElement(By.xpath(ePswd)).clear();
	  driver.findElement(By.xpath(ePswd)).sendKeys(dPswd);

	  // 9	Click on Proceed
	  driver.findElement(By.xpath(eProceed)).click();


	  // 10	Get confirmation over it - PENDING

	  
	  // TEST CASE : AA_UR_002	Small Team Sign Up
	  // 2	Navigate to the AUT
	  driver.get(dURL);
 
	  // 3	Click on Join Us link
	  driver.findElement(By.linkText(eJoinUs)).click();
	  
	  Thread.sleep(2000);
	  // 4	Click on Get this plan for a single user
	  // driver.findElement(By.linkText(eGetPlanSingleUser)).click();
	  driver.findElement(By.xpath(eGetPlanSmallTeam)).click();
	  
	  // 5	Enter 1st Name
	  driver.findElement(By.xpath(eFName)).clear();
	  driver.findElement(By.xpath(eFName)).sendKeys(dFName);
	  
	  // 6	Enter Last Name
	  driver.findElement(By.xpath(eLName)).clear();
	  driver.findElement(By.xpath(eLName)).sendKeys(dLName);
	  
	  // 7	Enter Email
	  driver.findElement(By.xpath(eEmail)).clear();
	  driver.findElement(By.xpath(eEmail)).sendKeys(dEmail);

	  // 8	Enter Password
	  driver.findElement(By.xpath(ePswd)).clear();
	  driver.findElement(By.xpath(ePswd)).sendKeys(dPswd);

	  // 9	Click on Proceed
	  driver.findElement(By.xpath(eProceed)).click();


	  // 10	Get confirmation over it - PENDING
 
  }
  
  @After
  public void cleanUp(){
	  // 11	Close Browser
	  driver.quit();
  }
}

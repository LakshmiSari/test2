import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driver1 {
	WebDriver driver;
	static String xlPath;
	static String[][] xTC, xTS;
	static int xTC_r, xTC_c, xTS_r, xTS_c;
	static String vTCID;
	static String vTSID, vKW, vEID, vTD;
	
	public static void main(String[] args) throws Exception{
		// Read the Test Cases, Test Steps excel sheet
		xlPath = "C:\\Users\\Kosireddi\\Documents\\SLT June 2017\\KDF - Proj 2 - 2.xls";
		xTC = readXL(xlPath, "TestCases");
		xTS = readXL(xlPath, "TestSteps");
		xTC_r = xTC.length;
		xTC_c = xTC[0].length;
		xTS_r = xTS.length;
		xTS_c = xTS[0].length;
		
		System.out.println("TC rows : " + xTC_r);
		System.out.println("TC Cols : " + xTC_c);
		System.out.println("TS rows : " + xTS_r);
		System.out.println("TS Cols : " + xTS_c);
		
		// Go to each row in TC sheet and see if executable
		for (int i=1; i<xTC_r; i++){
			if (xTC[i][3].equals("Y")){
				vTCID = xTC[i][0];
				System.out.println("Executing TC : " + vTCID);
				// IF yes, then go to each row in TS, very if belongs to that TC
				for (int j=1; j<xTS_r; j++){
					// Get the KW, EID, TD for each 
					vTSID = xTS[j][0];
					vKW = xTS[j][4];
					vEID = xTS[j][5];
					vTD = xTS[j][6];
					if (vTCID.equals(vTSID)){
						// Call the corresponding Reusable Function
						System.out.println("########");
						System.out.println("~~~~ KW : " +vKW);
						System.out.println("~~~~ EID : " +vEID);
						System.out.println("~~~~ TD : " +vTD);
						// Update the Excel with Test Results

						
					}

					
				}

				
			}
		
		}



		
	}
	
	// Reusable Keyword Functions 
	  public void NavigateBrowser(String fData){
		  driver.navigate().to(fData);
	  }
	  
	  public void openBrowser(String fData){
		  if (fData.equals("FireFox")) {
			  System.setProperty("webdriver.gecko.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\geckodriver.exe");
			  driver = new FirefoxDriver(); 	  
		  } else if (fData.equals("Chrome")){
			  System.setProperty("webdriver.chrome.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\chromedriver.exe");
			  driver = new ChromeDriver();
		  } else { // Default Browser
			  System.setProperty("webdriver.gecko.driver","C:\\Users\\Kosireddi\\Downloads\\Drivers\\geckodriver.exe");
			  driver = new FirefoxDriver();
		  }
	  }
	  
	  public void closeBrowser(){
		  driver.quit();
	  }
	  
	  public void maximizeBrowser(){
		  driver.manage().window().maximize();
	  }
	  
	  public void timeOutBrowser(int fData){
		  driver.manage().timeouts().implicitlyWait(fData, TimeUnit.SECONDS);
	  }
	  
	  public void clickLink(String fEID){
		  driver.findElement(By.linkText(fEID)).click();
	  }
	  
	  public void clickElement(String fEID){
		  driver.findElement(By.xpath(fEID)).click();
	  }
	  
	  public void typeText(String fEID, String fData){
		  driver.findElement(By.xpath(fEID)).clear();
		  driver.findElement(By.xpath(fEID)).sendKeys(fData);
	  }
	
	// Method to read XL
	public static String[][] readXL(String fPath, String fSheet) throws Exception{
		// Inputs : XL Path and XL Sheet name
		// Output : 2D Array
		int xRows, xCols;
		String[][] xData;  

		File myxl = new File(fPath);                                
		FileInputStream myStream = new FileInputStream(myxl);                                
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);                                
		HSSFSheet mySheet = myWB.getSheet(fSheet);                                 
		xRows = mySheet.getLastRowNum()+1;                                
		xCols = mySheet.getRow(0).getLastCellNum();   
		xData = new String[xRows][xCols];    
		
		for (int i = 0; i < xRows; i++) {                           
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < xCols; j++) {                               
				HSSFCell cell = row.getCell(j);
				String value = "-";
				if (cell!=null){
					value = cellToString(cell);
				}
				xData[i][j] = value; 
				System.out.print(" >> ");
				System.out.print(value);
			}        
			System.out.println("");
		}    
		myxl = null; // Memory gets released
		return xData;
	}

	//Change cell type
	public static String cellToString(HSSFCell cell) { 
		// This function will convert an object of type excel cell to a string value
		int type = cell.getCellType();                        
		Object result;                        
		switch (type) {                            
		case HSSFCell.CELL_TYPE_NUMERIC: //0                                
			result = cell.getNumericCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_STRING: //1                                
			result = cell.getStringCellValue();                                
			break;                            
		case HSSFCell.CELL_TYPE_FORMULA: //2                                
			throw new RuntimeException("We can't evaluate formulas in Java");  
		case HSSFCell.CELL_TYPE_BLANK: //3                                
			result = "%";                                
			break;                            
		case HSSFCell.CELL_TYPE_BOOLEAN: //4     
			result = cell.getBooleanCellValue();       
			break;                            
		case HSSFCell.CELL_TYPE_ERROR: //5       
			throw new RuntimeException ("This cell has an error");    
		default:                  
			throw new RuntimeException("We don't support this cell type: " + type); 
		}                        
		return result.toString();      
	}

	// Method to write into an XL
	public static void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{

		File outFile = new File(fPath);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet osheet = wb.createSheet(fSheet);
		int xR_TS = xData.length;
		int xC_TS = xData[0].length;
		for (int myrow = 0; myrow < xR_TS; myrow++) {
			HSSFRow row = osheet.createRow(myrow);
			for (int mycol = 0; mycol < xC_TS; mycol++) {
				HSSFCell cell = row.createCell(mycol);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(xData[myrow][mycol]);
			}
			FileOutputStream fOut = new FileOutputStream(outFile);
			wb.write(fOut);
			fOut.flush();
			fOut.close();
		}
	}

}

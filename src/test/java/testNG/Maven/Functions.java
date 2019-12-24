package testNG.Maven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Functions extends MainClass{
	
	
	public static void OpenBrowser(String browser, String Url)
	{
		browser= browser.toLowerCase(); // lower case the browser, in case it was passed with upper case
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "E:\\Eclipse\\WebDrivers\\chromedriver.exe");		//set Chrome Driver location
			driver = new ChromeDriver();
			break;
			
		case "mozilla":
			System.setProperty("webdriver.gecko.driver", "E:\\Eclipse\\WebDrivers\\GeckoDriver.exe");		//set FireFox Driver location
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}
		
		driver.get(Url);	//Navigate to URL
		driver.manage().window().maximize();	//Maximize Window
		
	}
	
	public static void CloseBrowser() 
	{
		driver.quit();
	}
	
	public static void ActivateObject(int timeout, String selector , String selectorValue, String action , String value)
	{
		WebElement FullElement = driver.findElement(By.tagName("body"));
		WebElement element;
		
		action = action.toLowerCase();	// Set string to lowercase
		
		Actions actionInter = new Actions(driver);
		element=FullElement;
		
		String [] selectorArr = selector.split("~");
		String [] selectValArr = selectorValue.split("~");
		
			for (int i=0; i<selectorArr.length ;i++)
			{
			try {
				switch (selectorArr[i]) {
			
				case "name":
					element= element.findElement(By.name(selectValArr[i]));
					break;
				case "id":
					element= element.findElement(By.id(selectValArr[i]));
					break;
				case "className":
					element= element.findElement(By.className(selectValArr[i]));
					break;
				case "cssselector":
					element =element.findElement(By.cssSelector(selectValArr[i]));
					break;
				case "text":
					element = element.findElement(By.linkText(selectValArr[i]));
					break;
				default:
					System.out.println("No valid selector found");					
			
				}
					
			}
			catch(Exception ex){
				
				throw new ArithmeticException("Can't Find Element: "+selectValArr[i]);
		}
		
		}
		switch (action) {
		
		case "set":
			element.sendKeys(value);
			break;
		case "click":
			element.click();
			break;
		case "hover":
			actionInter.moveToElement(element);
			actionInter.perform();
			break;
		case "gettext":
			element.getText();
			break;
		case "getattributeval":
			String attVal= element.getAttribute(value);
			WriteToFile(attVal);
			break;
		case "isstringequal":
			String strVal= element.getText();
			if (strVal != value)	//Check if strings are not equal , if different throw exception
			{
				System.out.println("Result as expected: " +strVal+" is equal to " + value);
			}
			else
			{
				throw new ArithmeticException( "First value: " +strVal + "should be equal to second value: "+value);
			}
			break;
		
		}
			
	}
	
	public static void Compare2Values(String value1 , String operator , String value2)
	{
		switch (operator)
		{
		
		case "equal":
			if (value1 != value2)
			{
				throw new ArithmeticException( "First value: " +value1 + "should be equal to second value: "+value2);
			}
		
		}
	}
	
	public static void WriteToFile(String valueToSet) 
	{
		
		try(FileWriter writeCsv = new FileWriter(filePath)){
			writeCsv.append(valueToSet);
		} catch (IOException e) {
			System.out.println("Couldn't save Data!");
			e.printStackTrace();
		}
		
	}
	
	public static void PrintStringFromFile()
	{
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			   String line;
			   while ((line = br.readLine()) != null) {
			       System.out.println(line);
			   }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public static List<String> ReadDataFromCSV(String path) throws FileNotFoundException, IOException
	{
		List<String> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        records.add(line);
		    }
		
		}
		
		return records;
	}
	
	public static String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText)
	{
		WebElement currCell;
		WebElement cell;
		String cellText="",cellData="";
		List <WebElement> tableRows;
		tableRows=table.findElements(By.tagName("tr"));
		tableRows.remove(0); // Remove the Column names rows from list of elements
		
		for (WebElement currRow : tableRows) {
			currCell=currRow.findElements(By.tagName("td")).get(searchColumn);
			cellData= currCell.getText();
			if (cellData.equals(searchText)){
				cell=currRow.findElements(By.tagName("td")).get(returnColumnText);
				cellText=cell.getText();
				break;	// we found the correct data
			}
				
		}	
		
		return cellText;
	}
	
	
	public static boolean verifyTableCellText(WebElement table, int searchColumn, String searchText,int returnColumnText, String expectedText)
	{
		String resultText="";
		resultText=getTableCellText(table,searchColumn,searchText,returnColumnText);
		if (resultText.equals(expectedText)) {
			return true;
		}
		else
			return false;
		
	}

	
	public static String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText,int returnColumnText) throws Exception
	{
					
		WebElement cell;
		String returnTxt;
		
		cell = table.findElement(By.xpath("//tr[contains(td["+searchColumn+"],'"+searchText+"')]/td["+returnColumnText+"]"));  // Get the Required Cell
								
		returnTxt=cell.getText();	// save the cell Text
		
		return returnTxt;
		
	}
}

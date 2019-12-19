package testNG.Maven;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testNG.Maven.Functions;

public class TestNG2 extends MainClass{

	
	@BeforeMethod
	public void SetUP() {	
		Functions.OpenBrowser("chrome", "http://www.w3schools.com/html/html_tables.asp");	// Open Browser
			
	}
	
	
	@Test
	public void GetTableData() throws FileNotFoundException, IOException {
		
		List<String> params;
		int searchCol,returnCol;
		String searchText;
		params= Functions.ReadDataFromCSV(searchFilePath); // Read data from CSV
		searchCol = Integer.parseInt(params.get(0));
		searchText = params.get(1);
		returnCol = Integer.parseInt(params.get(2));
		
		WebElement FullElement,element;
		FullElement= driver.findElement(By.tagName("body"));
		element= FullElement.findElement(By.id("customers"));
		String tableTxt= Functions.getTableCellText(element, searchCol, searchText, returnCol);
		
		System.out.print(tableTxt);
	}
	
	
	@Test
	public void verifyTableData() {
		
		boolean dataBool;
		
		WebElement FullElement,element;
		FullElement= driver.findElement(By.tagName("body"));
		element= FullElement.findElement(By.id("customers"));
		dataBool =Functions.verifyTableCellText(element, 1, "Francisco Chang", 2,"Mexico");
		
		System.out.print(dataBool);
	}
	
	
	@Test
	public void GetTableDataByXpath() throws Exception {
		
		WebElement FullElement,element;
		FullElement= driver.findElement(By.tagName("body"));
		element= FullElement.findElement(By.id("customers"));
		String tableTxt= Functions.getTableCellTextByXpath(element, 0, "Island Trading", 2);
		System.out.print(tableTxt);
	}
	
	@AfterMethod
	public void EndTest(){
		
		Functions.CloseBrowser();
	}
}

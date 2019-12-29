package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.PageObject;
import Pages.W3SchoolsPage;
import testNG.Maven.Functions;

public class HTMLTableTest extends FunctionalTests{

	
	@BeforeMethod
	public void SetUP() {	
		
		W3SchoolsPage HtmlTabPage = new W3SchoolsPage(driver);
		driver.get(HtmlTabPage.getURL());
		driver.manage().window().maximize();	//Maximize Window					
	}
		
	@Test
	public void GetTableDataByXpath() throws Exception {
		
		W3SchoolsPage HtmlTabPage= new W3SchoolsPage(driver);
		
		List<String> params;
		List<Integer> searchReturnVals;			
		int searchCol,returnCol;
		String searchText;
		
		params= Functions.ReadDataFromCSV(PageObject.searchFilePath); // Read data from CSV
		searchReturnVals = Functions.getIntegersFromList(params);
		searchCol = searchReturnVals.get(0)+1;  // get Search Column index, Increase value by 1 since Xpath first index is 1		
		returnCol = searchReturnVals.get(1)+1;	// get Return Column index, Increase value by 1 since Xpath first index is 1		
		searchText = params.get(1);	// Get the text we would like to search	
	
		String tableTxt = Functions.GetTableCellTextByXpath(HtmlTabPage.getCustomersTable(), searchCol, searchText, returnCol);
		
		System.out.print(tableTxt +"\n");
	}
	
	@Test
	public void GetTableData() throws FileNotFoundException, IOException {
		
		W3SchoolsPage HtmlTabPage= new W3SchoolsPage(driver);	
		List<String> params;
		List<Integer> searchReturnVals;
		
		int searchCol,returnCol;
		String searchText;
		params= Functions.ReadDataFromCSV(PageObject.searchFilePath); // Read data from CSV
		searchReturnVals = Functions.getIntegersFromList(params);
			
		searchCol = searchReturnVals.get(0);		
		returnCol = searchReturnVals.get(1);	
		searchText = params.get(1);
			
		String tableTxt= Functions.getTableCellText(HtmlTabPage.getCustomersTable(), searchCol, searchText, returnCol);
		
		System.out.print(tableTxt+"\n");
	}
	
	
	@Test
	public void verifyTableData() throws FileNotFoundException, IOException {
		
		boolean dataBool;		
		W3SchoolsPage HtmlTabPage= new W3SchoolsPage(driver);		
		List<String> params;
		List<Integer> searchReturnVals;
		int searchCol,returnCol;
		String searchText,ExpectedText;
		params = Functions.ReadDataFromCSV(PageObject.searchFilePath); // Read data from CSV
		
		searchReturnVals = Functions.getIntegersFromList(params);
		
		searchCol = searchReturnVals.get(0);		
		returnCol = searchReturnVals.get(1);	
		searchText = params.get(1);
		ExpectedText = params.get(3); //Get Expected Text
		dataBool = Functions.verifyTableCellText(HtmlTabPage.getCustomersTable(), searchCol, searchText, returnCol,ExpectedText);
		
		System.out.print("Table data is: " +dataBool+"\n");
	}
		
}
	


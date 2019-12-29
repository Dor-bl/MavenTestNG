package testNG.Maven;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import Pages.Site777Page;

public class Functions  {
	
	

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
	
	public static void WriteToFile(String FilePath,String valueToSet) 
	{
		
		try(FileWriter writeCsv = new FileWriter(FilePath)){
			writeCsv.append(valueToSet);
		} catch (IOException e) {
			System.out.println("Couldn't save Data!");
			e.printStackTrace();
		}
		
	}
	
	public static void PrintStringFromFile(String file)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
	
	public static List<Integer> getIntegersFromList(List<String> strList)
	{
		List<Integer> intsList = new ArrayList<>();
		Integer tempInt ;
		for (int i = 0; i < strList.size(); i++) {
			if (isNumeric(strList.get(i))) {
				tempInt= Integer.parseInt(strList.get(i));
				intsList.add(tempInt);
			}
				
		}
		
		return intsList;
		
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

	
	public static String GetTableCellTextByXpath(WebElement table, int searchColumn, String searchText,int returnColumnText) throws Exception
	{
					
		WebElement cell;
		cell = table.findElement(By.xpath("//tr[contains(td["+searchColumn+"],'"+searchText+"')]/td["+returnColumnText+"]"));  // Get the Required Cell
											
		return cell.getText();	// Return the cell Text
		
	}
	
    public static List<WebElement> getChildItemsByTagName(WebElement Element,String tagName) {
    	List<WebElement> langArrElem;
    	langArrElem =Element.findElements(By.tagName(tagName));  
		return langArrElem;    	
    }
	
	public static void LanguageValidation(Site777Page sitePage,String language ,WebElement langButton, String expctedStr)
	{
				
		// Hover language list
		sitePage.hoverLangBtn();
		//Click on  language
		sitePage.clickLang(langButton);
		//Get Login element Text and check it equals to expected per language 
		sitePage.assertLangValidation(expctedStr);		
		Reporter.log(""+language+" Screen is GOOD!");
				
	}
	
    public static String[] getTextArrayFromElementsList(List<WebElement> ElementsArr, String attribute) {
    	
    	String[] stringArr= new String[5] ;
    	for (WebElement elem : ElementsArr)
    	{
    		for (int i = 0;i < ElementsArr.size() ;i++)
    			stringArr[i]= elem.getAttribute(attribute);
    	}
		return stringArr;  	
    }
    
	
	@SuppressWarnings("unused")
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}

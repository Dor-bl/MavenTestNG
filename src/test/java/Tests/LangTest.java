package Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.PageObject;
import Pages.Site777Page;
import testNG.Maven.Functions;

public class LangTest extends FunctionalTests{
	
	
	@BeforeMethod
	public void SetUP() {
		
		Site777Page s777Page = new Site777Page(driver);		
		driver.get(s777Page.getURL());				
		driver.manage().window().maximize();	//Maximize Window
				 
	}
	
	@Test
	public void LangChoiches() {
			
			String langChoices = "";
			Site777Page s777Page = new Site777Page(driver);
			
			// Hover language list
			s777Page.hoverLangBtn();				
			
			// Get all available languages 
			langChoices = s777Page.getColumnLangText();
				
			//Write Values to CSV file
			Functions.WriteToFile(PageObject.filePath,langChoices);
			
			//Print all available languages 
			System.out.println("Selected languages options are:\n" +langChoices);

	}
	
	@Test
	public void LangValidation() {
		
		List<WebElement> langArrayElem;
		Site777Page s777Page = new Site777Page(driver);		//Initialize page driver
		
		langArrayElem= Functions.getChildItemsByTagName(s777Page.getColumnLang(), "a"); // get all languages elements from list		
		
		// Loop through all available languages 
		for (int i= 0;i < langArrayElem.size() ;i++)
		{
			 langArrayElem= Functions.getChildItemsByTagName(s777Page.getColumnLang(), "a"); // Refresh all languages elements from list 
			 WebElement lang= langArrayElem.get(i); //Get Current Language Element
			 Functions.LanguageValidation(s777Page,lang.getAttribute("text") ,lang, lang.getAttribute("origin"));
		}
		
	}
	
	
}

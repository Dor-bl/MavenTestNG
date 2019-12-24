package testNG.Maven;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.FunctionalTests;
import Pages.Site777Page;

public class LangTest extends FunctionalTests{
	
	
	@BeforeMethod
	public void SetUP() {
				
		driver.get("http://www.777.com/");
		
		driver.manage().window().maximize();	//Maximize Window
				 
	}
	
	@Test
	public void LangChoiches() {
			
			String langChoices="";
			Site777Page s777Page = new Site777Page(driver);
			
			// Hover language list
			s777Page.hoverLangBtn();				
			
			// Get all available languages 
			langChoices= s777Page.getColumnLangText();
				
			//Write Values to CSV file
			s777Page.WriteToFile(langChoices);
			
			//Print all available languages 
			System.out.println("Selected languages options are:\n" +langChoices);

	}
	
	@Test
	public void LangValidation() {
		
		Site777Page s777Page = new Site777Page(driver);
		
		// Hover language list
		s777Page.hoverLangBtn();
		//Click on First language - English
		s777Page.clickLang(s777Page.getEnglishBtn());
		
		//Get Login element Text and check it equals to expected per language 
		s777Page.assertLangText("LOGIN");
		
		Reporter.log("English Screen is GOOD!");
		
		// Hover language list
		s777Page.hoverLangBtn();
		//Click on First language - Deutsch
		s777Page.clickLang(s777Page.getDeutschhBtn());
		//Get Login element Text and check it equals to expected per language 
		s777Page.assertLangText("EINLOGGEN");
		
		Reporter.log("Deutsch Screen is GOOD!");
		
		// Hover language list
		s777Page.hoverLangBtn();
		//Click on First language - Suomi
		s777Page.clickLang(s777Page.getSuomihBtn());
		//Get Login element Text and check it equals to expected per language
		s777Page.assertLangText("KIRJAUTUMINEN");
		
		Reporter.log("Suomi Screen is GOOD!");
		
	}
	
	
}

package testNG.Maven;

import org.testng.Reporter;
import testNG.Maven.Functions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG1 {
	
	
	@BeforeMethod
	public void SetUP() {	
		Functions.OpenBrowser("chrome", "http://www.777.com/");	// Open Browser
	}
	
	@Test
	public void LangChoiches() {
			
		    // Hover language list
			Functions.ActivateObject(5, "className", "langBUtton", "hover", "");
			
			// Get all available languages to Csv File
			Functions.ActivateObject(5, "id~className", "ChooseLanguageDlg~FloatColumn", "getAttributeVal", "outerText");
			
			//Print all available languages 
			System.out.println("Selected languages options are: ");
			Functions.PrintStringFromFile();
	}
	
	@Test
	public void LangValidation() {
		
		// Hover language list
		Functions.ActivateObject(5, "className", "langBUtton", "hover", "");
		//Click on First language - English
		Functions.ActivateObject(5, "id~text", "ChooseLanguageDlg~English", "Click", "");
		//Get Login element Text and check it equals to expected per language 
		Functions.ActivateObject(5, "className", "logInButton", "isstringequal", "LOGIN");
		Reporter.log("English Screen is GOOD!");
		
		// Hover language list
		Functions.ActivateObject(5, "className", "langBUtton", "hover", "");
		//Click on First language - Deutsch
		Functions.ActivateObject(5, "id~text", "ChooseLanguageDlg~Deutsch", "Click", "");
		//Get Login element Text and check it equals to expected per language 
		Functions.ActivateObject(5, "className", "logInButton", "isstringequal", "EINLOGGEN");
		Reporter.log("Deutsch Screen is GOOD!");
		
		// Hover language list
		Functions.ActivateObject(5, "className", "langBUtton", "hover", "");
		//Click on First language - Suomi
		Functions.ActivateObject(5, "id~text", "ChooseLanguageDlg~Suomi", "Click", "");
		//Get Login element Text and check it equals to expected per language
		Functions.ActivateObject(5, "className", "logInButton", "isstringequal", "KIRJAUTUMINEN");
		Reporter.log("Suomi Screen is GOOD!");
		
	}
	
	@AfterMethod
	public void EndTest(){
		
		Functions.CloseBrowser();
	}
	
}

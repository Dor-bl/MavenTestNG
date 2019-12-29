package Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Site777Page extends PageObject {
	
	private final String URL = "http://www.777.com/";
	
	Actions actionInter = new Actions(driver);
	
	public Site777Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// WebElements
	
	@FindBy (className = "langBUtton")
	private WebElement langBtn;
	
	@FindBy (className = "logInButton")
	private WebElement loginBtn;
	
	@FindBy (id = "ChooseLanguageDlg")
	private WebElement chooseLang;
	
	@FindBy (className = "FloatColumn")
	private WebElement columnLang;
	
	@FindBy(linkText = "English")
	private WebElement englishLngBtn;
	
	@FindBy(linkText = "Deutsch")
	private WebElement dutchLngBtn;
	
	@FindBy(linkText = "Suomi")
	private WebElement soumiLngBtn;
	
	//Getters
	
	public WebElement getlangBtn() {
		return langBtn;
	}
	
	public WebElement getColumnLang() {
		return columnLang;
	}
	
    public String getURL() {
        return URL;
    }
    
    public void hoverLangBtn() {
		actionInter.moveToElement(this.langBtn);
		actionInter.perform();
    }
    
    public WebElement getEnglishBtn() {
    	return englishLngBtn;
    }
    
    public WebElement getDeutschhBtn() {
    	return dutchLngBtn;
    }
    
    public WebElement getSuomihBtn() {
    	return soumiLngBtn;
    }
    
    public WebElement getLoginBtn() {
    	return loginBtn;
    }
    
    public void clickLang(WebElement element ) {
    	element.click();
    }
    
    public void assertLangValidation(String strVal) {
    	String CurrentURL =driver.getCurrentUrl();
    	assertTrue(CurrentURL.contains(strVal));
    }
    
    public String getColumnLangText() {
    	String attVal= this.getColumnLang().getAttribute("outerText");
    	return attVal;
    }          
	
}

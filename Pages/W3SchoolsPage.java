package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class W3SchoolsPage extends PageObject {

	
	private final String URL = "http://www.w3schools.com/html/html_tables.asp";
	
	public W3SchoolsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	//WebElements
	
	@FindBy (id = "customers")
	private WebElement customersTable;
		
	
	public String getURL() {
        return URL;
    }
	
    public WebElement getCustomersTable() {
    	return customersTable;
    }
    

}

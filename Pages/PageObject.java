package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	
protected WebDriver driver;
public static String filePath ="E:\\Eclipse\\TestData\\tableData.csv";
	
	public PageObject(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}

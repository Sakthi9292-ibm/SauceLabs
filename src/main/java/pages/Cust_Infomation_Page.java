package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cust_Infomation_Page extends Cart_Page {

	WebDriver driver = drive;
	
	By firstname=By.id("first-name");
	By lastname=By.id("last-name");
	By postal_code=By.id("postal-code");
	By cancel = By.id("cancel");
	By cont= By.id("continue");
	public Cust_Infomation_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void Customer_information_page_enter_fname(String fname) {
		
	driver.findElement(firstname).sendKeys(fname);	
		
		
	}
	
	public void Customer_information_page_enter_lname(String lname) {
		
		driver.findElement(lastname).sendKeys(lname);	
			
			
		}
	

	public void Customer_information_page_enter_zcode(String code) {
		
		driver.findElement(postal_code).sendKeys(code);	
			
			
		}
	
	
	public void Customer_information_page_click_cancel() {
		
		driver.findElement(cancel).click();
		
	}
	
	public void Customer_information_page_click_continue() {
		
		driver.findElement(cont).click();
		
		
	}
	
	
	
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Complete_Page extends Overview_Page {

	WebDriver driver = drive;

	By header_message = By.xpath("//*[@class='complete-header']");
	By message = By.xpath("//*[@class='complete-text']");
	By homebutton = By.id("back-to-products");

	public Complete_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String complete_page_confirmation_title_text() {

		String header_msg = driver.findElement(header_message).getText();

		return header_msg;

	}

	public String complete_page_confirmation_detailed_message() {

		String msg = driver.findElement(message).getText();

		return msg;

	}

	public void complete_page_click_home_button() {

		driver.findElement(homebutton).click();

	}

}

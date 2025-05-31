package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home_Page extends Generic {

	By user_name = By.id("user-name");
	By password = By.id("password");
	By login = By.id("login-button");
	By errormessage = By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3");
	WebDriver driver = drive;

	Home_Page(WebDriver driver) {

		super(driver);
	}

	public void HomePage_enter_user_name(String name) {
		System.out.println(driver);
		driver.findElement(user_name).sendKeys(name);
	}

	public void HomePage_enter_password(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	public void HomePage_click_login() {
		driver.findElement(login).click();
		;
	}

	public String HomePage_get_errormessage() {
		return driver.findElement(errormessage).getText();
	}

}

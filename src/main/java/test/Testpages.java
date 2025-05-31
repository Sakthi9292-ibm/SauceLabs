package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Generic;
import pages.Shopping_Page;

public class Testpages {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();

		final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false);
		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("prefs", chromePrefs);

		WebDriver driver = new ChromeDriver(chromeOptions);

		driver.get("https://www.saucedemo.com/");

		Shopping_Page pages = new Shopping_Page(driver);

		pages.HomePage_enter_user_name("standard_user");

		pages.HomePage_enter_password("secret_sauce");

		pages.HomePage_click_login();

		HashMap<String, HashMap<String, String>> data = pages.shopping_page_get_all_product_details_in_page();

		pages.shopping_page_add_to_cart_by_product_name("Sauce Labs Bike Light");

		Thread.sleep(5000);

		//pages.take_screenshot();

		driver.close();

	}

}

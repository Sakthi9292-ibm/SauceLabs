package pages;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Eager;

public class Shopping_Page extends Home_Page {

	By Profile_section = By.id("react-burger-menu-btn");
	By Profile_section_all_items = By.xpath("//a[text()='All Items']");
	By Profile_section_about = By.xpath("//a[text()='About']");
	By Profile_section_logout = By.id("logout_sidebar_link");
	By Profile_section_reset_app_state = By.id("reset_sidebar_link");
	By filter = By.className("product_sort_container");
	By cart = By.className("shopping_cart_link");
	By items = By.xpath("//div[@class='inventory_item_name ']");
	 By price = By.xpath("//div[@class='inventory_item_price']");
	By desc = By.xpath("//div[@class='inventory_item_desc']");
	By add_to_cart = By.xpath("//button[contains(text(),'Add to cart')]");
	By remove = By.xpath("//button[text()='Remove']");
	By items_in_cart = By.xpath("//span[@class='shopping_cart_badge']");
	WebDriver driver = drive;

	public Shopping_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void Shopping_page_click_profile_section() {

		driver.findElement(Profile_section).click();

	}

	
	public List<WebElement> get_prices() {

		return driver.findElements(price);

	}
	
	public void Shopping_page_click_profile_section_all_items() {

		driver.findElement(Profile_section_all_items).click();

	}

	public void Shopping_page_click_profile_section_about() {

		driver.findElement(Profile_section_about).click();

	}

	public void Shopping_page_click_profile_section_logout() {

		driver.findElement(Profile_section_logout).click();

	}

	public void Shopping_page_click_profile_section_reset_app_state() {

		driver.findElement(Profile_section_reset_app_state).click();

	}

	public void Shopping_page_select_filter_by(String option) {

		Select fil = new Select(driver.findElement(filter));

		fil.selectByVisibleText(option);
	}

	public String Shopping_page_filter_get_selected() {

		Select fil = new Select(driver.findElement(filter));

		return fil.getFirstSelectedOption().getText();
	}

	public List<String> shopping_page_filter_get_all() {

		List<String> options = null;

		Select fil = new Select(driver.findElement(filter));

		List<WebElement> getoptions = fil.getOptions();
		for (WebElement webElement : getoptions) {

			options.add(webElement.getText());

		}

		return options;

	}

	public void shopping_page_select_product_by_name(String itemname) {

		List<WebElement> products = driver.findElements(items);
		
		for (WebElement product : products) {
			if (itemname.replace("\"", "").contains(product.getText())) {
				
				product.click();
			}

		}

	}

	
	public void shopping_page_check_clickable_product_by_name(String itemname) {

		List<WebElement> products = driver.findElements(items);
		
		
		for (WebElement product : products) {
			if (itemname.replace("\"", "").contains(product.getText())) {
				
				System.out.println("-> "+product.getText());				
				WebDriverWait wait = new WebDriverWait(driver, 2); 
				
				wait.until(ExpectedConditions.elementToBeClickable(product));
			}

		}

	}

	
	public HashMap<String, HashMap<String, String>> shopping_page_get_all_product_details_in_page() {

		List<WebElement> products = driver.findElements(items);
		List<WebElement> details = driver.findElements(desc);
		List<WebElement> prices = driver.findElements(price);

		HashMap<String, HashMap<String, String>> product_details = new HashMap<String, HashMap<String, String>>();

		for (int i = 0; i < products.size(); i++) {
			
			System.out.println("prodccc-- "+prices.get(i).getText());
			HashMap<String, String> pd = new HashMap<String, String>();

			pd.put("Product Details", details.get(i).getText());
			pd.put("Price", prices.get(i).getText());
			product_details.put(products.get(i).getText(), pd);

		}
		
		System.out.println(product_details.toString());

		return product_details;

	}

	public void shopping_page_add_to_cart_by_product_name(String product_name) {

		List<WebElement> products = driver.findElements(items);
		List<WebElement> cart = driver.findElements(add_to_cart);

		for (int i = 0; i < products.size(); i++) {
			System.out.println("---" + i + " " + cart.size());

			if (products.get(i).getText().equalsIgnoreCase(product_name.replace("\"", ""))) {

				System.out.println("PRODUCT: " + products.get(i).getText());
				cart.get(i).click();
			}

		}

	}

	public void shopping_page_remove_by_product_name(String product_name) {

		List<WebElement> products = driver.findElements(items);
		List<WebElement> cart = driver.findElements(remove);
		int j=0;
		for (int i = 0; i < products.size(); i++) {
			System.out.println("---" + i + " " + cart.size());

			if (products.get(i).getText().equalsIgnoreCase(product_name.replace("\"", ""))) {

				System.out.println("PRODUCT: " + products.get(i).getText());
				cart.get(j).click();
				j=j+1;
			}

		}

	}

	public String shopping_pages_get_items_in_cart() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		
		WebElement element = driver.findElement(items_in_cart);
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		WebDriverWait wait = new WebDriverWait(driver, 10); 
		
		wait.until(ExpectedConditions.visibilityOf(element));
		
		return driver.findElement(items_in_cart).getText();

	}

}

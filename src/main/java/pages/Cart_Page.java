package pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart_Page extends Shopping_Page {

	By cartitems = By.xpath("//div[@class='cart_item']");
	By cartquantity = By.xpath("//div[@class='cart_quantity']");
	By Removebutton = By.xpath("//button[contains(text(),'Remove')]");
	By Continue_shopping = By.id("continue-shopping");
	By Checkout=By.id("checkout");
	WebDriver driver = drive;

	public Cart_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public int cartpage_get_total_unique_products() {

		return driver.findElements(cartitems).size();

	}

	public int cartpage_get_total_quantity() {

		List<WebElement> x = driver.findElements(cartquantity);

		int total = 0;

		for (WebElement webElement : x) {

			total = total + Integer.getInteger(webElement.getText().trim());

		}

		return total;

	}

	public HashMap<String, String> cartpage_get_products_added() {

		HashMap<String, String> products_ = new HashMap<String, String>();
		List<WebElement> quantities = driver.findElements(cartquantity);
		List<WebElement> products = driver.findElements(cartitems);

		for (int i = 0; i < quantities.size(); i++) {
			products_.put(products.get(i).getText(), quantities.get(i).getText());
		}

		return products_;

	}

	public void cartpage_click_remove_product_by_name_or_index(String productname) {

		List<WebElement> removebutton = driver.findElements(Removebutton);
		List<WebElement> products = driver.findElements(cartitems);

		for (int i = 0; i < products.size(); i++) {

			if (products.get(i).getText().equalsIgnoreCase(productname)) {

				removebutton.get(i).click();

			}

		}

	}

	public void cartpage_click_remove_product_by_name_or_index(int i) {

		List<WebElement> removebutton = driver.findElements(Removebutton);

		removebutton.get(i).click();

	}

	public void cartpage_click_continue_shopping() {

		driver.findElement(Continue_shopping).click();

	}

	public void cartpage_click_checkout() {
		
		driver.findElement(Checkout).click();
		
		
	}



}



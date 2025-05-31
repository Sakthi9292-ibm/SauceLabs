package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Overview_Page extends Cust_Infomation_Page {

	By item_name = By.className("inventory_item_name");
	By item_desc = By.className("inventory_item_desc");
	By item_price = By.className("inventory_item_price");
	By summary_label = By.className("summary_info_label");
	By summary_value = By.className("summary_value_label");
	By subtotal = By.className("summary_subtotal_label");
	By subtax = By.className("summary_tax_label");
	By total = By.className("summary_total_label");
	By cancel = By.id("cancel");
	By finish = By.id("finish");
	By item_qty = By.className("cart_quantity");

	WebDriver driver = drive;

	public Overview_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public List<String> overview_page_get_products() {

		List<String> products = null;

		List<WebElement> pd = driver.findElements(item_name);

		for (WebElement webElement : pd) {

			products.add(webElement.getText());

		}

		return products;

	}

	public Map<String, HashMap<String, String>> overview_page_get_products_and_all_info() {

		List<WebElement> pd = driver.findElements(item_name);
		List<WebElement> qty = driver.findElements(item_qty);
		List<WebElement> pce = driver.findElements(item_price);
		Map<String, HashMap<String, String>> dtls = new HashMap<String, HashMap<String, String>>();

		for (int i = 0; i < pd.size(); i++) {

			HashMap<String, String> info = new HashMap<String, String>();

			info.put("Price", pce.get(i).getText());
			info.put("Quantity", qty.get(i).getText());

			dtls.put(pd.get(i).getText(), info);

		}

		return dtls;

	}

	public String overview_page_get_payment_info() {

		String payment_info = driver.findElements(summary_value).get(0).getText();

		return payment_info;

	}

	public String overview_page_get_shipping_info() {

		String shipping_info = driver.findElements(summary_value).get(1).getText();
		return shipping_info;
	}

	public String overview_page_get_item_total() {

		String item_total = driver.findElement(subtotal).getText();

		return item_total;

	}

	public String overview_page_get_tax_info() {

		String tax = driver.findElement(subtax).getText();

		return tax;

	}

	public String overview_page_get_overall_total() {

		String total_price = driver.findElement(total).getText();

		return total_price;

	}

	public void overview_page_click_cancel() {

		driver.findElement(cancel).click();

	}

	public void overview_page_click_finish() {

		driver.findElement(finish).click();

	}

}

package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Complete_Page;
import utils.ReadProperties;

public class stepdefinitions {

	WebDriver driver;
	Complete_Page cp;

	public stepdefinitions() {

	}

	@Before
	public void setup() {
		ReadProperties prop = new ReadProperties();

		if (prop.readConfig("browser").equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			final Map<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.password_manager_leak_detection", false);
			final ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(chromeOptions);
		}

		else if (prop.readConfig("browser").equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (prop.readConfig("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		cp = new Complete_Page(driver);

	}

	@AfterStep
	public void takeSS(Scenario scenario) throws IOException {
		scenario.attach(cp.attach_screenshot(), "image/png", "Screenshots");

	}

	@After
	public void flsh() {
		driver.close();
	}

	@Given("^User opens the sauceLab website \"(.*?)\"$")
	public void user_opens_the_sauceLab_website(String arg1) throws Throwable {

		driver.get(arg1);
		// cp.take_screenshot("website");
	}

	@When("^User enters login information with \"(.*?)\" and \"(.*?)\"$")
	public void user_enters_login_information_with_and(String arg1, String arg2) throws Throwable {

		cp.HomePage_enter_user_name(arg1);
		cp.HomePage_enter_password(arg2);

		// cp.take_screenshot("login_result_"+arg1+"_"+arg2+"_");

	}

	@When("^User click the login button$")
	public void user_click_the_login_button() throws Throwable {

		cp.HomePage_click_login();

	}

	@Then("^User should be redirected to the shopping page$")
	public void user_should_be_redirected_to_the_shopping_page() throws Throwable {

		// cp.take_screenshot("login_success_redirect");

		assertNotEquals(cp.shopping_page_get_all_product_details_in_page().size(), 0);

	}

	@Then("^website should throw \"(.*?)\"$")
	public void website_should_throw(String arg1) throws Throwable {

		Assert.assertTrue(cp.HomePage_get_errormessage().contains(arg1));
	}
	
	
	@When("User is in the shopping page")
	public void user_is_in_the_shopping_page() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println(cp.shopping_page_get_all_product_details_in_page().toString());
		
		int a=cp.shopping_page_get_all_product_details_in_page().size();

		assertNotEquals(a, 0);

	}

	@Then("User should see below {string} along with the {string}")
	public void user_should_see_below_along_with_the(String product, String price) {
		// Write code here that turns the phrase above into concrete actions

		HashMap<String, HashMap<String, String>> prod_details = cp.shopping_page_get_all_product_details_in_page();

		Set<String> keys = prod_details.keySet();

		for (String string : keys) {

			if (string.contains(product)) {

				System.out.println("Checking the price for ---" + string);

				String actual_price = prod_details.get(string).get("Price");

				System.out.println("Actual Price " + actual_price);
				assertEquals(price, actual_price);

			}

		}

	}

	@When("User clicks {string} button for products")
	public void user_clicks_button_for_products(String actiontotake, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> map : data) {

			if (actiontotake.contains("Add")) {

				cp.shopping_page_add_to_cart_by_product_name(map.get("product"));

			}

			if (actiontotake.contains("Remove")) {
				cp.shopping_page_remove_by_product_name(map.get("product"));
			}

		}

	}

	@Then("User should see {string} items added to cart in shopping page")
	public void user_should_see_items_added_to_cart_in_shopping_page(String string) {
		assertEquals(string, cp.shopping_pages_get_items_in_cart());

	}

	@When("User moves over the product name")
	public void user_moves_over_the_product_name() {
		// Write code here that turns the phrase above into concrete actions
		HashMap<String, HashMap<String, String>> products = cp.shopping_page_get_all_product_details_in_page();

		Set<String> pds = products.keySet();

		for (String string : pds) {

			cp.shopping_page_check_clickable_product_by_name(string);

		}

	}

	@Then("User should be allowed to click on it")
	public void user_should_be_allowed_to_click_on_it() {
		
		
	}

	@Then("User should not be allowed to edit the prices")
	public void user_should_not_be_allowed_to_edit_the_prices() {
		// Write code here that turns the phrase above into concrete actions
		
		List<WebElement> pcs = cp.get_prices();
		
		for (WebElement webElement : pcs) {
			
			 try {
				webElement.sendKeys("099");
				assertTrue(false);
			} catch (Exception e) {
				
				assertTrue(e.getMessage().contains("element not interactable"));
			}
			
			
		}
		
		
	}


}
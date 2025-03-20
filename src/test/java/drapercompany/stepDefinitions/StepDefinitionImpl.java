package drapercompany.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import drapercompany.TestComponents.BaseTest;
import drapercompany.pageobjects.CartPage;
import drapercompany.pageobjects.CheckoutPage;
import drapercompany.pageobjects.ConfirmationPage;
import drapercompany.pageobjects.LandingPage;
import drapercompany.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;	
	public ConfirmationPage confirmationPage;
	public CheckoutPage checkoutPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		//code that will be executed when it's triggered.
		landingPage = launchApplication();
	}
	
	@Given("^Logged in username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) throws InterruptedException {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) from Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		System.out.println("asserting product is displayed: " + productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		System.out.println("Comparing: "+string+"   To This: "+confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then ("{string} message is displayed")
	public void something_message_displayed(String strArg1) {
		System.out.println("Comparing: "+strArg1+"   To This: "+landingPage.getErrorMessage());
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.quit();
	}

}

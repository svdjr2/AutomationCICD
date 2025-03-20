package drapercompany.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import drapercompany.TestComponents.BaseTest;
import drapercompany.TestComponents.Retry;
import drapercompany.pageobjects.CartPage;
import drapercompany.pageobjects.CheckoutPage;
import drapercompany.pageobjects.ConfirmationPage;
import drapercompany.pageobjects.LandingPage;
import drapercompany.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";		
		//wrong password
		ProductCatalogue productCatalogue = landingPage.loginApplication("sdt74201@gmail.com", "Abcd!2345");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");// + "FAIL STRING");

	}
	
	
	@Test
	public void productErrorValidation() throws IOException, Exception {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("sdt74201@gmail.com", "Abcd!234");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");

		Assert.assertFalse(match);
		
	}

}

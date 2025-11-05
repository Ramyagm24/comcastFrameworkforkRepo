package com.webshop.crm.product;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.webshop.crm.FileUttility.ExcelUtility;

public class DemoShop {
	ExcelUtility elib = new ExcelUtility();

	int cell = 1;

	@DataProvider
	public Object[][] getData() throws Throwable {
		Object[][] obj = new Object[3][1];
		for (int i = 0; i < 3; i++) {
			obj[i][0] = elib.getDataFromExcelFile("DemowebShop", i + 1, 0);
		}
		return obj;
	}

	@Test(dataProvider = "getData")
	public void demoWebShopTest(String productname) throws Throwable {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.id("small-searchterms")).sendKeys(productname, Keys.ENTER);

		driver.findElement(By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'" + productname + "')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='overview']/div/div[@class='add-to-cart-panel']/input[@value='Add to cart']")).click();

		WebElement cart = driver.findElement(By.xpath("//span[.='Shopping cart']"));
		if (cart.isDisplayed()) {
			// Assert.assertEquals(cart1,"shopping cart");
			System.out.println(productname + " has been added to the cart");
		}

		cart.click();
		String price = driver.findElement(By.xpath("//td/a[text()='" + productname + "']/../following-sibling::td[@class='unit-price nobr']")).getText();
		Thread.sleep(1000);

		elib.writeDataIntoExcel("DemowebShop", cell++, 1, price);

		driver.quit();
	}
}

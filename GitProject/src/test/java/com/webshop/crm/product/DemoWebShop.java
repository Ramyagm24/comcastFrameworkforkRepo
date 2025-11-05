package com.webshop.crm.product;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.webshop.crm.FileUttility.ExcelUtility;
import com.webshop.crm.FileUttility.FileUtility;

public class DemoWebShop {
	ExcelUtility elib = new ExcelUtility();
	FileUtility flib = new FileUtility();

	@Test
	public void demoWebShopTest() throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.xpath("//div[@class='block block-category-navigation']/div/ul/li/a[contains(text(),'Apparel & Shoes')]")).click();
		driver.findElement(By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'Rockabilly Polka Dot Top')]")).click();
		driver.findElement(By.id("add-to-cart-button-5")).click();
		Thread.sleep(1000);
		WebElement cart1 = driver.findElement(By.xpath("//p[@class='content']/a[text()='shopping cart']"));
		if (cart1.isDisplayed()) {
			// Assert.assertEquals(cart1,"shopping cart");
			System.out.println("Top has been added to the cart");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"//div[@class='block block-category-navigation']/div/ul/li/a[contains(text(),'Apparel & Shoes')]"))
				.click();
		driver.findElement(
				By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'Blue and green Sneaker')]"))
				.click();
		driver.findElement(By.id("add-to-cart-button-28")).click();

		Thread.sleep(1000);
		WebElement cart2 = driver.findElement(By.xpath("//p[@class='content']/a[text()='shopping cart']"));
		if (cart2.isDisplayed()) {
			// Assert.assertEquals(cart1,"shopping cart");
			System.out.println("Sneaker has been added to the cart");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"//div[@class='block block-category-navigation']/div/ul/li/a[contains(text(),'Apparel & Shoes')]"))
				.click();
		driver.findElement(By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'Blue Jeans')]"))
				.click();
		driver.findElement(By.id("add-to-cart-button-36")).click();

		Thread.sleep(1000);
		WebElement cart3 = driver.findElement(By.xpath("//p[@class='content']/a[text()='shopping cart']"));
		if (cart3.isDisplayed()) {
			// Assert.assertEquals(cart1,"shopping cart");
			System.out.println("Blue Jeans has been added to the cart");
		}
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();

		List<WebElement> productname = driver.findElements(By.xpath("//a[@class='product-name']"));

		int count = productname.size();
		for (int i = 0; i < count; i++) {
			String name = productname.get(i).getText();

			Thread.sleep(1000);
			elib.setDataIntoExcel("product", i + 1, 0, name);

//			String price = prices.get(i).getText();
//			elib.setDataIntoExcel("product", i+1, 1, price);
			// System.out.println(text);
		}
		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='product-unit-price']"));
		int count1 = prices.size();
		for (int i = 0; i < count1; i++) {

			String price = prices.get(i).getText();
			elib.setDataIntoExcel("product", i + 1, 1, price);
		}

	}
}
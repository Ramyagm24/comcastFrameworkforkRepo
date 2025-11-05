package com.webshop.crm.product;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.webshop.crm.FileUttility.ExcelUtility;

public class DemoPractice {
	ExcelUtility elib = new ExcelUtility();
	@Test
	public void productName() throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/");
		
			String product = elib.getDataFromExcelFile("product", 1, 0);
			driver.findElement(By.id("small-searchterms")).sendKeys(product);
			driver.findElement(By.xpath("//input[@class='button-1 search-box-button']")).click();
			driver.findElement(By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'"+product+"')]")).click();
			driver.findElement(By.id("add-to-cart-button-28")).click();
		}
	}


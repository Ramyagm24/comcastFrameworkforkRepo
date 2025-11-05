package com.webshop.crm.product;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.webshop.crm.FileUttility.ExcelUtility;
import com.webshop.crm.FileUttility.FileUtility;

public class DemoProduct {
	WebDriver driver = null;
	
	ExcelUtility elib = new ExcelUtility();
	FileUtility flib = new FileUtility();
	WebDriverUtility wlib=new WebDriverUtility();
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		
		Object[][] obj=new Object[3][1];
		for(int i=0;i<3;i++) {
			obj[i][0]=elib.getDataFromExcelFile("product", i+1, 0);
			
		}
		return obj;
	}
	@Test(dataProvider = "getData")
	public void demoWebShopTest(String productname) throws Throwable {
		String BROWSER=flib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		//WebDriver driver = new ChromeDriver();
		wlib.maximize(driver);
		wlib.waitForPagetoLoad(driver);
		flib.getDataFromPropertiesFile(productname);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.id("small-searchterms")).sendKeys(productname);
		driver.findElement(By.xpath("//input[@class='button-1 search-box-button']")).click();
		driver.findElement(By.xpath("//div[@class='product-item']/descendant::h2/a[contains(text(),'"+productname+"')]")).click();
		driver.findElement(By.xpath("//div[@class='overview']/div/div[@class='add-to-cart-panel']/input[@value='Add to cart']")).click();
		
		
		
		WebElement cart = driver.findElement(By.xpath("//p[@class='content']/a[text()='shopping cart']"));
		if (cart.isDisplayed()) {
			// Assert.assertEquals(cart1,"shopping cart");
			System.out.println(productname+ " has been added to the cart");
		}
		
		 List<WebElement> price = driver.findElements(By.xpath("//span[@itemprop='price']"));
		Thread.sleep(1000);
		
		  int count = price.size();
		  for(int i=0;i<count;i++) {
			  String prices=price.get(i).getText();
			  System.out.println(prices);
			  
			  elib.setDataIntoExcel("product", i+1, 1, prices);
		  }
//		int i=1;
		 // elib.setDataIntoExcel("product", i+1, 1, prices);
		
//	 for(int i=0;i<elib.getRowCount("product");i++)
//	 {
//		 if(elib.getcell("product",i+1,0)==null) {
//			 i++;
//		 if(elib.getDataFromExcelFile("product",i+1, 0).contains(productname))
//		 {
//			 elib.setDataIntoExcel("product", i+1, 1, price);
//			 break;
//		 }
//		 }
//	 }
		
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		TakesScreenshot ts=(TakesScreenshot)driver;
		String d=new Date().toString().replace(" ","_").replace(":","_");
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/product_"+d+".png");
		FileUtils.copyFile(src, dest);
		
		driver.quit();
		
	}
}

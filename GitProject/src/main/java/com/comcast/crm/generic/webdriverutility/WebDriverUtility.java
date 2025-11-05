package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPagetoLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitforElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToTabOnURL(WebDriver driver, String partialurl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowid = it.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partialurl)) {
				break;
		}
		}
	}
		public void switchbackToTabOnURL(WebDriver driver, String partialurl) {
			Set<String> set1 = driver.getWindowHandles();
			Iterator<String> it1 = set1.iterator();
			while(it1.hasNext()) {
				String windowid = it1.next();
				driver.switchTo().window(windowid);
			String acturl1 = driver.getCurrentUrl();
			if(acturl1.contains(partialurl)) {
				break;
		}
	}
		}
	public void switchToTabOnTitle(WebDriver driver, String partialtitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowid = it.next();
			driver.switchTo().window(windowid);
			String acturl = driver.getTitle();
			if(acturl.contains(partialtitle)) {
				break;
		}
	}
	}
		public String parentwindowHandle(WebDriver driver) {
			String parent = driver.getWindowHandle();
			return parent;
		}
		public void switchBackToParent(WebDriver driver, String data) {
			driver.switchTo().window(data);
            	
		}
		public void switchWindow(WebDriver driver,String windowID) {
			 driver.switchTo().window(windowID);
		}
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void switchToAlertAndtext(WebDriver driver) {
		driver.switchTo().alert().getText();
	}
	public void switchToAlertAndsendkeys(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	public void select(WebElement element, String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void selectvalue(WebElement element, String value) {
		Select sel=new Select(element);
		sel.selectByValue(value);
	}
	public void select(WebElement element, int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();;
	}
	public void doubleclick(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).perform();;
	}
	public void rightclick(WebDriver driver, WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).perform();;
	}
	public void draganddrop(WebDriver driver, WebElement element,WebElement element1) {
		Actions action=new Actions(driver);
		action.dragAndDrop(element, element1).perform();;
	}
	public void scrollDown(WebDriver driver, int x, int y) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}
	public void handlingAlertPopup(WebDriver driver) {
		Alert a=driver.switchTo().alert();
		a.accept();
	}
	public void handlingAlertPopupByCancelling(WebDriver driver) {
		Alert a=driver.switchTo().alert();
		a.dismiss();
	}
}
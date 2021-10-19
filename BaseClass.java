package org.base;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static  WebDriver driver;
	public static WebDriver launchbrowser(String browsername) {
		switch (browsername) {
		case "chrome":
			WebDriverManager.chromedriver().setup(); 
			driver=new ChromeDriver(); 
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup(); 
	        driver=new FirefoxDriver();  
	        break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
	   break;
		default:
			System.out.println("........invalid driver......");
			break;
		}
		return driver;		
	}
	public static void launchurl(String url) {
	driver.get(url);
	}
	public static String currenturl() {
		String url=driver.getCurrentUrl();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return url;
	}
	public static WebElement FindElement(String locatename,String locate) {
		WebElement e=null;
		if(locatename.equals("id")) {
		e=driver.findElement(By.id(locate));
	}
		else if(locatename.equals("name")) {
		e=driver.findElement(By.name(locate));	
		}
		else if(locatename.equals("xpath")) {
			e=driver.findElement(By.xpath(locate));
		}
	return e;
	}
	public static void filltext(WebElement e,String words) {
		e.sendKeys(words);
	}
     public static void clickclse(WebElement e) {
		e.click();
	}
	public static void btnclick(WebElement e) {
		e.click();
	}
	public static String attribute(WebElement e) {
	return e.getAttribute("value");
	}
	public static String text(WebElement e) {
		return e.getText();
	}
	public static void movetoelement(WebElement e ) {
		Actions a=new Actions(driver); 
		a.moveToElement(e).perform();
		}
public static String screenshot( String name ) throws IOException {
TakesScreenshot tk=(TakesScreenshot)driver;
File src=tk.getScreenshotAs(OutputType.FILE);
File des=new File("C:\\Users\\Admin\\eclipse-workspace\\selenium0\\screenshot\\"+name+".png");
FileUtils.copyFile(src, des);
return name;
}
public static void  nextscreen() {
	Set<String> getallid = driver.getWindowHandles();
	int count=0;
	for(String eachid: getallid) {
		if(count==1) {
			driver.switchTo().window(eachid);
		}
	  count++;
	}
}
public static void selectbyvalue(WebElement e,String value) {
	Select s=new Select(e);
	s.selectByValue(value);
}	
public static void selectbyindex(WebElement e,int index) {
	Select s=new Select(e);
	s.selectByIndex(index);
}
public static void clear(WebElement e) {
	e.clear();
}
public static String getattribute(WebElement e) {
	 return e.getAttribute("value");
}
}

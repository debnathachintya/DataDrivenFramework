package com.w2a.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.w2a.utilities.ExcelUtils;

public class TestBase {
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static ExcelUtils excel = new ExcelUtils();
	public static WebDriverWait wait;
	
	@BeforeSuite
	public void setup() throws Exception {
		if(driver==null) {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			config.load(fis);
			
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			
			if(config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.get(config.getProperty("testsiteurl"));
			String durationWait = config.getProperty("implicit.wait");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(durationWait)));
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		}
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	@AfterSuite
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
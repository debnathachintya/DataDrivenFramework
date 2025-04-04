package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class AddCustomerTest extends TestBase {
	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText) throws InterruptedException {
		/*
		 * Thread.sleep(2000);
		 * driver.findElement(By.xpath(OR.getProperty("bmlBtn"))).click();
		 */
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
		
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(2000);
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		
		Thread.sleep(2000);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		String fileName = config.getProperty("testDataFilePath");
		String sheetName = config.getProperty("testDataSheetName");
		
		int rows = excel.getRowCount(fileName, sheetName);
		int cols = excel.getCellCount(fileName, sheetName, 2);
		
		System.out.println("Row Count: "+ rows + " Cell Count: "+cols);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			for(int colNum=0; colNum<cols; colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(fileName, sheetName, rowNum, colNum);
			}
		}
		return data;
	}
}

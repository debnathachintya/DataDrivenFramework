package com.w2a.utilities;

import com.aventstack.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	
	public static void getInstance() {
		if(extent==null) {
			extent = new ExtentReports();
		}
	}
}

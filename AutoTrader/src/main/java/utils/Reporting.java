package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class Reporting {
	static ExtentReports report;
	public String TestcaseName,Testcasedesc,authors,categories;
	static ExtentTest test;
	
	public void startReport() {
		long time =System.currentTimeMillis();
		 report = new ExtentReports("./reports/"+time+".html");
	}
	
	public void startTest(String TestcaseName,String Testcasedesc) {
		 test = report.startTest(TestcaseName,Testcasedesc);
		// test.assignAuthor(authors);
		 //test.assignCategory(categories);
	}
	
	public void logStep(String status,String desc) {
	
		long path = takeSnap();
		
		if(status.equalsIgnoreCase("pass")) {
		test.log(LogStatus.PASS, desc+test.addScreenCapture("../screenshots/"+path+".png"));
		} else if(status.equalsIgnoreCase("fail")) {
		test.log(LogStatus.FAIL,desc+test.addScreenCapture("../screenshots/"+path+".png"));
		} else if (status.equalsIgnoreCase("info")) {
		test.log(LogStatus.INFO,desc+test.addScreenCapture("../screenshots/"+path+".png"));
		}
	}
	public abstract long takeSnap();
	
	public void endTest() {
		report.endTest(test);
	}
	public void endReport() {
		report.flush();
	}

}

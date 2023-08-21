package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest test;
	
	String repName;

	public void onStart(ITestContext context) {
		//this will make the report with proper date and time mentioned
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		// giving the location of the report
		spark = new ExtentSparkReporter(".\\reports\\"+repName);
		//These three code is responsible to set the interface of the report
		spark.config().setDocumentTitle("RestAssuredAtumationPramework");
		spark.config().setReportName("Pet Store User API");
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Envirnoment", "QA");
		extent.setSystemInfo("user", "salman");

	}

	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passes");
		
		
	}

	public void onTestFailure(ITestResult result) {
	
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}

	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

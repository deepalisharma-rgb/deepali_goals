package api.utilities;

import java.io.File;
import java.nio.file.Paths;
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

	public ExtentSparkReporter reporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	static String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		if (extent == null) {
			extent = new ExtentReports();
			String reportDir = Paths.get(GenericMethods.getProjectRootDirectory(), "reports").toString();
			File dir = new File(reportDir);
			if (!dir.exists())
				dir.mkdir();
			String path = Paths.get(reportDir, "report.html").toString();
			reporter = new ExtentSparkReporter(path+repName); // specify the location of the report
//			reporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify the location of the report
			reporter.config().setReportName("Automation Report");
			reporter.config().setDocumentTitle("Rest Assured Automation Report");
			reporter.config().setTheme(Theme.DARK);
			extent.attachReporter(reporter);
			extent.setSystemInfo("user", "Deepali Sharma");
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}	
	
}

package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	public static String screenshotFolderPath;
	
	public void onStart(ITestContext context)
	{
		
		//Generating report name
		Date d = new Date();
		String reportsFolder=d.toString().replaceAll(":", "-") +"//screenshots";
		screenshotFolderPath = System.getProperty("user.dir") +"//reports//"+reportsFolder;
		String reportFolderPath = System.getProperty("user.dir") +"//reports//"+d.toString().replaceAll(":", "-");
		File f = new File(screenshotFolderPath);
		f.mkdirs();// create dynamic report folder name + screenshots
		
		
		
		//String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//String repName = "Test-report-"+timestamp+".html";
		//System.out.println(".\\reports\\"+repName);
		//spark = new ExtentSparkReporter(".\\reports\\"+repName);//specify  location of the reports
		spark = new ExtentSparkReporter(reportFolderPath+"//Test-report.html");//specify  location of the reports
		
		spark.config().setDocumentTitle("OpenCart Automation Report");
		spark.config().setReportName("OpenCart Functional Testing");
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = context.getCurrentXmlTest().getParameter("os");		
		extent.setSystemInfo("Operating System", os);
		
		String br = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", br);
		
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed");		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		try
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}
		catch (Exception e1)
		{
			e1.printStackTrace();			
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");	
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();		
	}
	
		
	
	
}

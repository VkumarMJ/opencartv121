package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ExtentReportManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties prop;
	
	 
	
	@BeforeClass(groups= {"Sanity","Master","Regression","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		
		FileInputStream fis= new FileInputStream("./src//test//resources//config.properties");
		prop= new Properties();
		prop.load(fis);
		
		
		logger = LogManager.getLogger(this.getClass());
		
		if(prop.getProperty("execution_env").equals("remote"))
		{
			DesiredCapabilities dc = new DesiredCapabilities();
			//dc.setPlatform(Platform.WIN11);
			//dc.setBrowserName(br)
			if(os.equalsIgnoreCase("windows"))
			{
				dc.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				dc.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
				case "chrome" : dc.setBrowserName("chrome"); break;
				case "edge" : dc.setBrowserName("MicrosoftEdge"); break;
				default :System.out.println("No matching browser : "); return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
			
		}
			
		if(prop.getProperty("execution_env").equals("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default :System.out.println("Invalid browser name : "); return;
			}
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	

	@AfterClass(groups= {"Sanity","Master","Regression","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException
	{
		//-String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		//-TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		
		//String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		//-File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(sourceFile, new File(screenshotFolderPath+"//"+screenshotFile));
		//-String targetFilePath = System.getProperty("user.dir")+"\\screeshots\\"+tname+"_"+timestamp+".png";
		//-File targetFile = new File(targetFilePath);
		
		//-sourceFile.renameTo(targetFile);
		
		//-return targetFilePath;
		
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//try {
			// get the dynamic folder name
		//String val = ExtentReportManager.screenshotFolderPath;
		String path = ExtentReportManager.screenshotFolderPath+"//"+screenshotFile;
		FileUtils.copyFile(srcFile, new File(path));
			//put screenshot file in reports
			//String targetFilePath = System.getProperty("user.dir")+"\\screeshots\\"+tname+"_"+timestamp+".png";
		//	test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(screenshotFolderPath+"//"+screenshotFile));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		return path;
		
	}
	
	
	
}

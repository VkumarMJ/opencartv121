package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	
	public void verify_login()
	{
		try
		{
			logger.info("******** starting Testcase TC002_LoginTest *****");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info(" Clicked on the My Account.....");
			hp.clickLogin();
			logger.info(" Clicked on the Login.....");
			
			LoginPage lt = new LoginPage(driver);
			logger.info("Providing Login detais........");
			lt.setEmailAddress(prop.getProperty("email"));
			lt.setPassword(prop.getProperty("password"));
			lt.clickLogin();
			logger.info("Validating Login successfull....");
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean maHeader = macc.isMyAccountpageExsits();
			if(maHeader)
			{
				Assert.assertTrue(maHeader);
			}
			else
			{
				logger.error("Test Failed ....");
			}
			MyAccountPage myacc = new MyAccountPage(driver);
			myacc.clickLogout();
			logger.info(" Clicked on the Logout.....");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("******** Finished Testcase TC002_LoginTest *****");
	}
	
}

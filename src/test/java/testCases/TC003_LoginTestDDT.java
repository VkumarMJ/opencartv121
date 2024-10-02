package testCases;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass
{
	@Test(dataProvider="data", dataProviderClass=DataProviders.class,groups="Sanity")	
	
	public void verfiy_loginddt(String email, String pwd, String expResult)
	//public void verfiy_loginddt(HashMap <String,ArrayList<String>> testData)
	{
		//Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\testdata\\Opencart_LoginData.xlsx");
		
		//int val = xls.getRowCount("Login");
		//System.out.println("--------------  "+val+"  ------------");
	
		
		try
		{
			logger.info("**************** Starting TC003_LoginDDT***************");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
						
			LoginPage lt = new LoginPage(driver);
			
			lt.setEmailAddress(email);
			lt.setPassword(pwd);
			lt.clickLogin();
			
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountpageExsits();
			if(expResult.equalsIgnoreCase("Valid"))
			{
				if(targetPage = true)
				{
					Assert.assertTrue(true);
					macc.clickLogout();
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			else
			{
				if(targetPage = true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**************** Finished TC003_LoginDDT***************");
		
		
		
		
		
	}
}

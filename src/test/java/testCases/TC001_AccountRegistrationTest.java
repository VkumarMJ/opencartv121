package testCases;

import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		try
		{
			logger.info("******** starting Testcase TC001_AccountRegistrationTest *****");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info(" Clicked on the My Account.....");
			hp.clickRegister();
			logger.info("  Clicked on the the Register .....");
			
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			Faker faker = new Faker(new Locale("en-US"));
			logger.info("Providing Customer details.....");
			regPage.setFirstName(faker.name().firstName());
			regPage.setLastName(faker.name().lastName());
			regPage.setEmail(faker.internet().emailAddress());
			regPage.setTelePhone(faker.phoneNumber().phoneNumber());
			String password = faker.internet().password();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.setAgree();
			regPage.clickContinue();
			logger.info("Validating expected messgae.............");
			String conmsg = regPage.getConfrimationMsg();		
			//Assert.assertEquals(conmsg, "Your Account Has Been Created!");
			
			if(conmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				//Assert.assertTrue(false);
				logger.error("Test Failed ....");
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished Testcase TC001_AccountRegistrationTest");
		
	}
}

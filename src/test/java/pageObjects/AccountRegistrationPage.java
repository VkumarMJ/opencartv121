package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.javafaker.PhoneNumber;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastName;
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txttelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtpasswordConfirm;
	@FindBy(xpath="//input[@name='agree']") WebElement chkagree;
	@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	     	       
	
	public void setFirstName(String fname)
	{
		txtfirstName.sendKeys(fname); 
	}	
	public void setLastName(String lname)
	{
		txtlastName.sendKeys(lname); 
	}	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email); 
	}	
	public void setTelePhone(String phoneNumber)
	{
		txttelephone.sendKeys(phoneNumber); 
	}	
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd); 
	}	
	public void setConfirmPassword(String pwdconfirm)
	{
		txtpasswordConfirm.sendKeys(pwdconfirm); 
	}	
	public void setAgree()
	{
		chkagree.click(); 
	}
	
	public void clickContinue()
	{
		btncontinue.click();
	}
	
	
	public String getConfrimationMsg()
	{
		try 
		{
			return(msgConfirmation.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	
	
}

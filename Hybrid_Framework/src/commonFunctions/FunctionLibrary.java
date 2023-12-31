package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
//method for login
	public static boolean pbLogin(String username,String password)
	{
		driver.findElement(By.xpath(conpro.getProperty(""))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty(""))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty(""))).click();
		String Expected = "adminflow";
		String Actual = driver.getCurrentUrl();
		if(Actual.toLowerCase().contains(Expected.toLowerCase()))
		{
			Reporter.log("Login Sucess::"+Expected+"     "+Actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+Expected+"   "+Actual,true);
	}
		return false;
}
	//method for click branches
	public static void pbBranches()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
	}
	//method for new branch creation
	public static boolean pbBranchecrate(String BranchName,String Address1,String Address2,String Address3,
			String Area,String Zipcode,String Country,String state,String City) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjBranchName"))).sendKeys(BranchName);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(Address1);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(Address2);
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(Address3);
		driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys(Area);
		driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys(Zipcode);
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjCountry")))).selectByVisibleText(Country);
		new Select(driver.findElement(By.xpath(conpro.getProperty("ObjState")))).selectByVisibleText(state);
	    new Select(driver.findElement(By.xpath(conpro.getProperty("Objcity")))).selectByVisibleText(City);
		driver.findElement(By.xpath(conpro.getProperty("Objsubmit"))).click();
		//capture alert message
		String Expected_Alert = driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String Actual_Alert = "New Branch with id";
		if(Expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
		{
			Reporter.log(Expected_Alert,true);
			return true;
		}
		else 
		{
			Reporter.log("Fail To create New Branch",true);
			return false;
		}
		}
	//method for branch updation
	public static boolean pbBranchUpdate(String Branchname,String Address,String Area,String Zip) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
		WebElement element1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
		element1.clear();
		element1.sendKeys(Branchname);
		WebElement element2 = driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(Address);
		WebElement element3 = driver.findElement(By.xpath(conpro.getProperty("ObjAreaName")));
		element3.clear();
		element3.sendKeys(Area);
		WebElement element4 = driver.findElement(By.xpath(conpro.getProperty("Objzip")));
		element4.clear();
		element4.sendKeys(Zip);
		driver.findElement(By.xpath(conpro.getProperty("ObjUpdate"))).click();
		String Expected_Alert = driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String Actual_Alert = "Branch updated";
		if(Expected_Alert.toLowerCase().contains(Actual_Alert.toLowerCase()))
		{
			Reporter.log(Expected_Alert,true);
			return true;
			}
		else
		{
			Reporter.log("Fail to update Branch",true);
			return true;
		}
	}
	//method for logout
	public static boolean pbLogout()
	{
		driver.findElement(By.xpath(conpro.getProperty(""))).click();
		if(driver.findElement(By.xpath(conpro.getProperty(""))).isDisplayed())
		{
			Reporter.log("Login success",true);
			return true;
		}
		else
		{
		Reporter.log("Logout Fail",true);
		return false;
		}
}

}









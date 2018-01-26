package tests;

import java.awt.AWTException;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TeamSetup_Ticket_67 {
	
	public static WebDriver driver;
	static String baseurl="http://leo-limra-controlpanel.limra.leo-development.leocnames.com";
	WebElement teamName2,PlayName2;
	
	@BeforeMethod
	public void setup(){
		
		//set chrome driver path here
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test(priority=1) //Check Recruitment & Selection link works correctly
	public void IsRSBreadcrumbDisplayCorrectly() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		//click on NO
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'RECRUITMENT & SELECTION')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'TRAINING & DEVELOPMENT')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'BUSINESS MANAGEMENT')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'SUBMIT')]")).isEnabled(), 
				"When I am on the Recruitment & Selection page\n Recruitment & Selection link is in an active state\n And all other Decision Input links are not inactive\n:Failing Test!");
		
				System.out.println("When I am on the Recruitment & Selection page\n Recruitment & Selection link is in an active state \n And all other Decision Input links are inactive\n:Passing Test!");
		
	}
	
	
	@Test(priority=2) //Check Training & Development link works correctly
	public void IsTNDLinkworksCorrectly() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		//click on NO
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click();
		Thread.sleep(2000);
		
		//Next button
		driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'TRAINING & DEVELOPMENT')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'BUSINESS MANAGEMENT')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'SUBMIT')]")).isEnabled(), 
				"When I am on the Recruitment & Selection and click on Next\n Then the Training & Development link is in an active state/visible\nAnd Business Mgt and Submit links are not inactive\n:Failing Test!");
		
				System.out.println("When I am on the Recruitment & Selection and click on Next\n Then the Training & Development link is in an active state/visible\nAnd Business Mgt and Submit links are inactive\n:Passing Test!");
		
	}
	
	
	@Test(priority=2) //Check Business Management link works correctly
	public void IsBMLinkworksCorrectly() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		//click on NO
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click();
		Thread.sleep(2000);
		
		//Next button on Recruitment & Selection
		driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
		Thread.sleep(2000);
		
		//Next button on TRAINING & DEVELOPMENT
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/button")).click();
				Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'BUSINESS MANAGEMENT')]")).isEnabled() && 
				!driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'SUBMIT')]")).isEnabled(), 
				"When I am on the TRAINING & DEVELOPMENT and click on Next\n Then the BUSINESS MANAGEMENT link is in an active state/visible\nAnd Submit links are not inactive\n:Failing Test!");
		
				System.out.println("When I am on the TRAINING & DEVELOPMENT and click on Next\n Then the BUSINESS MANAGEMENT link is in an active state/visible\nAnd Submit links are inactive\n:Passing Test!");
		
	}
	
	
	@Test(priority=2) //Check Submit link works correctly
	public void IsSubmitLinkworksCorrectly() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		//click on NO
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click();
		Thread.sleep(2000);
		
		//Next button on Recruitment & Selection
		driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
		Thread.sleep(2000);
		
		//Next button on TRAINING & DEVELOPMENT
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/button")).click();
				Thread.sleep(2000);
				
				//Next button on BUSINESS MANAGEMENT
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[2]/div/button")).click();
				Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'financeTab') and contains(text(),'SUBMIT')]")).isEnabled(), 
				"When I am on the BUSINESS MANAGEMENT and click on Next\n Then the SUBMIT link is not in an active state/visible\n:Failing Test!");
		
				System.out.println("When I am on the BUSINESS MANAGEMENT and click on Next\n Then the SUBMIT link is in an active state/visible\n:Passing Test!");
		
	}
	
	
	@AfterMethod
	public void teardown(ITestResult result){
		
		if(ITestResult.FAILURE==result.getStatus()){
			try{
				// To create reference of TakesScreenshot
				TakesScreenshot screenshot=(TakesScreenshot)driver;
				// Call method to capture screenshot
				File src=screenshot.getScreenshotAs(OutputType.FILE);
				// Copy files to specific location 
				// result.getName() will return name of test case so that screenshot name will be same as test case name
				FileUtils.copyFile(src, new File("screenshots\\"+result.getName()+".png"));
				System.out.println("Successfully captured a screenshot");
				
			}catch (Exception e){
				
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
	}
		driver.quit();
		
	}
	
}

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


public class TeamSetupHomepage_Ticket_29 {
	
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
	
	
	@Test(priority=1) //Check Year 1 inactive button state
	public void IsYear1BtnInDisabledState() throws InterruptedException, AWTException{
		
		WebElement Year1Btn=driver.findElement(By.xpath("//button[contains(@class,'homeButton yearOneBtn')]"));
		Thread.sleep(2000);
		
		if(Year1Btn.isDisplayed()){
			
			Year1Btn.click();
			Thread.sleep(2000);
			
			Assert.assertTrue(!driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/yearone/situation/1"), 
					"Year1Btn is Active and clickable, Failing Test!");
			
			System.out.println("Year1Btn is Inactive and not clickable, Passing Test!");
			
		}else {
			
			System.out.println("Year1Btn button is not displaying!");
		}
		
	}
	
	
	@Test(priority=2) //Check Year 1 active button state
	public void IsYear1BtnActiveState() throws InterruptedException, AWTException{
		
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
					
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
						Thread.sleep(2000);
						
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
				
				
				 /* clicking on Set up complete button to check
				 * homepage is displayed
				 * Year1Btn button displays in it's 'Completed' and 'Active' state 
				 */
				
				 
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(2000);
				
				WebElement Year1Btn=driver.findElement(By.xpath("//button[contains(@class,'homeButton yearOneBtn')]"));
				
				if(Year1Btn.isDisplayed()){
					
					Year1Btn.click();
					Thread.sleep(2000);
			
					Assert.assertTrue(driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/yearone/situation/1"), 
					"Year1Btn is inactive and not clickable, Failing Test!");
					
					System.out.println("Year1Btn displays in it's Active state, passing Test!");
					
				}else {
					
					System.out.println("Year1Btn button is not displaying!");
				}
		
	}
	
	
	@Test(priority=3) //Check the Year 1 inactive button works
	public void IsYear1InactiveButtonWorks() throws InterruptedException, AWTException{
		
				
				WebElement Year1Btn=driver.findElement(By.xpath("//button[contains(@class,'homeButton yearOneBtn')]"));
				Thread.sleep(2000);
				
				if(Year1Btn.isDisplayed()){
					
					Year1Btn.click();
					Thread.sleep(2000);
					
					Assert.assertTrue(!driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/yearone/situation/1"), 
							"Year1Btn is clickable, Failing Test!");
					
					System.out.println("Year1Btn is not clickable, Passing Test!");
					
				}else {
					
					System.out.println("Year1Btn button is Active");
				}
				
	}
	
	
	@Test(priority=4) //Check the Year 1 active button works
	public void IsYear1ActiveBtnWorks() throws InterruptedException, AWTException{
		
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
				boolean flag;
				
				//loop to enter values for all 2 teams tiles dynamically fetched locators
				for(int j=1;j<=teamDiv.size();j++){
					
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
						Thread.sleep(2000);
						
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
				
				/*
				 * clicking on Set up complete button to check
				 * homepage is displayed
				 * Year1Btn button displays in it's Active state
				 */
				 
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(2000);
				
					
				WebElement Year1Btn=driver.findElement(By.xpath("//button[contains(@class,'homeButton yearOneBtn')]"));
				
				if(Year1Btn.isDisplayed()){
					
					Year1Btn.click();
					Thread.sleep(2000);
					
					Assert.assertTrue(driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/yearone/situation/1") 
							&& driver.findElement(By.xpath("//*[@id='root']/div/div[1]/h2")).getText().equals("YEAR 1"), 
							"Year1Btn page is not displaying for Active state of Year1Btn Button, Failing Test!");
					
					System.out.println("Year1Btn page is displaying for Active state of Year1Btn Button , Passing Test!");
					
				}else {
					
					System.out.println("Year1Btn is not displaying/Enabled!");
					
				}		
	
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

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


public class TeamSetup_Ticket_45 {
	
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
	
	
	@Test(priority=1) //Check Setup Complete button becomes active
	public void IsSetupCompleteBtnActive() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(3000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(3000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(3000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(3000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(3000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		if(driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).isEnabled()){
			
			Assert.assertTrue(true);
			System.out.println("Setup Complete button becomes active on second team setup, passing Test!");
			
		}else {
			
			Assert.fail("Setup Complete button doesn't active on second team setup, Failing Test!");
		}
		
	}
	
	
	@Test(priority=2) //Check Setup Complete button works
	public void IsSetupCompleteBtnWorks() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(3000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(3000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(3000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(3000);
			
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		//clicking on Set up complete button to check
		/* homepage is displayed
		 * Team Setup button displays in it's 'Completed' state
		 * Year 1 button is now active
		 */
		
		WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
		SetCompleteBtn.click(); Thread.sleep(3000);
		
		if(driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/") && 
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/button[1]")).isEnabled() && 
				driver.findElement(By.xpath("//button[contains(@class,'teamSetupBtn completed')]")).getAttribute("class").endsWith("completed")){
			
			Assert.assertTrue(true);
			System.out.println("Set up complete button works, passing Test!");
			
		}else {
			
			Assert.fail("Set up complete button doesn't works, Failing Test!");
		}
		
	}
	
	
	@Test(priority=3) //Check Setup Complete button does not works while only 1 team
	public void IsSetupCompleteBtnDisabledWhile1Team() throws InterruptedException, AWTException{
		
				//finding team setup button
				WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
				teamSetupBtn.click();
				Thread.sleep(3000);

				//Adding a tile to test deletion of data
				WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
				WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
				
				teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
				PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
				
				//logo selection
				driver.findElement(By.xpath("//button[contains(text(),'LOGO')]")).click();
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id='l0']")).click();
				Thread.sleep(3000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(3000);
				
				//clicking away from the tile fields
				driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
				Thread.sleep(3000);
				
				if(!driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).isEnabled()){
					
					Assert.assertTrue(true);
					System.out.println("Setup Complete button is disabled for 1 team setup, passing Test!");
					
				}else {
					
					Assert.fail("Setup Complete button is not disabled for 1 team setup, Failing Test!");
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

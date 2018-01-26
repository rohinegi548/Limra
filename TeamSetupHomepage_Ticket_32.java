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


public class TeamSetupHomepage_Ticket_32 {
	
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
	
	
	@Test(priority=1) //Check Reset Game inactive button state
	public void IsResetGameBtnInactiveState() throws InterruptedException, AWTException{
		
		WebElement ResetGameBtn=driver.findElement(By.xpath("//button[contains(text(),'Reset the Profit Engine')]"));
		Thread.sleep(1000);
		
		if(ResetGameBtn.isDisplayed()){
			
			Assert.assertTrue(!ResetGameBtn.isEnabled(), "ResetGameBtn is in it's Active State, Failing Test!");
			
			System.out.println("ResetGameBtn is in it's Inactive State, Passing Test!");
			
		}else {
			
			System.out.println("ResetGameBtn button is not displaying!");
		}
		
	}
	
	
	@Test(priority=2) //Check Reset Game active button state
	public void IsResetGameBtnActiveState() throws InterruptedException, AWTException{
		
				//finding team setup button
				WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
				teamSetupBtn.click();
				Thread.sleep(1000);
				
				//finding Add Team button
				WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
				AddTeamBtn.click();
				Thread.sleep(1000);
						
				List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
				
				int k=1;
				
				//loop to enter values for all 2 teams tiles dynamically fetched locators
				for(int j=1;j<=teamDiv.size();j++){
					
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
						Thread.sleep(1000);
						
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
						Thread.sleep(1000);
						
						//logo selection
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
						Thread.sleep(1000);
						
						driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
						Thread.sleep(1000);
						
						//saving logo 
						driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
						Thread.sleep(1000);
					
				}
				
				//clicking away from the tile fields
				driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
				Thread.sleep(1000);
				
				
				/*  clicking on Set up complete button to check
				 * homepage is displayed
				 * Year1Btn button displays in it's 'Completed' and 'Active' state 
				 */
				 
				
				 
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(1000);
				
				WebElement ResetGameBtn=driver.findElement(By.xpath("//button[contains(text(),'Reset the Profit Engine')]"));
				
				if(ResetGameBtn.isDisplayed()){
					
					Assert.assertTrue(ResetGameBtn.isEnabled(), "ResetGameBtn is Inactive and not clickable, Failing Test!");
					
					System.out.println("ResetGameBtn is Active and clickable, Passing Test!");
					
				}else {
					
					System.out.println("ResetGameBtn button is not displaying!");
				}
		
	}
	
	
	@Test(priority=3) //Check the Reset Game inactive button works
	public void IsResetGameInactiveButtonWorks() throws InterruptedException, AWTException{
		
				WebElement ResetGameBtn=driver.findElement(By.xpath("//button[contains(text(),'Reset the Profit Engine')]"));
				Thread.sleep(1000);
				
				if(ResetGameBtn.isDisplayed()){
						
					Assert.assertTrue(!ResetGameBtn.isEnabled(), "ResetGameBtn is Active and clickable, Failing Test!");
					
					System.out.println("ResetGameBtn is Inactive and not clickable, Passing Test!");
					
				}else {
					
					System.out.println("ResetGameBtn button is not displaying!");
				}
				
	}
	
	
	@Test(priority=4) //Check the Reset Game active button works
	public void IsResetGameActiveBtnWorks() throws InterruptedException, AWTException{
		
		       //finding team setup button
				WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
				String tempteamSetupBtnClass1=teamSetupBtn.getAttribute("class");
				teamSetupBtn.click();
				Thread.sleep(1000);
				
				//finding Add Team button
				WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
				AddTeamBtn.click();
				Thread.sleep(1000);
						
				List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
				
				int k=1;
				
				//loop to enter values for all 2 teams tiles dynamically fetched locators
				for(int j=1;j<=teamDiv.size();j++){
					
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
						Thread.sleep(1000);
						
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
						Thread.sleep(1000);
						
						//logo selection
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
						Thread.sleep(1000);
						
						driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
						Thread.sleep(1000);
						
						//saving logo 
						driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
						Thread.sleep(1000);
					
				}
				
				//clicking away from the tile fields
				driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
				Thread.sleep(1000);
				
				
				/* clicking on Set up complete button to check
				 * homepage is displayed
				 * Year1Btn button displays in it's Active state
				 */
				 
				 
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(1000);
				
					
				WebElement ResetGameBtn=driver.findElement(By.xpath("//button[contains(text(),'Reset the Profit Engine')]"));
				Thread.sleep(1000);
				
				String tempteamSetupBtnClass2;
				
				if(ResetGameBtn.isDisplayed()){
					
					ResetGameBtn.click();
					Thread.sleep(1000);
					
					try {
						
						tempteamSetupBtnClass2=teamSetupBtn.getAttribute("class");
						
					} catch (Exception e) {
						
						teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
						tempteamSetupBtnClass2=teamSetupBtn.getAttribute("class");
					}
					
					Assert.assertTrue(
							driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/") && 
							(tempteamSetupBtnClass1.equals(tempteamSetupBtnClass2)), 
							"ResetGameBtn page is not displaying for Active state of ResetGameBtn Button, Failing Test!");
					
					System.out.println("ResetGameBtn page is displaying for Active state of ResetGameBtn Button , Passing Test!");
					
				}else {
					
					System.out.println("ResetGameBtn is not displaying/Enabled!");
					
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

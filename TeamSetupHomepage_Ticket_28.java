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


public class TeamSetupHomepage_Ticket_28 {
	
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
	
	
	@Test(priority=1) //Check Team Setup incomplete button state
	public void IsTeamSetupBtnIncompleteState() throws InterruptedException, AWTException{
		
		WebElement teamSetupBtn=driver.findElement(By.xpath("//button[contains(@class,'homeButton teamSetupBtn')]"));
		Thread.sleep(3000);
		
		Assert.assertTrue(teamSetupBtn.getAttribute("class").endsWith("notStarted"), "Team setup button found with the class attribute="+teamSetupBtn.getAttribute("class")+", Failing Test!");
				
		System.out.println("Check Team Setup button is in incomplete state, Passing Test!");
		
	}
	
	
	@Test(priority=2) //Check Team Setup button in completed state
	public void IsTeamSetupBtnCompleteState() throws InterruptedException, AWTException{
		
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
				
				/*
				 * clicking on Set up complete button to check
				 * homepage is displayed
				 * Team Setup button displays in it's 'Completed' state
				*/
				 
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(3000);
				
				if(driver.findElement(By.xpath("//button[contains(@class,'homeButton teamSetupBtn')]")).getAttribute("class").endsWith("completed")){
					
					Assert.assertTrue(true);
					System.out.println("Team Setup button displays in it's 'Completed' state, passing Test!");
					
				}else {
					
					Assert.fail("Team Setup button doesn't display in it's 'Completed' state"+"\nCurrent class attribute="+driver.findElement(By.xpath("//button[contains(@class,'homeButton teamSetupBtn')]")).getAttribute("class")+", Failing Test!");
				}
		
	}
	
	
	@Test(priority=3) //Check the Team Setup incomplete button works
	public void IsTeamSetupBtnInCompleteWorks() throws InterruptedException, AWTException{
		
				//finding team setup button
				WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
				
				if(teamSetupBtn.getAttribute("class").endsWith("notStarted")){
					
					teamSetupBtn.click();
					Thread.sleep(3000);
					
					Assert.assertTrue(driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/teamsetup") 
							&& driver.findElement(By.xpath("//*[@id='root']/div/div[1]/h2")).getText().equals("TEAM SETUP"), 
							"Team setup page is not displaying for Incomplete state of TeamSetup Button, Failing Test!");
					
					System.out.println("Team setup page is displaying for Incomplete state of TeamSetup Button, Passing Test!");
					
				}else {
					
					System.out.println("Team setup button found with the class attribute="+teamSetupBtn.getAttribute("class"));
					
				}		
	}
	
	
	@Test(priority=4) //Check the Team Setup completed button works 
	public void IsTeamSetupBtnCompleteWorks() throws InterruptedException, AWTException{
		
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
				boolean flag;
				
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
				 */
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(3000);
				
				try{
					
					flag= teamSetupBtn.getAttribute("class").endsWith("completed");
					
				}catch(Exception e){
					
					teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
					flag= teamSetupBtn.getAttribute("class").endsWith("completed");
				}
				
				if(flag){
					
					teamSetupBtn.click();
					Thread.sleep(3000);
					
					Assert.assertTrue(driver.getCurrentUrl().equals("http://leo-limra-controlpanel.limra.leo-development.leocnames.com/teamsetup") 
							&& driver.findElement(By.xpath("//*[@id='root']/div/div[1]/h2")).getText().equals("TEAM SETUP"), 
							"Team setup page is not displaying for completed state of TeamSetup Button, Failing Test!");
					
					System.out.println("Team setup page is displaying for completed state of TeamSetup Button , Passing Test!");
					
				}else {
					
					System.out.println("Team setup button found with the class attribute="+teamSetupBtn.getAttribute("class"));
					
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

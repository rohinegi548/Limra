package tests;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TeamSetup_Ticket_39 {
	
	public static WebDriver driver;
	static String baseurl="http://leo-limra-controlpanel.limra.leo-development.leocnames.com";
	WebElement teamName2,PlayName2;
	
	@BeforeClass
	public void setup(){
		
		//set chrome driver path here
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	
	@Test(priority=0) //Check empty team tile's text fields can be edited and existing team tile can be edited
	public void IsAbleToEditNewExistingTile() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
	
		//Adding a tile that will become an existing tile for scenario 2
		WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
		WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
		
		teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
		PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		if(teamName.getAttribute("value").equals("TestTeam01") && PlayName.getAttribute("value").equals("TestPlayer01")){
			
			Assert.assertTrue(true);
			System.out.println("The value is retained in team fields, Passing Test!");
			Thread.sleep(3000);
			
		}else {
			
			Assert.fail("The value is not retaining in team fields, Failing Test!");
			
		}
		
		
		//clicking on home button
		WebElement homeBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[1]/button[contains(text(),'HOME')]"));
		homeBtn.click();
		Thread.sleep(3000);
		
		//clicking on team setup button to verify if data is being saved for all tiles created
		try{
			teamSetupBtn.click();
			
		}catch (Exception e) {
			
			driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]")).click();
			Thread.sleep(3000);
		}
		
		//verifying existing team tile can be edited and clicking away from the tile fields, value is retained in team fields 
		
		try{
			
			teamName.sendKeys("TestTeam0101"); Thread.sleep(3000);
			PlayName.sendKeys("TestTeam0101"); Thread.sleep(3000);
			
		}catch (Exception e) {
			
			teamName2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
			teamName2.sendKeys("01");Thread.sleep(3000);
			PlayName2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
			PlayName2.sendKeys("01");
			Thread.sleep(3000);
			
		}
		
		//clicking away from the tile fields
				driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
				Thread.sleep(3000);
		
				if(teamName2.getAttribute("value").equals("TestTeam0101") && PlayName2.getAttribute("value").equals("TestPlayer0101")){
					
					Assert.assertTrue(true);
					System.out.println("The value is retained in team fields for an existing team tile, Passing Test!");
					Thread.sleep(3000);
					
				}else {
					
					Assert.fail("The value is not retaining in team fields for an existing team tile, Failing Test!");
					
				}
		
	}
	
	
	
	@AfterClass
	public void teardown(){
		
		driver.quit();
		
	}
	
}

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

public class TeamSetup_Ticket_40 {
	
	public static WebDriver driver;
	static String baseurl="http://leo-limra-controlpanel.limra.leo-development.leocnames.com";
	
	
	@BeforeClass
	public void setup(){
		
		//set chrome driver path here
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	
	@Test(priority=0) //Check logo selection popup can be launched for empty tile and existing tile
	public void IsLogoPopupAppears() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//finding logo button on empty tile
		WebElement logoBtn=driver.findElement(By.xpath("//button[contains(text(),'LOGO')]"));
		logoBtn.click();
		Thread.sleep(3000);
		
		if(driver.findElement(By.xpath("//div[contains(text(),'SELECT TEAM LOGO')]")).isDisplayed()){
			
			Assert.assertTrue(true);
			System.out.println("The Select Team Logo popup appears, Passing Test!");
			driver.findElement(By.xpath("//button[contains(text(),'CANCEL')]")).click();
			Thread.sleep(3000);
		}
		else {
			
			Assert.fail("The Select Team Logo popup doesn't appears, Failing Test!");
			
		}
		
		//Adding a tile 
		WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
		WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
		
		teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
		PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
		
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
		
		//verifying logo popup appears for an existing tile
		
		try{
			logoBtn.click();
			
		}catch (Exception e) {
			
			driver.findElement(By.xpath("//button[contains(text(),'LOGO')]")).click();
			Thread.sleep(3000);
		}
		
		if(driver.findElement(By.xpath("//div[contains(text(),'SELECT TEAM LOGO')]")).isDisplayed()){
			
			Assert.assertTrue(true);
			System.out.println("The Select Team Logo popup appears for an existing tile too, Passing Test!");
			Thread.sleep(3000);
		}
		else {
			
			Assert.fail("The Select Team Logo popup doesn't appears for an existing tile, Failing Test!");
			
		}
		
	}
	
	
	
	@AfterClass
	public void teardown(){
		
		driver.quit();
		
	}
	
}

package tests;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamSetup_Ticket_41 {
	
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
	
	
	
	@Test(priority=0) //Check an available logo can be applied
	public void IsAbleToApplyColorOnTile() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
	
		//finding logo button on empty tile
		WebElement logoBtn=driver.findElement(By.xpath("//button[contains(text(),'LOGO')]"));
		logoBtn.click();
		Thread.sleep(3000);
		
		//selecting the color from the l0 button
		WebElement L0Btn=driver.findElement(By.xpath("//button[@id='l0']"));
		L0Btn.click();
		Thread.sleep(3000);
		
		//clicking on CONFIRM button to save color
		WebElement CONFIRMBtn=driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]"));
		CONFIRMBtn.click();
		Thread.sleep(3000);
		
		//checking if any color is applied on tile
		WebElement TileDiv=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		if(!TileDiv.getCssValue("background-color").equals("")){
			
			Assert.assertTrue(true);
			System.out.println("The color has been applied on tile, Passing Test!");
			Thread.sleep(3000);
			
		}else {
			
			Assert.fail("The color didn't apply on tile, Failing Test!");
			
		}
	
	}
	
	
	
	@Test(priority=1) //Check cancel button works on popup
	public void IsAbleToCancelLogoPopUp() throws InterruptedException, AWTException{
				
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
			
		//finding logo button on empty tile
		WebElement logoBtn=driver.findElement(By.xpath("//button[contains(text(),'LOGO')]"));
		logoBtn.click();
		Thread.sleep(3000);
		
		//finding and clicking on cancle button on popup
		WebElement CancelBtn=driver.findElement(By.xpath("//button[contains(text(),'CANCEL')]"));
		CancelBtn.click();
		Thread.sleep(3000);
	
		if(driver.findElements(By.xpath("//div[contains(text(),'SELECT TEAM LOGO')]")).size()<1){
			
			Assert.assertTrue(true);
			System.out.println("The Logo PopUp has been closed successfully, Passing Test!");
			Thread.sleep(3000);
		}else {
			
			Assert.fail("Either Logo PopUp didn't close/Some Error occurring, Failing Test!");
			
		}
	
	}
	
	
	@Test(priority=2) //Check a used logo cannot be applied
	public void ShouldNotAbleToUseUsedColor() throws InterruptedException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//finding logo button on empty tile
		WebElement logoBtn=driver.findElement(By.xpath("//button[contains(text(),'LOGO')]"));
		logoBtn.click();
		Thread.sleep(3000);
				
		//selecting the color from the l0 button
		WebElement L0Btn=driver.findElement(By.xpath("//button[@id='l0']"));
		L0Btn.click();
		Thread.sleep(3000);
				
		//clicking on CONFIRM button to save color
		WebElement CONFIRMBtn=driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]"));
		CONFIRMBtn.click();
		Thread.sleep(3000);
		
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(3000);
		
		//finding logo button on empty tile2
		WebElement logoBtn2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[2]/div[1]/button"));
		logoBtn2.click();
		Thread.sleep(3000);
		
		if(!driver.findElement(By.xpath("//*[@id='l0']")).isEnabled()){
			
			Assert.assertTrue(true);
			System.out.println("The Earlier used color is disabled, Passing Test!");
			Thread.sleep(3000);
			
		}else {
			
			Assert.fail("Either Earlier used color is not disabled/Some Error occurring, Failing Test!");
			
		}
		
	}
	
	
	@AfterMethod
	public void teardown(){
		
		driver.quit();
		
	}
	
}

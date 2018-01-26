package tests;

import java.awt.AWTException;
import java.util.List;
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

public class TeamSetup_Ticket_43 {
	
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
	
	
	
	@Test(priority=0) //Click on 'Delete team' icon to check if able to delete team and to check message-'Are you sure?' message is displayed
	public void IsAbleToDeleteTeamsAndCheckMessage() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		//Adding a tile data
		WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
		WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
		
		teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
		PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		//clicking on remove button to check 'Are you sure?' message is displayed
		WebElement removeBtn=driver.findElement(By.xpath("//button[contains(text(),'REMOVE')]"));
		removeBtn.click();
		Thread.sleep(3000);
		
		/*Checking if Clicking the trash can icon will display an 'Are you sure?' message with Yes and Cancel buttons.*/
		if(driver.findElement(By.xpath("//div[contains(text(),'Are you sure?')]")).isDisplayed() 
				&& driver.findElement(By.xpath("//div[contains(text(),'Deleting a team will remove')]")).isDisplayed() && 
				driver.findElement(By.xpath("//button[contains(text(),'YES')]")).isDisplayed() &&
				driver.findElement(By.xpath("//button[contains(text(),'NO')]")).isDisplayed()){
			
			Assert.assertTrue(true);
			System.out.println("Are you sure? message with YES and NO button is visible, passing Test!");
			
		}else {
			
			Assert.fail("Are you sure? message with YES and NO button is not visible, Failing Test!");
		}
	}
	
	
	
	@AfterMethod
	public void teardown(){
		
		driver.quit();
		
	}
	
}

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

public class TeamSetup_Ticket_42 {
	
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
	
	
	
	@Test(priority=0) //Check the Add Team button available 5 or fewer teams
	public void IsAddTeamBtnVisibleFor5OrFewTeams() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		int i=1;
		
		System.out.println("Default Tile: 1, Add Team Button is visible");
		
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		
		//finding Add Team button
		for(int j=1;j<5;j++){
			
			try{
				
				AddTeamBtn.click();
				Thread.sleep(3000);
				
			}catch (Exception e) {
				
				AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
				AddTeamBtn.click();
				Thread.sleep(3000);
			}
			
			i=i+1;
			System.out.println("Tile: " +(j+1)+", Add Team Button still visible");
		}
		
		Assert.assertTrue(true);
		System.out.println("The Add team button is showing for "+i+" tiles, Passing Test!");
	
	}
	
	
	@Test(priority=1) //Check the Add Team button does not appear when team page is full
	public void IsAddTeamBtnNotVisibleWhenTeamPgIsFull() throws InterruptedException, AWTException{
		

		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		int j=1;
		
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		
		//clicking Add team button 5 times to create 6 teams
		for(int i=1;i<6;i++){
			
			try{
				
				AddTeamBtn.click();
				Thread.sleep(3000);
				
			}catch (Exception e) {
				
				AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
				AddTeamBtn.click();
				Thread.sleep(3000);
			}
			
			j=j+1;
		}
		
		System.out.println("All "+j+" Tiles has been created! Now checking: Add Team Button shouldn't visible");
			
			if(driver.findElements(By.xpath("//button[contains(text(),'ADD TEAM')]")).size()<1){
				
				Assert.assertTrue(true);
				System.out.println("Add Team Button is not visible now, passing Test!");
				
			}else {
				
				Assert.fail("Add Team Button is still visible, Failing Test!");
			}
	}
	
	
	@AfterMethod
	public void teardown(){
		
		driver.quit();
		
	}
	
}

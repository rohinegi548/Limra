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

public class TeamSetup_Ticket_44 {
	
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
	
	
	
	@Test(priority=0) //Delete a team that contains no game data
	public void IsAbleToDeleteTeamsWhenMoreThanOneEmptyTeam() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(3000);
		
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		//clicking on second empty tile REMOVE button
		WebElement REMOVEBtn=driver.findElement(By.xpath("//div[2]//div[2]//button[contains(text(),'REMOVE')]"));
		REMOVEBtn.click(); Thread.sleep(3000);
		
		//clicking on YES button to delete tile and data
		driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
		 Thread.sleep(3000);
		
		
		if( driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div")).size() < teamDiv.size()){
			
			Assert.assertTrue(true);
			System.out.println("Empty Team and associated data has been deleted, passing Test!");
			
		}else {
			
			Assert.fail("Not able to delete Empty Team and associated data, Failing Test!");
		}
		
	}
	
	
	@Test(priority=1) //Delete a team when only 1 team exists
	public void IsAbleToDeleteTeamsWhenOnlyOneTeam() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		//Adding a tile to test deletion of data
		WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
		WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
		
		teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
		PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		//clicking on tile REMOVE button
		WebElement REMOVEBtn=driver.findElement(By.xpath("//button[contains(text(),'REMOVE')]"));
		REMOVEBtn.click(); Thread.sleep(3000);
		
		//clicking on YES button to delete tile and data
		driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
		 Thread.sleep(3000);
		
		
		if( driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input")).getAttribute("value").equals("") 
				&& driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input")).getAttribute("value").equals("")){
			
			Assert.assertTrue(true);
			System.out.println("Valid Team and associated data has been deleted when only one team, passing Test!");
			
		}else {
			
			Assert.fail("Not able to delete Valid Team and associated data when only one team, Failing Test!");
		}
		
	}
	
	
	@Test(priority=2) //Delete a team that contains game data
	public void IsAbleToDeleteTeamsWhenMoreThanOneTeam() throws InterruptedException, AWTException{
		
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
		//loop to enter values for all 6 teams tiles dynamically fetched locators
		for(int j=1;j<=teamDiv.size();j++){
			
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+j);
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+j);
				Thread.sleep(3000);
			
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		//clicking on second tile REMOVE button
		WebElement REMOVEBtn=driver.findElement(By.xpath("//div[2]//div[2]//button[contains(text(),'REMOVE')]"));
		REMOVEBtn.click(); Thread.sleep(3000);
		
		//clicking on YES button to delete tile and data
		driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
		 Thread.sleep(3000);
		
		
		if( driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div")).size() < teamDiv.size()){
			
			Assert.assertTrue(true);
			System.out.println("Deleting second tile and associated data is done successfully when two tiles having data, passing Test!");
			
		}else {
			
			Assert.fail("Not able to Delete second tile and associated data when two tiles having data, Failing Test!");
		}
		
	}
	
	
	@Test(priority=3) //Choose not to delete a team
	public void IsAbleToCancelTeamDeletion() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);

		//Adding a tile to test deletion cancel
		WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input"));
		WebElement PlayName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input"));
		
		teamName.sendKeys("TestTeam01"); Thread.sleep(3000);
		PlayName.sendKeys("TestPlayer01"); Thread.sleep(3000);
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(3000);
		
		//clicking on tile REMOVE button
		WebElement REMOVEBtn=driver.findElement(By.xpath("//button[contains(text(),'REMOVE')]"));
		REMOVEBtn.click(); Thread.sleep(3000);
		
		//clicking on NO button to cancel delete tile and data
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click();
		 Thread.sleep(3000);
		
		
		if( teamName.getAttribute("value").equals("TestTeam01") 
				&& PlayName.getAttribute("value").equals("TestPlayer01")){
			
			Assert.assertTrue(true);
			System.out.println("Deletion successfully cancelled, Team data still exist.  passing Test!");
			
		}else {
			
			Assert.fail("Error in Cancelling Team deletion, Failing Test!");
		}
		
	}
	
	@AfterMethod
	public void teardown(){
		
		driver.quit();
		
	}
	
}

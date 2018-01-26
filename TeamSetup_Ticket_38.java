package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamSetup_Ticket_38 {
  
	public static WebDriver driver;
	static String baseurl="http://leo-limra-controlpanel.limra.leo-development.leocnames.com";
	static String tempTeamName,tempTeamPlayer1,tempTeamPlayer2;
	static String tempTeamName2,tempTeamPlayer3,tempTeamPlayer4;
	int k=1;
	
	@BeforeClass
	public void setup(){
		
		//set chrome driver path here
		System.setProperty("webdriver.chrome.driver", "D:\\Eclipse\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@Test(priority=0) //scenario1: check team values still retained, tiles displays correctly
	public void IsAbleToAddTeamsAndRetained() throws InterruptedException, AWTException{
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//waiting untill Add Team button is not clickable/visible
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[contains(text(),'ADD TEAM')]"))));
		
		List<WebElement> Tile=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]"));
		
		//checking if only one tile is available initially
		if((Tile.size() == 1)){
			
			//finding team name textbox
			WebElement teamName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[1]/div/input"));
			teamName.clear();
			teamName.sendKeys("TestingHub");
			
			//storing values to check back
			tempTeamName=teamName.getAttribute("value");
			Thread.sleep(3000);
			
			//finding player name text box
			WebElement playerName=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div/input"));
			playerName.clear();
			playerName.sendKeys("Tester01");
			tempTeamPlayer1=playerName.getAttribute("value");
			Thread.sleep(3000);
			
			//finding add player button to add and check if  more than one player can be added
			WebElement AddPlayerBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/button"));
			AddPlayerBtn.click();
			Thread.sleep(3000);
			
			WebElement playerName2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div[2]/input"));
			playerName2.clear();
			playerName2.sendKeys("Tester02");
			tempTeamPlayer2=playerName2.getAttribute("value");
			Thread.sleep(3000);
			
			System.out.println("Team with name: "+tempTeamName+" has been created successfully");
			
			System.out.println("Checking if previously created team still retains the team values");
			
			//clicking on home button
			WebElement homeBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[1]/button[contains(text(),'HOME')]"));
			homeBtn.click();
			Thread.sleep(3000);
			
			//clicking on team setup button again
			
			try{
				teamSetupBtn.click();
			}catch (Exception e) {
				
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]")).click();
			}
			
			Thread.sleep(3000);
			
			tempTeamName2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[1]/div/input")).getAttribute("value");
			tempTeamPlayer3=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div/input")).getAttribute("value");
			tempTeamPlayer4=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div[2]/input")).getAttribute("value");
			
			if((tempTeamName.equals(tempTeamName2)) && (tempTeamPlayer1.equals(tempTeamPlayer3)) && (tempTeamPlayer2.equals(tempTeamPlayer4)) ){
				Thread.sleep(10000);
				Assert.assertTrue(true);
				System.out.println("Team values are still retained, this functionality working fine");
			}else {
				
				Assert.fail("Team values are different/not retained, failing test");
			}
	
		}else {
			
			System.out.println("More than 1 teams are already setup");
			
		}

	}
	
	
	@Test(priority=1) //scenario2: Team values are still retained across session
	public void IsTeamDataRetainedOrSavedAcrossSessions() throws InterruptedException{
		
		//using team data created in previous test, now just opening homepage url
		driver.get(baseurl);
		Thread.sleep(5000);
		
		//clicking team setup button again to check team values still retained
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(3000);
		
		//using earlier created and used variable to check retained data
		tempTeamName2=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[1]/div/input")).getAttribute("value");
		tempTeamPlayer3=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div/input")).getAttribute("value");
		tempTeamPlayer4=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div[1]/div[2]/div[2]/input")).getAttribute("value");
		
		if((tempTeamName.equals(tempTeamName2)) && (tempTeamPlayer1.equals(tempTeamPlayer3)) && (tempTeamPlayer2.equals(tempTeamPlayer4)) ){
			
			Thread.sleep(10000);
			Assert.assertTrue(true);
			System.out.println("Team values are still retained across session(after closing window and opening new window), this functionality working fine");
			
		}else {
			
			Assert.fail("Team values are different/not retained, failing test");
		}
		
	}
	
	
	
	@Test(priority=2) //verifying scenario: 3 check team tiles display correctly and reset functionality works fine
	public void IsAbleToResetTiles() throws InterruptedException{
				
				driver.get(baseurl);
				Thread.sleep(5000);
				
				//reset button
				WebElement resetBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[2]"));
				resetBtn.click();
				Thread.sleep(3000);
				
				//finding team setup button
				WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
				teamSetupBtn.click();
				Thread.sleep(3000);
				
				WebElement AddTeamBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button"));
				
				//clicking Add team button 5 times to create 6 teams
				for(int i=1;i<6;i++){
					
					try{
						
						AddTeamBtn.click();
						Thread.sleep(3000);
						
					}catch (Exception e) {
						
						AddTeamBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button"));
						AddTeamBtn.click();
						Thread.sleep(3000);
					}
				}
				
				//storing size of displayed tiles
				List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
				
				//loop to enter values for all 6 teams tiles dynamically fetched locators
					for(int j=1;j<=teamDiv.size();j++){
						
							driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+j);
							Thread.sleep(3000);
							
							driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+j);
							Thread.sleep(3000);
						
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
					
					//clicking on home button again to verify reset functionality
					try{
						homeBtn.click();
						
					}catch (Exception e) {
						
						driver.findElement(By.xpath("//*[@id='root']/div/div[1]/button[contains(text(),'HOME')]")).click();
						Thread.sleep(3000);
					}
					
					//reset button
					try{
						resetBtn.click();
						
					}catch (Exception e) {
						
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[2]")).click();
						Thread.sleep(3000);
					}
					
					//clicking on team setup button to check if all tiles has been reset
					try{
						teamSetupBtn.click();
						
					}catch (Exception e) {
						
						driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]")).click();
						Thread.sleep(3000);
					}
					
					//checking if Add team button is visible, only one tile is visible on reset with empty data
					if(driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button")).isDisplayed() && 
							driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div")).size() == 1 && 
							driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/input")).getAttribute("value").equals("") && 
							driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div/input")).getAttribute("value").equals("")){
						
						Assert.assertTrue(true);
						System.out.println("Reset functionality is working fine and 6 tiles created successfully and retains data too");
						
					}else {
						
						Assert.fail("Some conditions does not match/reset functionality not working");
					}
					
	}
	
	
	
	@Test(priority=3) //clicking on Set up complete button to check, reset button is enabled for 2 teams.
	public void IsresetBtnGotEnabledOnSetupCompleteOf2Teams() throws InterruptedException, AWTException{
		
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
				
				//clicking on Set up complete button to check, reset button is enabled.
				
				WebElement SetCompleteBtn=driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]"));
				SetCompleteBtn.click(); Thread.sleep(3000);
				
				
				if(driver.findElement(By.xpath("//button[contains(text(),'Reset the Profit Engine')]")).isEnabled()){
					
					Assert.assertTrue(true);
					System.out.println("Reset the Profit Engine got enabled after completing setup for 2 teams, passing Test!");
					
				}else {
					
					Assert.fail("Reset the Profit Engine didn't enable after completing setup for 2 teams, Failing Test!");
				}
		
	}
	
	
	@AfterClass
	public void teardown(){
		
		driver.quit();
		
	}
	
}

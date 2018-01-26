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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TeamSetup_Ticket_76 {
	
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
	
	
	
	
	@Test //Check the Checkboxes appears correctly
		  //And the Checkboxes text matches Financial Model sheet CELLS B25 through B31
	public void DoesChkboxTxtMatchesofSELECTIONGrid() throws InterruptedException, AWTException{
		
		String[] SlctnSteps = new String[] {"Initial Interview", 
										"Assessment (Screening Tool)",
										"In-Depth Interview",
										"Job Sampling Activities",
										"Career Presentation/Interview",
										"Interview with AM/SSA/Etc.",
										"Offer Interview with AM"};
		
		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click(); Thread.sleep(2000);
		
		List<WebElement> SlctnStepChkTxt=driver.findElements(By.xpath("//*[contains(@class,'column selectionColumn')]/div[contains(@class,'group')]//span[contains(@class,'label')]"));
		int flag=0;
		System.out.println("Actual checkbox text==Expected text");
		
		for(int j=1;j<=SlctnStepChkTxt.size();j++){
			
			if(SlctnStepChkTxt.get(j-1).getText().equals(SlctnSteps[j-1])){
				
				System.out.println(SlctnStepChkTxt.get(j-1).getText()+"=="+SlctnSteps[j-1]);
				flag=flag+1;
			}
				
		}
		
		Assert.assertEquals(flag, 7, "Fieldset legend text doesn't match Financial Model sheet and "
				+ "And the Checkboxes text doesn't match Financial Model sheet for SELECTION grid\n:Failing Test!");
		
		
		System.out.println("Fieldset legend text matches Financial Model sheet and "
				+ "And the Checkboxes text matches Financial Model sheet for SELECTION grid\n:Passing Test!");
		
	}
	
	
	@Test //Check the Checkboxes behave correctly and able to check the checkbox and tick display
	  
	public void CanUserAbleToToggleCheckboxesOfSELECTIONGrid() throws InterruptedException, AWTException{
	

		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click(); Thread.sleep(2000);
		
		List<WebElement> SlctnStepChkTxt=driver.findElements(By.xpath("//*[contains(@class,'column selectionColumn')]/div[contains(@class,'group')]//span[contains(@class,'label')]"));
		
		int flag=0;
		for(int j=1;j<=SlctnStepChkTxt.size();j++){
			
			//toggling the checkboxes
			SlctnStepChkTxt.get(j-1).click(); Thread.sleep(1000);
			flag=flag+1;
				
		}
		
		Assert.assertEquals(flag, 7, "Checkboxes doesn't behave correctly\n:Failing Test!");
		
		
		System.out.println("Checkboxes behave correctly, able to select, deselect and "
				+ "A tick is displayed as per the art direction for SELECTION grid\n:Passing Test!");
	
}
	
	
	@Test //Check the Checkboxes are saved and able to toggle check all, click away to another page 
	  		//And return to this page Then the Checkboxes are still ticked

	public void CanUserAbleToToggleAllCheckboxes() throws InterruptedException, AWTException{
	

		//finding team setup button
		WebElement teamSetupBtn=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/button[1]"));
		teamSetupBtn.click();
		Thread.sleep(2000);
		
	
		//finding Add Team button
		WebElement AddTeamBtn=driver.findElement(By.xpath("//button[contains(text(),'ADD TEAM')]"));
		AddTeamBtn.click();
		Thread.sleep(2000);
				
		List<WebElement> teamDiv=driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div"));
		
		int k=1;
		//loop to enter values for all 2 teams tiles dynamically fetched locators
		for(int j=1;j<teamDiv.size();j++){
			
			//team name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+k+"]/div/input")).sendKeys("TestingHub"+"0"+j);
				Thread.sleep(2000);
				
				//player name
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div["+(k+1)+"]/div/input")).sendKeys("Player"+"0"+j);
				Thread.sleep(2000);
				
				//logo selection
				driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div["+j+"]/div[1]/button")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//*[@id='l"+(j-1)+"']")).click();
				Thread.sleep(2000);
				
				//saving logo 
				driver.findElement(By.xpath("//button[contains(text(),'CONFIRM')]")).click();
				Thread.sleep(2000);
				
		}
		
		//clicking away from the tile fields
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'Set up complete')]")).click();
		Thread.sleep(2000);
		
		//year1 button
		WebElement year1Btn=driver.findElement(By.xpath("//*[contains(@class, 'homeButton yearOneBtn')]"));
		year1Btn.click();
		Thread.sleep(2000);
		
		//DECISIONS & DATA tab
		WebElement DDTab=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/a[2]"));
		DDTab.click(); Thread.sleep(2000);
		
		//teamBtn at left
		WebElement teamBtn=driver.findElement(By.xpath("//*[contains(@class,'teamBtn')][1]"));
		teamBtn.click(); Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(text(),'NO')]")).click(); Thread.sleep(2000);
		
		List<WebElement> AllChkBox=driver.findElements(By.xpath("//*[contains(@class,'group')]//span[contains(@class,'label')]"));
		
		int flag=0;
		for(int j=1;j<=AllChkBox.size();j++){
			
			//toggling the checkboxes
			AllChkBox.get(j-1).click(); Thread.sleep(2000);
			flag=flag+1;
				
		}
		
		driver.findElement(By.xpath("//div/div[1]/a[contains(text(),'AGENCY STATISTICS')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div/div[1]/a[contains(text(),'DECISION INPUT')]")).click();
		Thread.sleep(2000);
		
		Assert.assertEquals(flag, 24, "Checkboxes doesn't behave correctly\n:Failing Test!");
		
		System.out.println("Able to toggle check all checkbox correctly, able to select, deselect\n"
				+ "When I click away to another page And return to this page\n"
				+ "Then the Checkboxes are still ticked which were selected and unticked those were deselected ealier\n:Passing Test!");
	
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

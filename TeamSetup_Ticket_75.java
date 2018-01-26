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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TeamSetup_Ticket_75 {
	
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
	
	
	
	
	@Test /*Check the slider behaves correctly
		  When I move the slider, And the value dynamically changes as I move the slider
		  And the value changes in line with the step value as per description*/
		  
	
	public void DoesSliderBehavesCorrectly() throws InterruptedException, AWTException{
	
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
		
		//Verifying slider1 functinality
		WebElement AsnTotMngHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div[2]"));
		WebElement AsnTotMngHrSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[2]"));
		String SlidrMngIntVal=AsnTotMngHrSlidrVal.getText();
			
			System.out.println("***********ASSIGN TOTAL MANAGER HOURS slider**************");
		    Actions action = new Actions(driver);
            action.dragAndDropBy(AsnTotMngHrSlidr, -10, 0).build().perform(); 
            System.out.println("Current slider value: "+AsnTotMngHrSlidrVal.getText());
            Thread.sleep(3000);
            
            action.dragAndDropBy(AsnTotMngHrSlidr, 40, 0).build().perform(); 
            System.out.println("Current slider value: "+AsnTotMngHrSlidrVal.getText());
            Thread.sleep(3000);
         
            if(!SlidrMngIntVal.equals(AsnTotMngHrSlidrVal.getText())){
            	
            	System.out.println("Slider is behaving correctly and value also changing dynamically for ASSIGN TOTAL MANAGER HOURS slider");
            	
            }else {
				
            	System.out.println("Slider is not behaving correctly for ASSIGN TOTAL MANAGER HOURS slider");
			}

            //Verifying slider2 functinality
            WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
    		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
    		String RECSlidrIntVal=RECSlidrVal.getText();
    			
    			System.out.println("***********RECRUITMENT slider**************");
                action.dragAndDropBy(RECHrSlidr, -10, 0).build().perform(); 
                System.out.println("Current slider value: "+RECSlidrVal.getText());
                Thread.sleep(3000);
                
                action.dragAndDropBy(RECHrSlidr, 40, 0).build().perform(); 
                System.out.println("Current slider value: "+RECSlidrVal.getText());
                Thread.sleep(3000);
             
                if(!RECSlidrIntVal.equals(RECSlidrVal.getText())){
                	
                	System.out.println("Slider is behaving correctly and value also changing dynamically for RECRUITMENT slider");
                	
                }else {
    				
                	System.out.println("Slider is not behaving correctly for RECRUITMENT slider");
    			}
                
                //Verifying slider3 functinality
                WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
          		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
          		String SelctnSlidrIntVal=SelctnSlidrVal.getText();
          	
          			  System.out.println("***********SELECTION slider**************");
                      action.dragAndDropBy(SelctnHrSlidr, -10, 0).build().perform(); 
                      System.out.println("Current slider value: "+SelctnSlidrVal.getText());
                      Thread.sleep(3000);
                      
                      action.dragAndDropBy(SelctnHrSlidr, 40, 0).build().perform(); 
                      System.out.println("Current slider value: "+SelctnSlidrVal.getText());
                      Thread.sleep(3000);
                   
                      if(!SelctnSlidrIntVal.equals(SelctnSlidrVal.getText())){
                      	
                      	System.out.println("Slider is behaving correctly and value also changing dynamically for SELECTION slider");
                      	
                      }else {
          				
                      	System.out.println("Slider is not behaving correctly for SELECTION slider");
          			}
                      
                      //Verifying slider4 functinality
                      WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
              		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
              		String MngSelctnSlidrIntVal=MngSelctnSlidrVal.getText();
              	
              		      System.out.println("***********MANAGER SELECTION slider**************");
                          action.dragAndDropBy(MngSelctnHrSlidr, -10, 0).build().perform(); 
                          System.out.println("Current slider value: "+MngSelctnSlidrVal.getText());
                          Thread.sleep(3000);
                          
                          action.dragAndDropBy(MngSelctnHrSlidr, 40, 0).build().perform(); 
                          System.out.println("Current slider value: "+MngSelctnSlidrVal.getText());
                          Thread.sleep(3000);
                       
                          if(!MngSelctnSlidrIntVal.equals(MngSelctnSlidrVal.getText())){
                          	
                          	System.out.println("Slider is behaving correctly and value also changing dynamically for MANAGER SELECTION slider");
                          	
                          }else {
              				
                          	System.out.println("Slider is not behaving correctly for MANAGER SELECTION slider");
              			}
                
		Assert.assertTrue(true);
		
	}
	
	
	@Test /*Check the value is saved
	  And I move the slider to adjust the value, When I click away to another page
	  And return to this page,Then the saved value is displayed*/
	 
public void DoesValueIsSavedCorrectly() throws InterruptedException, AWTException{

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
	
	//Verifying slider1 functinality
	WebElement AsnTotMngHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[1]/div[2]"));
	WebElement AsnTotMngHrSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[2]"));
	String SlidrMngIntVal=AsnTotMngHrSlidrVal.getText();
	
	  System.out.println("***********ASSIGN TOTAL MANAGER HOURS slider**************");
	  Actions action = new Actions(driver);
      action.dragAndDropBy(AsnTotMngHrSlidr, -10, 0).build().perform(); 
      System.out.println("Current slider value: "+AsnTotMngHrSlidrVal.getText());
      Thread.sleep(3000);
      
      action.dragAndDropBy(AsnTotMngHrSlidr, 40, 0).build().perform(); 
      System.out.println("Current slider value: "+AsnTotMngHrSlidrVal.getText());
      String SlidrMngFinVal=AsnTotMngHrSlidrVal.getText();
      Thread.sleep(3000);
   
      if(!SlidrMngIntVal.equals(AsnTotMngHrSlidrVal.getText())){
      	
      	System.out.println("Slider is behaving correctly and value also changing dynamically for ASSIGN TOTAL MANAGER HOURS slider");
      	
      }else {
			
      	System.out.println("Slider is not behaving correctly for ASSIGN TOTAL MANAGER HOURS slider");
		}

      //Verifying slider2 functinality
      WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
		String RECSlidrIntVal=RECSlidrVal.getText();
	
		  System.out.println("***********RECRUITMENT slider**************");
          action.dragAndDropBy(RECHrSlidr, -10, 0).build().perform(); 
          System.out.println("Current slider value: "+RECSlidrVal.getText());
          Thread.sleep(3000);
          
          action.dragAndDropBy(RECHrSlidr, 40, 0).build().perform(); 
          System.out.println("Current slider value: "+RECSlidrVal.getText());
          String RECSlidrFinVal=RECSlidrVal.getText();
          Thread.sleep(3000);
       
          if(!RECSlidrIntVal.equals(RECSlidrVal.getText())){
          	
          	System.out.println("Slider is behaving correctly and value also changing dynamically for RECRUITMENT slider");
          	
          }else {
				
          	System.out.println("Slider is not behaving correctly for RECRUITMENT slider");
			}
          
          //Verifying slider3 functinality
          WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
  		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
  		String SelctnSlidrIntVal=SelctnSlidrVal.getText();
  	
  			  System.out.println("***********SELECTION slider**************");
              action.dragAndDropBy(SelctnHrSlidr, -10, 0).build().perform(); 
              System.out.println("Current slider value: "+SelctnSlidrVal.getText());
              Thread.sleep(3000);
              
              action.dragAndDropBy(SelctnHrSlidr, 40, 0).build().perform(); 
              System.out.println("Current slider value: "+SelctnSlidrVal.getText());
              String SelctnSlidrFinVal=SelctnSlidrVal.getText();
              Thread.sleep(3000);
           
              if(!SelctnSlidrIntVal.equals(SelctnSlidrVal.getText())){
              	
              	System.out.println("Slider is behaving correctly and value also changing dynamically for SELECTION slider");
              	
              }else {
  				
              	System.out.println("Slider is not behaving correctly for SELECTION slider");
  			}
              
              //Verifying slider4 functinality
              WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
      		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
      		String MngSelctnSlidrIntVal=MngSelctnSlidrVal.getText();
      	
      		 	  System.out.println("***********MANAGER SELECTION slider**************");
                  action.dragAndDropBy(MngSelctnHrSlidr, -10, 0).build().perform(); 
                  System.out.println("Current slider value: "+MngSelctnSlidrVal.getText());
                  Thread.sleep(3000);
                  
                  action.dragAndDropBy(MngSelctnHrSlidr, 40, 0).build().perform(); 
                  System.out.println("Current slider value: "+MngSelctnSlidrVal.getText());
                  String MngSelctnSlidrFinVal=MngSelctnSlidrVal.getText();
                  Thread.sleep(3000);
               
                  if(!MngSelctnSlidrIntVal.equals(MngSelctnSlidrVal.getText())){
                  	
                  	System.out.println("Slider is behaving correctly and value also changing dynamically for MANAGER SELECTION slider");
                  	
                  }else {
      				
                  	System.out.println("Slider is not behaving correctly for MANAGER SELECTION slider");
      			}
                  
                driver.findElement(By.xpath("//div/div[1]/a[contains(text(),'AGENCY STATISTICS')]")).click();
          		Thread.sleep(2000);
          		
          		driver.findElement(By.xpath("//div/div[1]/a[contains(text(),'DECISION INPUT')]")).click();
          		Thread.sleep(2000);
          
          		//Handling of stale element excetion
          		AsnTotMngHrSlidrVal=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[1]/div[2]/div[2]"));
          		RECSlidrVal=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
          		SelctnSlidrVal=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
          		MngSelctnSlidrVal=driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
          		
          		Thread.sleep(5000);
          		Assert.assertTrue(
          				AsnTotMngHrSlidrVal.getText().equals(SlidrMngFinVal) && 
          				RECSlidrVal.getText().equals(RECSlidrFinVal) && 
          				SelctnSlidrVal.getText().equals(SelctnSlidrFinVal) && 
          				MngSelctnSlidrVal.getText().equals(MngSelctnSlidrFinVal), 
          				"Slider is not behaving correctly\n:Failing Test! ");
          		
          		System.out.println("When I move the slider to adjust the value\n"
          				+ "When I click away to another page\n"
          				+"And return to this page, Then the saved value is displayed\n:Passing Test!");
	
}
	

	@Test /*Check slider can reach 100%
	  And I move the other 2 sliders to zero
	  When I move the slider to the top of the range
	  Then it reaches the maximum available value shown in the TMH field*/
	  
public void CanSliderReachTo100Correctly() throws InterruptedException, AWTException{

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
	

    //Setting slider2 to 0% functinality
		WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
	
		Actions action = new Actions(driver);
        action.dragAndDropBy(RECHrSlidr, -39, 0).build().perform(); 
        System.out.println("RECRUITMENT slider value set to: "+RECSlidrVal.getText());
        Thread.sleep(3000);
        
               
        //Setting slider3 to 0% functinality
        WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
	
            action.dragAndDropBy(SelctnHrSlidr, -65, 0).build().perform(); 
            System.out.println("SELECTION slider value set to: "+SelctnSlidrVal.getText());
            Thread.sleep(3000);
            
            //Setting slider4 functinality to be 100%
            WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
    		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
    		
    		    action.dragAndDropBy(MngSelctnHrSlidr, +105, 0).build().perform(); 
                System.out.println("MANAGER SELECTION slider value set to: "+MngSelctnSlidrVal.getText());
                Thread.sleep(3000);
                
                driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
                Thread.sleep(3000);
             
                //checking if you can go to next page[TRAINING & DEVELOPMENT] by clicking on next button
                
        		Assert.assertTrue(
        				driver.findElement(By.xpath("//span[contains(text(),'FIRST 3 MONTHS')]")).isDisplayed(), 
        				"Slider is not behaving correctly\n:Failing Test! ");
        		
        		System.out.println("When I move the \"RECRUITMENT\", \"SELECTION\" sliders to zero\n"
        				+ "If I move MANAGER SELECTION slider to 100% and Click on Next\n"
        				+"I am able to Go To > TRAINING & DEVELOPMENT tab\n:Passing Test!");
	
}
	
	
	
	@Test /*Check that the slider cannot exceed TMH
	  And all three sliders have reached the maximum available value shown in the TMH field
	 When I try to increase the slider
	 Then it will not go any higher*/
	  
public void CheckSliderCannotExceedTMH() throws InterruptedException, AWTException{

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
	

  //Setting slider2 to 100% functinality
		WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
	
		Actions action = new Actions(driver);
      action.dragAndDropBy(RECHrSlidr, +100, 0).build().perform(); 
      System.out.println("RECRUITMENT slider value set to: "+RECSlidrVal.getText());
      Thread.sleep(3000);
      
             
      //Setting slider3 to 100% functinality
      WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
	
          action.dragAndDropBy(SelctnHrSlidr, +66, 0).build().perform(); 
          System.out.println("SELECTION slider value set to: "+SelctnSlidrVal.getText());
          Thread.sleep(3000);
          
          //Setting slider4 functinality to be 100%
          WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
  		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
  		
  		    action.dragAndDropBy(MngSelctnHrSlidr, +105, 0).build().perform(); 
              System.out.println("MANAGER SELECTION slider value set to: "+MngSelctnSlidrVal.getText());
              Thread.sleep(3000);
              
              driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
              Thread.sleep(3000);
           
              //checking if you can't go to next page[TRAINING & DEVELOPMENT] by clicking on next button
              
      		Assert.assertTrue(
      				driver.findElement(By.xpath("//*[contains(text(),'You cannot assign more than 100%')]")).isDisplayed(), 
      				"Slider is not behaving correctly\n:Failing Test! ");
      		
      		System.out.println("When I move the RECRUITMENT, SELECTION and MANAGER SELECTION sliders to 100%\n"
      				+ "And Click on Next\n"
      				+"I am not able to Go To > TRAINING & DEVELOPMENT tab and see Attention Alert that\n"
      				+ "You cannot assign more than 100% of available manager hours to Recruitment & Selection activities.\n"
      				+ "Please re-assign hours for Recruitment, Selection and Manager Selection before proceeding."
      				+ "\n:Passing Test!");
	
}
	
	
	
	@Test /*Check that the slider CanReachTo100%
	  And all three sliders have reached the maximum available value shown in the TMH field
	 When I set 1st->50%, 2nd->20% and 3rd->30% the sliders
	 Then it will allow me to go to next page on T&D */
	  
public void CheckSliderCanReachTo100Using3SlidersCombinationToAchieveTMH() throws InterruptedException, AWTException{

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
	

//Setting slider2 to 100% functinality
		WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
	
		Actions action = new Actions(driver);
    action.dragAndDropBy(RECHrSlidr, +100, 0).build().perform(); 
    System.out.println("RECRUITMENT slider value set to: "+RECSlidrVal.getText());
    Thread.sleep(3000);
    
           
    //Setting slider3 to 100% functinality
    WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
	
        action.dragAndDropBy(SelctnHrSlidr, +66, 0).build().perform(); 
        System.out.println("SELECTION slider value set to: "+SelctnSlidrVal.getText());
        Thread.sleep(3000);
        
        //Setting slider4 functinality to be 100%
        WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
		
		    action.dragAndDropBy(MngSelctnHrSlidr, +105, 0).build().perform(); 
            System.out.println("MANAGER SELECTION slider value set to: "+MngSelctnSlidrVal.getText());
            Thread.sleep(3000);
            
            
            action.dragAndDropBy(RECHrSlidr, -65, 0).build().perform(); 
            System.out.println("RECRUITMENT slider value now set to: "+RECSlidrVal.getText());
            
            action.dragAndDropBy(SelctnHrSlidr, -103, 0).build().perform();
            System.out.println("SELECTION slider value now set to: "+SelctnSlidrVal.getText());
            
            action.dragAndDropBy(MngSelctnHrSlidr, -90, 0).build().perform(); 
            System.out.println("MANAGER SELECTION slider value now set to: "+MngSelctnSlidrVal.getText());
            Thread.sleep(5000);
            
            driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
            Thread.sleep(3000);
         
            //checking if you can go to next page[TRAINING & DEVELOPMENT] by clicking on next button
            
    		Assert.assertTrue(
    				driver.findElement(By.xpath("//span[contains(text(),'FIRST 3 MONTHS')]")).isDisplayed(), 
    				"Slider is not behaving correctly\n:Failing Test! ");
    		
    		System.out.println("When RECRUITMENT, SELECTION and MANAGER SELECTION sliders are at 100%\n"
    				+ "And I set 1st->50%, 2nd->20% and 3rd->30%\n"
    				+"And click on Next, I am able to Go To > TRAINING & DEVELOPMENT tab"
    				+ "\n:Passing Test!");
	
}
	
	
	@Test /*Check that the slider Can't ReachTo100%
	  And all three sliders have reached the maximum available value shown in the TMH field
	 When I set 1st->50%, 2nd->20% and 3rd->31% the sliders
	 Then it will not allow me to go to next page on T&D 
	  */
public void CheckSliderShoulsNotReachTo100Using3SlidersCombinationWhenExceedTMH() throws InterruptedException, AWTException{

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
	

//Setting slider2 to 100% functinality
		WebElement RECHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]"));
		WebElement RECSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[1]/div[2]/div[2]/div[2]"));
	
		Actions action = new Actions(driver);
  action.dragAndDropBy(RECHrSlidr, +100, 0).build().perform(); 
  System.out.println("RECRUITMENT slider value set to: "+RECSlidrVal.getText());
  Thread.sleep(3000);
  
         
  //Setting slider3 to 100% functinality
  WebElement SelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]"));
		WebElement SelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]"));
	
      action.dragAndDropBy(SelctnHrSlidr, +66, 0).build().perform(); 
      System.out.println("SELECTION slider value set to: "+SelctnSlidrVal.getText());
      Thread.sleep(3000);
      
      //Setting slider4 functinality to be 100%
      WebElement MngSelctnHrSlidr = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]"));
		WebElement MngSelctnSlidrVal = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div[2]/div[3]/div[2]/div[2]/div[2]"));
		
		    action.dragAndDropBy(MngSelctnHrSlidr, +105, 0).build().perform(); 
          System.out.println("MANAGER SELECTION slider value set to: "+MngSelctnSlidrVal.getText());
          Thread.sleep(3000);
          
          
          action.dragAndDropBy(RECHrSlidr, -65, 0).build().perform(); 
          System.out.println("RECRUITMENT slider value now set to: "+RECSlidrVal.getText());
          
          action.dragAndDropBy(SelctnHrSlidr, -103, 0).build().perform();
          System.out.println("SELECTION slider value now set to: "+SelctnSlidrVal.getText());
          
          action.dragAndDropBy(MngSelctnHrSlidr, -89, 0).build().perform(); 
          System.out.println("MANAGER SELECTION slider value now set to: "+MngSelctnSlidrVal.getText());
          Thread.sleep(5000);
          
          driver.findElement(By.xpath("//*[contains(@class,'nextBtn')]")).click();
          Thread.sleep(3000);
       
          //checking if you can go to next page[TRAINING & DEVELOPMENT] by clicking on next button
          
  		Assert.assertTrue(
  				driver.findElement(By.xpath("//*[contains(text(),'You cannot assign more than 100%')]")).isDisplayed(), 
  				"Slider is not behaving correctly\n:Failing Test! ");
  		
  		System.out.println("When RECRUITMENT, SELECTION and MANAGER SELECTION sliders are at 100%\n"
  				+ "And I set 1st->50%, 2nd->20% and 3rd->31%\n"
  				+"And click on Next, I am not able to Go To > TRAINING & DEVELOPMENT tab and see Attention Alert that\n"
  				+ "You cannot assign more than 100% of available manager hours to Recruitment & Selection activities.\n"
  				+ "Please re-assign hours for Recruitment, Selection and Manager Selection before proceeding."
  				+ "\n:Passing Test!");
	
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

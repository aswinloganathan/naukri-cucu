package steps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NaukriSteps {

	public WebDriver driver;
	public Actions action;
	public WebDriverWait wait;	

	
	@Given("Launch the Browser") 
	public void launchBrowser(){
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver83.exe");
		System.setProperty("webdriver.chrome.silentOutput","true");
    
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		
		driver = new ChromeDriver(options);
	}
	
	@Given("Set the Timeouts") 
	public void timeOut() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Given("Load the URL Naukri.com") 
	public void loadURL() {
		driver.get("https://www.naukri.com/");
	}
	
	@Given("Get the company names from new windows and close it")
	public void getCompanyName() {
		Set<String> windows = driver.getWindowHandles();
		List<String> getWindows = new ArrayList<String>(windows);
		//getWindows.addAll(windows);		
		
		System.out.println(getWindows.size());
		
		for (int i = 1; i < getWindows.size(); i++) {
			driver.switchTo().window(getWindows.get(i));
			String companyName = driver.findElement(By.xpath("//img[contains(@src,'company.naukri.com/popups/')]")).getAttribute("alt");
			System.out.println("Company name is : "+ companyName);
			driver.close();
		}		
	
		driver.switchTo().window(getWindows.get(0));
	}
	
	@When("click on the upload CV button and upload some random image")
	public void uploadImage() throws AWTException {
		
		Robot rbt = new Robot();
		
		Actions builder = new Actions(driver);
		builder.click(driver.findElement(By.id("wdgt-file-upload")));
		builder.perform();
				
		rbt.setAutoDelay(2000);
		
		StringSelection strpath = new StringSelection("C:\\Users\\aswin\\OneDrive\\Pictures\\Areyouzero.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strpath, null);
		
		rbt.setAutoDelay(2000);
		rbt.keyPress(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_V);
		
		rbt.keyRelease(KeyEvent.VK_CONTROL);
		rbt.keyRelease(KeyEvent.VK_V);
		
		rbt.setAutoDelay(2000);	
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
	}
	
	@Then("Get the error message printed")
	public void getMessage() {		
		String errorMsg = driver.findElement(By.xpath("//div[@class='error-header']/div[2]")).getText();
		System.out.println("Error message got as : " + errorMsg);
		
		driver.close();
	}
	
	
	
}

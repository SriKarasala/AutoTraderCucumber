package wrappers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reporting;

public class GenericWrappers extends Reporting implements Wrappers{

	public RemoteWebDriver driver;
	public Properties prop;
	
	
	public void enterValueIntoTextField(String key, String data) {
		
		String value = prop.getProperty(key);
		String[] locator = value.split("#");
		String locatorKey=locator[0];
		String locatorValue = locator[1];
		
		if(locatorKey.equalsIgnoreCase("id")) {
			enterById(locatorValue, data);
		}else if(locatorKey.equalsIgnoreCase("name")) {
			enterByName(locatorValue, data);
		}else if(locatorKey.equalsIgnoreCase("xpath")) {
			enterByXpath(locatorKey, data);
		}
		
	}
	
public void clickOnButtonorLink(String key) throws InterruptedException {		
		String value = prop.getProperty(key);
		String[] locator = value.split("#");
		String locatorKey=locator[0];
		String locatorValue = locator[1];
		
		if(locatorKey.equalsIgnoreCase("id")) {
			clickById(locatorValue);
		}else if(locatorKey.equalsIgnoreCase("name")) {
			clickByName(locatorValue);
		}else if(locatorKey.equalsIgnoreCase("xpath")) {
			clickByXpath(locatorValue);
		}else if(locatorKey.equalsIgnoreCase("linktest")) {
			clickByLink(locatorValue);
		}else if(locatorKey.equalsIgnoreCase("partiallinkText")) {
			clickBypartialLinkText(locatorValue);
		}
		
	}

public void selctdropdown(String key, String dropdownvalue) {
	
	String value = prop.getProperty(key);
	String[] locator = value.split("#");
	String locatorKey=locator[0];
	String locatorValue = locator[1];
	
	if(locatorKey.equalsIgnoreCase("id")) {
	
		selectVisibileTextById(locatorValue, dropdownvalue);
}
	if(locatorKey.equalsIgnoreCase("xpath")) {
		WebElement elementId= driver.findElementByXPath(locatorKey);
		Select droplist1= new Select(elementId);
		droplist1.selectByValue(locatorValue);
	}
	
}	
	public void invokeApp(String browser, String url) {
		
		
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("user-agent=YOUR_USER_AGENT");
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("--js-flags=--expose-gc");
				chromeOptions.addArguments("--enable-precise-memory-info");
				chromeOptions.addArguments("--disable-popup-blocking");
				chromeOptions.addArguments("--disable-default-apps");
				chromeOptions.addArguments("--disable-infobars");
				chromeOptions.addArguments("--disable-javascript");
				chromeOptions.addArguments("--disable-web-security");
				chromeOptions.addArguments("--allow-running-insecure-content");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-blink-features=AutomationControlled") ;
				chromeOptions.addArguments("--profile-directory=Default");
				//chromeOptions.addArguments("--incognito");
				chromeOptions.addArguments("--disable-plugins-discovery");
				//chromeOptions.setExperimentalOption("excludeSwitches","enable-automation");
				//chromeOptions.setExperimentalOption("useAutomationExtension", false);

				//WebDriverManager.chromedriver().setup();
				 driver = new ChromeDriver(chromeOptions);
				 driver.manage().deleteAllCookies();
			}else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "");
				 driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "");
				 driver = new InternetExplorerDriver();
			}
				
			driver.manage().window().maximize();
			driver.get(url);
			
			//logStep("pass", "The "+browser+" Browser launched successfully and loaded the url - "+url);
		} catch (UnreachableBrowserException e) {
			
			//logStep("fail", "unable to reach the browser");
		}catch (WebDriverException e) {
			
			//logStep("fail","Browser got closed due to unknown error");
		}
	
		
	}
	
public void clickByLinkNoSnap(String name) {
		
		try {
			driver.findElementByLinkText(name).click();
			
			logStep("Pass","Elelement" + name + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+name+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+name+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+name+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+name+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}
  
public void clickByXpathNoSnap(String xpathVal) {
	
	try {
		driver.findElementByXPath(xpathVal).click();
		
		logStep("Pass","Elelement" + xpathVal + "is clicked");
	} catch (NoSuchElementException e) {
		
		logStep("Fail","There is No Such Element with id -"+xpathVal+" to click");
	}catch (ElementNotVisibleException e) {
		
		logStep("Fail"," Element with id -"+xpathVal+ "is not Visible"+ "to clcik ");
		
	}catch (ElementNotInteractableException e) {
		
		logStep("Fail"," Element with id -"+xpathVal+" is not interactable to click ");
	}catch (StaleElementReferenceException e) {
		
		logStep("Fail"," Element with idvalue -"+xpathVal+" is staled element to click");
	}catch (WebDriverException e) {
		
		logStep("Fail","Browser got closed due to unknown error");
	}
}

	public void enterById(String idValue, String data) {
		
		
		try {
			driver.findElementById(idValue).sendKeys(data);
			logStep("Pass","Element with idvalue - "+idValue+" is found and entered the data - "+data);
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with idvalue -"+idValue+" to enter the data - "+data);
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with idvalue -"+idValue+ "is not Visible"+ "to enter the data - "+data);
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with idvalue -"+idValue+" is not interactable to enter the data - "+data);
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+idValue+"  is not available to enter the data - "+data);
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
		
		
	}

	public void enterByName(String nameValue, String data) {
		
		try {
			driver.findElementByName(nameValue).sendKeys(data);
			logStep("Pass","Element with idvalue - "+nameValue+" is found and entered the data - "+data);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			logStep("Fail","There is No Such Element with idvalue -"+nameValue+" to enter the data - "+data);
		}catch (ElementNotVisibleException e) {
			logStep("Fail","ElementNotVisibleException occured");
			
		}catch (ElementNotInteractableException e) {
			logStep("Fail","ElementNotInteractableException occured");
		}catch (StaleElementReferenceException e) {
			logStep("Fail","StaleElementReferenceException occured");
		}catch (WebDriverException e) {
			logStep("Fail","WebDriverException occured");
		}
	}

	public void enterByXpath(String xpathValue, String data) {
		
		try {
			driver.findElementByXPath(xpathValue).sendKeys(data);
			logStep("Pass","Element with idvalue - "+xpathValue+" is found and entered the data - "+data);
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with idvalue -"+xpathValue+" to enter the data - "+data);
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail","The element is not visible -" + xpathValue + " to enter te data -"+ data );
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail","The element is not interactable -" + xpathValue + " to enter te data -"+ data );
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail","The element is a stale element -" + xpathValue + " to enter te data -"+ data );
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	public void verifyTitle(String title) {
		
		
		try {
			String titlename =driver.getTitle();
			
			if(titlename.equals(title)) {
				logStep("Pass","Titile is present");
				
			}else {
				logStep("Fail","Titile mismatch or Titile is not present");
				
			}
		} catch (NoSuchWindowException e) {
			logStep("Fail","NoSuchWindowException occured");
		} catch (WebDriverException e) {
			logStep("Fail","WebDriverException occured");
		}
		
	}

	public void verifyTextById(String id, String text) {
		
		 String TxtID= driver.findElementById(id).getText();
		 
		 if(TxtID.equalsIgnoreCase(text)) {
			 logStep("Pass","Validation is true"); 
		 }else {
				logStep("Fail","Validation is false");
			}
	}

	public void verifyTextByXpath(String xpath, String text) {
		
		String TxtXpath= driver.findElementByXPath(xpath).getText();
		
		if(TxtXpath.equalsIgnoreCase(text)) {
			logStep("Pass","Validation is true");
		}else {
			logStep("Fail","Validation is false");
		}
	}

	public void verifyTextContainsByXpath(String xpath, String text) {
		
		String txtcontains= driver.findElementByXPath(xpath).getText();
		if (txtcontains.contains(text))
		{
			logStep("Pass","Validation is true");
		}
		else {
			logStep("Fail","Validation is false");
		}
	}
	

	public void clickById(String id) {
		
		try {
			driver.findElementById(id).click();
			
			logStep("Pass","Elelement" + id + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+id+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+id+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+id+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+id+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	

	public void clickByName(String name) {
		
		try {
			driver.findElementByName(name).click();
			
			logStep("Pass","Elelement" + name + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+name+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+name+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+name+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+name+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	public void getPresenceOfElement(String key)
	{
		String value = prop.getProperty(key);
		String[] locator = value.split("#");
		String locatorKey=locator[0];
		String locatorValue = locator[1];
		if(locatorKey.equalsIgnoreCase("id")) {
			findbyID(locatorValue);
		}else if(locatorKey.equalsIgnoreCase("xpath")) {
			findbyXPath(locatorValue);
		}
	}
	
	private void findbyID(String locatorValue) {
		try {
			if(driver.findElementById(locatorValue).isDisplayed());
				
			//logStep("Pass","Elelement with ID " + locatorValue + "is visible and displaed");
			
			
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+locatorValue+".");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+locatorValue+ "is not Visible"+ ".");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+locatorValue+" is not interactable");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+locatorValue+" is staled element");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
		
	
	}

	private void findbyXPath(String locatorValue) {
			try {
				if(driver.findElementByXPath(locatorValue).isDisplayed())
					if(driver.findElementByXPath(locatorValue).isEnabled()) {
						logStep("Pass","Elelement with XPath " + locatorValue + "is visible and enabled");
				}
				
			} catch (NoSuchElementException e) {
				
				logStep("Fail","There is No Such Element with XPath -"+locatorValue+".");
			}catch (ElementNotVisibleException e) {
				
				logStep("Fail"," Element with XPath -"+locatorValue+ "is not Visible"+ ".");
				
			}catch (ElementNotInteractableException e) {
				
				logStep("Fail"," Element with XPath -"+locatorValue+" is not interactable");
			}catch (StaleElementReferenceException e) {
				
				logStep("Fail"," Element with XPath value -"+locatorValue+" is staled element");
			}catch (WebDriverException e) {
				
				logStep("Fail","Browser got closed due to unknown error");
			}
		
		}
	

	public void clickByLink(String name) {
		
		try {
			driver.findElementByLinkText(name).click();
			
			logStep("Pass","Elelement" + name + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+name+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+name+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+name+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+name+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	

	public void clickByXpath(String xpathVal) throws InterruptedException {
		
		try {
			driver.findElementByXPath(xpathVal).click();	
			Thread.sleep(1000);
		//	logStep("Pass","Element" + xpathVal.toString() + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+xpathVal.toString()+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+xpathVal.toString()+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+xpathVal+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+xpathVal+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	
	public void clickBypartialLinkText(String partialLinkTextValue) {
		
		try {
			driver.findElementByXPath(partialLinkTextValue).click();			
			logStep("Pass","Elelement" + partialLinkTextValue + "is clicked");
		} catch (NoSuchElementException e) {
			
			logStep("Fail","There is No Such Element with id -"+partialLinkTextValue+" to click");
		}catch (ElementNotVisibleException e) {
			
			logStep("Fail"," Element with id -"+partialLinkTextValue+ "is not Visible"+ "to clcik ");
			
		}catch (ElementNotInteractableException e) {
			
			logStep("Fail"," Element with id -"+partialLinkTextValue+" is not interactable to click ");
		}catch (StaleElementReferenceException e) {
			
			logStep("Fail"," Element with idvalue -"+partialLinkTextValue+" is staled element to click");
		}catch (WebDriverException e) {
			
			logStep("Fail","Browser got closed due to unknown error");
		}
	}

	public String getTextById(String idVal) {
		
		String value= driver.findElementById(idVal).getText();
		return value;
	}

	public String getTextByXpath(String xpathVal) {		
		String xpathvalue=null;
		try {
			 xpathvalue= driver.findElementByXPath(xpathVal).getText();
			
		} catch (Exception e) {
			
			
		}
		return xpathvalue;
	}

	public void selectVisibileTextById(String id, String value) {	
		
		try {
			WebElement elementId= driver.findElementById(id);
			Select droplist1= new Select(elementId);
			droplist1.selectByVisibleText(value);
			logStep("Pass","Was able to find the index by using the element's xpath successfully");
		} catch (Exception e) {
			logStep("Fail","Was not able to find the index by using the element's xpath successfully");
		}
	}

	public void selectIndexById(String id, int value) {
		try {
			WebElement elementId= driver.findElementById(id);
			Select droplist1= new Select(elementId);
			droplist1.selectByIndex(value);
			logStep("Pass","Was able to find the index by using the element's ID successfully");
			
		} catch (Exception e) {
			logStep("Fail","Was not able to find the index by using the element's ID successfully");
		}
		
	}

	public void switchToParentWindow() {
		
		Set<String> sessionids = driver.getWindowHandles();
		for(String id : sessionids) {
			driver.switchTo().window(id);
			break;
		}
	}

	public void switchToLastWindow() {
		
		try {
			Set<String> sessionids = driver.getWindowHandles();
			for(String id : sessionids) {
				driver.switchTo().window(id);
				logStep("Pass","Was able to switch to last window successfully");
			}
		} catch (Exception e) {
			logStep("Fail","Was not able to switch to last window successfully");
		}
	}

	public void acceptAlert() {
		
		try {
			driver.switchTo().alert().accept();
			logStep("Pass","Was able to accept the alert successfully");
		} catch (Exception e) {
			logStep("Fail","Was not able to accept the alert successfully");
		}
	}

	public void dismissAlert() {
		
		try {
			driver.switchTo().alert().dismiss();
			logStep("Pass","Was able to dismiss the alert successfully");
		} catch (Exception e) {
			logStep("Fail","Was not able to dismiss the alert successfully");
		}
	}

	public  String getAlertText() {
		try {
			Alert confirmation = driver.switchTo().alert();
			String alerttext = confirmation.getText();
			return alerttext;
			
		} catch (Exception e) {
			
		}
		return TestcaseName;
	}

	

	public void closeBrowser() {
		
		try {
			driver.close();
			logStep("Pass","Was able to close the browsers successfully");
		} catch (WebDriverException e) {
			logStep("Fail","Web Driver exception occured");
			
		}
	}

	public void closeAllBrowsers() {
		try {
			
			driver.quit();			
			
		} catch (SessionNotCreatedException e) {

		} catch (UnreachableBrowserException e) {			
			
			
		} 
	}

	public void clickByClassName(String classVal) {
		
		try {
			driver.findElementByClassName(classVal).click();
			logStep("Pass"," Able to click the element by class name");
		} catch (Exception e) {
			logStep("Pass"," Not Able to click the element by class name");
		}
	}

	public void selectValueById(String idVal, String value) {
		
		try {
			WebElement elementId= driver.findElementByXPath(idVal);
			Select droplist1= new Select(elementId);
			droplist1.selectByValue(value);
			logStep("Pass"," Able to select the drop down by value");
		} catch (Exception e) {			
			logStep("Fail"," Not Able to select the drop down by value");
		}
	}
	public void selectValueByXpath(String XpathVal, String Value)
	{
		try {
			WebElement elementXpath= driver.findElementByXPath(XpathVal);
			Select droplist1= new Select(elementXpath);
			droplist1.selectByValue(Value);
			logStep("Pass"," Able to select the drop down by value");
		} catch (Exception e) {			
			logStep("Fail"," Not Able to select the drop down by value");
		}
	}
     
	public void selectValueByName(String name, String Value)
	{
		try {
			WebElement elementname= driver.findElementByName(name);
			Select droplist1= new Select(elementname);
			droplist1.selectByValue(Value);
			logStep("Pass"," Able to select the drop down by value");
		} catch (Exception e) {
			
			logStep("Fail"," Not Able to select the drop down by value");
		}
	}
	public void selectVisibleTextByXpath(String xpath, String text) {
		
		try {
			WebElement elementxpath= driver.findElementByXPath(xpath);
			Select droplist= new Select(elementxpath);
			droplist.selectByVisibleText(text);
			logStep("Pass"," Able to select the drop down by visible text");
		} catch (Exception e) {
			
			logStep("Fail"," Not Able to select the drop down by visible text");
		}
	}

	public long takeSnap() {
		// TODO Auto-generated method stub
		long screen = (long)Math.floor((Math.random()*1000000l));
		File pic = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/"+screen+".png");
		try {
			FileUtils.copyFile(pic, dest);
			//logStep("Pass"," Able to take the screen shot");
		} catch (IOException e) {
			//logStep("fail", "IO exception occured");
		}
		return screen;
	}
	
	public void loadData() {
		 prop = new Properties();
		try {
			File file = new File("src/test/java/config/AutoTrader.properties");
			
			FileInputStream obj = new FileInputStream(file);
			//prop.load(new FileInputStream(new File("./src/test/java/Locators.properties")));
			prop.load(obj);
			//logStep("Pass"," Able to load the file");
		} catch (FileNotFoundException e) {			
			//logStep("fail", "File is not found");
		} catch (IOException e) {
			
			//logStep("fail", "IO exception is thrown");
		}
	}

	public void validateURLFor(String string) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains(string));;
	//	logStep("pass", "new web page containing URL "+string+" has loaded");
		Thread.sleep(1000);
		
		
	}

	public void validateAllListings(String string) throws InterruptedException {
		
		validateURLFor(string);
		Thread.sleep(1000);
		String value = prop.getProperty(string);
		String[] locator = value.split("#");
		String locatorKey=locator[0];
		String locatorValue = locator[1];
		
		if(locatorKey.equalsIgnoreCase("xpath")) {
			List<WebElement> BMWCars=findElementsByXPath(locatorValue);
			boolean allBMWcars=true;
			for(WebElement webElement :BMWCars)
			{
				if(!webElement.getText().contains(string))
					{allBMWcars=false;
				break;}
				
			}
			
			if(allBMWcars)
			{
				logStep("pass", "All listings loaded in first page are of "+string);
				System.out.println("There are "+BMWCars.size()+" BMW listings in first page");
			}
			else
				logStep("fail", "Not all listings loaded in first page are of "+string);
			
		}
		
	}

	private List<WebElement> findElementsByXPath(String locatorKey) {
		List<WebElement> listOfElements = driver.findElements(By.xpath(locatorKey));
		return listOfElements;

		
	}
		
}

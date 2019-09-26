package remotedriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
 
public class BaseTest {
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public CapabilityFactory capabilityFactory = new CapabilityFactory();
	
	@BeforeMethod
	@Parameters(value= {"browser"})
	
	public void setup (String browser) throws MalformedURLException{
		  driver.set(new RemoteWebDriver(new URL("http://goliath:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));		
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	
	@AfterClass void terminate() {
		driver.remove();
	}
	
	
}

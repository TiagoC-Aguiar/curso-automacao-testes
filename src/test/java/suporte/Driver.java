package suporte;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

	private static WebDriver driver;
	
	public static WebDriver criarDriver() {
		System.setProperty("webdriver.chrome.driver", "/home/tiago/Drivers/chromedriver");
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		
		driver.get("http://www.juliodelima.com.br/taskit");
		return driver;
	}
}

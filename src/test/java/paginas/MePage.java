package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

	public MePage(WebDriver driver) {
		super(driver);		
	}
	
	public MePage clicarAbaMoreDataAboutYou() {
		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		return this;
	}
	
	public AddContactPage clicarBotaoAddMoreDataAboutYou() {
		driver.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
		return new AddContactPage(driver); 
	}

	
}

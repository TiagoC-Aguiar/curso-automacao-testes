package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage {

	private WebElement addMoreData;
	
	public AddContactPage(WebDriver driver) {
		super(driver);
		addMoreData = driver.findElement(By.id("addmoredata"));
	}

	public AddContactPage escolherTipoContato(String tipo) {		
		WebElement campoType = driver.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByValue(tipo);
		return this;
	}
	
	public AddContactPage digitarContato(String contato) {
		addMoreData.findElement(By.name("contact")).sendKeys(contato);
		return this;
	}
	
	public MePage clicarSalvar() {
		addMoreData.findElement(By.linkText("SAVE")).click();
		return new MePage(driver);
	}
	
	public MePage adicionarContato(String tipo, String contato) {
		escolherTipoContato(tipo.toLowerCase());
		digitarContato(contato);
		return clicarSalvar();
	}
}

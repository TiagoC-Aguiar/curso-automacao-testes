package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginFormPage extends BasePage {

	private WebElement signInBox;
	
	public LoginFormPage(WebDriver driver) {
		super(driver);
		signInBox = driver.findElement(By.id("signinbox")); 
	}
	
	public LoginFormPage digitarLogin(String login) {
		signInBox.findElement(By.name("login")).sendKeys(login);
		return this;
	}
	
	public LoginFormPage digitarSenha(String senha) {
		signInBox.findElement(By.name("password")).sendKeys(senha);
		return this;
	}
	
	public SecretaPage clicarSignIn() {
		signInBox.findElement(By.linkText("SIGN IN")).click();
		return new SecretaPage(driver);
	}
	
	public SecretaPage fazerLogin(String login, String senha) {
		digitarLogin(login);
		digitarSenha(senha);
		return clicarSignIn();		
	}
}

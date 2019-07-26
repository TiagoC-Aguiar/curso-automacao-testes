package tests;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InformacoesUsuarioTest {

	private static WebDriver navegador;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/tiago/Drivers/chromedriver");
		navegador = new ChromeDriver();
		navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		navegador.get("http://www.juliodelima.com.br/taskit");	
		
		
		navegador.findElement(By.linkText("Sign in")).click();
		
		WebElement formSignInBox = navegador.findElement(By.id("signinbox"));
		
		formSignInBox.findElement(By.name("login")).sendKeys("julio0001");
		formSignInBox.findElement(By.name("password")).sendKeys("123456");
		formSignInBox.findElement(By.linkText("SIGN IN")).click();
		
		// Clicar em um elemento li que possui uma class "me"
		navegador.findElement(By.className("me")).click();
		
		// Clicar em um link que possui um href #moredata
		navegador.findElement(By.cssSelector("li a[href='#moredata']")).click();
	}
	
//	@Test
	public void testAdicionarInformacaoUsuario() {
		
		navegador.findElement(By.linkText("Sign in")).click();
		
		WebElement formSignInBox = navegador.findElement(By.id("signinbox"));
		
		formSignInBox.findElement(By.name("login")).sendKeys("julio0001");
		formSignInBox.findElement(By.name("password")).sendKeys("123456");
		formSignInBox.findElement(By.linkText("SIGN IN")).click();
		
//		WebElement me = navegador.findElement(By.className("me"));
//		String nome = me.getText();
//		boolean logout = navegador.findElement(By.cssSelector("li a[href='http://www.juliodelima.com.br/taskit/user/logout']")).isDisplayed();
//		
//		assertEquals("Hi, Julio", nome);		
//		assertTrue(logout);
		
		// Clicar em um elemento li que possui uma class "me"
		navegador.findElement(By.className("me")).click();
		
		// Clicar em um link que possui um href #moredata
		navegador.findElement(By.cssSelector("li a[href='#moredata']")).click();
		
		// Clicar em um botão dentro de um elemento com id "moredata"
		navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();
		
		// Selecionar o valor "phone" dentro elemento com id "addmoredata"
		
		WebElement modalBox = navegador.findElement(By.id("addmoredata"));
		Select sel = new Select(modalBox.findElement(By.xpath("//select[@name='type']")));
		sel.selectByValue("phone");
		
		// Digitar "+5561999991112" no campo input com name "contact" dentro de um elemento com id "addmoredata"
		modalBox.findElement(By.name("contact")).sendKeys("+5561999991113");
		
		// Clicar no link com texto "SAVE"
		modalBox.findElement(By.linkText("SAVE")).click();
		
//		div "toast-container" "Your contact has added!"
		String msg = navegador.findElement(By.cssSelector("#toast-container div")).getText();		
		System.out.println(msg);
		
		assertEquals("Your contact has been added!", msg);
	}
	
	@Test
	public void removerContatoUsuario() {
	// clicar no xpath	//span[text()='+551199990007']/following-sibling::a
	navegador.findElement(By.xpath("//span[text()='+55644444444']/following-sibling::a")).click();
		
	// confirmar tela javascript
	navegador.switchTo().alert().accept();	
	
	// "Rest in peace, dear phone!"
	WebElement msgPop = navegador.findElement(By.cssSelector("#toast-container div"));
	String msg = msgPop.getText();		
	System.out.println(msg);
	assertEquals("Rest in peace, dear phone!", msg);
	
	// Aguardar até 10 seg para q a msg desapareça	
//	WebDriverWait aguarda = new WebDriverWait(navegador, 10);
//	aguarda.until(ExpectedConditions.stalenessOf(msgPop));
	navegador.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	
	// Fazer logout. Link com texto "Logout"
	navegador.findElement(By.linkText("Logout")).click();
	}
	
//	@AfterClass
	public static void tearDown() {		
		navegador.quit();
	}
}

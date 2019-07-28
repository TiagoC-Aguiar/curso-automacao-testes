package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import suporte.Driver;
import suporte.Generator;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoes_usuario_test.csv")
public class InformacoesUsuarioTest {

	private static WebDriver navegador;
	
	@Rule
	public TestName test = new TestName();
	
	@BeforeClass
	public static void setUp() {		

	}
	
	@Test
	public void testAdicionarInformacaoUsuario(@Param(name = "tipo")String tipo, 
		@Param(name="contato")String contato, @Param(name="mensagem")String msgEsperada) {
		
		System.out.println("clica no botão + add more data");
		// Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        WebElement botao = navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]"));
   
        System.out.println("Exibe botão: " + botao.isDisplayed());        
        botao.click();

        // Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        System.out.println("modal depois de clicar no botão: \n" + popupAddMoreData.isDisplayed());
        // Na combo de name "type" escolhe a opção "Phone"
        System.out.println("Selecionar o valor \"phone\" dentro elemento com id \"addmoredata\"");
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByValue(tipo);

        // No campo de name "contact" digitar "+5511999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que está na popup
        System.out.println("Exibe botão: " + botao.isDisplayed());
        popupAddMoreData.findElement(By.linkText("SAVE")).click();
        System.out.println("modal depois de salvar: \n" + popupAddMoreData.isDisplayed());

        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        
        System.out.println("modal no final: \n" + popupAddMoreData.isDisplayed());
       
        String scrShot = "/home/tiago/eclipse-workspace/CursoWebdriverJava/test-report/taskit/" 
    			+ Generator.dataHoraArquivo() + test.getMethodName() + ".png";
//    	Screenshot.tirar(navegador, scrShot);
    	assertEquals(msgEsperada, mensagem);    	
        
	}
	
	@Test
	public void removerContatoUsuario() {
	// clicar no xpath	//span[text()='+551199990007']/following-sibling::a
	navegador.findElement(By.xpath("//span[text()='+5511999999945']/following-sibling::a")).click();
		
	// confirmar tela javascript
	navegador.switchTo().alert().accept();	
	
	// "Rest in peace, dear phone!"
	WebElement msgPop = navegador.findElement(By.cssSelector("#toast-container div"));
	String msg = msgPop.getText();		
	System.out.println(msg);
	assertEquals("Rest in peace, dear phone!", msg);
	
	String scrShot = "/home/tiago/eclipse-workspace/CursoWebdriverJava/test-report/taskit/" 
			+ Generator.dataHoraArquivo() + test.getMethodName() + ".png";
//	Screenshot.tirar(navegador, scrShot);
	
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

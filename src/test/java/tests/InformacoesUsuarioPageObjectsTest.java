package tests;

import static org.junit.Assert.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import paginas.LoginPage;
import suporte.Driver;
import suporte.Generator;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {

	private WebDriver driver;
	private boolean erro;
	
	@Rule
	public TestName test = new TestName();
	
	@Before
	public void setUp() {
		driver = Driver.criarDriver();
		erro = false;
	}
	
	@Test
	public void testAdicionarInformacaoUsuario(
			@Param(name = "login")String login,
			@Param(name = "senha")String senha,
			@Param(name = "tipo")String tipo,
			@Param(name = "contato")String contato,
			@Param(name = "mensagem")String msgEsperada) {
		String textoToast = new LoginPage(driver)
			.clicarSignIn()
			.fazerLogin(login, senha)
			.clicarMe()
			.clicarAbaMoreDataAboutYou()
			.clicarBotaoAddMoreDataAboutYou()
			.adicionarContato(tipo, contato)
			.capturarMsgToast();
		
		String scrShot = "/home/tiago/eclipse-workspace/CursoWebdriverJava/test-report/taskit/" 
				+ Generator.dataHoraArquivo() + test.getMethodName() + ".png";
		
		Screenshot.tirar(driver, scrShot);
		
		assertEquals(msgEsperada, textoToast);	
		
		Screenshot.apagarArquivoSucesso();
	}
	
	@After
	public void tearDown() {
//		driver.quit();
//		System.out.println("terminou");
	}
}

package suporte;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	private static WebDriver driver;
	private static String arquivo;

	public static void tirar(WebDriver driver, String arquivo) {
		Screenshot.driver = driver;
		Screenshot.arquivo = arquivo;
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(arquivo));
			System.out.println("Arquivo gerado: " + arquivo);
		} catch (Exception e) {
			System.out.println("Houveram problemas ao copiar o arquivo para a pasta: ");
			e.getMessage();
			e.printStackTrace();
		}	

	}
	
	public static void apagarArquivoSucesso() {
		FileUtils.deleteQuietly(new File(arquivo));		
	}
}

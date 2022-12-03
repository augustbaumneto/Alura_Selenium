package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * 
 * Classe pai de todas as PageObjects
 * 
 * @author August Neto
 *
 */
public class PageObject {

	protected WebDriver driver;
	
	private static final int TEMPO_ESPERA = 2;
	
	public PageObject (WebDriver driver, String navegador) {
		
		if (driver==null) {
			//verifica qual o navegador para inicializar o driver
			switch (navegador) {
			
			case "firefox":
				this.driver = new FirefoxDriver();
				break;
				
			default:
				this.driver = new ChromeDriver();
				break;
			}
			
			this.driver.manage().timeouts().implicitlyWait(TEMPO_ESPERA, TimeUnit.SECONDS);

		} else {
			this.driver = driver;
		
		}

	}
	
	
	/**
	 * Método para finalizar o navegador
	 */
	public void fechar() {
		this.driver.quit();
		
	}
	
}

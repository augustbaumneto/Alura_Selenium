package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * Classe pai de todas as PageObjects
 * 
 * @author August Neto
 *
 */
public class PageObject {

	protected WebDriver driver;
	
	public PageObject (WebDriver driver) {
		
		if (driver==null) {
			this.driver = new ChromeDriver();
			this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

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

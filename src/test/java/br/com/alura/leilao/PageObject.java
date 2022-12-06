package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * Classe pai de todas as PageObjects
 * 
 * @author August Neto
 *
 */
public class PageObject {

	protected WebDriver driver;
	
	//Antes de exceções
	private static final int TEMPO_ESPERA = 2;
	
	//PAra aguardar página ou elementos
	private static final long TEMPO_ESPERA_ELEMENTO = 4;
	
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
	
	/**
	 * 
	 * Verifica se a pagina atual é a informada
	 * 
	 * @param urlverificao	URL da página a ser verificada
	 * @return				true se for a mesma página
	 */
	protected boolean ePaginaAtual(String urlverificao) {
		
		//Aguarda um tempo para verificar se não existe uma página carregando
		try {
			new WebDriverWait(this.driver, TEMPO_ESPERA_ELEMENTO).until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlverificao)));
		}catch (TimeoutException e) {
			//PAra evitar a exceção quando a página não muda
		}
		
		return (this.driver.getCurrentUrl().equals(urlverificao));
		
	}
	
	/**
	 * 
	 * Aguardar a pagina ser carregada.
	 * 
	 * @param urlverificao	URL da página a ser aguardada.
	 */
	protected void aguardaPagina(String urlverificao) {
		
			try {
				new WebDriverWait(this.driver, TEMPO_ESPERA_ELEMENTO).until(ExpectedConditions.urlToBe(urlverificao));
			}catch (TimeoutException e) {
				//PAra evitar a exceção quando a página não muda
			}
	
		
	}
	
	
}

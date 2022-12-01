/**
 * 
 */
package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Leiloes
 * Page Objects do Login
 *
 */
public class LeiloesPage {

	/**
	 *  URL da Tela de lista de leiloes
	 */
	public static final String URL_LEILOES = "http://localhost:8080/leiloes";
	private WebDriver driver;
	
	/**
	 * Construtor da página
	 */
	public LeiloesPage(WebDriver driver) {
		this.driver = driver;
		
	}

	/**
	 * Método para finalizar o navegador
	 */
	public void fechar() {
		this.driver.quit();
		
	}

	/**
	 *   Clica no botao novo leilao e direciona para a tela de cadastro de formulario
	 */
	public CadastroLeilaoPage navegarNovoFormulario() {
		this.driver.findElement(By.id("novo_leilao_link")).click();
		return new CadastroLeilaoPage(this.driver);
	}
		
}

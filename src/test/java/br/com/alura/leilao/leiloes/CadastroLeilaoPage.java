/**
 * 
 */
package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.login.LoginPage;
import br.com.alura.leilao.parametros.ParametrosTest;

/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Leiloes
 * Page Objects do Login
 *
 */
public class CadastroLeilaoPage {

	/**
	 *  URL da Tela de cadastro de leilao
	 */
	public static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
	private WebDriver driver;
	
	/**
	 * Construtor da página
	 */
	public CadastroLeilaoPage(WebDriver driver) {
		this.driver = driver;
		
	}

	/**
	 * Método para finalizar o navegador
	 */
	public void fechar() {
		this.driver.quit();
		
	}

		
}

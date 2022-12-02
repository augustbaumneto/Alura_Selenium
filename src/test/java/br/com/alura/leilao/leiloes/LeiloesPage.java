/**
 * 
 */
package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Leiloes
 * Page Objects do Login
 *
 */
public class LeiloesPage extends PageObject {

	/**
	 *  URL da Tela de lista de leiloes
	 */
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";

	
	/**
	 *  Identificadores dos Elementos da página
	 * 
	 */
	
	private static final String ID_BOTAO_NOVO = "novo_leilao_link";
	private static final String ID_TABELA_LEILOES = "tabela-leiloes";
	
	
	/**
	 * Construtor da página
	 */
	public LeiloesPage(WebDriver driver) {
		super(driver);
	}

	/**
	 *   Clica no botao novo leilao e direciona para a tela de cadastro de formulario
	 */
	public CadastroLeilaoPage navegarNovoFormulario() {
		this.driver.findElement(By.id(ID_BOTAO_NOVO)).click();
		return new CadastroLeilaoPage(this.driver);
	}

	/**
	 * 
	 * Verifica se um leilão existe
	 * 
	 * @param nomeLeilao 	nome do leilão cadastrado
	 * @param valorabertura	Valor inicial do leilao	
	 * @param dataabertura	Data de abertura do leilao
	 * 
	 * @return true se leilao existe
	 */
	public boolean isLeilaoCadastrado(String usuario, String nomeLeilao, String valorabertura, String dataabertura) {
		WebElement linha = this.driver.findElement(By.cssSelector("#"+ID_TABELA_LEILOES+" tbody tr:last-child"));
		WebElement colunanome = linha.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunadata = linha.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunavalor = linha.findElement(By.cssSelector("td:nth-child(3)"));
		WebElement colunacriador = linha.findElement(By.cssSelector("td:nth-child(4)"));
		
		return (colunanome.getText().equals(nomeLeilao)&&(colunacriador.getText().equals(usuario))&&
				(colunadata.getText().equals(dataabertura))&&(colunavalor.getText().equals(valorabertura)));
	}
	
	/**
	 * Verifica se texto esta presente na página
	 * 
	 * @param texto	para consultar na página
	 * @return	true se contiver
	 */
	public boolean contemMensagem(String texto) {
		return driver.getPageSource().contains(texto);
	}
		
}

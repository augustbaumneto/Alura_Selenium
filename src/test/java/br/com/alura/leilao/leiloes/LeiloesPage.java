/**
 * 
 */
package br.com.alura.leilao.leiloes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.login.LoginPage;

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
	private static final String CSS_BOTAO_ENTRAR = "[href='/login']";
	private static final String CSS_LABEL_NOMELEILAO = "td:first-child"; //Localizador da coluna nome na tabela
	private static final String CSS_BOTAO_DARLANCE = "td a.btn.btn-block.btn-info";
	
	/**
	 * Construtor da página
	 */
	public LeiloesPage(String navegador) {
		this(null, navegador);
		this.driver.navigate().to(URL_LEILOES);
	}

	/**
	 * Construtor da página
	 */
	public LeiloesPage(WebDriver driver, String navegador) {
		super(driver, navegador);
	}
	
	/**
	 *   Clica no botao novo leilao e direciona para a tela de cadastro de formulario
	 */
	public CadastroLeilaoPage navegarNovoFormulario() {
		//Aguarda carregar a pagina
		aguardaPagina(URL_LEILOES);
		
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
		//Aguarda carregar a pagina
		aguardaPagina(URL_LEILOES);
		
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
	/**
	 * 
	 * Acessa a página de Login
	 * 
	 * @return LoginPage  a página de login
	 */
	public LoginPage acessaLogin(){
		//Espera a página carregou
		aguardaPagina(URL_LEILOES);
		
		this.driver.findElement(By.cssSelector(CSS_BOTAO_ENTRAR)).click();
		
		return new LoginPage(this.driver);
	}

	/**
	 * 
	 * Acessa a página de lance do leilão
	 * 
	 * @param nomeleilao	nome do leilao para entrar	
	 * @return	Rertorna a pagina de Lance
	 */
	public LancePage selecionaLeilaoLance(String nomeleilao) {
	
		//Aguarda carregar a pagina
		aguardaPagina(URL_LEILOES);
				
		WebElement tabela = this.driver.findElement(By.id(ID_TABELA_LEILOES));
		List<WebElement> linhas = tabela.findElements(By.cssSelector("tbody tr"));
		WebElement linhadesejada = null;
		
		for (WebElement linha : linhas ) {
			
			String valor = linha.findElement(By.cssSelector(CSS_LABEL_NOMELEILAO)).getText();
			if (valor.equals(nomeleilao)) {
				linhadesejada = linha;
				break;
			}
		
		}
		
		linhadesejada.findElement(By.cssSelector(CSS_BOTAO_DARLANCE)).click();
		
		
		return new LancePage(this.driver);
	}
		
}

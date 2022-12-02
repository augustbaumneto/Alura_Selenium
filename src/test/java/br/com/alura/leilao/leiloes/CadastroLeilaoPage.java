/**
 * 
 */
package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;


/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Leiloes
 * Page Objects do Login
 *
 */
public class CadastroLeilaoPage extends PageObject{

	/**
	 *  URL da Tela de cadastro de leilao
	 */
	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
	
	/**
	 *  URL da Tela de cadastro de leilao com erro
	 */
	private static final String URL_CADASTRO_LEILAO_ERRO = "http://localhost:8080/leiloes";
	
	/**
	 *  Identificadores dos Elementos da página
	 * 
	 */
	private static final String ID_CAMPO_NOME = "nome";
	private static final String ID_CAMPO_DATAABERTURA = "dataAbertura";
	private static final String ID_CAMPO_VALORINICIAL = "valorInicial";
	private static final String ID_BOTAO_SALVAR = "button-submit";
	
	/**
	 *  Tamanho minimo do campo nome
	 * 
	 */
	private static final int NOME_TAMANHO_MINIMO = 3;
	
	/**
	 *  VAlor minimo aceitável para o campo Valor inicial
	 * 
	 */
	private static final double VALORINICAL_MINIMO = 0.1;
	
	
	/**
	 * Construtor da página
	 */
	public CadastroLeilaoPage(WebDriver driver) {
		super(driver);
		
	}

	
	/**
	 * 
	 * Cadastra um leilão
	 * 
	 * @param nome			nome do leilão
	 * @param valorinicial	valor do lance inicial
	 * @param dataabertura	data de inicio do leilao
	 */
	public LeiloesPage cadastrarLeilao(String nome, String valorinicial, String dataabertura) {
		this.driver.findElement(By.id(ID_CAMPO_NOME)).sendKeys(nome);
		this.driver.findElement(By.id(ID_CAMPO_DATAABERTURA)).sendKeys(dataabertura);
		this.driver.findElement(By.id(ID_CAMPO_VALORINICIAL)).sendKeys(valorinicial);
		this.driver.findElement(By.id(ID_BOTAO_SALVAR)).click();
		
		return new LeiloesPage(this.driver);
	}

	/**
	 * 
	 *  Verifica se a pagina esta na url correta
	 * 
	 * @return	true se estiver na url correta
	 */
	public boolean isPaginaCadastro() {
		
		return this.driver.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
		
	}

	/**
	 * 
	 *  Verifica se a pagina esta na url correta de erro
	 * 
	 * @return	true se estiver na url correta
	 */
	public boolean isPaginaCadastroErro() {
		
		return this.driver.getCurrentUrl().equals(URL_CADASTRO_LEILAO_ERRO);
		
	}
	
	/**
	 * 
	 *  Verifica se apresenta mensagem de erro de caracter minimo no campo nome
	 * 
	 * @return	true se apresentar mensagem
	 */
	public boolean contemMensagemErroNomeTamanhoMinimo() {
		
		return this.driver.getPageSource().contains("minimo "+ NOME_TAMANHO_MINIMO +" caracteres");
	}

	/**
	 * 
	 *  Verifica se apresenta mensagem de erro de campo nome em branco
	 * 
	 * @return	true se apresentar mensagem
	 */
	public boolean contemMensagemErroNomeEmBranco() {
		
		return this.driver.getPageSource().contains("não deve estar em branco");
	}

	/**
	 * 
	 *  Verifica se apresenta mensagem de erro de campo valor não ultrapassa o minimo
	 * 
	 * @return	true se apresentar mensagem
	 */
	public boolean contemMensagemErroValorMinimo() {

		return this.driver.getPageSource().contains("deve ser um valor maior de "+VALORINICAL_MINIMO);
		
	}

	/**
	 * 
	 *  Verifica se apresenta mensagem de erro de campo Data Inicial no formato incorreto
	 * 
	 * @return	true se apresentar mensagem
	 */
	public boolean contemMensagemErroDataForaFormato() {
		
		return this.driver.getPageSource().contains("deve ser uma data no formato dd/MM/yyyy");
		
	}

		
}

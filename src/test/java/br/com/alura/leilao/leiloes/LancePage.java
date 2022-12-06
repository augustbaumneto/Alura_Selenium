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
 * Classe da API do Selenium para a página de Lances
 * Page Objects do Login
 *
 */
public class LancePage extends PageObject {

	/**
	 *  URL da Tela de lista de leiloes
	 */
	private static final String URL_LANCE = "http://localhost:8080/leiloes/2";

	
	/**
	 *  Identificadores dos Elementos da página
	 * 
	 */
	
	private static final String ID_CAMPO_VALORLANCE = "valor";
	private static final String ID_BOTAO_LANCES = "btnDarLance";
	private static final String ID_TABELA_LANCE = "lancesDados";


	private static final String MSG_LANCEOK = "Lance adicionado com sucesso!";
	
	
	/**
	 * Construtor da página
	 */
	public LancePage(WebDriver driver) {
		super(driver, null);
	}

	/**
	 * 
	 * Verifica se um lance foi dado
	 * 
	 * @param valorlance 	valor do lance
	 * @param usuario		usuario que deu o lance	
	 * @return true 		se lance existe
	 */
	public boolean isLeilaoCadastrado(String valorlance, String usuario, String datalance) {
		//Aguarda carregar a pagina
		aguardaPagina(URL_LANCE);
		
		WebElement linha = this.driver.findElement(By.cssSelector("#"+ ID_TABELA_LANCE +" tbody tr:last-child"));
		WebElement colunausuario = linha.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunadata = linha.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunavalor = linha.findElement(By.cssSelector("td:nth-child(3)"));

		
		return (colunausuario.getText().equals(usuario)&&
				(colunadata.getText().equals(datalance))&&(colunavalor.getText().equals(valorlance)));
	}
	
	/**
	 * Verifica se texto esta presente na página
	 * 
	 * @param texto	para consultar na página
	 * @return	true se contiver
	 */
	public boolean contemMensagemLanceOK() {
		return driver.getPageSource().contains(MSG_LANCEOK);
	}

	/**
	 * 
	 *  Verifica se a página é de lance
	 * 
	 * @return	true se for a url de lance
	 */
	public boolean ePaginaLance() {
		
		return ePaginaAtual(URL_LANCE);
	}
	
	/**
	 * 
	 * Da um lance no leilao
	 * 
	 * @param valorlance Valor a fazer o lance
	 */
	public void darlance(String valorlance) {
		
		aguardaPagina(URL_LANCE);
		
		this.driver.findElement(By.id(ID_CAMPO_VALORLANCE)).sendKeys(valorlance);
		this.driver.findElement(By.id(ID_BOTAO_LANCES)).click();
		
		
	}

		
}

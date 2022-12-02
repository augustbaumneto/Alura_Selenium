/**
 * 
 */
package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import br.com.alura.leilao.parametros.ParametrosTest;

/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Login
 * Page Objects do Login
 *
 */
public class LoginPage extends PageObject{

	/**
	 *  URL da Tela de login 
	 */
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	/**
	 *  Identificadores dos Elementos da página
	 * 
	 */
	
	private static final String CSS_BOTAO_ENTRAR = "[href='/login']";
	private static final String ID_CAMPO_USUARIO = "username";
	private static final String ID_CAMPO_SENHA = "password";
	private static final String ID_BOTAO_LOGIN = "login-form";
	private static final String ID_LABEL_USUARIOLOGADO = "usuario-logado";
	
	
	/**
	 * Construtor da página
	 */
	public LoginPage() {
		super(null);
		this.driver.navigate().to(ParametrosTest.URL_INICIAL);
		this.driver.findElement(By.cssSelector(CSS_BOTAO_ENTRAR)).click();
	}

	/**
	 * Preenchimento do formulário de login
	 * 
	 * @param usuario	Usuário para logar
	 * @param senha		Senha para logar
	 */
	public void preencheFormularioAcesso(String usuario, String senha) {
		driver.findElement(By.id(ID_CAMPO_USUARIO)).sendKeys(usuario);
		driver.findElement(By.id(ID_CAMPO_SENHA)).sendKeys(senha);
		
	}

	/**
	 *  Clica no botão de Login e enviar os dados para validão e autorização
	 * 
	 * @return LEiloesPage retorna uma pagina de leiloes
	 */
	public LeiloesPage submeteLogin() {
		driver.findElement(By.id(ID_BOTAO_LOGIN)).submit();
		
		return new LeiloesPage(driver);
	}

	/**
	 * 
	 *  Verifica se a página é de login
	 * 
	 * @return	true se for a url de login
	 */
	
	public boolean ePaginaLogin() {
		
		
		return (driver.getCurrentUrl().equals(URL_LOGIN));
		
	}

	/**
	 * Retorna o usuário logado 
	 * 
	 * @return O texto do usuário logado, se não houver usuário logado retorna null
	 */
	public String retornaUsuarioLogado() {
		try {	
			return driver.findElement(By.id(ID_LABEL_USUARIOLOGADO)).getText();
		} catch (NoSuchElementException e) {
			
			return null ;
		}
	}

	
	/**
	 * Navega até a página esperada
	 * 
	 * @param url de destino
	 */
	public void navegaPagina(String url) {
		this.driver.navigate().to(url);
		
	}

	/**
	 * Verifica se texto esta presente na página
	 * 
	 * @param texto	para consultar na página
	 * @return	true se contiver
	 */
	public boolean contemDado(String texto) {
		return driver.getPageSource().contains(texto);
	}
	
	
}
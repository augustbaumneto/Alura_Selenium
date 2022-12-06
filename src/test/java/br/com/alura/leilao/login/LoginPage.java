/**
 * 
 */
package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

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
	
	private static final String ID_CAMPO_USUARIO = "username";
	private static final String ID_CAMPO_SENHA = "password";
	private static final String ID_BOTAO_LOGIN = "login-form";
	private static final String ID_LABEL_USUARIOLOGADO = "usuario-logado";

	private static final String MSG_LOGIN_ERRO = "Usuário e senha inválidos.";
	
	
	/**
	 * Construtor da página
	 */
	public LoginPage(WebDriver driver) {
		super(driver,null);
	}

	/**
	 * Preenchimento do formulário de login
	 * 
	 * @param usuario	Usuário para logar
	 * @param senha		Senha para logar
	 */
	public void preencheFormularioAcesso(String usuario, String senha) {
		//Aguarda página entrar
		aguardaPagina(URL_LOGIN);
		
		driver.findElement(By.id(ID_CAMPO_USUARIO)).sendKeys(usuario);
		driver.findElement(By.id(ID_CAMPO_SENHA)).sendKeys(senha);
		
	}

	/**
	 *  Clica no botão de Login e enviar os dados para validão e autorização
	 * 
	 * @return LEiloesPage retorna uma pagina de leiloes
	 */
	public LeiloesPage submeteLogin() {
		//Aguarda página entrar
		aguardaPagina(URL_LOGIN);
		
		driver.findElement(By.id(ID_BOTAO_LOGIN)).submit();
		
		return new LeiloesPage(driver,null);
	}

	/**
	 * 
	 *  Verifica se a página é de login
	 * 
	 * @return	true se for a url de login
	 */
	
	public boolean ePaginaLogin() {
		//Espera a página mudar de url
		
		return ePaginaAtual(URL_LOGIN);
		

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

	/**
	 * 
	 * Verifica se a mensagem de erro esta presente na mensagem
	 * 
	 * @return true se mensagem for a correta
	 */
	public boolean mensagemErroUsuarioInvalido() {
		
		return (this.driver.getPageSource().contains(MSG_LOGIN_ERRO));
	}
	
	
}
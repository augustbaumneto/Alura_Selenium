/**
 * 
 */
package br.com.alura.leilao.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeiloesPage;
import br.com.alura.leilao.parametros.ParametrosTest;

/**
 * @author August Neto
 *
 * Classe da API do Selenium para a página de Login
 * Page Objects do Login
 *
 */
public class LoginPage {

	/**
	 *  URL da Tela de login 
	 */
	public static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver driver;
	
	/**
	 * Construtor da página
	 */
	public LoginPage() {
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		this.driver.navigate().to(ParametrosTest.URL_INICIAL);
		this.driver.findElement(By.cssSelector("[href='/login']")).click();
	}

	/**
	 * Método para finalizar o navegador
	 */
	public void fechar() {
		this.driver.quit();
		
	}
	/**
	 * Preenchimento do formulário de login
	 * 
	 * @param usuario	Usuário para logar
	 * @param senha		Senha para logar
	 */
	public void preencheFormularioAcesso(String usuario, String senha) {
		driver.findElement(By.id("username")).sendKeys(usuario);
		driver.findElement(By.id("password")).sendKeys(senha);
		
	}

	/**
	 *  Clica no botão de Login e enviar os dados para validão e autorização
	 * 
	 * @return LEiloesPage retorna uma pagina de leiloes
	 */
	public LeiloesPage submeteLogin() {
		driver.findElement(By.id("login-form")).submit();
		
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
			return driver.findElement(By.id("usuario-logado")).getText();
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
	 *    Getter do drive
	 * 
	 * @return Webdrive
	 */
	public WebDriver getWebDriver(){
	
		return this.driver;

	}
	
}
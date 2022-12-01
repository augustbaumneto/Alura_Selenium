package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverException;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.parametros.ParametrosTest;


public class HelloWorldSelenium {

	@Test
	public void hello () {
	
		//WebDriver browser = new FirefoxDriver();
		WebDriver browser = new ChromeDriver();
		browser.navigate().to(ParametrosTest.URL_INICIAL);
		browser.quit();

	}

}

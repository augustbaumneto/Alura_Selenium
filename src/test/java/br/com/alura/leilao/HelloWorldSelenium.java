package br.com.alura.leilao;

import org.junit.jupiter.api.Test;



import br.com.alura.leilao.leiloes.LeiloesPage;



public class HelloWorldSelenium {

	private static final String NAVEGADOR = "chrome";


	@Test
	public void hello () {
	
		
		LeiloesPage pagina = new LeiloesPage(NAVEGADOR); 
		
		pagina.acessaLogin();
		pagina.fechar();

	}

}

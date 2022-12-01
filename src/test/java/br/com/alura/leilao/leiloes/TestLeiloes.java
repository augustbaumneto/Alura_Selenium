package br.com.alura.leilao.leiloes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

/**
 * 
 * @author August Neto
 *
 * Classe para testes relacionado a leiloes
 *
 */
public class TestLeiloes {
	
	private LeiloesPage paginaleiloes;
	

	
	@AfterEach
	/**
	 * Finalização de cada teste
	 */
	public void finaliza() {
		this.paginaleiloes.fechar();
	}
	
	/**
	 * Tenta cadastrar um novo leilao
	 */
	@Test
	public void cadastraleilao() {
		LoginPage paginalogin = new LoginPage();
		paginalogin.preencheFormularioAcesso("fulano", "pass");
		paginaleiloes = paginalogin.submeteLogin();
		CadastroLeilaoPage paginacadastro = paginaleiloes.navegarNovoFormulario();
		
	}
	
}

package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.leiloes.LeiloesPage;
import br.com.alura.leilao.parametros.ParametrosTest;

/**
 * 
 * @author August Neto
 *
 * Classe para testes do login
 *
 */
public class TestLogin {
	
	private LoginPage paginalogin;
	private LeiloesPage paginaleilao;
	
	@BeforeEach
	/**
	 * Setup de cada teste
	 */
	public void setup() {
		this.paginaleilao = new LeiloesPage();
		this.paginalogin = paginaleilao.acessaLogin();
	}
	
	@AfterEach
	/**
	 * Finalização de cada teste
	 */
	public void finaliza() {
		this.paginalogin.fechar();
	}
	
	/**
	 * Testa o login com dados válidos
	 */
	@Test
	public void efetuaLoginDadosValidos (){
		
		this.paginalogin.preencheFormularioAcesso("fulano","pass");
		this.paginalogin.submeteLogin();
		
		Assert.assertFalse(this.paginalogin.ePaginaLogin());
		Assert.assertEquals("fulano", this.paginalogin.retornaUsuarioLogado());
		
	}
	
	
	/**
	 * Testa o login com dados inválidos
	 */
	@Test
	public void tentarEfetuaLoginDadosInvalidos (){
		
		this.paginalogin.preencheFormularioAcesso("inválido", "XER");
		this.paginalogin.submeteLogin();
			
		Assert.assertFalse(this.paginalogin.ePaginaLogin());
		Assert.assertTrue(this.paginalogin.contemDado("Usuário e senha inválidos."));
		Assert.assertTrue(this.paginalogin.mensagemErroUsuarioInvalido());
		Assert.assertNull(this.paginalogin.retornaUsuarioLogado());
		
	}

	/**
	 * Testa acesso em uma página sem esta logado
	 */
	@Test
	public void tentarAcessarPaginaRestritaSemLogin (){
		this.paginalogin.navegaPagina(ParametrosTest.URL_LEILOES2);
		
		Assert.assertTrue(this.paginalogin.ePaginaLogin());
		Assert.assertFalse(this.paginalogin.contemDado("Dados do Leilão"));
		
	}	
	
}

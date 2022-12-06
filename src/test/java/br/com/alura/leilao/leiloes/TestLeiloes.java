package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
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
	private LoginPage paginalogin;
	private CadastroLeilaoPage paginacadastro;
	private String usuario;
	private static final String NAVEGADOR = "firefox";
	
	@BeforeEach
	/**
	 * Navegar até a página de leilão
	 */
	public void navegaAteLeilao() {
		this.paginaleiloes = new LeiloesPage(NAVEGADOR);
		this.paginalogin = this.paginaleiloes.acessaLogin();
		usuario = "fulano";
		this.paginalogin.preencheFormularioAcesso(usuario, "pass");
		this.paginaleiloes = this.paginalogin.submeteLogin();
		this.paginacadastro = paginaleiloes.navegarNovoFormulario();
	}
	
	
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
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do Dia "+ hoje;
		String valor = "550.00";
		
		System.out.println(nome + "    "+ hoje+ "    "+valor);
		
		this.paginaleiloes=paginacadastro.cadastrarLeilao(nome, valor, hoje);
		
		Assert.assertTrue(paginaleiloes.isLeilaoCadastrado(usuario, nome, valor, hoje));
		Assert.assertTrue(paginaleiloes.contemMensagem("Leilão salvo com sucesso"));
		
	}
	
	/**
	 *  Valida preenchimento de campos de cadastro incorretos
	 */
	@Test
	public void cadastraleilaoCamposErrados() {
		
		this.paginaleiloes=paginacadastro.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.paginacadastro.ePaginaCadastro());
		Assert.assertTrue(this.paginacadastro.ePaginaCadastroErro());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroNomeTamanhoMinimo());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroNomeEmBranco());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroValorMinimo());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroDataForaFormato());
		
	}
	
}

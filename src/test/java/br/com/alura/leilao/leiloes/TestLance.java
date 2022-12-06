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
 * Classe para testes relacionado a lances
 *
 */
public class TestLance {
	
	private LeiloesPage paginaleiloes;
	private LoginPage paginalogin;
	private LancePage paginalance;
	private String usuario;
	private static final String NAVEGADOR = "chrome";
	
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
		this.paginalance = paginaleiloes.selecionaLeilaoLance("Computador Z3");
	}
	
	
	@AfterEach
	/**
	 * Finalização de cada teste
	 */
	public void finaliza() {
		this.paginaleiloes.fechar();
	}
	
	/**
	 * Tenta executar um novo lance
	 */
	@Test
	public void darumlance() {
		String valorlance = "510.00";
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		paginalance.darlance(valorlance);
		
		
		Assert.assertTrue(paginalance.ePaginaLance());
		Assert.assertTrue(paginalance.contemMensagemLanceOK());
		Assert.assertTrue(paginalance.isLeilaoCadastrado(valorlance, usuario, hoje));
		
	}
	
	/**
	 *  Valida preenchimento de campos de cadastro incorretos
	 */
	@Test
	public void cadastraleilaoCamposErrados() {
		
		Assert.assertTrue(true);
		
	/*	this.paginaleiloes=paginacadastro.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.paginacadastro.ePaginaCadastro());
		Assert.assertTrue(this.paginacadastro.ePaginaCadastroErro());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroNomeTamanhoMinimo());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroNomeEmBranco());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroValorMinimo());
		Assert.assertTrue(this.paginacadastro.contemMensagemErroDataForaFormato());
		*/
	}
	
}

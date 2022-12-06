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
	public void navegaAteLance() {
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
	public void darLanceValido() {
		String valorlance = "510.00";
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		paginalance.darlance(valorlance);
		
		
		Assert.assertTrue(paginalance.ePaginaLance());
		Assert.assertTrue(paginalance.contemMensagemLanceOK());
		Assert.assertTrue(paginalance.eLanceEnviado(valorlance, usuario, hoje));
		
	}
	
	/**
	 *  Tentativa de dar dois lances seguidos
	 */
	@Test
	public void tentarLancesSeguidos() {
		
		String valorlance1 = "510.00";
		String valorlance2 = "520.00";
		
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		paginalance.darlance(valorlance1);
		paginalance.darlance(valorlance2);
		
		Assert.assertTrue((paginalance.ePaginaLance()));
		Assert.assertTrue(paginalance.contemMensagemLanceNOK());
		Assert.assertFalse(paginalance.eLanceEnviado(valorlance2, usuario, hoje));
		
	}

	/**
	 * Tenta dar um lance sem preencher campo de lance
	 */
	@Test
	public void darLanceVazio() {
		String valorlance = "";

		paginalance.darlance(valorlance);
		
		
		Assert.assertTrue(paginalance.ePaginaLance());
		Assert.assertTrue(paginalance.contemMensagemLanceVazio());
		
	}
	
	/**
	 * Tenta dar um lance menor que 0.1
	 */
	@Test
	public void darLanceInferior() {
		String valorlance = "0";
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		paginalance.darlance(valorlance);
		
		
		Assert.assertTrue(paginalance.ePaginaLance());
		Assert.assertTrue(paginalance.contemMensagemLanceInferior());
		Assert.assertFalse(paginalance.eLanceEnviado(valorlance, usuario, hoje));
	}
	
}

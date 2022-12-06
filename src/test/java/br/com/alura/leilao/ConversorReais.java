/**
 * 
 */
package br.com.alura.leilao;

/**
 * @author August Neto
 *
 */
public class ConversorReais {
	
	
	public ConversorReais() {
		
	}
	
	public String converteReaisExibicao(String valor) {
		
		valor = "R$ "+valor;
		
		return valor.replace(".", ",");
		
	}

}

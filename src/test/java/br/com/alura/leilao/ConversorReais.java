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
		
		String centavos = valor.substring(valor.length()-2);
		String reais = valor.substring(0, valor.length()-3);
		
		return "R$ "+reais+","+centavos;
		
	}

}

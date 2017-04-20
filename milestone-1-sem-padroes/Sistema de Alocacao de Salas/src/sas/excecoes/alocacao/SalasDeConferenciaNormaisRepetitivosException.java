package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar um evento repetitivo em uma sala de conferecia normal
 * @author Andre Luis
 *
 */

public class SalasDeConferenciaNormaisRepetitivosException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalasDeConferenciaNormaisRepetitivosException(
			String msg) {
		super(msg);
	}
	
}

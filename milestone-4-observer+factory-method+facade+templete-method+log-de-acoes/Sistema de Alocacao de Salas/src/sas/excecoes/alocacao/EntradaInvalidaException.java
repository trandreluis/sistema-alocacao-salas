package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se obtem uma entrada invalida
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class EntradaInvalidaException extends RoomsAllocationException {

	/**
	 * Metodo construtor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public EntradaInvalidaException(String msg) {
		super(msg);
	}

}

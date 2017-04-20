package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar, em uma sala que nao e de fisica, um evento que nao e da area de fisica
 * @author Andre Luis
 *
 */

public class SalaExclusivaFisicaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalaExclusivaFisicaException(String msg) {
		super(msg);
	}

}

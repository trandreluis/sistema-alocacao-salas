package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar, em uma sala que nao e de quimica, um evento que nao e da area de quimica
 * @author Andre Luis
 *
 */

public class SalaExclusicaQuimicaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalaExclusicaQuimicaException(String msg) {
		super(msg);
	}

}

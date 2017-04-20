package sas.excecoes.sala;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Exccecao que e lancada quando se passa um tipo invalido
 * @author Andre Luis
 *
 */

public class TipoInvalidoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public TipoInvalidoException(String msg) {
		super(msg);
	}

}

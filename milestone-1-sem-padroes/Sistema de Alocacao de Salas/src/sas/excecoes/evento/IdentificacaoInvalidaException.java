package sas.excecoes.evento;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Excecao que e lancada quando se passa algum ID invalido
 * @author Andre Luis
 *
 */

public class IdentificacaoInvalidaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public IdentificacaoInvalidaException(String msg) {
		super(msg);
	}

}

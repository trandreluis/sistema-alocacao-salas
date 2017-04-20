package sas.excecoes.sala;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Excecao que e lancada quando se tenta realizar alguma operacao com uma Sala que nao existe
 * @author Andre Luis
 *
 */

public class SalaInexistenteException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalaInexistenteException(String msg) {
		super(msg);
	}

}

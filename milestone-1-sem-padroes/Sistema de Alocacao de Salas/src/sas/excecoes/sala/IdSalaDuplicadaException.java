package sas.excecoes.sala;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Execao que e lancada quando se tenta cadastrar uma Sala com um ID de uma Sala ja cadastrada
 * @author Andre Luis
 *
 */

public class IdSalaDuplicadaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public IdSalaDuplicadaException(String msg) {
		super(msg);
	}

}

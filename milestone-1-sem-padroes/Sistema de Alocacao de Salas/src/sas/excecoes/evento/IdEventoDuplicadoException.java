package sas.excecoes.evento;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Execao que e lancada quando se tenta cadastrar um Evento com um ID de um Evento ja cadastrado
 * @author Andre Luis
 *
 */

public class IdEventoDuplicadoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public IdEventoDuplicadoException(String msg) {
		super(msg);
	}

}

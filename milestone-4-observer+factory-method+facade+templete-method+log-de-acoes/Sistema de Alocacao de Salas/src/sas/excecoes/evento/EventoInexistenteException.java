package sas.excecoes.evento;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Excecao que e lancada quando se tenta realizar alguma operacao com um Evento
 * que nao existe
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class EventoInexistenteException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public EventoInexistenteException(String msg) {
		super(msg);
	}

}

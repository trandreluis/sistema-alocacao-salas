package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tentar alocar um evento novamente
 * @author Andre Luis
 *
 */

public class EventoJaAlocadoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public EventoJaAlocadoException(String msg) {
		super(msg);
	}

}

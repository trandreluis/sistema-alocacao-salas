package sas.excecoes.alocacao;

/**
 * Execao que e lancada quando se tenta fazer uma operacao com um evento nao alocado
 * @author Andre Luis
 *
 */

public class EventoNaoAlocadoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public EventoNaoAlocadoException(String msg) {
		super(msg);
	}

}

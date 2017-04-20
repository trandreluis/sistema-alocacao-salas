package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar um evento que ocorre em final de semana em uma sala
 * @author Andre Luis
 *
 */

public class SalasNaoAlocadasFinsDeSemanaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalasNaoAlocadasFinsDeSemanaException(String msg) {
		super(msg);
	}

}

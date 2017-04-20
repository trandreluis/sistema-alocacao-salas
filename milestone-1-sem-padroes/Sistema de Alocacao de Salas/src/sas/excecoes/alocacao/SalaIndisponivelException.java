package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta alocar um evento em uma sala que nao esta disponivel naquela data
 * @author Andre Luis
 *
 */

public class SalaIndisponivelException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public SalaIndisponivelException(String msg) {
		super(msg);
	}

}

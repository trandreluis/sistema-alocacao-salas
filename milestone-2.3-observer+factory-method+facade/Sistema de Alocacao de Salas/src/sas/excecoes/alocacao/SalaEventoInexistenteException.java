package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tenta realizar alguma operacao com um evento
 * ou sala que nao existe
 * 
 * @author Andre Luis
 *
 */

public class SalaEventoInexistenteException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public SalaEventoInexistenteException(String msg) {
		super(msg);
	}

}

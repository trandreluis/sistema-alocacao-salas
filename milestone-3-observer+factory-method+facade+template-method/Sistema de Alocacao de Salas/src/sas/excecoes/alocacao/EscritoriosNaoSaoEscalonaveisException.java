package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tentar alocar um escritorio
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class EscritoriosNaoSaoEscalonaveisException extends
		RoomsAllocationException {

	/**
	 * Metodo construtor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public EscritoriosNaoSaoEscalonaveisException(String msg) {
		super(msg);
	}

}

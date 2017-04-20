package sas.excecoes.alocacao;

/**
 * Excecao que e lancada quando se tentar alocar um laboratorio aberto
 * @author Andre Luis
 *
 */

public class LaboratoriosAbertosNaoSaoEscalonaveisException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * @param msg Mensagem a ser exibida quando a excecao for lancada
	 */
	
	public LaboratoriosAbertosNaoSaoEscalonaveisException(String msg) {
		super(msg);
	}

}

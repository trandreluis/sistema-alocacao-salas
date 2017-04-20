package sas.excecoes.alocacao;

/**
 * Super Excecao, da qual todas as outras deste projeto estendem
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class RoomsAllocationException extends Exception {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public RoomsAllocationException(String msg) {
		super(msg);
	}

}

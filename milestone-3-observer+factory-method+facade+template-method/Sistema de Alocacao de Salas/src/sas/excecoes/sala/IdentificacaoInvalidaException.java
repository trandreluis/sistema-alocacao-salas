package sas.excecoes.sala;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Execao que e lancada quando se tenta realizar alguma operacao passando um ID
 * invalido
 * 
 * @author Andre Luis
 *
 */

@SuppressWarnings("serial")
public class IdentificacaoInvalidaException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public IdentificacaoInvalidaException(String msg) {
		super(msg);
	}

}

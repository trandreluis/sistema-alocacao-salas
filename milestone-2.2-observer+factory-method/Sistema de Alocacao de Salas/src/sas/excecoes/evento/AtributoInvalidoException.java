package sas.excecoes.evento;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Excecao que e lancada quando se passa um atributo invalido em um metodo de
 * cadastro
 * 
 * @author André Luís
 *
 */

public class AtributoInvalidoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public AtributoInvalidoException(String msg) {
		super(msg);
	}

}

package sas.excecoes.evento;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Excecao que e lancada quando se tenta cadastrar um evento passando um
 * intervalo de datas invalido
 * 
 * @author Andre Luis
 *
 */

public class IntervaloDeDatasInvalidoException extends RoomsAllocationException {

	/**
	 * Metodo contrutor da Exception
	 * 
	 * @param msg
	 *            Mensagem a ser exibida quando a excecao for lancada
	 */

	public IntervaloDeDatasInvalidoException(String msg) {
		super(msg);
	}

}

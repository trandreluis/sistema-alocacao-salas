package sas.observer.interfaces;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Interface referente a implementacao do Padrao de Projeto Observer, esta
 * define o metodo inerente a um Observador.
 * 
 * @author Andre Luis
 */

public interface Observador {

	/**
	 * Metodo que recebe a notificacao das alteracoes que ocorrem nos sujeitos
	 * em que o observador esta cadastrado.
	 * 
	 * @param id
	 *            ID de algum objeto (como uma Sala ou Evento) que tenha sido
	 *            removido do sistema.
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso os metodos de validacao
	 *             identifiquem algo de errado.
	 */

	public void notificado(String id) throws RoomsAllocationException;

}

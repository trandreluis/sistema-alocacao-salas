package sas.observer.interfaces;

import sas.excecoes.alocacao.RoomsAllocationException;

/**
 * Interface referente a implementacao do Padrao de Projeto Observer, esta
 * define os metodos inerentes a um Subject.
 * 
 * @author Andre Luis
 */

public interface Sujeito {

	/**
	 * Metodo responsavel por cadastrar um novo observador ao sujeito que venha
	 * a implementar esta interface.
	 * 
	 * @param o
	 *            Observador a ser cadastrado.
	 */

	public void adicionar(Observador o);

	/**
	 * Metodo responsavel por notificar os observadores que se cadastraram para
	 * receber atualizacoes.
	 * 
	 * @param id
	 *            ID de algum objeto (como uma Sala ou Evento) que tenha sido
	 *            removido do sistema.
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso os metodos de validacao
	 *             identifiquem algo de errado.
	 */

	public void notifica(String id) throws RoomsAllocationException;

	/**
	 * Metodo responsavel por descadastrar um observador.
	 * 
	 * @param o
	 *            Observador a ser removido.
	 */

	public void remover(Observador o);

}

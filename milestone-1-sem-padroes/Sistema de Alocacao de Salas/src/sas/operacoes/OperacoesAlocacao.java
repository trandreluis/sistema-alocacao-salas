package sas.operacoes;

import java.util.ArrayList;

import sas.dao.AlocacaoDao;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Alocacao;
import sas.validacao.ValidacaoAlocacao;

/**
 * Classe responsavel pelas operacoes logicas de manipulacao de Alocacoes
 * 
 * @author Andre Luis
 *
 */

public class OperacoesAlocacao {

	Alocacao alocacao = null;
	AlocacaoDao crudAlocacao = null;
	ArrayList<Alocacao> alocacoes = null;
	ValidacaoAlocacao validacao = new ValidacaoAlocacao();

	/**
	 * Metodo responsavel por cadastrar uma alocacao (Alocar um Evento a uma
	 * Sala criara uma Alocacao)
	 * 
	 * @param idEvento
	 *            ID do evento a ser alocado na sala
	 * @param idSala
	 *            ID da sala que recebera o Evento
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso as informacoes inseridas
	 *             estejam incorretas
	 */

	public void alocarEvento(String idEvento, String idSala)
			throws RoomsAllocationException {

		validacao = new ValidacaoAlocacao();
		validacao.alocarEvento(idEvento, idSala);

		alocacao = new Alocacao();
		alocacao.setIdEvento(idEvento);
		alocacao.setIdSala(idSala);

		crudAlocacao = new AlocacaoDao();
		crudAlocacao.salvar(alocacao);

	}

	/**
	 * Metodo responsavel por buscar na classe de manipulacao do banco, a lista
	 * de Alocacoes
	 * 
	 * @return Lista de Alocacoes
	 */

	public ArrayList<Alocacao> listarAlocacoes() {

		alocacoes = new ArrayList<>();
		crudAlocacao = new AlocacaoDao();

		alocacoes = crudAlocacao.buscarAlocacoes();

		return alocacoes;

	}

	/**
	 * Metodo responsavel por obter o ID de uma Sala
	 * 
	 * @param idEvento
	 *            ID do Evento atrelado (alocado) em tal sala
	 * @return ID da sala correspondente
	 */

	public String getIdSala(String idEvento) {

		String valorAtributo;
		crudAlocacao = new AlocacaoDao();
		valorAtributo = crudAlocacao.getIdSalaAlocacao(idEvento);

		return valorAtributo;

	}

	/**
	 * Metodo responsavel por obter o ID de um Evento
	 * 
	 * @param idEvento
	 *            ID da Sala que recebe o Evento
	 * @return ID do Evento correspondente
	 */

	public String getIdEvento(String idSala) {

		String valorAtributo;
		crudAlocacao = new AlocacaoDao();
		valorAtributo = crudAlocacao.getIdEventoAlocacao(idSala);

		return valorAtributo;

	}

	/**
	 * Metodo responsavel por obter Alocacoes que se encaixem nos criterios de
	 * vusca dos parametros
	 * 
	 * @param nome
	 *            Atributo a ser analisado
	 * @param valor
	 *            Valor que o atributo (nome) devera possuir para entrar na
	 *            lista
	 * @return
	 * @throws RoomsAllocationException
	 */

	public String localizarEvento(String nome, String valor)
			throws RoomsAllocationException {

		validacao.localizaEvento(nome, valor);

		crudAlocacao = new AlocacaoDao();
		String atrib = crudAlocacao.localizaEvento(nome, valor);

		return atrib;

	}

	/**
	 * Metodo que verifica a existencia de uma Alocacao
	 * 
	 * @param idEvento
	 *            ID do Evento possivelmente atrelado a Alocacao
	 * @return Se a alocacao existe ou nao
	 */

	public boolean verificarExistencia(String idEvento) {

		crudAlocacao = new AlocacaoDao();
		boolean s = crudAlocacao.verificaExistencia(idEvento);

		return s;
	}

	/**
	 * Metodo que verifica a existencia de uma Alocacao
	 * 
	 * @param idSala
	 *            ID da Sala possivelmente atrelada a Alocacao
	 * @return Se a Alocacao existe ou nao
	 */

	public boolean verificarExistenciaSala(String idSala) {

		crudAlocacao = new AlocacaoDao();
		boolean s = crudAlocacao.verificaExistenciaSala(idSala);

		return s;
	}

	/**
	 * Metodo responsavel excluir uma alocacao (desalocar um Evento de uma Sala)
	 * 
	 * @param idEvento
	 *            ID do Evento atrelado a possivel Alocacao
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso as informacoes inseridas
	 *             estejam incorretas
	 */

	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException {

		validacao.desalocarEvento(idEvento);

		crudAlocacao = new AlocacaoDao();
		crudAlocacao.removerAlocacao(idEvento);

	}

}

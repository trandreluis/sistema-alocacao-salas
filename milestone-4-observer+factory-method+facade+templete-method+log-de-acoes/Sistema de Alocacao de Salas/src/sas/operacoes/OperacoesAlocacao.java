package sas.operacoes;

import java.util.ArrayList;

import sas.dao.AlocacaoDao;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Alocacao;
import sas.validacao.ValidacaoAlocacao;
import sas.observer.interfaces.Observador;
import sas.observer.interfaces.Sujeito;

/**
 * Classe responsavel pelas operacoes logicas de manipulacao de Alocacoes
 * 
 * @author Andre Luis
 *
 */

public class OperacoesAlocacao implements Observador {

	private Alocacao alocacao;
	private AlocacaoDao crudAlocacao;
	private OperacoesEvento operacoesEvento = new OperacoesEvento();
	private ArrayList<Alocacao> alocacoes;
	private ValidacaoAlocacao validacao = new ValidacaoAlocacao();
	private Sujeito opSalas;
	private Sujeito opEventos;

	public OperacoesAlocacao(Sujeito opSalas, Sujeito opEventos) {

		this.opSalas = opSalas;
		this.opSalas.adicionar(this);
		this.opEventos = opEventos;
		this.opEventos.adicionar(this);
	
	}

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

		alocacoes = new ArrayList<>();
		crudAlocacao = new AlocacaoDao();

		alocacoes = crudAlocacao.buscarAlocacoes();
		
		validacao.alocarEvento(operacoesEvento.listarEventos(), alocacoes, idSala, idEvento);

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
	 * @param idSala
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
	 * busca dos parametros
	 * 
	 * @param nome
	 *            Atributo a ser analisado
	 * @param valor
	 *            Valor que o atributo (nome) devera possuir para entrar na
	 *            lista
	 * @return String contendo os pares de eventos alocados que se encaixem na
	 *         busca (ex.: SA-01:EV-02).
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso os dados passados sejam
	 *             icoerentes
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
	
	public Alocacao buscarAlocacao(String idEventoAlocado) {
		
		crudAlocacao = new AlocacaoDao();
		
		return crudAlocacao.buscarPorID(idEventoAlocado);
		
	}

	@Override
	public void notificado(String id) throws RoomsAllocationException {

		if (verificarExistencia(id)) {
			desalocarEvento(id);
		} else if (verificarExistenciaSala(id)) {
			desalocarEvento(getIdEvento(id));
		}

	}

}

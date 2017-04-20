package sas.operacoes;

import java.util.ArrayList;

import sas.dao.EventoDao;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Evento;
import sas.validacao.ValidacaoEvento;
import sas.observer.interfaces.Observador;
import sas.observer.interfaces.Sujeito;

;

/**
 * Classe responsavel pelas operacoes logicas de manipulacao de Eventos
 * 
 * @author Andre Luis
 *
 */

public class OperacoesEvento implements Sujeito {

	private Evento novoEvento;
	private EventoDao crudEvento;
	private ArrayList<Evento> eventos;
	private ArrayList<Observador> observadores = new ArrayList<>();

	ValidacaoEvento validacao = new ValidacaoEvento();

	/**
	 * Metodo responsavel por adicionar um evento passando as caracteristicas
	 * minimas obrigatorias
	 * 
	 * @param id
	 *            ID que o Evento tera
	 * @param nome
	 *            Nome que o evento tera
	 * @param inicio
	 *            Data e hora de inicio do novo Evento
	 * @param fim
	 *            Data e hora de fim do novo Evento
	 * @param area
	 *            Area do Evento
	 * @param contato
	 *            Responsavel pelo Evento
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes inseridas
	 *             nao estejam corretas
	 */

	public void adicionarEventoBasico(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException {

		validacao.adicionarEventoBasico(id, nome, inicio, fim, area, contato);

		novoEvento = new Evento();
		novoEvento.setIdEvento(id);
		novoEvento.setNome(nome);
		novoEvento.setInicio(inicio);
		novoEvento.setFim(fim);
		novoEvento.setArea(area);
		novoEvento.setContato(contato);

		crudEvento = new EventoDao();
		crudEvento.salvar(novoEvento);

	}

	/**
	 * Metodo responsavel por adicionar um evento passando as caracteristicas
	 * completas
	 * 
	 * @param id
	 *            ID que o Evento tera
	 * @param nome
	 *            Nome que o evento tera
	 * @param inicio
	 *            Data e hora de inicio do novo Evento
	 * @param fim
	 *            Data e hora de fim do novo Evento
	 * @param area
	 *            Area do Evento
	 * @param contato
	 *            Responsavel pelo Evento
	 * @param repeticoes
	 *            Numero de vezes que o evento se repetira (as repeticoes sao
	 *            semanalmente: ex.: esta terca e terca da semana que vem, isso
	 *            para um evento de uma repeticao)
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes inseridas
	 *             nao estejam corretas
	 */

	public void adicionarEventoCompleto(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException {

		validacao.adicionarEventoCompleto(id, nome, inicio, fim, area, contato,
				repeticoes);

		novoEvento = new Evento();
		novoEvento.setIdEvento(id);
		novoEvento.setNome(nome);
		novoEvento.setInicio(inicio);
		novoEvento.setFim(fim);
		novoEvento.setArea(area);
		novoEvento.setContato(contato);
		novoEvento.setRepeticoes(repeticoes);

		crudEvento = new EventoDao();
		crudEvento.salvar(novoEvento);

	}

	/**
	 * Metodo responsavel por obter a lista de Eventos cadastrados
	 * 
	 * @return Lista de Eventos existentes
	 */

	public ArrayList<Evento> listarEventos() {

		crudEvento = new EventoDao();
		eventos = crudEvento.buscarEventosCadastrados();

		return eventos;

	}

	/**
	 * Metodo responsavel por buscar um Evento passando seu ID
	 * 
	 * @param idEvento
	 *            ID do Evento a ser buscado
	 * @return Evento procurado
	 */

	public Evento buscarEvento(String idEvento) {

		novoEvento = new Evento();
		crudEvento = new EventoDao();
		novoEvento = crudEvento.buscarPorID(idEvento);

		return novoEvento;

	}

	/**
	 * Metodo responsavel por obter o atributo de um Evento
	 * 
	 * @param idEvento
	 *            ID do Evento que tera o atributo retornado
	 * @param atributo
	 *            Atributo do buscado
	 * @return Valor do atributo procurado
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes passadas
	 *             estejam incorretas
	 */

	public String getAtributo(String idEvento, String atributo)
			throws RoomsAllocationException {

		validacao.getAtributoEvento(idEvento, atributo);

		String valorAtributo;
		crudEvento = new EventoDao();
		valorAtributo = crudEvento.getAtributoEvento(idEvento, atributo);

		return valorAtributo;

	}

	/**
	 * Metodo responsavel por verificar a existencia de um Evento
	 * 
	 * @param idEvento
	 *            ID do Evento a ser veificado
	 * @return Se o evento existe ou nao
	 */

	public boolean verificarExistencia(String idEvento) {

		crudEvento = new EventoDao();
		boolean existe = crudEvento.verificaExistencia(idEvento);
		return existe;

	}

	/**
	 * Metodo responsavel por excluir um Evento (cancelar)
	 * 
	 * @param idEvento
	 *            ID do Evento a ser cancelado
	 * @throws RoomsAllocationException
	 *             Excecao que pode ocorrer, caso o Evento nao exista
	 */

	public void cancelarEvento(String idEvento) throws RoomsAllocationException {

		validacao.cancelarEvento(idEvento);

		crudEvento = new EventoDao();
		crudEvento.removerEvento(idEvento);

		notifica(idEvento);

	}

	@Override
	public void adicionar(Observador o) {

		if (!observadores.contains(o)) {
			observadores.add(o);
		}

	}

	@Override
	public void notifica(String id) throws RoomsAllocationException {

		if (observadores.size() > 0) {
			for (int i = 0; i < observadores.size(); i++) {
				observadores.get(i).notificado(id);
			}
		}

	}

	@Override
	public void remover(Observador o) {

		if (observadores.contains(o)) {
			int i = observadores.indexOf(o);
			observadores.remove(i);
		}

	}

}

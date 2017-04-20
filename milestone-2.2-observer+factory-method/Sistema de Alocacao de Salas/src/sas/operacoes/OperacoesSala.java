package sas.operacoes;

import java.util.ArrayList;

import sas.dao.SalaDao;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Sala;
import sas.validacao.ValidacaoSala;
import sas.observer.interfaces.Observador;
import sas.observer.interfaces.Sujeito;

/**
 * Classe responsavel pelas operacoes logicas de manipulacao de Salas
 * 
 * @author Andre Luis
 *
 */

public class OperacoesSala implements Sujeito {

	Sala novaSala = null;
	SalaDao crudSala = null;
	ArrayList<Sala> salas = null;
	ValidacaoSala validacao = new ValidacaoSala();
	ArrayList<Observador> observadores = new ArrayList<>();

	/**
	 * Metodo resposavel por adicionar uma Sala
	 * 
	 * @param id
	 *            ID que a sala tera
	 * @param capacidade
	 *            Capacidade que a Sala tera
	 * @param finalidade
	 *            Finalidade que a Sala tera
	 * @param tipo
	 *            Tipo que a Sala sera
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancadacaso as infromacoes passadas nao
	 *             sejam validas
	 */

	public void adicionarSalaBasico(String id, int capacidade,
			String finalidade, String tipo) throws RoomsAllocationException {

		validacao.adicionarSalaBasico(id, capacidade, finalidade, tipo);

		novaSala = new Sala();
		novaSala.setIdSala(id);
		novaSala.setCapacidade(capacidade);
		novaSala.setFinalidade(finalidade);
		novaSala.setTipo(tipo);

		crudSala = new SalaDao();
		crudSala.salvar(novaSala);

	}

	/**
	 * Metodo resposavel por adicionar uma Sala
	 * 
	 * @param id
	 *            ID que a sala tera
	 * @param capacidade
	 *            Capacidade que a Sala tera
	 * @param finalidade
	 *            Finalidade que a Sala tera
	 * @param tipo
	 *            Tipo que a Sala sera
	 * @param apelido
	 *            Apelido que a Sala tera
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancadacaso as infromacoes passadas nao
	 *             sejam validas
	 */

	public void adicionarSalaMedio(String id, int capacidade,
			String finalidade, String tipo, String apelido)
			throws RoomsAllocationException {

		validacao.adicionarSalaMedio(id, capacidade, finalidade, tipo, apelido);

		novaSala = new Sala();
		novaSala.setIdSala(id);
		novaSala.setCapacidade(capacidade);
		novaSala.setFinalidade(finalidade);
		novaSala.setTipo(tipo);
		novaSala.setApelido(apelido);

		crudSala = new SalaDao();
		crudSala.salvar(novaSala);

	}

	/**
	 * Metodo resposavel por adicionar uma Sala
	 * 
	 * @param id
	 *            ID que a sala tera
	 * @param capacidade
	 *            Capacidade que a Sala tera
	 * @param finalidade
	 *            Finalidade que a Sala tera
	 * @param tipo
	 *            Tipo que a Sala sera
	 * @param apelido
	 *            Apelido que a Sala tera
	 * @param aberto
	 *            Se a sala e ou nao aberta
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancadacaso as infromacoes passadas nao
	 *             sejam validas
	 */

	public void adicionarSalaCompleto(String id, int capacidade,
			String finalidade, String tipo, String apelido, boolean aberto)
			throws RoomsAllocationException {

		validacao.adicionarSalaCompleto(id, capacidade, finalidade, tipo,
				apelido, aberto);

		novaSala = new Sala();
		novaSala.setIdSala(id);
		novaSala.setCapacidade(capacidade);
		novaSala.setFinalidade(finalidade);
		novaSala.setTipo(tipo);
		novaSala.setApelido(apelido);
		novaSala.setAberta(aberto);

		crudSala = new SalaDao();
		crudSala.salvar(novaSala);

	}

	/**
	 * Metodo que retorna a lista de Salas cadastradas
	 * 
	 * @return Lista de Salas cadastradas
	 */

	public ArrayList<Sala> listarSalas() {

		crudSala = new SalaDao();
		salas = crudSala.buscarSalasCadastradas();

		return salas;

	}

	/**
	 * Metodo que procura por uma Sala especifica
	 * 
	 * @param idSala
	 *            ID da sala buscada
	 * @return Sala encontrada
	 */

	public Sala buscarSala(String idSala) {

		novaSala = new Sala();
		crudSala = new SalaDao();
		novaSala = crudSala.buscarPorID(idSala);

		return novaSala;

	}

	/**
	 * Metodo para verificar existencia de uma Sala
	 * 
	 * @param idSala
	 *            ID da Sala a ser verificada
	 * @return Se a Sala existe ou nao
	 */

	public boolean verificarExistencia(String idSala) {

		crudSala = new SalaDao();
		boolean existe = crudSala.verificaExistencia(idSala);

		return existe;

	}

	/**
	 * Metodo para pegar atributos de uma Sala
	 * 
	 * @param idSala
	 *            ID da sala que tera os atributos buscados
	 * @param atributo
	 *            Atributo buscado da Sala
	 * @return Valor do atributo procurado
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso os atributos passados nao
	 *             sejam validos
	 */

	public String getAtributo(String idSala, String atributo)
			throws RoomsAllocationException {

		validacao.getAtributoSala(idSala, atributo);

		String valorAtributo;
		crudSala = new SalaDao();
		valorAtributo = crudSala.getAtributoSala(idSala, atributo);

		return valorAtributo;

	}

	/**
	 * Metodo para remover uma Sala
	 * 
	 * @param idSala
	 *            ID da Sala que sera removida
	 * @throws RoomsAllocationException
	 *             Excecao que podera ser lancada caso as infrmacoes passadas
	 *             nao sejam validas
	 */

	public void removerSala(String idSala) throws RoomsAllocationException {

		validacao.removerSala(idSala);

		crudSala = new SalaDao();
		crudSala.removerSala(idSala);

		notifica(idSala);

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

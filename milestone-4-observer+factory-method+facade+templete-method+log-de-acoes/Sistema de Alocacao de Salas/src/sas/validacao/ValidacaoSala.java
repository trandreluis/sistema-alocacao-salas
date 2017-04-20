package sas.validacao;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.sala.IdSalaDuplicadaException;
import sas.excecoes.sala.IdentificacaoInvalidaException;
import sas.excecoes.sala.SalaInexistenteException;
import sas.excecoes.sala.TipoInvalidoException;
import sas.operacoes.OperacoesSala;

/**
 * Classe responsavel pela validacao das operacoes que envolvem Salas
 * 
 * @author Andre Luis
 *
 */

public class ValidacaoSala {

	private OperacoesSala ops;

	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema * @param id
	 * 
	 * @param id
	 *            ID que a sala tera
	 * 
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
		ops = new OperacoesSala();
		if (id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (id.equalsIgnoreCase("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException(
					"Ja existe sala com esta identificacao.");
		} else if (tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		} else if (finalidade.equalsIgnoreCase("Sala de Aula")) {
			if (!tipo.equalsIgnoreCase("Normal") && !tipo.equalsIgnoreCase("Inteligente")
					&& !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Laboratorio")) {
			if (!tipo.equals("Quimica") && !tipo.equals("quimica")
					&& !tipo.equalsIgnoreCase("fisica") && !tipo.equalsIgnoreCase("biologia")
					&& !tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Escritorio")) {
			if (!tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Sala de Conferencia")) {
			if (!tipo.equalsIgnoreCase("Normal") && !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}

	}

	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema * @param id
	 * 
	 * @param id
	 *            ID que a sala tera
	 * 
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
		ops = new OperacoesSala();
		if (id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (id.equalsIgnoreCase("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException(
					"Ja existe sala com esta identificacao.");
		} else if (tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		} else if (finalidade.equalsIgnoreCase("Sala de Aula")) {
			if (!tipo.equalsIgnoreCase("Normal") && !tipo.equalsIgnoreCase("Inteligente")
					&& !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Laboratorio")) {
			if (!tipo.equalsIgnoreCase("Quimica") && !tipo.equalsIgnoreCase("quimica")
					&& !tipo.equalsIgnoreCase("fisica") && !tipo.equalsIgnoreCase("biologia")
					&& !tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Escritorio")) {
			if (!tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Sala de Conferencia")) {
			if (!tipo.equalsIgnoreCase("Normal") && !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}

	}

	/**
	 * Metodo responsavel por validar a adicao de Salas no sistema * @param id
	 * 
	 * @param id
	 *            ID que a sala tera
	 * 
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
		ops = new OperacoesSala();
		if (id == null) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (id.equalsIgnoreCase("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (ops.verificarExistencia(id)) {
			throw new IdSalaDuplicadaException(
					"Ja existe sala com esta identificacao.");
		} else if (tipo == null) {
			throw new TipoInvalidoException("Tipo invalido.");
		} else if (finalidade.equalsIgnoreCase("Sala de Aula")) {
			if (!tipo.equalsIgnoreCase("Normal") && !tipo.equalsIgnoreCase("Inteligente")
					&& !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Laboratorio")) {
			if (!tipo.equalsIgnoreCase("Quimica") && !tipo.equalsIgnoreCase("quimica")
					&& !tipo.equalsIgnoreCase("fisica") && !tipo.equalsIgnoreCase("biologia")
					&& !tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Escritorio")) {
			if (!tipo.equalsIgnoreCase("computacao")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		} else if (finalidade.equalsIgnoreCase("Sala de Conferencia")) {
			if (!tipo.equals("Normal") && !tipo.equalsIgnoreCase("Videoconferencia")) {
				throw new TipoInvalidoException("Tipo invalido.");
			}
		}

	}

	/**
	 * Metodo responsavel por validar as entradas que o getAtributo() recebe
	 * 
	 * @param idSala
	 *            ID da sala que tera os atributos buscados
	 * @param atributo
	 *            Atributo buscado da Sala
	 *
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso os atributos passados nao
	 *             sejam validos
	 */

	public void getAtributoSala(String idSala, String atributo)
			throws RoomsAllocationException {
		ops = new OperacoesSala();
		if (!ops.verificarExistencia(idSala)) {
			throw new SalaInexistenteException("Sala nao existe.");
		}
	}

	/**
	 * Metodo responsavel por validar a remocao de Salas no sistema
	 * 
	 * @param idSala
	 *            ID da sala que sera removida
	 * 
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada caso os atributos passados nao
	 *             sejam validos
	 */

	public void removerSala(String idSala) throws RoomsAllocationException {

		ops = new OperacoesSala();

		if (!ops.verificarExistencia(idSala)) {
			throw new SalaInexistenteException("Sala nao existe.");
		}

	}

}

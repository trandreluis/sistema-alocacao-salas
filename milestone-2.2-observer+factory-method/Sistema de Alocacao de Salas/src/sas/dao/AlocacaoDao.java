package sas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sas.objetos.Alocacao;
import sas.objetos.Evento;

/**
 * Classe responsavel pela manipulacao das alocacoes
 * 
 * @author Andre Luis
 *
 */

public class AlocacaoDao extends GenericDao {

	EventoDao evdao = null;

	/**
	 * Metodo responsavel por salvar uma Alocacao
	 * 
	 * @param alocacao
	 *            Objeto Alocacao que sera salvo no banco
	 */

	public void salvar(Alocacao alocacao) {

		String sql = "INSERT INTO ALOCADOS (id_evento, id_sala)"
				+ "VALUES (?,?)";

		super.salvar(sql, alocacao.getIdEvento(), alocacao.getIdSala());

	}

	/**
	 * 
	 * @return Retorna uma lista contendo as alocacoes cadastradas no banco.
	 */

	public ArrayList<Alocacao> buscarAlocacoes() {

		ArrayList<Alocacao> alocacoes = new ArrayList<>();

		String sql = "SELECT * FROM ALOCADOS";
		Alocacao alocacao = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				alocacao = new Alocacao();
				alocacao.setIdSala(rs.getString("id_sala"));
				alocacao.setIdEvento(rs.getString("id_evento"));
				alocacoes.add(alocacao);
			}

			rs.close();
			stmt.close();
			return alocacoes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por obter um ID de uma sala em uma Alocacao
	 * 
	 * @param idEvento
	 *            ID de um evento que esteja alocado
	 * @return: Retorna o ID da sala correspondente na alocacao (obs.: uma
	 *          alocacao e composta por um ID-SALA e por um ID-EVENTO)
	 */

	public String getIdSalaAlocacao(String idEvento) {

		String sql = "SELECT * FROM ALOCADOS WHERE id_evento = (?)";

		String valorAtributo = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idEvento);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				valorAtributo = (rs.getString("id_sala"));
			}

			rs.close();
			stmt.close();
			return valorAtributo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por obter o ID de um evento em uma alocacao
	 * 
	 * @param idSala
	 *            ID de uma sala que esteja alocada
	 * @return: ID de um evento que esteja alocado na sala que tem seu ID
	 *          passado como parametro
	 */

	public String getIdEventoAlocacao(String idSala) {

		String sql = "SELECT * FROM ALOCADOS WHERE id_sala = (?)";

		String valorAtributo = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idSala);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				valorAtributo = (rs.getString("id_evento"));
			}

			rs.close();
			stmt.close();
			return valorAtributo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param nome
	 *            Pode ser um dos atributos que um Evento contenha (repeticoes,
	 *            inicio, fim, horario e etc...)
	 * @param valor
	 *            O valor que o atributo correspondente ao parametro "nome" deve
	 *            possuir para se encaixar na busca (ex.: o "valor" caso o
	 *            atributo "fim" seja passado no parametro "nome" pode ser
	 *            30/01/2016 12:09)
	 * @return Uma lista contendo todas as alocacoes que se encaixam nos
	 *         parametros passados (ex.: "SA-01:EV-05, SA-92:EV-12")
	 */

	public String localizaEvento(String nome, String valor) {

		ArrayList<String> idsEventos = new ArrayList<>();
		ArrayList<String> idsGrupos = new ArrayList<>();

		idsEventos = getIds(nome, valor);

		for (String id : idsEventos) {
			String temp = getIdSalaAlocacao(id);
			idsGrupos.add(temp + ":" + id);
		}

		String ret = "";

		if (idsGrupos.isEmpty()) {
			return "Nenhum evento encontrado.";
		}

		String temporaria = idsGrupos.toString();
		ret = temporaria.substring(1, temporaria.length() - 1);

		return ret;

	}

	/**
	 * Metodo responsavel por verificar se um Evento esta ou nao alocado
	 * 
	 * @param idEvento
	 *            ID de um evento (ex.: EV-02)
	 * @return verdadeiro (true) caso o evento esteja em uma alocacao (alocado)
	 *         e falso (false) caso o mesmo nao esteja alocado
	 */

	public boolean verificaExistencia(String idEvento) {

		ArrayList<Alocacao> alocados = new ArrayList<>();

		alocados = buscarAlocacoes();

		for (Alocacao a : alocados) {

			if (a.getIdEvento().equals(idEvento)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Metodo responsavel por verificar se uma sala esta ou nao alocada
	 * 
	 * @param idSala
	 *            ID de uma sala (ex.: SA-08)
	 * @return verdadeiro (true) caso a sala esteja em uma alocacao (alocado) e
	 *         falso (false) caso a mesmo nao esteja alocada
	 */

	public boolean verificaExistenciaSala(String idSala) {

		ArrayList<Alocacao> alocados = new ArrayList<>();

		alocados = buscarAlocacoes();

		for (Alocacao a : alocados) {

			if (a.getIdSala().equals(idSala)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Metodo auxiliar do localizaEvento(), responsavel por pegar os ID's de
	 * eventos que que se encaixem na busca
	 * 
	 * @param atributo
	 *            Atributo que um evento contem, como por exemplo: repeticoes,
	 *            nome, area, contato e etc...
	 * @param valor
	 *            Valor que o atributo passado como parametro deve possuir para
	 *            entrar na lista
	 * @return Lista contendo os ID's de todos os evento que se encaixem na
	 *         busca
	 */

	public ArrayList<String> getIds(String atributo, String valor) {

		ArrayList<String> idsLocalizados = new ArrayList<>();
		ArrayList<Evento> eventosLocalizados = new ArrayList<>();

		evdao = new EventoDao();

		eventosLocalizados = evdao.buscarEventosCadastrados();

		if (atributo.equals("nome")) {
			for (Evento e : eventosLocalizados) {
				if (e.getNome().contains(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("inicio")) {
			for (Evento e : eventosLocalizados) {
				if (e.getInicio().contains(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("fim")) {
			for (Evento e : eventosLocalizados) {
				if (e.getFim().contains(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("area")) {
			for (Evento e : eventosLocalizados) {
				if (e.getArea().contains(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("contato")) {
			for (Evento e : eventosLocalizados) {
				if (e.getContato().contains(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("repeticoes")) {
			for (Evento e : eventosLocalizados) {
				if (e.getRepeticoes() == Integer.parseInt(valor)) {
					if (verificaExistencia(e.getIdEvento())) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		} else if (atributo.equals("horario")) {
			for (Evento e : eventosLocalizados) {
				if (verificaExistencia(e.getIdEvento())) {
					SimpleDateFormat formato = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm");

					Date inicio = null;
					Date fim = null;
					Date valorDate = null;

					try {
						inicio = (Date) formato.parse(e.getInicio());
						fim = (Date) formato.parse(e.getFim());
						valorDate = (Date) formato.parse(valor);
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
					if (valorDate.equals(inicio)
							|| valorDate.equals(fim)
							|| (valorDate.after(inicio) && valorDate
									.before(fim))) {
						idsLocalizados.add(e.getIdEvento());
					}
				}
			}
		}

		return idsLocalizados;
	}

	/**
	 * Metodo responsavel por remover uma Alocacao do banco de dados
	 * 
	 * @param idEvento
	 *            Parametro a ser passado na query de busca para que a exclusao
	 *            aconteca
	 */

	public void removerAlocacao(String idEvento) {
		String sql = "DELETE FROM ALOCADOS WHERE id_evento = (?)";
		super.apagar(sql, idEvento);
	}

}

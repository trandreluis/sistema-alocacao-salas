package sas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sas.objetos.Evento;

/**
 * Classe responsavel pela persistencia dos eventos
 * 
 * @author Andre Luis
 *
 */

public class EventoDao extends GenericDao {

	/**
	 * Metodo resposavel por salvar um Evento
	 * 
	 * @param evento
	 *            Objeto Evento que sera salvo no banco
	 */

	public void salvar(Evento evento) {

		String sql = "INSERT INTO EVENTOS (id_evento, nome, inicio, fim, area, contato, repeticoes, tipo)"
				+ "VALUES (?,?,?,?,?,?,?,?)";

		super.salvar(sql, evento.getIdEvento(), evento.getNome(),
				evento.getInicio(), evento.getFim(), evento.getArea(),
				evento.getContato(), evento.getRepeticoes(), evento.getTipoRepeticao());
		
	}

	/**
	 * Metodo que realiza uma busca na tabela Eventos pelo ID
	 * 
	 * @param idEvento
	 *            ID de um Evento (ex.: EV-12)
	 * @return Retorna um objeto que possui o ID igual ao passado como parametro
	 */

	public Evento buscarPorID(String idEvento) {

		String sql = "SELECT * FROM EVENTOS WHERE id_evento = (?)";
		Evento evento = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idEvento);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				evento = new Evento();
				evento.setIdEvento(rs.getString("id_evento"));
				evento.setNome(rs.getString("nome"));
				evento.setInicio(rs.getString("inicio"));
				evento.setFim(rs.getString("fim"));
				evento.setArea(rs.getString("area"));
				evento.setContato(rs.getString("contato"));
				evento.setRepeticoes(rs.getInt("repeticoes"));
				evento.setTipoRepeticao(rs.getString("tipo"));
			}

			rs.close();
			stmt.close();
			return evento;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por buscar todos os eventos cadastrados
	 * 
	 * @return Lista de eventos cadastrados
	 */

	public ArrayList<Evento> buscarEventosCadastrados() {

		ArrayList<Evento> eventos = new ArrayList<>();

		String sql = "SELECT * FROM EVENTOS";
		Evento evento = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				evento = new Evento();
				evento.setIdEvento(rs.getString("id_evento"));
				evento.setNome(rs.getString("nome"));
				evento.setInicio(rs.getString("inicio"));
				evento.setFim(rs.getString("fim"));
				evento.setArea(rs.getString("area"));
				evento.setContato(rs.getString("contato"));
				evento.setRepeticoes(rs.getInt("repeticoes"));
				evento.setTipoRepeticao(rs.getString("tipo"));
				eventos.add(evento);
			}

			rs.close();
			stmt.close();
			return eventos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por pegar atributo de um evento
	 * 
	 * @param idEvento
	 *            ID do evento que tera o atributo retornado
	 * @param atributo
	 *            Atributo do evento que sera retornado
	 * @return Atributo retornado
	 */

	public String getAtributoEvento(String idEvento, String atributo) {

		String sql = "SELECT * FROM EVENTOS WHERE id_evento = (?)";

		String valorAtributo = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idEvento);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				valorAtributo = (rs.getString(atributo));
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
	 * Metodo responsavel por verificar existencia de um Evento
	 * 
	 * @param idEvento
	 *            ID do Evento a ser testado
	 * @return Se o evento com o ID passado no parametro existe ou nao
	 */

	public boolean verificaExistencia(String idEvento) {

		ArrayList<Evento> eventos = new ArrayList<>();

		eventos = buscarEventosCadastrados();

		for (Evento ev : eventos) {

			if (ev.getIdEvento().equals(idEvento)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Metodo responsavel por apagar um Evento
	 * 
	 * @param idEvento
	 *            ID do Evento que sera apagado
	 */

	public void removerEvento(String idEvento) {
		String sql = "DELETE FROM EVENTOS WHERE id_evento = (?)";
		super.apagar(sql, idEvento);
	}

}

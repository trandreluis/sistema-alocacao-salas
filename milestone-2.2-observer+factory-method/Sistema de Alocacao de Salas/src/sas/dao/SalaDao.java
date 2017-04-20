package sas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sas.objetos.Sala;

/**
 * Classe responsavel por monipular a persistencia das Salas
 * 
 * @author Andre Luis
 *
 */

public class SalaDao extends GenericDao {

	/**
	 * Metodo para salvar uma Sala
	 * 
	 * @param sala
	 *            Objeto a ser salvo no Banco
	 */

	public void salvar(Sala sala) {

		String sql = "INSERT INTO SALAS (id_sala, capacidade, finalidade, tipo, apelido, aberta)"
				+ "VALUES (?,?,?,?,?,?)";

		super.salvar(sql, sala.getIdSala(), sala.getCapacidade(),
				sala.getFinalidade(), sala.getTipo(), sala.getApelido(),
				sala.isAberta());

	}

	/**
	 * Metodo de busca por ID
	 * 
	 * @param idSala
	 *            ID de uma Sala a ser buscada
	 * @return Objeto Sala encontrado
	 */

	public Sala buscarPorID(String idSala) {
		String sql = "SELECT * FROM SALAS WHERE id_sala = (?)";
		Sala sala = null;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idSala);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				sala = new Sala();
				sala.setIdSala(rs.getString("id_sala"));
				sala.setCapacidade(rs.getInt("capacidade"));
				sala.setFinalidade(rs.getString("finalidade"));
				sala.setTipo(rs.getString("tipo"));
				sala.setApelido(rs.getString("apelido"));
				sala.setAberta(rs.getBoolean("aberta"));
			}

			rs.close();
			stmt.close();
			return sala;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por buscar todas as salas cadastradas na tabela Salas
	 * 
	 * @return Lista de Salas existentes
	 */

	public ArrayList<Sala> buscarSalasCadastradas() {

		ArrayList<Sala> salas = new ArrayList<>();

		String sql = "SELECT * FROM SALAS";
		Sala sala = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				sala = new Sala();
				sala.setIdSala(rs.getString("id_sala"));
				sala.setCapacidade(rs.getInt("capacidade"));
				sala.setFinalidade(rs.getString("finalidade"));
				sala.setTipo(rs.getString("tipo"));
				sala.setApelido(rs.getString("apelido"));
				sala.setAberta(rs.getBoolean("aberta"));
				salas.add(sala);
			}

			rs.close();
			stmt.close();
			return salas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo responsavel por obter um atributo de determinada Sala
	 * 
	 * @param idSala
	 *            ID da sala que tera o atributo retornado
	 * @param atributo
	 *            Atributo que a sala contem (ex.: capacidade, apelido e etc...)
	 * @return Valor do atributo correspondente ao ID da sala passada
	 */

	public String getAtributoSala(String idSala, String atributo) {

		String sql = "SELECT * FROM SALAS WHERE id_sala = (?)";

		String valorAtributo = null;

		try {
			PreparedStatement stmt = getConnection().prepareStatement(sql);

			stmt.setString(1, idSala);
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
	 * Metodo que verifica a existencia de uma Sala
	 * 
	 * @param idSala
	 *            ID da sala a ser verificada a existencia
	 * @return Se a sala existe ou nao
	 */

	public boolean verificaExistencia(String idSala) {

		ArrayList<Sala> salasCadastradas = new ArrayList<>();

		salasCadastradas = buscarSalasCadastradas();

		for (Sala sala : salasCadastradas) {

			if (sala.getIdSala().equals(idSala)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo de remocao da sala
	 * 
	 * @param idSala
	 *            ID da sala a ser removida
	 */

	public void removerSala(String idSala) {
		String sql = "DELETE FROM SALAS WHERE id_sala = (?)";
		super.apagar(sql, idSala);
	}

}

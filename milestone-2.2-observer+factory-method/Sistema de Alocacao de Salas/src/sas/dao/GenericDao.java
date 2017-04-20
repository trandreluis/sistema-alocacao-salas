package sas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sas.dao.factoryMethod.fabricas.conexao.FabricaDeConexao;

/**
 * Classe responsavel pelos metodos genericos de persistencia
 * 
 * @author Andre Luis
 *
 */

public abstract class GenericDao {
	private Connection connection;

	/**
	 * Metodo construtor que obtem a conexao com o Banco de Dados
	 */

	protected GenericDao() {
		this.connection = FabricaDeConexao.getConnection(1);
	}

	/**
	 * Metodo que retorna a conexao obtida
	 * 
	 * @return
	 */

	protected Connection getConnection() {
		return connection;
	}

	/**
	 * Metodo generico para inserir dados no Banco
	 * 
	 * @param insertSql
	 *            Comando SQL a ser executado
	 * @param parametros
	 *            Array de objects
	 */

	protected void salvar(String insertSql, Object... parametros) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					insertSql);

			for (int i = 0; i < parametros.length; i++) {
				pstmt.setObject(i + 1, parametros[i]);
			}

			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo generico responsavel por alterar dados no banco
	 * 
	 * @param updateSql
	 *            Comando SQL para update
	 * @param parametros
	 *            Array de objects
	 */

	protected void alterar(String updateSql, Object... parametros) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(updateSql);

			for (int i = 0; i < parametros.length; i++) {
				pstmt.setObject(i + 1, parametros[i]);
			}

			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo generico de remocao do banco
	 * 
	 * @param deleteSql
	 *            Comando SQL de remocao
	 * @param parametros
	 *            Array de objects
	 */

	protected void apagar(String deleteSql, Object... parametros) {
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(
					deleteSql);

			for (int i = 0; i < parametros.length; i++) {
				pstmt.setObject(i + 1, parametros[i]);
			}

			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Metodo responsavel por zerar todas as informacoes do Banco
	 */

	public void zerarSistema() {
		String sql1 = "DELETE FROM SALAS WHERE id > 0";
		try {
			getConnection().createStatement().executeUpdate(sql1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql2 = "DELETE FROM EVENTOS WHERE id > 0";
		try {
			getConnection().createStatement().executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql3 = "DELETE FROM ALOCADOS WHERE id > 0";
		try {
			getConnection().createStatement().executeUpdate(sql3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
package sas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 *         Classe responsavel por inicializar o banco de dados e suas
 *         respectivas tabelas
 * @author Andre Luis
 *
 * 
 * */

public class Banco {

	private Connection connection;

	/**
	 * Metodo construtor que, ao inicializado, obtem uma conexao com o DAO
	 */

	public Banco() {
		this.connection = Conexao.getConnection();
	}

	/**
	 * Metodo que reune a criacao de todas as tabelas e, por consequencia, o
	 * banco.
	 * 
	 * @throws SQLException
	 *             Possivel erro na inicializacao das tabelas
	 */

	public void criarBanco() throws SQLException {

		criarTabelaEventos();
		criarTabelaSalas();
		criarTabelaAlocados();

	}

	/**
	 * Metodo responsavel por criar a tabela Eventos
	 * 
	 * @throws SQLException
	 *             Possivel erro na criacao da tabela Eventos
	 */

	public void criarTabelaEventos() throws SQLException {
		String sql = null;
		try {
			sql = "CREATE TABLE EVENTOS( "
					+ "id integer not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "id_evento varchar(20) not null, "
					+ "nome varchar(100) not null,"
					+ "inicio varchar(50) not null,"
					+ "fim varchar(50) not null,"
					+ "area varchar(50) not null,"
					+ "contato varchar(50) not null," + "repeticoes integer"
					+ ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
	}

	/**
	 * Metodo responsavel por criar a tabela Salas
	 * 
	 * @throws SQLException
	 *             Possivel erro na criacao da tabela Salas
	 */

	public void criarTabelaSalas() throws SQLException {
		String sql = null;
		try {
			sql = "CREATE TABLE SALAS( "
					+ "id integer not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "id_sala varchar(10), " + "capacidade integer not null, "
					+ "finalidade varchar(100), "
					+ "tipo varchar(50) not null, " + "apelido varchar(100), "
					+ "aberta boolean not null" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
	}

	/**
	 * Metodo responsavel por criar da tabela Alocados
	 * 
	 * @throws SQLException
	 */

	public void criarTabelaAlocados() throws SQLException {
		String sql = null;
		try {
			sql = "CREATE TABLE ALOCADOS( "
					+ "id integer not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "id_evento varchar(20) not null, "
					+ "id_sala varchar(20) not null" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new SQLException();
		}
	}

}

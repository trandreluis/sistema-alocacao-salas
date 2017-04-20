package sas.dao.factory.fabricas.conexao;

import java.sql.Connection;

import sas.dao.factory.conexoes.derby.Conexao;
import sas.dao.factory.conexoes.derby.ConexaoDerby;

/**
 * Classe responsavel por fabricar as conexoes com os bancos de dados, que se
 * fazem necessarias para que haja a persistencia dos dados no sistema
 * 
 * (Factory Method implementado)
 * 
 * @author Andre Luis
 *
 */

public class FabricaDeConexao {

	private static Conexao conexao;

	/**
	 * Metodo Fabrica - responsavel por criar a Connection correta de acordo com o parametro passado
	 * 
	 * @param banco Banco de dados desejado para se estabelecer uma conexao
	 * @return Conexao com o banco de dados desejado
	 */
	
	public static Connection getConnection(int banco) {

		// caso a entrada seja 1, a fabrica de conexoes criara uma conexao com o
		// banco correspondente a entrada
		if (banco == 1) {
			conexao = new ConexaoDerby();
			return conexao.getConnection();
		}

		// aqui, futuramente, podera haver uma condicional que quando obedecida
		// resulte na criacao de uma conexao com outro banco de dados diferente
		// do primeiro, e assim por diante...

		return null;
	}

}

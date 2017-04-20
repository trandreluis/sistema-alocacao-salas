package sas.dao.factoryMethod.conexoes.derby;

/**
 * Classe de Conexao com o banco de Dados Derby, onde as propriedades do banco sao definidas
 * 
 * @author Andre Luis
 *
 */


public class ConexaoDerby extends Conexao {

	/**
	 * Metodo construtor responsavel por inicializar os valores (url e driver) para a conexao ao Danco de Dados
	 */
	
	public ConexaoDerby() {
		url = "jdbc:derby:banco-sas;create=true;user=derby;password=derby";
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
	}
		
}

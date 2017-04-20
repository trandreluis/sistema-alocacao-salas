package sas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsavel pela criacao do Banco de Dados
 * @author Andre Luis
 *
 */

public class Conexao {	

	    private static final String URL = 
			"jdbc:derby:banco-sas;create=true;user=derby;password=derby";
			
	    private static final String DRIVER =
			"org.apache.derby.jdbc.EmbeddedDriver";

	    /**
	     * Metodo responsavel por obter a conexao com o Banco
	     * @return Retorna uma conexao
	     */
	    
	    public static Connection getConnection() {
	        try {
	            Class.forName(DRIVER);
	            return DriverManager.getConnection(URL);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return null;
	    }
	
}

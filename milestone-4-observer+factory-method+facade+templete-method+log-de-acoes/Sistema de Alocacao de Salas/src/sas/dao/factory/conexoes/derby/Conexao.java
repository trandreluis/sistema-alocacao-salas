package sas.dao.factory.conexoes.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsavel por representar o supertipo Conexao, utilizado no
 * polimorfismo do Metodo Fabrica
 * 
 * @author Andre Luis
 *
 */

public abstract class Conexao {

	protected String url;
	protected String driver;

	public Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String uRL) {
		url = uRL;
	}

	public String getDRIVER() {
		return driver;
	}

	public void setDRIVER(String dRIVER) {
		driver = dRIVER;
	}

}

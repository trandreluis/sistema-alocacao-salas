package sas.inicializar;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import sas.dao.factory.conexoes.derby.BancoDerby;
import sas.interfaceTexto.MenuPrincipal;

/**
 * Classe principal, responsavel por inicializar o sistema. Esta classe (que
 * contem o main) verifica se o Banco de Dados ja existe, caso ainda nao exista,
 * criara um novo e chamara o Menu Principal, caso exista, apenas chamara o Menu
 * Principal.
 * 
 * @author Andre Luis
 *
 */

public class Sistema {

	static BancoDerby banco = new BancoDerby();
	
	/**
	 * Metodo responsavel por inicializar o banco de dados, avisando via interface texto
	 */
	public static void inicializarBancoInterfaceTexto() {
		try {
			System.out.println("Inicializando sitema...");
			banco.criarBanco();
			System.out.println("Banco de Dados criado.");
			System.out.println();
		} catch (SQLException e) {
			System.out.println("Banco de dados local encontrado.");
		}

		MenuPrincipal mp = new MenuPrincipal();

		mp.menu();
	}

	/**
	 * Metodo responsavel por inicializar o banco de dados, avisando via interface grafica
	 */
	
	public static void inicializarBancoInterfaceGrafica() {
		JOptionPane.showMessageDialog(null, "Por favor, aguarde enquanto o sistema carrega.\nEste processo pode demorar um pouco.");
		JOptionPane.showMessageDialog(null, "Inicializando sitema...");
		try {
			banco.criarBanco();
			JOptionPane.showMessageDialog(null, "Banco de Dados criado.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Banco de Dados local encontrado!");
		}

	}
	
}

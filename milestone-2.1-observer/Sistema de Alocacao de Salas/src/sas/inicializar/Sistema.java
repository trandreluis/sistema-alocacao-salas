package sas.inicializar;

import java.sql.SQLException;

import sas.dao.Banco;
import sas.interfaceTexto.MenuPrincipal;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

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

	public static void main(String[] args) {

		Banco banco = new Banco();
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

}

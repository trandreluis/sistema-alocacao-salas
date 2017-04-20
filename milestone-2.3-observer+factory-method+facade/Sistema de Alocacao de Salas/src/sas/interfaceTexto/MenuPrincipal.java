package sas.interfaceTexto;

import java.util.Scanner;

import sas.dao.AlocacaoDao;

/**
 * classe responsavel pelas interacoes referentes a a todas as opcoes do Sistema
 * de Alocacao de Salas
 * 
 * @author Andre Luis
 *
 */

public class MenuPrincipal {

	private ExibicaoSala exibeSala = null;
	private ExibicaoEvento exibeEvento = null;
	private ExibicaoAlocao exibeAlocao = null;
	private MenuPrincipal mp = null;

	/**
	 * Metodo de interacao como o usuario para escolher o que o mesmo deseja
	 * fazer
	 */

	public void menu() {

		System.out.println();
		System.out.println("Sistema de Alocacao de Salas");
		System.out.println();
		System.out
				.println("Selecione a opcao desejada para acessar os sub-menus");
		System.out.println();
		System.out.println("1 - Realizar operacoes com Salas.");
		System.out.println("2 - Realizar operacoes com Eventos.");
		System.out.println("3 - Realizar operacoes com Alocacao.");
		System.out.println("4 - Operacoes de listagem.");
		System.out.println("5 - Opcoes avancadas.");
		System.out.println("6 - Encerrar sistema.");

		Scanner s = new Scanner(System.in);

		String opcao = s.nextLine();

		if (!validaEntrada(opcao)) {
			System.out.println();
			System.out.println("Escolha invalida, esta nao e uma das opcoes.");
			menu();
		}

		int acao = Integer.parseInt(opcao);

		delegarAcao(acao);

	}

	/**
	 * Metodo que realiza a chamada dos metodos correspondentes a opcao
	 * escolhida pelo usuario (opcao esta que ja esta validada)
	 * 
	 * @param opcao
	 *            Escolha do usuario (ja validada)
	 */

	public void delegarAcao(int opcao) {

		switch (opcao) {

		case 1:
			exibeSala = new ExibicaoSala();
			exibeSala.menu();

			break;
		case 2:
			exibeEvento = new ExibicaoEvento();
			exibeEvento.menu();
			break;
		case 3:
			exibeAlocao = new ExibicaoAlocao();
			exibeAlocao.menu();
			break;
		case 4:
			mp = new MenuPrincipal();
			mp.operacoesListagem();
			menu();
			break;
		case 5:
			opcoesAvancadas();
			break;
		case 6:
			System.out.println();
			System.out.println("Sistema de alocacoes encerrado.");
			System.exit(0);
			break;
		}
	}

	/**
	 * Metodo responsavel pelas listagens de Eventos, Salas e Alocacoes
	 */

	public void operacoesListagem() {

		System.out.println();
		System.out.println("Sub-menu: Operacoes Listagem");
		System.out.println();
		System.out.println("1 - Listar salas.");
		System.out.println("2 - Listar eventos.");
		System.out.println("3 - Listar alocacoes.");
		System.out.println("4 - Ir para Menu Principal.");

		Scanner sc = new Scanner(System.in);

		String opcao = sc.nextLine();

		if (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
				&& !opcao.equals("4")) {
			System.out.println();
			System.out.println("Escolha invalida, esta nao e uma das opcoes.");
			menu();
		} else {
			if (opcao.equals("1")) {
				exibeSala = new ExibicaoSala();
				System.out.println();
				System.out.println("Realizando busca...");
				System.out.println();
				exibeSala.listar();
				operacoesListagem();
				System.out.println();
			} else if (opcao.equals("2")) {
				exibeEvento = new ExibicaoEvento();
				System.out.println();
				System.out.println("Realizando busca...");
				System.out.println();
				exibeEvento.listar();
				System.out.println();
				operacoesListagem();
			} else if (opcao.equals("3")) {
				exibeAlocao = new ExibicaoAlocao();
				System.out.println();
				System.out.println("Realizando busca...");
				System.out.println();
				exibeAlocao.listar();
				operacoesListagem();
			} else if (opcao.equals("4")) {
				menu();
			}

		}

	}

	/**
	 * Metodo responsavel por validar a entrada do usuario de acordo com as
	 * opcoes oferecidas
	 * 
	 * @param escolha
	 *            Escolha do usuario (ainda nao validada)
	 * @return Se a esolha foi ou nao encontrada dentre as opcoes fornecidas
	 */

	public boolean validaEntrada(String escolha) {
		if (!escolha.equals("1") && !escolha.equals("2")
				&& !escolha.equals("3") && !escolha.equals("4")
				&& !escolha.equals("5") && !escolha.equals("6")
				&& !escolha.endsWith("7")) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo responsavel por interagir com o usuario a cerca das opcoes
	 * avancadas do sistema que, por enquanto, possui somente zerarSistema()
	 */

	public void opcoesAvancadas() {

		System.out.println("Sub-menu: Opcoes avancadas.");
		System.out.println();
		System.out.println("1 - Zerar todo o sistema.");
		System.out.println("2 - Ir para Menu Principal");
		Scanner sc = new Scanner(System.in);
		String opcao = sc.nextLine();
		if (!opcao.equals("1") && !opcao.equals("2")) {
			System.out.println("Escolha invalida, esta nao e uma das opcoes.");
			menu();
		} else if (opcao.equals("1")) {
			System.out.println();
			System.out
					.println("Antencao, a opcao ecolhida ira remover todas as salas,\neventos e consequentemente suas alocaoes.");
			System.out.println();
			System.out
					.println("Confirme sua escolha: 1 - Continuar, 2 - Cancelar e ir para Menu Principal.");
			Scanner s = new Scanner(System.in);
			String opcao2 = s.nextLine();

			if (!opcao2.equals("1") && !opcao2.equals("2")) {
				System.out
						.println("Escolha invalida, esta nao e uma das opcoes.");
				menu();
			} else if (opcao2.equals("1")) {
				AlocacaoDao alocacao = new AlocacaoDao();
				try {
					alocacao.zerarSistema();
					System.out
							.println("O sistema foi zerado com sucesso.\nNao existem mais salas, eventos ou alocaoes cadastradas.");
					menu();
				} catch (Exception e) {
					System.out
							.println("Ouve um erro ao tentar zerar o sistema, tente novamente mais tarde.");
					menu();
				}
			} else if (opcao2.equals("2")) {
				menu();
			}

		} else if (opcao.equals("2")) {
			menu();
		}

	}

}

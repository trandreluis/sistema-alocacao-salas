package sas.interfaceTexto;

import java.util.ArrayList;
import java.util.Scanner;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.sala.IdSalaDuplicadaException;
import sas.excecoes.sala.IdentificacaoInvalidaException;
import sas.excecoes.sala.SalaInexistenteException;
import sas.excecoes.sala.TipoInvalidoException;
import sas.objetos.Sala;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

/**
 * classe responsavel pelas interacoes referentes as Salas
 * 
 * @author Andre Luis
 *
 */

public class ExibicaoSala implements Exibicao {

	private OperacoesSala opSala = new OperacoesSala();
	private OperacoesEvento opEvento = new OperacoesEvento();
	private OperacoesAlocacao opAlocacao = new OperacoesAlocacao(opSala, opEvento);

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void menu() {

		System.out.println();
		System.out.println("Sub-menu: Operacoes Sala");
		System.out.println();
		System.out.println("1 - Adicionar sala.");
		System.out.println("2 - Listar salas.");
		System.out.println("3 - Pegar caracteristica de uma sala.");
		System.out.println("4 - Remover sala.");
		System.out.println("5 - Ir para Menu Principal.");

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
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public boolean validaEntrada(String opcao) {
		if (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3")
				&& !opcao.equals("4") && !opcao.equals("5")) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void delegarAcao(int acao) {

		switch (acao) {

		case 1:
			adicionar();
			break;
		case 2:
			listar();
			menu();
			break;
		case 3:
			pegarCaracteristica();
			break;
		case 4:
			remover();
			break;
		case 5:
			MenuPrincipal mp = new MenuPrincipal();
			mp.menu();
			break;

		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void adicionar() {

		System.out.println("Informe o ID da sala:");
		Scanner adds = new Scanner(System.in);

		String idSala = adds.nextLine();

		char str1 = idSala.charAt(0);
		char str2 = idSala.charAt(1);

		String str = Character.toString(str1) + Character.toString(str2);

		if (idSala.length() != 5 || !str.equals("SA") && !str.equals("SC")
				&& !str.equals("LA") && !str.equals("ES")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		System.out.println("A capacidade:");
		Scanner adds2 = new Scanner(System.in);

		String cap = adds2.nextLine();

		int capacidade = 0;

		try {
			capacidade = Integer.parseInt(cap);
		} catch (NumberFormatException n) {
			System.out
					.println("Operacao abortada: O numero digitado nao e valido.");
			menu();
		}

		System.out.println("A finalidade:");
		Scanner adds3 = new Scanner(System.in);

		String finalidade = adds3.nextLine();

		System.out.println("O tipo:");
		Scanner adds4 = new Scanner(System.in);

		String tipo = adds4.nextLine();

		System.out.println("A sala possui um apelido? 1 - Sim, 2 - Nao:");
		Scanner adds5 = new Scanner(System.in);

		String temApelido = adds5.nextLine();

		if (temApelido.equals("1")) {

			System.out.println("Informe o apelido:");
			Scanner adds6 = new Scanner(System.in);

			String apelido = adds6.nextLine();

			System.out.println("A sala e aberta? 1 - Sim, 2 - Nao:");
			Scanner adds7 = new Scanner(System.in);

			String eAberta = adds7.nextLine();

			boolean aberto = false;

			if (eAberta.equals("1")) {

				aberto = true;

				try {
					opSala.adicionarSalaCompleto(idSala, capacidade,
							finalidade, tipo, apelido, aberto);
					System.out.println("Sala adicionada com sucesso.");
					menu();
				} catch (RoomsAllocationException e) {
					if (e instanceof IdentificacaoInvalidaException) {
						System.out
								.println("Operacao abortada: O ID informado nao e valido.");
						menu();
					} else if (e instanceof IdSalaDuplicadaException) {
						System.out
								.println("Operacao abortada: Ja existe uma sala com este ID.");
						menu();
					} else if (e instanceof TipoInvalidoException) {
						System.out
								.println("Operacao abortada: O tipo infromado nao e valido.");
						menu();
					}
				}

			} else if (eAberta.equals("2")) {
				try {
					opSala.adicionarSalaMedio(idSala, capacidade, finalidade,
							tipo, apelido);
					System.out.println("Sala adicionada com sucesso.");
					menu();
				} catch (RoomsAllocationException e) {
					if (e instanceof IdentificacaoInvalidaException) {
						System.out
								.println("Operacao abortada: O ID informado nao e valido.");
						menu();
					} else if (e instanceof IdSalaDuplicadaException) {
						System.out
								.println("Operacao abortada: Ja existe uma sala com este ID.");
						menu();
					} else if (e instanceof TipoInvalidoException) {
						System.out
								.println("Operacao abortada: O tipo infromado nao e valido.");
						menu();
					}
				}
			} else {
				System.out
						.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
				menu();
			}

		} else if (temApelido.equals("2")) {

			try {
				opSala.adicionarSalaBasico(idSala, capacidade, finalidade, tipo);
				System.out.println("Sala adicionada com sucesso.");
				menu();
			} catch (RoomsAllocationException e) {
				if (e instanceof IdentificacaoInvalidaException) {
					System.out
							.println("Operacao abortada: O ID informado nao e valido.");
					menu();
				} else if (e instanceof IdSalaDuplicadaException) {
					System.out
							.println("Operacao abortada: Ja existe uma sala com este ID.");
					menu();
				} else if (e instanceof TipoInvalidoException) {
					System.out
							.println("Operacao abortada: O tipo infromado nao e valido.");
					menu();
				}
			}
		} else {
			System.out
					.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
			menu();
		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void listar() {

		ArrayList<Sala> salas = new ArrayList<>();

		salas = opSala.listarSalas();

		if (salas == null || salas.size() == 0) {
			System.out.println("/////Nao existem salas cadastradas/////");
		} else {
			for (Sala s : salas) {
				if (s.isAberta()) {
					String aberta = "Sim";
					if (s.getApelido().equals("")) {
						System.out.println("|ID: " + s.getIdSala() + " | "
								+ "Capacidade: " + s.getCapacidade() + " | "
								+ "Finalidade: " + s.getFinalidade() + " | "
								+ "Tipo: " + s.getTipo() + " | " + "Aberta: "
								+ aberta + " | ");
					} else {
						System.out.println("|ID: " + s.getIdSala() + " | "
								+ "Capacidade: " + s.getCapacidade() + " | "
								+ "Finalidade: " + s.getFinalidade() + " | "
								+ "Tipo: " + s.getTipo() + " | " + "Apelido: "
								+ s.getApelido() + " | " + "Aberta: " + aberta
								+ " | ");
					}
				} else {
					String aberta = "Nao";
					if (s.getApelido().equals("")) {
						System.out.println("|ID: " + s.getIdSala() + " | "
								+ "Capacidade: " + s.getCapacidade() + " | "
								+ "Finalidade: " + s.getFinalidade() + " | "
								+ "Tipo: " + s.getTipo() + " | " + "Aberta: "
								+ aberta + " | ");
					} else {
						System.out.println("|ID: " + s.getIdSala() + " | "
								+ "Capacidade: " + s.getCapacidade() + " | "
								+ "Finalidade: " + s.getFinalidade() + " | "
								+ "Tipo: " + s.getTipo() + " | " + "Apelido: "
								+ s.getApelido() + " | " + "Aberta: " + aberta
								+ " | ");
					}
				}
			}
		}
	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void remover() {
		System.out
				.println("Remocao de Sala: Note que, ao remover uma sala,\nremove-se tambem as alocacoes relacionadas a ela.");
		System.out.println();

		System.out
				.println("Deseja listar as salas? 1 - Listar, 2 - Remover sem listar.");
		Scanner sc5 = new Scanner(System.in);

		String opcaoListagem = sc5.nextLine();

		if (!opcaoListagem.equals("1") && !opcaoListagem.equals("2")) {
			System.out
					.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
			menu();
		} else if (opcaoListagem.equals("1")) {
			listar();
			System.out.println();
		}

		System.out.println("Informe o ID:");

		Scanner rem = new Scanner(System.in);

		String idSala = rem.nextLine();

		char str1 = idSala.charAt(0);
		char str2 = idSala.charAt(1);

		String str = Character.toString(str1) + Character.toString(str2);

		if (idSala.length() != 5 || !str.equals("SA") && !str.equals("SC")
				&& !str.equals("LA") && !str.equals("ES")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		if (!opSala.verificarExistencia(idSala)) {

			System.out
					.println("Operacao abortada: O ID infromado nao pertence a nenhuma sala cadastrada.");
			menu();

		}

		try {
			opSala.removerSala(idSala);
			System.out.println("Sala " + idSala + " removida com sucesso.");
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof SalaInexistenteException) {
				System.out
						.println("Operacao abortada: O ID infromado nao pertence a nenhuma sala cadastrada.");
				menu();
			}
		}

	}

	/**
	 * Metodo responsavel por interagir com o usuario a fim de obter uma
	 * caracteristica de uma Sala
	 */

	public void pegarCaracteristica() {

		System.out.println("Pegar atributo de uma sala.");
		System.out.println();
		System.out.println("Informe o ID da sala:");
		Scanner scann = new Scanner(System.in);
		String id = scann.nextLine();

		char str1 = id.charAt(0);
		char str2 = id.charAt(1);

		String str = Character.toString(str1) + Character.toString(str2);

		if (id.length() != 5 || !str.equals("SA") && !str.equals("SC")
				&& !str.equals("LA") && !str.equals("ES")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		if (opSala.verificarExistencia(id)) {
			System.out
					.println("Lista de possiveis atributos: capacidade, finalidade, tipo, apelido e aberta.");
			System.out.println();
			System.out.println("Informe um dos atributos acima:");
			Scanner scann2 = new Scanner(System.in);
			String atributo = scann2.nextLine();

			String atributoRetornado = "";

			if (atributo.equals("capacidade") || atributo.equals("finalidade")
					|| atributo.equals("tipo") || atributo.equals("apelido")
					|| atributo.equals("aberta")) {

				try {
					atributoRetornado = opSala.getAtributo(id, atributo);
					if (!atributoRetornado.equals("")) {
						if (atributoRetornado.equals("true")) {
							System.out.println("Atributo aberta: sim");
						} else if (atributoRetornado.equals("false")) {
							System.out.println("Atributo aberta: nao");
						} else {
							System.out.println("Atributo " + atributo + ": "
									+ atributoRetornado);
							menu();
						}
					}
				} catch (Exception e) {
					System.out.println("Ouve um erro.");
					menu();
				}

			}

		} else {
			System.out
					.println("Operacao abortada: Este ID nao pertence a uma sala cadastrada.");
			menu();
		}

	}

}

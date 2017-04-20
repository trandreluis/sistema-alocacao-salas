package sas.interfaceTexto;

import java.util.ArrayList;
import java.util.Scanner;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.evento.AtributoInvalidoException;
import sas.excecoes.evento.EventoInexistenteException;
import sas.excecoes.evento.IdEventoDuplicadoException;
import sas.excecoes.evento.IdentificacaoInvalidaException;
import sas.excecoes.evento.IntervaloDeDatasInvalidoException;
import sas.objetos.Evento;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

/**
 * classe responsavel pelas interacoes referentes aos Eventos
 * 
 * @author Andre Luis
 *
 */

public class ExibicaoEvento implements Exibicao {

	private OperacoesSala opSala = new OperacoesSala();
	private OperacoesEvento opEvento = new OperacoesEvento();
	private OperacoesAlocacao opAlocacao = new OperacoesAlocacao(opSala, opEvento);

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void menu() {

		System.out.println();
		System.out.println("Sub-menu: Operacoes Evento");
		System.out.println();
		System.out.println("1 - Adicionar evento.");
		System.out.println("2 - Listar eventos.");
		System.out.println("3 - Pegar caracteristica de um evento.");
		System.out.println("4 - Cancelar evento.");
		System.out.println("5 - Ir para Menu Principal.");

		Scanner s = new Scanner(System.in);

		String opcao = s.nextLine();

		if (!validaEntrada(opcao)) {
			System.out.println();
			System.out
					.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
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
			// chama adicionar eventos
			adicionar();
			menu();
			break;
		case 2:
			// lista os eventos
			listar();
			menu();
			break;
		case 3:
			// chama pega caraceristica
			pegarCaracteristica();
			break;
		case 4:
			// cancela um evento
			remover();
			menu();
			break;
		case 5:
			// chama menu principal
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

		System.out.println();
		System.out.println("Informe o ID do evento:");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine();

		char str1 = 'a';
		char str2 = 'b';

		try {
			str1 = id.charAt(0);
			str2 = id.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str = Character.toString(str1) + Character.toString(str2);

		if (id.length() != 5 || !str.equals("EV")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		if (opEvento.verificarExistencia(id))
			;

		if (opEvento.verificarExistencia(id)) {
			System.out
					.println("Operacao abortada: Ja existe um evento com este ID.");
			menu();
		} else {
			System.out.println("O nome:");
			Scanner scan = new Scanner(System.in);
			String nome = scan.nextLine();

			System.out
					.println("Instrucao para preenchimento de datas (onde tambem se passa o horario, juntamente),\nex.: 16/02/2016 22:44");
			System.out.println("Inicio e Fim sao datas no formato acima.");
			System.out.println();
			System.out.println("O inicio:");
			Scanner s = new Scanner(System.in);
			String inicio = s.nextLine();

			if (inicio.length() != 16) {
				System.out
						.println("Operacao abortada: Formato de data e hora incorreto, observe atentamente\nas instrucoes apresentadas acima.");
				menu();
			}

			System.out.println("O fim:");
			Scanner scann = new Scanner(System.in);
			String fim = scann.nextLine();

			if (inicio.length() != 16) {
				System.out
						.println("Operacao abortada: Formato de data e hora incorreto, observe atentamente\nas instrucoes apresentadas acima.");
				menu();
			}

			System.out.println("A area:");
			Scanner scann2 = new Scanner(System.in);
			String area = scann2.nextLine();

			System.out.println("O contato:");
			Scanner sds = new Scanner(System.in);
			String contato = sds.nextLine();

			System.out.println("O evento possui repeticoes? 1 - Sim, 2 - Nao.");
			Scanner op = new Scanner(System.in);

			String escolha = op.nextLine();

			if (escolha.equals("1")) {
				System.out.println("Informe o numero de repeticoes:");
				Scanner ads = new Scanner(System.in);
				String repeticoesString = ads.nextLine();

				int repeticoes = 0;

				try {
					repeticoes = Integer.parseInt(repeticoesString);
				} catch (NumberFormatException n) {
					System.out
							.println("Operacao abortada: O numero digitado nao e valido.");
					menu();
				}

				try {
					opEvento.adicionarEventoCompleto(id, nome, inicio, fim,
							area, contato, repeticoes);
					System.out.println("O evento foi cadastrado com sucesso.");
					menu();
				} catch (RoomsAllocationException e) {
					if (e instanceof AtributoInvalidoException) {
						System.out
								.println("Operacao abortada: Atributo informado nao e valido.");
						menu();
					} else if (e instanceof IdEventoDuplicadoException) {
						System.out
								.println("Operacao abortada: Ja existe um evento com este ID.");
						menu();
					} else if (e instanceof IntervaloDeDatasInvalidoException) {
						System.out
								.println("Operacao abortada: O intervalo de datas nao e valido.");
						menu();
					} else if (e instanceof IdentificacaoInvalidaException) {
						System.out
								.println("Operacao abortada: O ID nao e valido.");
						menu();
					}
				}

			} else if (escolha.equals("2")) {
				try {
					opEvento.adicionarEventoBasico(id, nome, inicio, fim, area,
							contato);
					System.out.println("O evento foi cadastrado com sucesso.");
					menu();
				} catch (RoomsAllocationException e) {
					if (e instanceof AtributoInvalidoException) {
						System.out
								.println("Operacao abortada: Atributo informado nao e valido.");
						menu();
					} else if (e instanceof IdEventoDuplicadoException) {
						System.out
								.println("Operacao abortada: Ja existe um evento com este ID.");
						menu();
					} else if (e instanceof IntervaloDeDatasInvalidoException) {
						System.out
								.println("Operacao abortada: O intervalo de datas nao e valido.");
						menu();
					} else if (e instanceof IdentificacaoInvalidaException) {
						System.out
								.println("Operacao abortada: O ID nao e valido.");
						menu();
					}
				}

			} else {
				System.out
						.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
				menu();
			}

		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void listar() {

		ArrayList<Evento> eventos = new ArrayList<>();

		eventos = opEvento.listarEventos();

		if (eventos == null || eventos.size() == 0) {
			System.out.println("/////Nao existem eventos cadastrados/////");
		}

		else {

			for (Evento e : eventos) {

				if (e.getRepeticoes() > 0) {
					System.out.println("|ID: " + e.getIdEvento() + " | "
							+ "Nome: " + e.getNome() + " | " + "Inicio: "
							+ e.getInicio() + " | " + "Fim: " + e.getFim()
							+ " | " + "Area: " + e.getArea() + " | "
							+ "Contato: " + e.getContato() + " | "
							+ "Repeticoes: " + e.getRepeticoes() + " | ");
				} else {
					System.out.println("|ID: " + e.getIdEvento() + " | "
							+ "Nome: " + e.getNome() + " | " + "Inicio: "
							+ e.getInicio() + " | " + "Fim: " + e.getFim()
							+ " | " + "Area: " + e.getArea() + " | "
							+ "Contato: " + e.getContato() + " | ");
				}
			}
		}

	}

	/**
	 * Metodo responsavel por interagir com o usuario a fim de obter uma
	 * caracteristica de um Evento
	 */

	public void pegarCaracteristica() {

		System.out.println("Informe o ID do evento");
		Scanner sc = new Scanner(System.in);
		String id = sc.nextLine();

		char str1 = 'a';
		char str2 = 'b';

		try {
			str1 = id.charAt(0);
			str2 = id.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str = Character.toString(str1) + Character.toString(str2);

		if (id.length() != 5 || !str.equals("EV")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		if (!opEvento.verificarExistencia(id)) {
			System.out
					.println("Operacao abortada: O ID informado nao pertence a nenhum evento cadastrado.");
			menu();
		}

		System.out
				.println("Informacoes possiveis: nome, inicio, fim, area, contato e repeticoes.");

		System.out.println("Informe o atributo desejado:");
		Scanner scan = new Scanner(System.in);

		String atributo = scan.nextLine();

		try {
			String valorAtributo = opEvento.getAtributo(id, atributo);
			System.out.println("Atributo: " + valorAtributo);
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof EventoInexistenteException) {
				System.out
						.println("Operacao abortada: O ID informado nao pertence a nenhum evento cadastrado.");
				menu();
			}
		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void remover() {

		System.out
				.println("Cancelamento de Evento: Note que, ao remover um evento,\nremove-se tambem suas alocacoes.");
		System.out.println();

		System.out
				.println("Deseja listar os eventos? 1 - Listar, 2 - Remover sem listar.");
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

		Scanner scn = new Scanner(System.in);

		String id = scn.nextLine();

		char str1 = 'a';
		char str2 = 'b';

		try {
			str1 = id.charAt(0);
			str2 = id.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str = Character.toString(str1) + Character.toString(str2);

		if (id.length() != 5 || !str.equals("EV")) {
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		try {
			opEvento.cancelarEvento(id);
			System.out
					.println("O evento " + id + " foi cancelado com sucesso.");
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof EventoInexistenteException) {
				System.out
						.println("Operacao abortada: O ID infromado nao pertence a nenhum evento cadastrado.");
				menu();
			}
		}

	}

}

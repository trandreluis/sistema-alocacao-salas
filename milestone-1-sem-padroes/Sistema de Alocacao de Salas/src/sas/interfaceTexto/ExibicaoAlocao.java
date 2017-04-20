package sas.interfaceTexto;

import java.util.ArrayList;
import java.util.Scanner;

import sas.excecoes.alocacao.EntradaInvalidaException;
import sas.excecoes.alocacao.EscritoriosNaoSaoEscalonaveisException;
import sas.excecoes.alocacao.EventoJaAlocadoException;
import sas.excecoes.alocacao.EventoNaoAlocadoException;
import sas.excecoes.alocacao.LaboratoriosAbertosNaoSaoEscalonaveisException;
import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.alocacao.SalaEventoInexistenteException;
import sas.excecoes.alocacao.SalaExclusicaQuimicaException;
import sas.excecoes.alocacao.SalaExclusivaBiologiaException;
import sas.excecoes.alocacao.SalaExclusivaFisicaException;
import sas.excecoes.alocacao.SalaIndisponivelException;
import sas.excecoes.alocacao.SalasDeConferenciaNormaisRepetitivosException;
import sas.excecoes.alocacao.SalasNaoAlocadasFinsDeSemanaException;
import sas.objetos.Alocacao;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

/**
 * classe responsavel pelas interacoes referentes a Alocacao
 * @author Andre Luis
 *
 */

public class ExibicaoAlocao implements Exibicao {

	OperacoesAlocacao opAlocacao = null;
	OperacoesEvento opEvento = null;

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void menu() {

		System.out.println();
		System.out.println("Sub-menu: Operacoes Alocacao");
		System.out.println();
		System.out.println("1 - Alocar evento em sala.");
		System.out.println("2 - Listar eventos alocados.");
		System.out
				.println("3 - Busca avancada (localizar alocacoes por conteudo).");
		System.out.println("4 - Remover alocacao.");
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
			adicionar();
			break;
		case 2:
			listar();
			menu();
			break;
		case 3:
			localizarAlocacoes();
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

		System.out.println("Alocar eventos nas salas.");
		System.out.println();

		System.out
				.println("Deseja listar os eventos e salas existentes? 1 - Listar, 2 - Alocar sem listar.");
		System.out.println();
		Scanner sc5 = new Scanner(System.in);

		String opcaoListagem = sc5.nextLine();

		if (!opcaoListagem.equals("1") && !opcaoListagem.equals("2")) {
			System.out
					.println("Operacao abortada: Escolha invalida, esta nao e uma das opcoes.");
			menu();
		} else if (opcaoListagem.equals("1")) {
			ExibicaoEvento exibeEvento = new ExibicaoEvento();
			ExibicaoSala exibeSala = new ExibicaoSala();
			System.out.println("-Eventos-");
			exibeEvento.listar();
			System.out.println();
			System.out.println("-Salas-");
			exibeSala.listar();
			System.out.println();
		}

		System.out.println("Informe o ID do Evento:");

		Scanner sc = new Scanner(System.in);

		String idEvento = sc.nextLine();

		char str1 = 'a';
		char str2 = 'b';

		try {
			str1 = idEvento.charAt(0);
			str2 = idEvento.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str = Character.toString(str1) + Character.toString(str2);

		if (idEvento.length() != 5 || !str.equals("EV")) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		opEvento = new OperacoesEvento();

		if (!opEvento.verificarExistencia(idEvento)) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao pertence a nenhum evento cadastrado.");
			menu();
		}

		System.out.println("Informe o ID da Sala:");

		Scanner sc2 = new Scanner(System.in);

		String idSala = sc2.nextLine();

		char str12 = 'a';
		char str23 = 'b';

		try {
			str12 = idSala.charAt(0);
			str23 = idSala.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str3 = Character.toString(str12) + Character.toString(str23);

		if (idSala.length() != 5 || !str3.equals("SA") && !str3.equals("SC")
				&& !str3.equals("LA") && !str3.equals("ES")) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		OperacoesSala opSala = new OperacoesSala();

		if (!opSala.verificarExistencia(idSala)) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao pertence a nenhuma sala cadastrada.");
			menu();
		}

		opAlocacao = new OperacoesAlocacao();

		try {
			opAlocacao.alocarEvento(idEvento, idSala);
			System.out.println("Evento " + idEvento
					+ " alocado com sucesso na sala " + idSala);
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof SalaEventoInexistenteException) {
				System.out
						.println("Operacao abortada: Sala/Evento nao existem.");
				menu();
			} else if (e instanceof EscritoriosNaoSaoEscalonaveisException) {
				System.out
						.println("Operacao abortada: Escritorios nao sao escalonaveis.");
				menu();
			} else if (e instanceof SalasDeConferenciaNormaisRepetitivosException) {
				System.out
						.println("Operacao abortada: Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
				menu();
			} else if (e instanceof LaboratoriosAbertosNaoSaoEscalonaveisException) {
				System.out
						.println("Operacao abortada: Laboratorios abertos nao sao escalonaveis.");
				menu();
			} else if (e instanceof EventoJaAlocadoException) {
				System.out
						.println("Operacao abortada: O Evento ja foi alocado anteriormente.");
				menu();
			} else if (e instanceof SalasNaoAlocadasFinsDeSemanaException) {
				System.out
						.println("Operacao abortada: As salas nao sao alocadas nos fins de semana.");
				menu();
			} else if (e instanceof SalaIndisponivelException) {
				System.out
						.println("Operacao abortada: A sala nao esta disponivel neste horario.");
				menu();
			} else if (e instanceof SalaExclusivaBiologiaException) {
				System.out
						.println("Operacao abortada: Sala exclusiva para a area de Biologia.");
				menu();
			} else if (e instanceof SalaExclusicaQuimicaException) {
				System.out
						.println("Operacao abortada: Sala exclusiva para a area de Quimica.");
				menu();
			} else if (e instanceof SalaExclusivaFisicaException) {
				System.out
						.println("Operacao abortada: Sala exclusiva para a area de Fisica.");
				menu();
			}
		}

	}

	/**
	 * Metodo responsavel por realizar uma busca personalizada nas Alocacoes
	 */

	public void localizarAlocacoes() {

		opAlocacao = new OperacoesAlocacao();

		System.out.println("Localizar alocacoes.");
		System.out.println();
		System.out
				.println("Atributos disponiveis para busca: nome, inicio, fim, area, contato, repeticoes e horario.");
		System.out.println();
		System.out
				.println("Caso escolha o horario como um criterio de busca, preencha da seguinte forma: ex.: 25/10/1998 12:43\nInformando data e hora.");
		System.out.println("Informe o atributo de busca:");
		Scanner s = new Scanner(System.in);
		String atributo = s.nextLine();

		if (!atributo.equals("nome") && !atributo.equals("inicio")
				&& !atributo.equals("fim") && !atributo.equals("area")
				&& !atributo.equals("contato")
				&& !atributo.equals("repeticoes")
				&& !atributo.equals("horario")) {

			System.out
					.println("Operacao abortada: O atributo de busca nao existe. obs.: (verifique letras maiusculas.)");
			menu();
		}

		System.out.println("Informe o valor que este atributo deve possuir:");

		Scanner sc = new Scanner(System.in);

		String valor = sc.nextLine();

		String resultado = "";

		try {
			resultado = opAlocacao.localizarEvento(atributo, valor);
			System.out.println("Resultado da busca: " + resultado);
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof EntradaInvalidaException) {
				System.out.println("Operacao abortada: Valor invalido.");
				menu();
			}
		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void listar() {

		opAlocacao = new OperacoesAlocacao();

		ArrayList<Alocacao> alocacoes = new ArrayList<>();

		alocacoes = opAlocacao.listarAlocacoes();

		if (alocacoes == null || alocacoes.size() == 0) {
			System.out.println("/////Nao existem alocacoes cadastradas/////");
		}

		for (Alocacao a : alocacoes) {
			System.out.println("|ID-SALA: " + a.getIdSala() + " | "
					+ "ID-EVENTO: " + a.getIdEvento() + " | ");
		}

	}

	/**
	 * Metodo da interface Exibicao, onde ja esta descrito
	 */

	@Override
	public void remover() {

		opAlocacao = new OperacoesAlocacao();
		opEvento = new OperacoesEvento();

		System.out.println("Desalocar evento.");
		System.out.println();

		System.out
				.println("Deseja listar as alocacoes? 1 - Listar, 2 - Desalocar sem listar.");
		System.out.println();
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

		System.out.println("Informe o ID do evento:");

		Scanner s = new Scanner(System.in);
		String id = s.nextLine();

		char str12 = 'a';
		char str23 = 'b';

		try {
			str12 = id.charAt(0);
			str23 = id.charAt(1);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		String str3 = Character.toString(str12) + Character.toString(str23);

		if (id.length() != 5 || !str3.equals("EV")) {
			System.out.println();
			System.out
					.println("Operacao abortada: O ID informado nao e valido.");
			menu();
		}

		if (!opEvento.verificarExistencia(id)) {
			System.out.println("Operacao abordada: O evento nao existe.");
			menu();
		}

		try {
			opAlocacao.desalocarEvento(id);
			String idSala = opAlocacao.getIdSala(id);
			System.out.println("A alocacao " + id + ":" + idSala
					+ " foi removida com sucesso.");
			menu();
		} catch (RoomsAllocationException e) {
			if (e instanceof EventoNaoAlocadoException) {
				System.out
						.println("Operacao abordada: O evento nao esta alocado.");
				menu();
			}
		}

	}

}

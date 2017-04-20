package sas.validacao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

/**
 * Classe responsavel pela validacao das operacoes que envolvem Alocacoes
 * @author Andre Luis
 *
 */

public class ValidacaoAlocacao {

	OperacoesAlocacao opAlocacao;
	OperacoesEvento opEvento;
	OperacoesSala opSala;

	/**
	 * Metodo responsavel por validar as Alocacoes
	 * @param idEvento ID do Evento que sera alocado
	 * @param idSala ID da Sala que recebera o Evento
	 * @throws RoomsAllocationException Excecao que pode ser lancada caso as informacoes sejam invalidas para alocacao
	 */
	
	public void alocarEvento(String idEvento, String idSala)
			throws RoomsAllocationException {

		opAlocacao = new OperacoesAlocacao();
		opEvento = new OperacoesEvento();
		opSala = new OperacoesSala();

		if (opEvento.verificarExistencia(idEvento) == false
				|| opSala.verificarExistencia(idSala) == false
				|| idEvento.equals("") || idSala.equals("")) {
			throw new SalaEventoInexistenteException("Sala/Evento nao existe.");
		}

		else if (opSala.getAtributo(idSala, "finalidade").equalsIgnoreCase(
				"Escritorio")) {
			throw new EscritoriosNaoSaoEscalonaveisException(
					"Escritorios nao sao escalonaveis.");
		}

		else if (opSala.getAtributo(idSala, "finalidade").equalsIgnoreCase(
				"Sala de Conferencia")) {
			if (opSala.getAtributo(idSala, "tipo").equalsIgnoreCase("Normal")) {
				if (Integer.parseInt(opEvento.getAtributo(idEvento,
						"repeticoes")) > 0) {
					throw new SalasDeConferenciaNormaisRepetitivosException(
							"Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
				}
			}
		}

		else if (opSala.getAtributo(idSala, "finalidade").equalsIgnoreCase(
				"Laboratorio")
				&& opSala.getAtributo(idSala, "aberta") == "true") {
			throw new LaboratoriosAbertosNaoSaoEscalonaveisException(
					"Laboratorios abertos nao sao escalonaveis.");
		}

		if (opAlocacao.verificarExistencia(idEvento)) {
			throw new EventoJaAlocadoException(
					"O Evento ja foi alocado anteriormente.");
		}

		
		else if (opAlocacao.verificarExistenciaSala(idSala)) {

			String idEventoAlocado = opAlocacao.getIdEvento(idSala);
			String inicioAlocado = opEvento.getAtributo(idEventoAlocado,
					"inicio");
			String fimAlocado = opEvento.getAtributo(idEventoAlocado, "fim");

			String inicioCandidato = opEvento.getAtributo(idEvento, "inicio");
			String fimCandidato = opEvento.getAtributo(idEvento, "fim");

			String diaCandidato = inicioCandidato.split("/")[0];
			String diaAlocado = inicioAlocado.split("/")[0];

			int repeticoesAlocado = Integer.parseInt(opEvento.getAtributo(
					idEventoAlocado, "repeticoes"));

			Date dataInicioAlocado = null;
			Date dataFimAlocado = null;

			Date dataInicioCandidat = null;
			Date dataFimCandidat = null;

			try {
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formato.setLenient(false);
				dataInicioAlocado = (Date) formato.parse(inicioAlocado);
				dataFimAlocado = (Date) formato.parse(fimAlocado);

				dataInicioCandidat = (Date) formato.parse(inicioCandidato);
				dataFimCandidat = (Date) formato.parse(fimCandidato);

			} catch (ParseException e) {

			}

			Calendar calendarDataInicioAlocado = Calendar.getInstance();
			calendarDataInicioAlocado.setTime(dataInicioAlocado);
			Calendar calendarDataFimAlocado = Calendar.getInstance();
			calendarDataFimAlocado.setTime(dataFimAlocado);

			Calendar calendarDataInicioCandidat = Calendar.getInstance();
			calendarDataInicioCandidat.setTime(dataInicioCandidat);
			Calendar calendarDataFimCandidat = Calendar.getInstance();
			calendarDataInicioCandidat.setTime(dataFimCandidat);

			if (calendarDataInicioCandidat.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| calendarDataInicioCandidat.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				throw new SalasNaoAlocadasFinsDeSemanaException(
						"As salas nao sao alocadas nos fins de semana.");
			}

			else if (repeticoesAlocado > 0) {

				Calendar calendarInicioAloc = Calendar.getInstance();
				Calendar calendarFimAloc = Calendar.getInstance();

				Calendar calendarInicioCandid = Calendar.getInstance();
				Calendar calendarFimCandid = Calendar.getInstance();

				Date inicioAloc = null;
				Date fimAloc = null;

				SimpleDateFormat formato = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");

				try {
					inicioAloc = (Date) formato.parse(inicioAlocado);
					fimAloc = (Date) formato.parse(fimAlocado);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				calendarInicioAloc.setTime(inicioAloc);
				calendarFimAloc.setTime(fimAloc);

				calendarInicioCandid.setTime(dataInicioCandidat);
				calendarFimCandid.setTime(dataFimCandidat);

				for (int i = 0; i < repeticoesAlocado; i++) {
					if (calendarInicioCandid.equals(calendarInicioAloc)
							|| calendarInicioCandid.equals(calendarFimAloc)
							|| calendarFimCandid.equals(calendarFimAloc)
							|| calendarFimCandid.equals(calendarInicioAloc)) {
						throw new SalaIndisponivelException(
								"A sala nao esta disponivel neste horario.");
					}
					calendarInicioAloc.add(Calendar.DAY_OF_MONTH, 7);
					calendarFimAloc.add(Calendar.DAY_OF_MONTH, 7);
				}

			}

			else if (diaAlocado.equals(diaCandidato)) {
				if (!calendarDataFimCandidat.before(calendarDataInicioAlocado)
						&& !calendarDataInicioCandidat
								.after(calendarDataFimAlocado)) {
					throw new SalaIndisponivelException(
							"A sala nao esta disponivel neste horario.");
				}
			}

		}

		if (!opSala.getAtributo(idSala, "tipo").equalsIgnoreCase(
				opEvento.getAtributo(idEvento, "area"))) {
			if (opSala.getAtributo(idSala, "tipo").equalsIgnoreCase("biologia")) {
				throw new SalaExclusivaBiologiaException(
						"Sala exclusiva para a area de Biologia.");
			} else if (opSala.getAtributo(idSala, "tipo").equalsIgnoreCase(
					"quimica")) {
				throw new SalaExclusicaQuimicaException(
						"Sala exclusiva para a area de Quimica.");
			} else if (opSala.getAtributo(idSala, "tipo").equalsIgnoreCase(
					"fisica")) {
				throw new SalaExclusivaFisicaException(
						"Sala exclusiva para a area de Fisica.");
			}

		}

	}
	
	/**
	 * Metodo responsavel por validar as entradas de o metodo localizaEvento() rebcebe
	 * @param nome Nome fornecido no metodo
	 * @param valor Valor que o "nome" devera possuir
	 * @throws RoomsAllocationException Excecao que pode ser lancada caso as infromacoes passadas sejam incorretas
	 */
	
	public void localizaEvento(String nome, String valor)
			throws RoomsAllocationException {

		if (valor.equals("")) {
			throw new EntradaInvalidaException("Entrada Invalida");
		}

	}

	/**
	 * Metodo responsavel pela validacao de cancelar um evento (desalocar)
	 * @param idEvento ID do evento a ser desalocado
	 * @throws RoomsAllocationException Excecao que pode ser lancada caso as informacoes passadas nao sejam validas
	 */
	
	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException {

		opAlocacao = new OperacoesAlocacao();

		if (!opAlocacao.verificarExistencia(idEvento)) {
			throw new EventoNaoAlocadoException("O evento nao esta alocado.");
		}

	}

}

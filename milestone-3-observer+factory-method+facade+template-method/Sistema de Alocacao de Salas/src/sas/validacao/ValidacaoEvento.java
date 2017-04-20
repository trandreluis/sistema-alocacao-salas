package sas.validacao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.excecoes.evento.AtributoInvalidoException;
import sas.excecoes.evento.EventoInexistenteException;
import sas.excecoes.evento.IdEventoDuplicadoException;
import sas.excecoes.evento.IdentificacaoInvalidaException;
import sas.excecoes.evento.IntervaloDeDatasInvalidoException;
import sas.operacoes.OperacoesEvento;

/**
 * Classe responsavel pela validacao das operacoes que envolvem Eventos
 * 
 * @author Andre Luis
 *
 */

public class ValidacaoEvento {

	private OperacoesEvento ops;

	/**
	 * Metodo responsavel por validar o cadastro de novos Eventos
	 * 
	 * @param id
	 *            ID que o Evento tera
	 * @param nome
	 *            Nome que o evento tera
	 * @param inicio
	 *            Data e hora de inicio do novo Evento
	 * @param fim
	 *            Data e hora de fim do novo Evento
	 * @param area
	 *            Area do Evento
	 * @param contato
	 *            Responsavel pelo Evento
	 * @param repeticoes
	 *            Numero de vezes que o evento se repetira (as repeticoes sao
	 *            semanalmente: ex.: esta terca e terca da semana que vem, isso
	 *            para um evento de uma repeticao)
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes inseridas
	 *             nao estejam corretas
	 */

	public void adicionarEventoCompleto(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException {
		ops = new OperacoesEvento();
		if (id == null || id.equalsIgnoreCase("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (nome == null) {
			throw new AtributoInvalidoException("Atributo invalido.");
		} else if (nome.equalsIgnoreCase("") || area.equalsIgnoreCase("") || area == null
				|| repeticoes < 0 || contato == null || contato.equalsIgnoreCase("")) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		if (inicio.equalsIgnoreCase("") || fim.equalsIgnoreCase("")) {
			throw new IntervaloDeDatasInvalidoException(
					"Intervalo de datas invalido.");
		} else if (!inicio.equalsIgnoreCase("") || !fim.equalsIgnoreCase("")) {
			Date dataInicio = null;
			Date dataFim = null;
			try {
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formato.setLenient(false);
				dataInicio = (Date) formato.parse(inicio);
				dataFim = (Date) formato.parse(fim);
			} catch (ParseException e) {
				throw new IntervaloDeDatasInvalidoException(
						"Intervalo de datas invalido.");
			}
			Calendar calendarDataInicio = Calendar.getInstance();
			calendarDataInicio.setTime(dataInicio);
			Calendar calendarDataFim = Calendar.getInstance();
			calendarDataFim.setTime(dataFim);

			if (calendarDataInicio.get(Calendar.DAY_OF_MONTH) != calendarDataFim
					.get(Calendar.DAY_OF_MONTH)
					|| calendarDataInicio.after(calendarDataFim)) {
				throw new IntervaloDeDatasInvalidoException(
						"Intervalo de datas invalido.");
			}
		}
		if (ops.verificarExistencia(id)) {
			throw new IdEventoDuplicadoException(
					"Ja existe evento com esta identificacao.");
		}

	}

	/**
	 * Metodo responsavel por validar o cadastro de novos Eventos
	 * 
	 * @param id
	 *            ID que o Evento tera
	 * @param nome
	 *            Nome que o evento tera
	 * @param inicio
	 *            Data e hora de inicio do novo Evento
	 * @param fim
	 *            Data e hora de fim do novo Evento
	 * @param area
	 *            Area do Evento
	 * @param contato
	 *            Responsavel pelo Evento
	 * 
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes inseridas
	 *             nao estejam corretas
	 */

	public void adicionarEventoBasico(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException {
		ops = new OperacoesEvento();
		if (id == null || id.equalsIgnoreCase("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} else if (nome == null) {
			throw new AtributoInvalidoException("Atributo invalido.");
		} else if (nome.equalsIgnoreCase("") || area.equalsIgnoreCase("") || area == null
				|| contato == null || contato.equalsIgnoreCase("")) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		if (inicio.equalsIgnoreCase("") || fim.equalsIgnoreCase("")) {
			throw new IntervaloDeDatasInvalidoException(
					"Intervalo de datas invalido.");
		} else if (!inicio.equalsIgnoreCase("") || !fim.equalsIgnoreCase("")) {
			Date dataInicio = null;
			Date dataFim = null;
			try {
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formato.setLenient(false);
				dataInicio = (Date) formato.parse(inicio);
				dataFim = (Date) formato.parse(fim);
			} catch (ParseException e) {
				throw new IntervaloDeDatasInvalidoException(
						"Intervalo de datas invalido.");
			}
			Calendar calendarDataInicio = Calendar.getInstance();
			calendarDataInicio.setTime(dataInicio);
			Calendar calendarDataFim = Calendar.getInstance();
			calendarDataFim.setTime(dataFim);

			if (calendarDataInicio.get(Calendar.DAY_OF_MONTH) != calendarDataFim
					.get(Calendar.DAY_OF_MONTH)
					|| calendarDataInicio.after(calendarDataFim)) {
				throw new IntervaloDeDatasInvalidoException(
						"Intervalo de datas invalido.");
			}
		}
		if (ops.verificarExistencia(id)) {
			throw new IdEventoDuplicadoException(
					"Ja existe evento com esta identificacao.");
		}
	}

	/**
	 * Metodo responsavel por validar os valores passados nos parametros (como:
	 * se o evento passado realmente existe e etc)
	 * 
	 * @param idEvento
	 *            ID do Evento que tera o atributo retornado
	 * @param atributo
	 *            Atributo do buscado
	 * @throws RoomsAllocationException
	 *             Excecao que pode ser lancada, caso as informacoes passadas
	 *             estejam incorretas
	 */

	public void getAtributoEvento(String idEvento, String atributo)
			throws RoomsAllocationException {

		ops = new OperacoesEvento();
		if (idEvento == null) {
			throw new EventoInexistenteException("Evento nao existe.");
		} else if (!atributo.equalsIgnoreCase("nome") && !atributo.equalsIgnoreCase("inicio")
				&& !atributo.equalsIgnoreCase("fim") && !atributo.equalsIgnoreCase("contato")
				&& !atributo.equalsIgnoreCase("area") && !atributo.equalsIgnoreCase("repeticoes")) {
			throw new RoomsAllocationException("Atributo nao existe.");
		} else if (!ops.verificarExistencia(idEvento)) {
			throw new EventoInexistenteException("Evento nao existe.");
		}

	}

	/**
	 * Metodo responsavel por validar o cancelamento (exclusao) de eventos no
	 * sistema
	 * 
	 * @param idEvento
	 *            ID do Evento a ser cancelado
	 * @throws RoomsAllocationException
	 *             Excecao que pode ocorrer, caso o Evento nao exista
	 */

	public void cancelarEvento(String idEvento) throws RoomsAllocationException {

		ops = new OperacoesEvento();

		if (!ops.verificarExistencia(idEvento)) {
			throw new EventoInexistenteException("Evento nao existe.");
		}

	}

}

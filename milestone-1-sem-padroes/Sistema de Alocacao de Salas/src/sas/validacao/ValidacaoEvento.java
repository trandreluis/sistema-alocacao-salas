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
 * @author Andre Luis
 *
 */

public class ValidacaoEvento {

	OperacoesEvento ops;

	/**
	 * Metodo responsavel por validar a adicao de eventos no sistema 
	 */
	
	public void adicionarEventoBasico(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes)
			throws RoomsAllocationException {
		ops = new OperacoesEvento();
		if (id == null || id.equals("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} 
		else if (nome == null) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		else if (nome.equals("") || area.equals("")
				|| area == null || repeticoes < 0 || contato == null
				|| contato.equals("")) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		if(inicio.equals("") || fim.equals("")) {
			throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
		}
		else if(!inicio.equals("") || !fim.equals("")){
			Date dataInicio = null;
			Date dataFim = null;
			try {
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formato.setLenient(false);
				dataInicio = (Date) formato.parse(inicio);
				dataFim = (Date) formato.parse(fim);
			} catch(ParseException e) {
				throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
			}
			Calendar calendarDataInicio = Calendar.getInstance();
			calendarDataInicio.setTime(dataInicio);
			Calendar calendarDataFim = Calendar.getInstance();
			calendarDataFim.setTime(dataFim);
			
			if(calendarDataInicio.get(Calendar.DAY_OF_MONTH) != calendarDataFim.get(Calendar.DAY_OF_MONTH) || calendarDataInicio.after(calendarDataFim)) {
				throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
			}
		}
		if (ops.verificarExistencia(id)) {
			throw new IdEventoDuplicadoException(
					"Ja existe evento com esta identificacao.");
		}
		
	}

	public void adicionarEventoCompleto(String id, String nome, String inicio,
			String fim, String area, String contato)
			throws RoomsAllocationException {
		ops = new OperacoesEvento();
		if (id == null || id.equals("")) {
			throw new IdentificacaoInvalidaException("Identificacao Invalida");
		} 
		else if (nome == null) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		else if (nome.equals("") || area.equals("")
				|| area == null || contato == null
				|| contato.equals("")) {
			throw new AtributoInvalidoException("Atributo invalido.");
		}
		if(inicio.equals("") || fim.equals("")) {
			throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
		}
		else if(!inicio.equals("") || !fim.equals("")){
			Date dataInicio = null;
			Date dataFim = null;
			try {
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				formato.setLenient(false);
				dataInicio = (Date) formato.parse(inicio);
				dataFim = (Date) formato.parse(fim);
			} catch(ParseException e) {
				throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
			}
			Calendar calendarDataInicio = Calendar.getInstance();
			calendarDataInicio.setTime(dataInicio);
			Calendar calendarDataFim = Calendar.getInstance();
			calendarDataFim.setTime(dataFim);
			
			if(calendarDataInicio.get(Calendar.DAY_OF_MONTH) != calendarDataFim.get(Calendar.DAY_OF_MONTH) || calendarDataInicio.after(calendarDataFim)) {
				throw new IntervaloDeDatasInvalidoException("Intervalo de datas invalido.");
			}
		}
		if (ops.verificarExistencia(id)) {
			throw new IdEventoDuplicadoException(
					"Ja existe evento com esta identificacao.");
		}
	}
	
	/**
	 * Metodo responsavel por validar os valores passados no getAtributoEvento() 
	 */

	public void getAtributoEvento(String idEvento, String atributo)
			throws RoomsAllocationException {

		ops = new OperacoesEvento();
		if (idEvento == null) {
			throw new EventoInexistenteException("Evento nao existe.");
		} else if (!atributo.equals("nome") && !atributo.equals("inicio")
				&& !atributo.equals("fim") && !atributo.equals("contato")
				&& !atributo.equals("area") && !atributo.equals("repeticoes")) {
			throw new RoomsAllocationException("Atributo nao existe.");
		} else if (!ops.verificarExistencia(idEvento)) {
			throw new EventoInexistenteException("Evento nao existe.");
		}

	}
	
	/**
	 * Metodo responsavel por validar o cancelamento (exclusao) de eventos no sistema 
	 */
	
	public void cancelarEvento(String idEvento) throws RoomsAllocationException {
		
		ops = new OperacoesEvento();
		
		if(!ops.verificarExistencia(idEvento)) {
			throw new EventoInexistenteException("Evento nao existe.");
		}

	}
	
	

}

package sas.validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
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
import sas.objetos.Evento;
import sas.objetos.Sala;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;

public class ValidacaoAlocacao {

	private Calendar calendarDataFim = Calendar.getInstance();
	private Calendar calendarDataInicio = Calendar.getInstance();
	private Date dataInicio = null;
	private Date dataFim = null;
	private OperacoesEvento opEvento = new OperacoesEvento();
	private OperacoesSala opSala = new OperacoesSala();
	private OperacoesAlocacao opAlocacao;

	/**
	 * 
	 * Metodo responsavel por validar a alocacao de um evento em uma sala
	 * 
	 * @param eventos Lista de evento cadastrados no sistema
	 * @param alocacoes Lista de alocacoes cadastradas no sistema
	 * @param idSala ID da sala a ser cadastrada
	 * @param idEvento ID do evento a ser cadastrado
	 * @throws RoomsAllocationException Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void alocarEvento(ArrayList<Evento> eventos,
			ArrayList<Alocacao> alocacoes, String idSala, String idEvento)
			throws RoomsAllocationException {

		validaGeral(alocacoes, idSala, idEvento);

	}

	/**
	 * Metodo geral que chama os metodos de validacao concretos
	 * 
	 * @param alocacoes Lisa de alocacoes cadastradas no sistema
	 * @param idSala ID da sala a ser alocada
	 * @param idEvento ID do evento a ser alocado
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void validaGeral(ArrayList<Alocacao> alocacoes, String idSala,
			String idEvento) throws RoomsAllocationException {

		Sala sala = this.opSala.buscarSala(idSala);
		Evento evento = this.opEvento.buscarEvento(idEvento);

		verificarExistencia(sala, evento);

		validarAreaComTipo(evento, sala);

		validaSalasNaoEscalonaveis(sala, evento);

		verificaEventoAlocado(idEvento, alocacoes);

		validaFinsDeSemana(evento.getInicio(), evento.getFim());

		validarDatas(idSala, alocacoes, this.calendarDataInicio,
				this.calendarDataFim, evento.getRepeticoes(),
				evento.getTipoRepeticao());

	}

	/**
	 * 
	 * Metodo responsavel por validar a assosciação da area do evento com tipo da sala
	 * 
	 * @param evento Evento a ser validado na associacao
	 * @param sala Sala a ser validada na associacao
	 * 
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void validarAreaComTipo(Evento evento, Sala sala)
			throws RoomsAllocationException {
		
		if (!this.opSala
				.getAtributo(sala.getIdSala(), "tipo")
				.equalsIgnoreCase(
						this.opEvento.getAtributo(evento.getIdEvento(), "area"))) {
			if (this.opSala.getAtributo(sala.getIdSala(), "tipo")
					.equalsIgnoreCase("biologia")) {
				throw new SalaExclusivaBiologiaException(
						"Sala exclusiva para a area de Biologia.");
			}
			if (this.opSala.getAtributo(sala.getIdSala(), "tipo")
					.equalsIgnoreCase("quimica")) {
				throw new SalaExclusicaQuimicaException(
						"Sala exclusiva para a area de Quimica.");
			}
			if (this.opSala.getAtributo(sala.getIdSala(), "tipo")
					.equalsIgnoreCase("fisica")) {
				throw new SalaExclusivaFisicaException(
						"Sala exclusiva para a area de Fisica.");
			}
		}
		
	}

	/**
	 * Metodo que verifica a existencia da sala e do evento passado como parametro
	 * 
	 * @param sala Sala a ser verificada
	 * @param evento Evento a ser verificado
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void verificarExistencia(Sala sala, Evento evento)
			throws RoomsAllocationException {
		
		if ((sala == null) || (evento == null)) {
			throw new SalaEventoInexistenteException("Sala/Evento nao existe.");
		}
		
	}

	/**
	 * Metodo responsavel por verificar se o evento ja esta alocado ou nao
	 * 
	 * @param idEvento ID do evento a ser verificado
	 * @param alocacoes Lista de alocacoes
	 *@throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void verificaEventoAlocado(String idEvento,
			ArrayList<Alocacao> alocacoes) throws RoomsAllocationException {
		
		for (Alocacao alocacao : alocacoes) {
			if (alocacao.getIdEvento().equals(idEvento)) {
				throw new EventoJaAlocadoException(
						"O Evento ja foi alocado anteriormente.");
			}
		}
		
	}

	/**
	 * 
	 * Metodo responsavel por verificar conflitos de horario que possam vir a ocorrer
	 * 
	 * @param dataInicioAlocado Data de inicio do evento alocado
	 * @param dataFimAlocado Data do fim do evento alocado
	 * @param dataInicioCandidato Data de inicio do evento candidato
	 * @param dataFimCandidato Data de fim do evento candidato
	 *
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void validarConflitoDeHorario(Calendar dataInicioAlocado,
			Calendar dataFimAlocado, Calendar dataInicioCandidato,
			Calendar dataFimCandidato) throws RoomsAllocationException {
		
		if ((dataInicioCandidato.after(dataInicioAlocado))
				&& (dataInicioCandidato.before(dataFimAlocado))) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if ((dataInicioCandidato.before(dataInicioAlocado))
				&& (dataFimCandidato.after(dataInicioAlocado))) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if (dataInicioCandidato.get(7) == 1) {
			throw new SalasNaoAlocadasFinsDeSemanaException(
					"As salas nao sao alocadas nos fins de semana.");
		}
		if ((dataFimCandidato.after(dataInicioAlocado))
				&& (dataFimCandidato.before(dataFimAlocado))) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if (dataInicioCandidato.after(dataInicioAlocado)) {
			if (dataInicioCandidato.before(dataFimAlocado)) {
				throw new SalaIndisponivelException(
						"A sala nao esta disponivel neste horario.");
			}
		}
		if (dataInicioCandidato.toString().equals(dataInicioAlocado.toString())) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if (dataFimCandidato.toString().equals(dataFimAlocado.toString())) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if ((dataInicioAlocado.after(dataInicioCandidato))
				&& (dataInicioAlocado.before(dataFimCandidato))) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		if ((dataInicioCandidato.after(dataInicioAlocado))
				&& (dataFimCandidato.after(dataFimAlocado))
				&& (dataFimAlocado.after(dataInicioCandidato))) {
			throw new SalaIndisponivelException(
					"A sala nao esta disponivel neste horario.");
		}
		
	}

	/**
	 * 
	 * Metodo que calcula as repeticoes e verifica os conflitos com suas respectivas repeticoes
	 * 
	 * @param dataInicioComRepeticoes Data de inicio do evento com repeticoes
	 * @param dataFimComRepeticoes Data de fim do evento com repeticoes
	 * @param dataInicioSemRepetcoes Data de inicio do evento sem repeticoes
	 * @param dataFimSemRepeticoes Data de fim do evento sem repeticoes
	 * @param repeticoesAlocado Numero de repeticoes do evento alocado
	 * @param tipoRepeticao Tipo de repeticao do evento
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void calcularRepeticoes(Calendar dataInicioComRepeticoes,
			Calendar dataFimComRepeticoes, Calendar dataInicioSemRepetcoes,
			Calendar dataFimSemRepeticoes, int repeticoesAlocado, String tipoRepeticao)
			throws RoomsAllocationException {
		
		Calendar auxInicio = dataInicioComRepeticoes;
		Calendar auxFim = dataFimComRepeticoes;

		validarConflitoDeHorario(auxInicio, auxFim, dataInicioSemRepetcoes,
				dataFimSemRepeticoes);
		
		if (tipoRepeticao.equalsIgnoreCase("diario")) {
			
			for (int i = 1; i <= repeticoesAlocado; i++) {
				auxInicio.add(5, 1);
				auxFim.add(5, 1);
				if (auxInicio.get(7) == 1) {
					auxInicio.add(5, 1);
					auxFim.add(5, 1);
				}
				if ((auxInicio.get(5) == dataInicioSemRepetcoes.get(5))
						&& (auxInicio.get(2) == dataInicioSemRepetcoes.get(2))
						&& (auxInicio.get(1) == dataInicioSemRepetcoes.get(1))
						&& (auxInicio.get(10) == dataInicioSemRepetcoes.get(10))
						&& (auxInicio.get(12) == dataInicioSemRepetcoes.get(12))) {
					throw new SalaIndisponivelException(
							"A sala nao esta disponivel neste horario.");
				}
				validarConflitoDeHorario(auxInicio, auxFim,
						dataInicioSemRepetcoes, dataFimSemRepeticoes);
			}
			
		} else if (tipoRepeticao.equalsIgnoreCase("semanal")) {
			
			for (int i = 1; i <= repeticoesAlocado; i++) {
				auxInicio.add(5, 7);
				auxFim.add(5, 7);
				if (auxInicio.compareTo(dataInicioSemRepetcoes) == 0) {
					throw new SalaIndisponivelException(
							"A sala nao esta disponivel neste horario.");
				}
				validarConflitoDeHorario(auxInicio, auxFim,
						dataInicioSemRepetcoes, dataFimSemRepeticoes);
			}
			
		} else if (tipoRepeticao.equalsIgnoreCase("mensal")) {
			
			for (int i = 1; i <= repeticoesAlocado; i++) {
				auxInicio.add(2, 1);
				auxFim.add(2, 1);
				if (auxInicio.get(7) == 1) {
					auxFim.add(5, 1);
					auxInicio.add(5, 1);
				}
				if (auxInicio.compareTo(dataInicioSemRepetcoes) == 0) {
					throw new SalaIndisponivelException(
							"A sala nao esta disponivel neste horario.");
				}
				validarConflitoDeHorario(auxInicio, auxFim,
						dataInicioSemRepetcoes, dataFimSemRepeticoes);
			}
		}
		
	}

	/**
	 * 
	 * Metodo responsavel por validar as datas
	 * 
	 * @param idSala ID da sala
	 * @param alocacoes Lista de alocacoes
	 * @param dataInicio Data de inicio do evento candidato
	 * @param dataFinal Data de fim do evento candidato
	 * @param repeticao Numero de repeticoes do evento candidato
	 * @param tipoRepeticao Tipo da repeticao do evento candidato
	 * @throws RoomsAllocationException
	 */
	
	public void validarDatas(String idSala, ArrayList<Alocacao> alocacoes,
			Calendar dataInicio, Calendar dataFinal, int repeticao, String tipoRepeticao)
			throws RoomsAllocationException {
		
		for (Alocacao alocacao : alocacoes) {
			if (alocacao.getIdSala().equalsIgnoreCase(idSala)) {
				Evento evento = this.opEvento.buscarEvento(alocacao
						.getIdEvento());

				Calendar inicio = Calendar.getInstance();
				Calendar fim = Calendar.getInstance();

				SimpleDateFormat formato = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");

				Date dateInicio = null;
				Date dateFim = null;
				try {
					dateInicio = formato.parse(evento.getInicio());
					dateFim = formato.parse(evento.getFim());
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null,
							"Erro na conversao das datas.\n" + e.getMessage());
				}
				inicio.setTime(dateInicio);
				fim.setTime(dateFim);
				if (inicio.compareTo(dataInicio) == 0) {
					throw new SalaIndisponivelException(
							"A sala nao esta disponivel neste horario.");
				}
				if ((evento.getRepeticoes() == 0) && (repeticao == 0)) {
					if (inicio.get(5) == dataInicio.get(5)) {
						validarConflitoDeHorario(inicio, fim, dataInicio,
								dataFinal);
					}
				} else if ((evento.getRepeticoes() > 0) && (repeticao == 0)) {
					if (inicio.get(5) <= dataInicio.get(5)) {
						calcularRepeticoes(inicio, fim, dataInicio, dataFinal,
								evento.getRepeticoes(),
								evento.getTipoRepeticao());
					}
				} else if ((evento.getRepeticoes() == 0) && (repeticao > 0)) {
					if (inicio.get(5) >= dataInicio.get(5)) {
						calcularRepeticoes(dataInicio, dataFinal, inicio, fim,
								repeticao, tipoRepeticao);
					}
				} else if ((evento.getRepeticoes() > 0) && (repeticao > 0)) {
					if (inicio.get(5) <= dataInicio.get(5)) {
						calcularRepeticoes(inicio, fim, dataInicio, dataFinal,
								evento.getRepeticoes(),
								evento.getTipoRepeticao());
					}
					if (inicio.get(5) > dataInicio.get(5)) {
						calcularRepeticoes(dataInicio, dataFinal, inicio, fim,
								repeticao, tipoRepeticao);
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * Metodo que valida se as repeticoes caem aos domingos
	 * 
	 * @param inicio Data de inicio do evento
	 * @param fim Data de fim do evento
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */

	public void validaFinsDeSemana(String inicio, String fim)
			throws RoomsAllocationException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			this.dataInicio = formatter.parse(inicio);
			this.dataFim = formatter.parse(fim);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Erro na conversao das datas.\n" + e.getMessage());
		}
		this.calendarDataInicio.setTime(this.dataInicio);
		this.calendarDataFim.setTime(this.dataFim);
		if (this.calendarDataInicio.get(7) == 1) {
			throw new SalasNaoAlocadasFinsDeSemanaException(
					"As salas nao sao alocadas nos fins de semana.");
		}
		
	}
	
	/**
	 * 
	 * Metodo que valida a busca pelos eventos alocados
	 * 
	 * @param nome Propriedade a ser buscada
	 * @param valor Valor da propriedade
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */

	public void localizaEvento(String nome, String valor)
			throws RoomsAllocationException {
		
		if (valor.equals("")) {
			throw new EntradaInvalidaException("Entrada Invalida");
		}
		
	}

	
	/**
	 * 
	 * Metodo responsavel por validar se as salas sao escalonaveis ou nao
	 * 
	 * @param sala Sala a ser verificada
	 * @param evento Evento a ser verificado
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void validaSalasNaoEscalonaveis(Sala sala, Evento evento)
			throws RoomsAllocationException {
		
		if (sala.getFinalidade().equalsIgnoreCase("escritorio")) {
			throw new EscritoriosNaoSaoEscalonaveisException(
					"Escritorios nao sao escalonaveis.");
		}
		if ((sala.getFinalidade().equalsIgnoreCase("Laboratorio"))
				&& (sala.isAberta())) {
			throw new LaboratoriosAbertosNaoSaoEscalonaveisException(
					"Laboratorios abertos nao sao escalonaveis.");
		}
		if ((sala.getFinalidade().equalsIgnoreCase("sala de conferencia"))
				&& (sala.getTipo().equalsIgnoreCase("normal"))
				&& (evento.getRepeticoes() != 0)) {
			throw new SalasDeConferenciaNormaisRepetitivosException(
					"Salas de Conferencia do tipo Normal nao sao escalonaveis para eventos repetitivos.");
		}
		
	}

	/**
	 * 
	 * Metodo responsavel por validar um desalocacao
	 * 
	 * @param idEvento ID do evento a ser validado
	 * @throws RoomsAllocationException  Excecao que pode ser lancada em caso de parametros invalidos serem passados
	 */
	
	public void desalocarEvento(String idEvento)
			throws RoomsAllocationException {
		
		this.opAlocacao = new OperacoesAlocacao(this.opSala, this.opEvento);
		if (!this.opAlocacao.verificarExistencia(idEvento)) {
			throw new EventoNaoAlocadoException("O evento nao esta alocado.");
		}
		
	}

}

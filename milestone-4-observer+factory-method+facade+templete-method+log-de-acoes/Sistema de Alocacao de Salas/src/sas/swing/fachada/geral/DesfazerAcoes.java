package sas.swing.fachada.geral;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Alocacao;
import sas.objetos.Evento;
import sas.objetos.Sala;

public class DesfazerAcoes {

	FachadaGeralGUI fachada;

	public DesfazerAcoes(FachadaGeralGUI fachada) {
		this.fachada = fachada;
	}

	public void adicionarSalaAcoes(Sala sala) throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Adicionar Sala (desfazer remocao) - Valores (ID = "
				+ sala.getIdSala()
				+ ", Capacidade = "
				+ sala.getCapacidade()
				+ ", Finalidade = "
				+ sala.getFinalidade()
				+ ", Tipo = "
				+ sala.getTipo()
				+ ", Apelido = "
				+ sala.getApelido()
				+ ", Aberta = " + sala.isAberta() + ")");

		fachada.operacoesSala.adicionarSalaCompleto(sala.getIdSala(),
				sala.getCapacidade(), sala.getFinalidade(), sala.getTipo(),
				sala.getApelido(), sala.isAberta());

	}

	public void removerSalaAcoes(Sala sala) throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Remover Sala (desfazer adicao) - Valores (ID = "
				+ sala.getIdSala() + ")");

		fachada.operacoesSala.removerSala(sala.getIdSala());

	}

	public void adicionarEventoAcoes(Evento evento)
			throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Adicionar Evento (desfazer remocao) - Valores (ID = "
				+ evento.getIdEvento()
				+ ", Nome = "
				+ evento.getNome()
				+ ", Inicio = "
				+ evento.getInicio()
				+ ", Fim = "
				+ evento.getFim()
				+ ", Area = "
				+ evento.getArea()
				+ ", Contato = "
				+ evento.getContato()
				+ ", Repeticoes = "
				+ evento.getRepeticoes()
				+ ", Tipo = "
				+ evento.getTipoRepeticao()+ ")");

		fachada.operacoesEvento.adicionarEventoCompletoTipado(evento.getIdEvento(),
				evento.getNome(), evento.getInicio(), evento.getFim(),
				evento.getArea(), evento.getContato(), evento.getRepeticoes(), evento.getTipoRepeticao());

	}

	public void removerEventoAcoes(Evento evento)
			throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Remover Evento (desfazer adicao) - Valores (ID = "
				+ evento.getIdEvento() + ")");

		fachada.operacoesEvento.cancelarEvento(evento.getIdEvento());

	}

	public void adicionarAlocacaoAcoes(Alocacao alocacao)
			throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Adicionar Alocacao (desfazer remocao) - Valores (ID-SALA = "
				+ alocacao.getIdSala()
				+ ", ID-EVENTO = "
				+ alocacao.getIdEvento() + ")");

		fachada.operacoesAlocacao.alocarEvento(alocacao.getIdEvento(),
				alocacao.getIdSala());

	}

	public void removerAlocacaoAcoes(Alocacao alocacao)
			throws RoomsAllocationException {

		Log.gravarNoLog("Acao: Remover Alocacao (desfazer adicao) - Valores (ID-EVENTO ALOCADO = "
				+ alocacao.getIdEvento() + ")");

		fachada.operacoesAlocacao.desalocarEvento(alocacao.getIdEvento());

	}

}

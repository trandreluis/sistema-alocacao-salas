package sas.swing.fachada.geral;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.objetos.Alocacao;
import sas.objetos.Evento;
import sas.objetos.Sala;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.Principal;

public class FachadaGeralGUI {

	public OperacoesSala operacoesSala = new OperacoesSala();
	public OperacoesEvento operacoesEvento = new OperacoesEvento();
	public OperacoesAlocacao operacoesAlocacao = new OperacoesAlocacao(operacoesSala,
			operacoesEvento);
	
	public Principal janelaPrincipal;
	
	public FachadaGeralGUI(Principal janela) {
		this.janelaPrincipal = janela;
	}

	public void adicionarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberto) throws RoomsAllocationException {
		
		Sala sala = new Sala();
		
		sala.setIdSala(id);
		sala.setCapacidade(capacidade);
		sala.setFinalidade(finalidade);
		sala.setTipo(tipo);
		sala.setApelido(apelido);
		sala.setAberta(aberto);
		
		Acoes.cadastrar("Adicionar Sala", sala);

		Log.gravarNoLog("Acao: Adicionar Sala - Valores (ID = " + id
				+ ", Capacidade = " + capacidade + ", Finalidade = "
				+ finalidade + ", Tipo = " + tipo + ", Apelido = " + apelido
				+ ", Aberta = " + aberto + ")");

			operacoesSala.adicionarSalaCompleto(id, capacidade, finalidade,
					tipo, apelido, aberto);
	}

	public void removerSala(String idSala) throws RoomsAllocationException {
		
		Sala sala = new Sala();
		
		sala = operacoesSala.buscarSala(idSala);
		
		Acoes.cadastrar("Remover Sala", sala);

		Log.gravarNoLog("Acao: Remover Sala - Valores (ID = " + idSala + ")");

			operacoesSala.removerSala(idSala);
	}
	
	public void adicionarEvento(String id, String nome, String inicio,
			String fim, String area, String contato, int repeticoes, String tipoRepeticao) throws RoomsAllocationException {
		
		Evento evento = new Evento();
		
		evento.setIdEvento(id);
		evento.setNome(nome);
		evento.setInicio(inicio);
		evento.setFim(fim);
		evento.setArea(area);
		evento.setContato(contato);
		evento.setRepeticoes(repeticoes);
		evento.setTipoRepeticao(tipoRepeticao);
		
		Acoes.cadastrar("Adicionar Evento", evento);
		
		Log.gravarNoLog("Acao: Adicionar Evento - Valores (ID = " + id + ", Nome = "
				+ nome + ", Inicio = " + inicio + ", Fim = " + fim
				+ ", Area = " + area + ", Contato = " + contato
				+ ", Repeticoes = " + repeticoes + ", Tipo = "+ tipoRepeticao +")");

			operacoesEvento.adicionarEventoCompletoTipado(id, nome, inicio, fim,
					area, contato, repeticoes, tipoRepeticao);
			
	}
	
	public void removerEvento(String idEvento) throws RoomsAllocationException {
		
		Evento evento = operacoesEvento.buscarEvento(idEvento);
		
		Acoes.cadastrar("Remover Evento", evento);
		
		Log.gravarNoLog("Acao: Remover Evento - Valores (ID = "+idEvento+")");
		
			operacoesEvento.cancelarEvento(idEvento);
	}

	public void adicionarAlocacao(String idEvento, String idSala) throws RoomsAllocationException {
		
		Alocacao alocacao = new Alocacao();
		
		alocacao.setIdEvento(idEvento);
		alocacao.setIdSala(idSala);
		
		Acoes.cadastrar("Adicionar Alocacao", alocacao);
		
		Log.gravarNoLog("Acao: Adicionar Alocacao - Valores (ID-SALA = "+idSala+", ID-EVENTO = "+idEvento+")");
		
			operacoesAlocacao.alocarEvento(idEvento, idSala);
	}

	public void removerAlocacao(String idEventoAlocado) throws RoomsAllocationException {
		
		Alocacao alocacao = operacoesAlocacao.buscarAlocacao(idEventoAlocado);
		
		Acoes.cadastrar("Remover Alocacao", alocacao);
		
		Log.gravarNoLog("Acao: Remover Alocacao - Valores (ID-EVENTO ALOCADO = " + idEventoAlocado + ")");
		
			operacoesAlocacao.desalocarEvento(idEventoAlocado);
	}

}

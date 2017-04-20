package sas.swing.paineis.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.dao.AlocacaoDao;
import sas.operacoes.OperacoesAlocacao;
import sas.operacoes.OperacoesEvento;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.JPanelAvancado;
import sas.swing.paineis.JPanelEventos;
import sas.swing.paineis.JPanelSalas;

public class OuvintePainelAvancado implements ActionListener {

	private Principal janela;
	private JPanelAvancado painel;
	private OperacoesSala opSala = new OperacoesSala();
	private OperacoesEvento opEvento = new OperacoesEvento();
	private OperacoesAlocacao opAlocacao = new OperacoesAlocacao(opSala,
			opEvento);
	private AlocacaoDao dao = new AlocacaoDao();

	public OuvintePainelAvancado(Principal janela, JPanelAvancado painel) {
		this.janela = janela;
		this.painel = painel;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == painel.botaoApagarTodasSalas) {
			botaoApagarSalas();
		}

		else if (evento.getSource() == painel.botaoApagarTodosEventos) {
			botaoApagarEventos();
		}

		else if (evento.getSource() == painel.removerTodaAlocacoes) {
			botaoRemoverAlocacoes();
		}

		else if (evento.getSource() == painel.botaoZerarSistema) {
			botaoZerarSistema();
		}

		else if (evento.getSource() == painel.opcoesMaisAvancadas) {
			botaoOpcoesAvancadas();
		}

	}

	public void botaoApagarSalas() {

		if (opSala.listarSalas().toString().equals("[]")) {

			JOptionPane.showMessageDialog(null,
					"Nao existem Salas cadastradas!");

		}

		else {

			if (confirmacao("apagar todas as Salas e Alocacoes")) {

				try {
					dao.apagarSalas();
					dao.apagarAlocacoes();

					atualizarPaineis();

					JOptionPane.showMessageDialog(null,
							"Todas as Salas foram apagadas com sucesso!");
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao tentar apagar todas as Salas!");
				}
			}

		}

	}

	public void botaoApagarEventos() {

		if (opEvento.listarEventos().toString().equals("[]")) {

			JOptionPane.showMessageDialog(null,
					"Nao existem Eventos cadastrados!");

		}

		else {

			if (confirmacao("apagar todos os Eventos e Alocacoes")) {
				try {

					dao.apagarEventos();
					dao.apagarAlocacoes();

					atualizarPaineis();

					JOptionPane.showMessageDialog(null,
							"Todas os Eventos foram apagados com sucesso!");
				}

				catch (Exception e) {
					JOptionPane
							.showMessageDialog(null,
									"Ocorreu um erro ao tentar apagar todos os Eventos!");
				}
			}

		}

	}

	public void botaoRemoverAlocacoes() {

		if (opAlocacao.listarAlocacoes().toString().equals("[]")) {

			JOptionPane.showMessageDialog(null,
					"Nao existem Alocacoes cadastradas!");

		}

		else {

			if (confirmacao("apagar todas as Alocacoes")) {
				try {
					dao.apagarAlocacoes();

					atualizarPaineis();

					JOptionPane.showMessageDialog(null,
							"Todas as Alocacoes foram removidas com sucesso!");
				}

				catch (Exception e) {
					JOptionPane
							.showMessageDialog(null,
									"Ocorreu um erro ao tentar remover todas as Alocacoes!");
				}

			}

		}

	}

	public void botaoZerarSistema() {

		if (opSala.listarSalas().toString().equals("[]")) {

			JOptionPane
					.showMessageDialog(
							null,
							"Nao existe nada cadastrado!\nAdicione uma nova Sala, Evento ou Alocacao para poder zerar o sistema!");

		}

		else {

			if (confirmacao("zerar o sistema")) {
				try {

					dao.zerarSistema();

					atualizarPaineis();

					JOptionPane
							.showMessageDialog(null,
									"Todas os dados do sistema foram apagados com sucesso!");
				}

				catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro ao tentar zerar o sistema!");
				}
			}

		}

	}

	public void botaoOpcoesAvancadas() {

		if (painel.opcoesMaisAvancadas.isSelected()) {
			painel.botaoZerarSistema.setEnabled(true);
			painel.botaoZerarSistema.setVisible(true);
		}

		else if (!painel.opcoesMaisAvancadas.isSelected()) {
			painel.botaoZerarSistema.setEnabled(false);
			painel.botaoZerarSistema.setVisible(false);
		}

	}

	public void atualizarPaineis() {
		// atualizando paineis
		JPanelSalas painelSalas = new JPanelSalas();
		painelSalas.montarPainel(janela);
		janela.menus.setComponentAt(0, painelSalas);
		JPanelEventos painelEventos = new JPanelEventos();
		painelEventos.montarPainel(janela);
		janela.menus.setComponentAt(1, painelEventos);
		JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
		painelAlocacoes.montarPainel(janela);
		janela.menus.setComponentAt(2, painelAlocacoes);
	}

	public boolean confirmacao(String configuracao) {
		int escolha = JOptionPane.showConfirmDialog(null,
				"Esta acao nao podera ser desfeita, tem certeza que deseja continuar e "
						+ configuracao + "?");
		// sim = 0
		// nao = 1
		// cancelar = 2
		if (escolha == 0) {
			return true;
		}

		else {
			return false;
		}

	}

}

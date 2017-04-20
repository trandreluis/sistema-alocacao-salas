package sas.swing.paineis.ouvintes;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.swing.frames.AdicionarEvento;
import sas.swing.frames.BuscarAtributoEvento;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.JPanelEventos;
import sas.swing.paineis.PaineisCRUD;

public class OuvintePainelEventos extends OuvintePainel {

	public OuvintePainelEventos(Principal janela, PaineisCRUD painel) {
		super(janela, painel);
	}

	@Override
	public void botaoAdicionar() {
		@SuppressWarnings("unused")
		AdicionarEvento adicionar = new AdicionarEvento(janela);
	}

	@Override
	public void botaoBuscar() {
		@SuppressWarnings("unused")
		BuscarAtributoEvento buscar = new BuscarAtributoEvento(janela);
	}
	
	@Override
	public void botaoRemover() {
		int linha = painel.tabela.getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione um evento!");
		}

		else {
			String idEvento = (String) painel.tabela.getValueAt(linha, 0);
			if (operacoesAlocacao.verificarExistencia(idEvento)) {
				int escolha = JOptionPane
						.showConfirmDialog(
								null,
								"Este evento esta alocado em uma sala!\n"
										+ "Se continuar com a remocao, o Evento e a Alocacao serao removidos!\nDeseja Continuar?");
				// sim = 0
				// nao = 1
				// cancelar = 2

				if (escolha == 0) {
					try {
						janela.fachada.removerEvento(idEvento);
						JPanelEventos painelEventos = new JPanelEventos();
						painelEventos.montarPainel(janela);
						janela.menus.setComponentAt(1, painelEventos);
						JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
						painelAlocacoes.montarPainel(janela);
						janela.menus.setComponentAt(2, painelAlocacoes);
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null,
								"Ocorreu um erro durante a remocao do Evento!");
					}
				}

			}

			else {
				try {
					janela.fachada.removerEvento(idEvento);
					JPanelEventos painelEventos = new JPanelEventos();
					painelEventos.montarPainel(janela);
					janela.menus.setComponentAt(1, painelEventos);
					JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
					painelAlocacoes.montarPainel(janela);
					janela.menus.setComponentAt(2, painelAlocacoes);
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro durante a remocao do Evento!");
				}
			}

		}
	}


}

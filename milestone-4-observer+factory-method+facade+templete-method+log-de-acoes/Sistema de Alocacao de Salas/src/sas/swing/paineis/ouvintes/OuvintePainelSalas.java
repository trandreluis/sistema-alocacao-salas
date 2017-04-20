package sas.swing.paineis.ouvintes;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.swing.frames.AdicionarSala;
import sas.swing.frames.BuscarAtributoSala;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.JPanelSalas;
import sas.swing.paineis.PaineisCRUD;

public class OuvintePainelSalas extends OuvintePainel {

	public OuvintePainelSalas(Principal janela, PaineisCRUD painel) {
		super(janela, painel);
	}

	@Override
	public void botaoAdicionar() {
		@SuppressWarnings("unused")
		AdicionarSala adicionar = new AdicionarSala(janela);
	}

	@Override
	public void botaoBuscar() {
		@SuppressWarnings("unused")
		BuscarAtributoSala buscar = new BuscarAtributoSala(janela);
	}
	
	@Override
	public void botaoRemover() {

		int linha = painel.tabela.getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione uma sala!");
		}

		else {
			String idSala = (String) painel.tabela.getValueAt(linha, 0);
			if (operacoesAlocacao.verificarExistenciaSala(idSala)) {
				int escolha = JOptionPane
						.showConfirmDialog(
								null,
								"Esta sala contem um evento alocado a ela!\n"
										+ "Se continuar com a remocao, a Sala e a Alocacao serao removidas!\nDeseja Continuar?");
				// sim = 0
				// nao = 1
				// cancelar = 2

				if (escolha == 0) {
					try {
						janela.fachada.removerSala(idSala);
						JPanelSalas painelSalas = new JPanelSalas();
						painelSalas.montarPainel(janela);
						janela.menus.setComponentAt(0, painelSalas);
						JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
						painelAlocacoes.montarPainel(janela);
						janela.menus.setComponentAt(2, painelAlocacoes);
					} catch (RoomsAllocationException e) {
						JOptionPane.showMessageDialog(null,
								"Ocorreu um erro durante a remocao da Sala!");
					}
				}

			}

			else {
				try {
					janela.fachada.removerSala(idSala);
					JPanelSalas painelSalas = new JPanelSalas();
					painelSalas.montarPainel(janela);
					janela.menus.setComponentAt(0, painelSalas);
					JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
					painelAlocacoes.montarPainel(janela);
					janela.menus.setComponentAt(2, painelAlocacoes);
				} catch (RoomsAllocationException e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro durante a remocao da Sala!");
				}
			}

		}

	}

}

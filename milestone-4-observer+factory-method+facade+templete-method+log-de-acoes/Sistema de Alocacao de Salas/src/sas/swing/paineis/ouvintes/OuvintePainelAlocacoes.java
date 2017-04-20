package sas.swing.paineis.ouvintes;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.swing.frames.AdicionarAlocacao;
import sas.swing.frames.LocalizarEvento;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelAlocacoes;
import sas.swing.paineis.PaineisCRUD;

public class OuvintePainelAlocacoes extends OuvintePainel {

	public OuvintePainelAlocacoes(Principal janela, PaineisCRUD painel) {
		super(janela, painel);
	}

	@Override
	public void botaoAdicionar() {
		@SuppressWarnings("unused")
		AdicionarAlocacao adicionar = new AdicionarAlocacao(janela);
	}

	@Override
	public void botaoBuscar() {
		@SuppressWarnings("unused")
		LocalizarEvento buscar = new LocalizarEvento(janela);
	}
	
	@Override
	public void botaoRemover() {

		int linha = painel.tabela.getSelectedRow();

		if (linha == -1) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione uma Alocacao!");
		}

		else {
			String idEvento = (String) painel.tabela.getValueAt(linha, 1);
			try {
				janela.fachada.removerAlocacao(idEvento);
				JPanelAlocacoes painelAlocacoes = new JPanelAlocacoes();
				painelAlocacoes.montarPainel(janela);
				janela.menus.setComponentAt(2, painelAlocacoes);
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro durante a remocao da Alocacao!");
			}
		}

	}

}

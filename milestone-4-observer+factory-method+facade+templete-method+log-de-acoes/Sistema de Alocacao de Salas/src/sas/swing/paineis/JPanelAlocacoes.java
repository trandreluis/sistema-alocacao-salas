package sas.swing.paineis;

import javax.swing.JTable;

import sas.swing.modelosDeTabela.TableModelAlocacoes;
import sas.swing.paineis.ouvintes.OuvintePainelAlocacoes;

/**
 * Classe de interface grafica.
 * Classe do painel de Alocacoes
 * 
 * @author André Luís
 *
 */

@SuppressWarnings("serial")
public class JPanelAlocacoes extends PaineisCRUD {

	@Override
	public void inicializarTabela() {
		TableModelAlocacoes modeloEventos = new TableModelAlocacoes(operacoesAlocacao.listarAlocacoes());
		tabela = new JTable();
		tabela.setModel(modeloEventos);
	}

	@Override
	public void inicializarOuvinte() {
		ouvinte = new OuvintePainelAlocacoes(principal, this);
	}

}

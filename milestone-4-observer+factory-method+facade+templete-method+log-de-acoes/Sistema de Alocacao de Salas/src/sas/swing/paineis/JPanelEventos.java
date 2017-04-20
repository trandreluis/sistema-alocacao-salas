package sas.swing.paineis;

import javax.swing.JTable;

import sas.swing.modelosDeTabela.TableModelEventos;
import sas.swing.paineis.ouvintes.OuvintePainelEventos;

@SuppressWarnings("serial")
public class JPanelEventos extends PaineisCRUD {

	@Override
	public void inicializarTabela() {
		TableModelEventos modeloEventos = new TableModelEventos(operacoesEvento.listarEventos());
		tabela = new JTable();
		tabela.setModel(modeloEventos);
	}

	@Override
	public void inicializarOuvinte() {
		ouvinte = new OuvintePainelEventos(principal, this);
	}

}

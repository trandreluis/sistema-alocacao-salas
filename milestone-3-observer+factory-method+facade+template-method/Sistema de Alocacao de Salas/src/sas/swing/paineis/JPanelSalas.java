package sas.swing.paineis;

import javax.swing.JTable;

import sas.swing.modelosDeTabela.TableModelSalas;
import sas.swing.paineis.ouvintes.OuvintePainelSalas;

/**
 * Classe responsavel pelos paineis das salas
 * @author André Luís
 *
 */

@SuppressWarnings("serial")
public class JPanelSalas extends PaineisCRUD {

	@Override
	public void inicializarTabela() {
		TableModelSalas modeloSalas = new TableModelSalas(operacoesSala.listarSalas());
		tabela = new JTable();
		tabela.setModel(modeloSalas);
	}

	@Override
	public void inicializarOuvinte() {
		ouvinte = new OuvintePainelSalas(principal, this);
	}
	
}

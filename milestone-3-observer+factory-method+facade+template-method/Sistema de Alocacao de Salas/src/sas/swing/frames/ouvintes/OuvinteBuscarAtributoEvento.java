package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesEvento;
import sas.swing.frames.BuscarAtributoEvento;
import sas.swing.frames.Principal;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Buscar Atributo Evento e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteBuscarAtributoEvento implements ActionListener {

	BuscarAtributoEvento janelaBuscar;
	Principal janelaPrincipal;
	OperacoesEvento operacoes = new OperacoesEvento();

	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaBuscar janela que tera o ouvinte
	 */
	
	public OuvinteBuscarAtributoEvento(BuscarAtributoEvento janelaBuscar) {
		this.janelaBuscar = janelaBuscar;
		this.janelaPrincipal = janelaBuscar.janelaPrincipal;
	}

	/**
	 * Metodo que recebe o evento e o delega
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == janelaBuscar.botaoBuscar) {
			verificaCadastroEDecide();
		}

		else if (evento.getSource() == janelaBuscar.botaoCancelar) {
			janelaBuscar.dispose();
		}

	}

	/**
	 * metodo que valida o evento delegado
	 */
	
	public void verificaCadastroEDecide() {

		String idEvento = (String) janelaBuscar.comboID.getSelectedItem();
		String atributo = (String) janelaBuscar.comboAtributo.getSelectedItem();

		if (idEvento.equalsIgnoreCase("-- selecione o ID --")
				&& atributo.equalsIgnoreCase("-- selecione o Atributo --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione uma Sala e um Atributo!");
		}

		else if (atributo.equalsIgnoreCase("-- selecione o Atributo --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione um Atributo!");
		}
		
		else if (idEvento.equalsIgnoreCase("-- selecione o ID --")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, selecione uma Sala!");
		}

		else {
			realizarBusca(idEvento, atributo);
		}

	}

	/**
	 * Metodo que realiza a busca do valor do atributo
	 * @param idEvento ID do evento
	 * @param atributo atributo a ser buscado
	 */
	
	public void realizarBusca(String idEvento, String atributo) {

		String valorAtributo = "";

		try {
			valorAtributo = operacoes.getAtributo(idEvento, atributo);
			if (valorAtributo.equals("")) {
				janelaBuscar.labelAtributo.setText(atributo);
				janelaBuscar.labelValorAtributo
						.setText("-- Este atributo nao foi definido --");
			}

			else {
				janelaBuscar.labelAtributo.setText(atributo);
				janelaBuscar.labelValorAtributo.setText(valorAtributo);
			}

		} catch (RoomsAllocationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}

package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.AdicionarSala;
import sas.swing.frames.Principal;
import sas.swing.paineis.JPanelSalas;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Adicionar Sala e trata-los
 * 
 * @author Andre Luis
 *
 */

public class OuvinteAdicionarSala implements ActionListener {

	AdicionarSala janelaAdicionar;
	Principal janelaPrincipal;
	OperacoesSala operacoes = new OperacoesSala();

	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaAdicionar janela que tera o ouvinte
	 */
	
	public OuvinteAdicionarSala(AdicionarSala janelaAdicionar) {
		this.janelaAdicionar = janelaAdicionar;
		this.janelaPrincipal = janelaAdicionar.janelaPrincipal;
	}

	/**
	 * Metodo que recebe o evento e o delega
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {

		if (evento.getSource() == janelaAdicionar.botaoCadastrarSala) {
			verificaCadastroEDecide();
		}

		else if (evento.getSource() == janelaAdicionar.botaoCancelar) {
			janelaAdicionar.dispose();
		}

		else if (evento.getSource() == janelaAdicionar.comboFinalidade) {
			opcoesCorretasFinalidade();
		}

	}

	/**
	 * Metodo que validaraos BomboBox
	 */
	
	public void opcoesCorretasFinalidade() {

		String[] tipos = new String[] { "Normal", "Inteligente",
				"Videoconferencia", "Quimica", "Fisica", "Biologia",
				"Computacao" };

		String itemFinalidade = (String) janelaAdicionar.comboFinalidade
				.getSelectedItem();

		if (itemFinalidade.equalsIgnoreCase("Sala de aula")) {
			janelaAdicionar.comboTipo.removeAllItems();
			janelaAdicionar.comboTipo.addItem("-- selecione o tipo da sala --");
			janelaAdicionar.comboTipo.addItem(tipos[0].toString());
			janelaAdicionar.comboTipo.addItem(tipos[1].toString());
			janelaAdicionar.comboTipo.addItem(tipos[2].toString());
		}

		else if (itemFinalidade.equalsIgnoreCase("Sala de conferencia")) {
			janelaAdicionar.comboTipo.removeAllItems();
			janelaAdicionar.comboTipo.addItem("-- selecione o tipo da sala --");
			janelaAdicionar.comboTipo.addItem(tipos[0].toString());
			janelaAdicionar.comboTipo.addItem(tipos[2].toString());
		}

		else if (itemFinalidade.equalsIgnoreCase("Laboratorio")) {
			janelaAdicionar.comboTipo.removeAllItems();
			janelaAdicionar.comboTipo.addItem("-- selecione o tipo da sala --");
			janelaAdicionar.comboTipo.addItem(tipos[3].toString());
			janelaAdicionar.comboTipo.addItem(tipos[4].toString());
			janelaAdicionar.comboTipo.addItem(tipos[5].toString());
			janelaAdicionar.comboTipo.addItem(tipos[6].toString());
		}

		else if (itemFinalidade.equalsIgnoreCase("Escritorio")) {
			janelaAdicionar.comboTipo.removeAllItems();
			janelaAdicionar.comboTipo.addItem("-- selecione o tipo da sala --");
			janelaAdicionar.comboTipo.addItem(tipos[6].toString());
		}
	}

	/**
	 * Metodo que rece a acao delegada e a repassa ou nao
	 */
	
	public void verificaCadastroEDecide() {

		janelaAdicionar.botaoCadastrarSala.setEnabled(false);
		
		String id = janelaAdicionar.fieldID.getText();
		String capacidade = janelaAdicionar.fieldCapacidade.getText();
		String finalidade = (String) janelaAdicionar.comboFinalidade
				.getSelectedItem();
		String tipo = (String) janelaAdicionar.comboTipo.getSelectedItem();

		String apelido = janelaAdicionar.fieldApelido.getText();
		boolean aberta = janelaAdicionar.checkAberta.isSelected();

		int capacidadeInt = 0;

		if (id == null || id.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe o ID!");
			janelaAdicionar.botaoCadastrarSala.setEnabled(true);
		}

		else if (capacidade == null || capacidade.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe a Capacidade!");
			janelaAdicionar.botaoCadastrarSala.setEnabled(true);
		}

		else if (finalidade == null || finalidade.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Informe a Finalidade!");
			janelaAdicionar.botaoCadastrarSala.setEnabled(true);
		}

		else if (tipo == null || tipo.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "informe o Tipo!");
			janelaAdicionar.botaoCadastrarSala.setEnabled(true);
		}

		else {
			try {
				capacidadeInt = Integer.parseInt(capacidade);
				
				if(finalidade.equalsIgnoreCase("-- selecione a finalidade da sala --") && tipo.equalsIgnoreCase("-- selecione o tipo da sala --")) {
					JOptionPane.showMessageDialog(null, "Informe uma Finalidade e um Tipo!");
					janelaAdicionar.botaoCadastrarSala.setEnabled(true);
				}
				
				else if(finalidade.equalsIgnoreCase("-- selecione a finalidade da sala --")) {
					JOptionPane.showMessageDialog(null, "Informe uma Finalidade!");
					janelaAdicionar.botaoCadastrarSala.setEnabled(true);
				}
				
				else if(tipo.equalsIgnoreCase("-- selecione o tipo da sala --")) {
					JOptionPane.showMessageDialog(null, "Informe um Tipo!");
					janelaAdicionar.botaoCadastrarSala.setEnabled(true);
				}
				
				else {					
					cadastrarSala(id, capacidadeInt, finalidade, tipo, apelido,
							aberta);
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Informe numeros (123) no campo capacidade!");
				janelaAdicionar.botaoCadastrarSala.setEnabled(true);
			}
		}

	}

	/**
	 * Metodo que realmente cadastra a sala
	 * @param id ID da sala
	 * @param capacidade Capacidade da sala
	 * @param finalidade finalidade da sala
	 * @param tipo tipo da sala
	 * @param apelido apelido da sala
	 * @param aberta aberta ou nao
	 */
	
	public void cadastrarSala(String id, int capacidade, String finalidade,
			String tipo, String apelido, boolean aberta) {
		try {
			operacoes.adicionarSalaCompleto(id, capacidade, finalidade, tipo,
					apelido, aberta);
			JPanelSalas painelSalas = new JPanelSalas();
			painelSalas.montarPainel(janelaPrincipal);
			janelaPrincipal.menus.setComponentAt(0, painelSalas);
			janelaAdicionar.botaoCadastrarSala.setEnabled(true);
			janelaAdicionar.dispose();
		} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				janelaAdicionar.botaoCadastrarSala.setEnabled(true);
		}
	}

}

package sas.swing.frames.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sas.excecoes.alocacao.RoomsAllocationException;
import sas.operacoes.OperacoesSala;
import sas.swing.frames.BuscarAtributoSala;
import sas.swing.frames.Principal;

/**
 * Classe responsavel por ouvir os eventos na interface grafica de Buscar Atributo Sala e trata-los
 * 
 * @author Andre Luis
 *
 */
public class OuvinteBuscarAtributoSala implements ActionListener {

	BuscarAtributoSala janelaBuscar;
	Principal janelaPrincipal;
	OperacoesSala operacoes = new OperacoesSala();
	
	/**
	 * Metodo contrutor do ouvinte
	 * @param janelaBuscar janela que tera o ouvinte
	 */
	
	public OuvinteBuscarAtributoSala(BuscarAtributoSala janelaBuscar) {
		this.janelaBuscar = janelaBuscar;
		this.janelaPrincipal = janelaBuscar.janelaPrincipal;
	}
	
	/**
	 * metodo que recebe o evento o delega
	 */
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getSource() == janelaBuscar.botaoBuscar) {
			verificaCadastroEDecide();
		}
		
		else if(evento.getSource() == janelaBuscar.botaoCancelar) {
			janelaBuscar.dispose();
		}
		
	}

	/**
	 * Metodo que validara a acao
	 */
	
	public void verificaCadastroEDecide() {

		String idSala = (String) janelaBuscar.comboID.getSelectedItem();
		String atributo = (String) janelaBuscar.comboAtributo.getSelectedItem();
		
		if(idSala.equalsIgnoreCase("-- selecione o ID --") && atributo.equalsIgnoreCase("-- selecione o Atributo --")) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione uma Sala e um Atributo!");
		}
		
		else if(atributo.equalsIgnoreCase("-- selecione o Atributo --")) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione um Atributo!");
		}
		
		else if(idSala.equalsIgnoreCase("-- selecione o ID --")) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione uma Sala!");
		}
		
		else {
			realizarBusca(idSala, atributo);
		}

	}
	
	/**
	 * metodo que realizara a busca pelo valor do atributo
	 * @param idSala ID da slaa
	 * @param atributo atributo a ser buscado
	 */
	
	public void realizarBusca(String idSala, String atributo) {
		
		String valorAtributo = "";
		
		if(atributo.equals("Aberta?")) {
			try {
				valorAtributo = operacoes.getAtributo(idSala, "Aberta");
				if(valorAtributo.equals("false")) {
					janelaBuscar.labelAtributo.setText(atributo);
					janelaBuscar.labelValorAtributo.setText("Nao");
				}
				else if(valorAtributo.equals("true")) {
					janelaBuscar.labelAtributo.setText(atributo);
					janelaBuscar.labelValorAtributo.setText("Sim");
				}
			} catch (RoomsAllocationException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		else {
			try {
				valorAtributo = operacoes.getAtributo(idSala, atributo);
				if(valorAtributo.equals("")) {
					janelaBuscar.labelAtributo.setText(atributo);
					janelaBuscar.labelValorAtributo.setText("-- Este atributo nao foi definido --");
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
	
}
